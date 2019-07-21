package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.OrderDetailsPayAdapter;
import com.example.meiriuser.adapter.PayMethodAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.OrderDetailsModel;
import com.example.meiriuser.model.PayMethodModel;
import com.example.meiriuser.model.net.PayModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PayCountDownUtils;
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
 * 在线支付
 */

public class OnlinePaymentActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_payment_time)
    TextView tvPaymentTime;
    @BindView(R.id.tv_takeaway_order)
    TextView tvTakeawayOrder;
    @BindView(R.id.rv_order_details)
    RecyclerView rvOrderDetails;
    @BindView(R.id.tv_distribu_fee)
    TextView tvDistribuFee;
    @BindView(R.id.tv_full_reduction_price)
    TextView tvFullReductionPrice;
    @BindView(R.id.tv_total_commodity)
    TextView tvTotalCommodity;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.rv_pay_method)
    RecyclerView rvPayMethod;
    @BindView(R.id.btn_confirm_payment)
    Button btnConfirmPayment;
    OrderDetailsPayAdapter orderDetailsPayAdapter;
    PayMethodAdapter payMethodAdapter;
    List<OrderDetailsModel.DataBean.GoodsBean> goodsBeanList;
    List<PayMethodModel> payMethodModelList;
    @BindView(R.id.line_distribu_fee)
    RelativeLayout lineDistribuFee;
    @BindView(R.id.line_full_reduction_price)
    RelativeLayout lineFullReductionPrice;
    private PayCountDownUtils countDownUtils;
    String order_id;
    String storeName;
    String totalPrice;
    private int netType;//0为代购，其他的为外卖团购

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_online_payment;
    }


    /**
     * 开启倒数
     */
    public void startCountDown() {
        countDownUtils = new PayCountDownUtils(60000 * 15, 1000, tvPaymentTime);
        countDownUtils.setStartColor(Color.parseColor("#FFC016"));
        countDownUtils.setEndColor(Color.parseColor("#FFC016"));
        countDownUtils.setStartTxt("剩余支付时间");
        countDownUtils.setEndTxt("支付超时");
        countDownUtils.start();
    }


    @Override
    public void initView() {
        startCountDown();
        toolbarTitle.setText(getString(R.string.title_online_payment));
        rvOrderDetails.setNestedScrollingEnabled(false);
        rvPayMethod.setNestedScrollingEnabled(false);
        rvOrderDetails.setLayoutManager(new LinearLayoutManager(this));
        rvPayMethod.setLayoutManager(new LinearLayoutManager(this));
        order_id = getIntent().getExtras().getString("order_id");
        netType = getIntent().getExtras().getInt("netType");
        goodsBeanList = new ArrayList<>();
        payMethodModelList = new ArrayList<>();
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_paypal, "paypal", ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_wechat, getString(R.string.text_ay_wechat), "工行信用卡支付, 享随机立减"));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_alipay, getString(R.string.text_pay_alipay), "支付订单, 赢1999元红包"));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_union, getString(R.string.text_pay_union), ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_card, "MasterCard", ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_visa, "VISA", ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_stripe, "stripe", ""));

    }

    @Override
    public void initData() {
        PayPalHelper.getInstance().startPayPalService(this);
        orderDetailsPayAdapter = new OrderDetailsPayAdapter(goodsBeanList);
        rvOrderDetails.setAdapter(orderDetailsPayAdapter);
        payMethodAdapter = new PayMethodAdapter(payMethodModelList);
        payMethodAdapter.setSelectPosition(0);
        rvPayMethod.setAdapter(payMethodAdapter);
        getOrderDetails(order_id);
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
                                OrderDetailsModel bean = (OrderDetailsModel) GsonUtil.JSONToObject(response.toString(), OrderDetailsModel.class);
                                if (bean.getResult() == HttpStatus.SUCCESS) {
                                    OrderDetailsModel.DataBean dataBean = bean.getData();
                                    if (dataBean != null) {
                                        if (netType == 0) {
                                            tvTakeawayOrder.setText(String.format(getString(R.string.text_takeaway_buy_num), dataBean.getOrder_sn()));
                                            lineDistribuFee.setVisibility(View.GONE);
                                            lineFullReductionPrice.setVisibility(View.GONE);
                                            tvTotalPrice.setText(String.format(getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(dataBean.getOrder_amount()))) + "");
                                            totalPrice = CommanUtils.doubleTrans(Double.valueOf(dataBean.getOrder_amount())) + "";
                                            storeName=dataBean.getShipping_name();
                                        } else {
                                            tvTakeawayOrder.setText(String.format(getString(R.string.text_takeaway_order_num), dataBean.getOrder_sn()));
                                            tvDistribuFee.setText(String.format(getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(dataBean.getShipping_fee())) + ""));
                                            tvFullReductionPrice.setText(String.format(getString(R.string.text_reduct_price), String.valueOf(dataBean.getDiscount())));
                                            tvTotalPrice.setText(String.format(getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(dataBean.getOrder_amount()))) + "");
                                            totalPrice = CommanUtils.doubleTrans(Double.valueOf(dataBean.getOrder_amount())) + "";
                                            if (dataBean.getGoods() != null) {
                                                goodsBeanList.addAll(dataBean.getGoods());
                                                orderDetailsPayAdapter.notifyDataSetChanged();
                                                int goodNum = 0;
                                                for (int i = 0; i < dataBean.getGoods().size(); i++) {
                                                    goodNum += dataBean.getGoods().get(i).getGoods_number();
                                                }
                                                tvTotalCommodity.setText(String.format(getResources().getString(R.string.text_total_comodity), goodNum + ""));//商品件数
                                            }

                                            if (dataBean.getStore() != null) {
                                                storeName = dataBean.getStore().getStore_name();
                                            }
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


    @OnClick({R.id.icon_back, R.id.btn_confirm_payment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_confirm_payment:
                if(TextUtils.isEmpty(totalPrice)){
                    totalPrice="0";
                }if(TextUtils.isEmpty(storeName)){
                   storeName="";
                }
                PayPalHelper.getInstance().doPayPalPay(this, totalPrice, storeName);
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

    private void doNetPay(final int order_id, String pay_no) {
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
                        Intent intent = new Intent(OnlinePaymentActivity.this, OrderFinishDetailsActivity.class);
                        intent.putExtra(Constant.ORDERSTATE, "未完成");
                        intent.putExtra("order_id", order_id+"");
                        jumpToActivity(intent);
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


}
