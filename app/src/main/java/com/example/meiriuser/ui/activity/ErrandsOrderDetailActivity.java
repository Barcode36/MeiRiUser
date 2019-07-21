package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.PayMethodAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.ErrandsOrderDetailModel;
import com.example.meiriuser.model.PayMethodModel;
import com.example.meiriuser.model.net.PayModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PayPalHelper;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2019/6/27.
 */

public class ErrandsOrderDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_take_address)
    TextView tvTakeAddress;
    @BindView(R.id.tv_collect_address)
    TextView tvCollectAddress;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_substitute_shopping)
    TextView tvSubstituteShopping;
    @BindView(R.id.tv_commodity_charges)
    TextView tvCommodityCharges;
    @BindView(R.id.tv_distribution_fee)
    TextView tvDistributionFee;
    @BindView(R.id.tv_gratuity)
    TextView tvGratuity;
    @BindView(R.id.rv_pay_method)
    RecyclerView rvPayMethod;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    List<PayMethodModel> payMethodModelList;
    PayMethodAdapter payMethodAdapter;
    String order_id;
    String storeName;
    String totalPrice;
    int pay_status;
    @BindView(R.id.tv_pay_method)
    TextView tvPayMethod;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_errands_order_detail;
    }

    @Override
    public void initView() {
        toolbarTitle.setText(R.string.title_errands_order_detail);
        rvPayMethod.setNestedScrollingEnabled(false);
        rvPayMethod.setLayoutManager(new LinearLayoutManager(this));
        payMethodModelList = new ArrayList<>();
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_wechat, "paypal", ""));
        order_id = getIntent().getExtras().getString("order_id");
    }


    @Override
    public void initData() {
        PayPalHelper.getInstance().startPayPalService(this);
        payMethodAdapter = new PayMethodAdapter(payMethodModelList);
        payMethodAdapter.setSelectPosition(0);
        rvPayMethod.setAdapter(payMethodAdapter);
        getOrderDetails(order_id);


    }


    public void orderCancel(final String order_id) {

        showDialog();
        String url = ApiUrl.orderCancelUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("order_id", order_id)
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showShort(e.getMessage());
                        dissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dissDialog();
                        String string = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                showShort(getString(R.string.text_order_cancel));
                            } else {
                                String info = jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 订单详情
     */
    public void getOrderDetails(final String order_id) {

        showDialog();
        String url = ApiUrl.orderDetailsUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("order_id", order_id)
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showShort(e.getMessage());
                        dissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dissDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                ErrandsOrderDetailModel bean = (ErrandsOrderDetailModel) GsonUtil.JSONToObject(response.toString(), ErrandsOrderDetailModel.class);
                                if (bean.getResult() == HttpStatus.SUCCESS) {
                                    ErrandsOrderDetailModel.DataBean dataBean = bean.getData();
                                    if (dataBean != null) {
                                        tvCollectAddress.setText(dataBean.getAddress());
                                        tvContact.setText(dataBean.getConsignee() + "  " + dataBean.getTel());
                                        ErrandsOrderDetailModel.DataBean.OtherInfoBean otherBean = dataBean.getOther_info();
                                        if (otherBean != null) {
                                            tvTakeAddress.setText(otherBean.getBuy_address_name());
                                            tvCommodityCharges.setText(String.format(getString(R.string.text_price), CommanUtils.doubleTrans(Float.valueOf(dataBean.getGoods_amount()))));
                                            totalPrice = CommanUtils.doubleTrans(Float.valueOf(dataBean.getOrder_amount()));
                                            tvOrderPrice.setText(totalPrice);
                                            storeName = dataBean.getShipping_name();
                                            tvDistributionFee.setText(String.format(getString(R.string.text_price), 8 + ""));
                                            if (otherBean.getBuy_list().size() > 0) {
                                                String substituteShopping = otherBean.getBuy_list().toString();
                                                tvSubstituteShopping.setText(substituteShopping.substring(1, substituteShopping.length() - 1));
                                                tvGratuity.setText(String.format(getString(R.string.text_price), CommanUtils.doubleTrans(Float.valueOf(otherBean.getGratuity()))));
                                            }
                                        }
                                        pay_status = dataBean.getPay_status();
                                        if(pay_status == 0){
                                            btnPay.setText(getString(R.string.text_sure_pay));
                                            tvPayMethod.setVisibility(View.VISIBLE);
                                            rvPayMethod.setVisibility(View.VISIBLE);
                                        }else {
                                            btnPay.setText(getString(R.string.text_paymented));
                                            tvPayMethod.setVisibility(View.GONE);
                                            rvPayMethod.setVisibility(View.GONE);
                                        }

                                    }
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }


    @Override
    public void initListener() {
        payMethodAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        payMethodAdapter.setSelectPosition(position);
                        payMethodAdapter.notifyDataSetChanged();
                        break;

                }
            }
        });
    }


    @OnClick({R.id.icon_back, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_pay:
                if (pay_status == 0) {
                    PayPalHelper.getInstance().doPayPalPay(this, totalPrice, storeName);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        PayPalHelper.getInstance().confirmPayResult(this, requestCode, resultCode, data, new PayPalHelper.DoResult() {
            @Override
            public void confirmSuccess(String id) {
                doNetPay(Integer.valueOf(order_id), id);
            }

            @Override
            public void confirmNetWorkError() {
                /*showShortToast("支付失败");*/
            }

            @Override
            public void customerCanceled() {

            }

            @Override
            public void confirmFuturePayment() {

            }

            @Override
            public void invalidPaymentConfiguration() {

            }
        });
    }

    private void doNetPay(int order_id, String pay_no) {
        ToastUtils.showShort(R.string.text_system_process);
        showDialog();
        String url = ApiUrl.payNo;
        PayModel payModel = new PayModel(order_id, pay_no);
        Gson gson = new Gson();
        final String s = gson.toJson(payModel);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
        OkHttpClient okHttpClient1 = OkHttpUtils.getInstance().getOkHttpClient();
        Request request = new Request.Builder().url(url)
                .post(requestBody).addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY)).build();
        okHttpClient1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dissDialog();
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dissDialog();
                String string = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    int result = jsonObject.getInt("result");
                    if (result == 1) {
                        ToastUtils.showShort(R.string.toast_success_pay);
                        jumpToActivity(ErrandsOrderListActivity.class);
                        BusProvider.getBus().post(new OrderFinishEvent());
                        finish();
                    } else {
                        String info = jsonObject.getString("info");
                        showShort(info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        PayPalHelper.getInstance().stopPayPalService(this);
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
