package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.PayMethodAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.OrderDetailsModel;
import com.example.meiriuser.model.PayMethodModel;
import com.example.meiriuser.model.net.CreateTaskModel;
import com.example.meiriuser.model.net.PickUpDeliveryModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PayCountDownUtils;
import com.example.meiriuser.until.PayPalHelper;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by admin on 2019/5/29.
 */

public class TaskPayActivity extends BaseActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tv_payment_time)
    TextView tvPaymentTime;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.rv_pay_method)
    RecyclerView rvPayMethod;
    @BindView(R.id.btn_confirm_payment)
    Button btnConfirmPayment;
    private PayCountDownUtils countDownUtils;
    PayMethodAdapter payMethodAdapter;
    List<PayMethodModel> payMethodModelList;
    CreateTaskModel createTaskModel;
    String price;
    String type;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_task_pay;
    }



    /**
     * 开启倒数
     */
    public void startCountDown() {
        countDownUtils = new PayCountDownUtils(60000*15, 1000, tvPaymentTime);
        countDownUtils.setStartColor(Color.parseColor("#353535"));
        countDownUtils.setEndColor(Color.parseColor("#353535"));
        countDownUtils.setStartTxt("支付剩余时间  ");
        countDownUtils.setEndTxt("支付超时");
        countDownUtils.start();
    }

    @Override
    public void initView() {
        startCountDown();
        toolbarTitle.setText("付款");
        payMethodModelList=new ArrayList<>();
        rvPayMethod.setNestedScrollingEnabled(false);
        rvPayMethod.setLayoutManager(new LinearLayoutManager(this));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_paypal, "paypal", ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_wechat, getString(R.string.text_ay_wechat), "工行信用卡支付, 享随机立减"));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_alipay, getString(R.string.text_pay_alipay), "支付订单, 赢1999元红包"));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_union, getString(R.string.text_pay_union), ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_card, "MasterCard", ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_pay_visa, "VISA", ""));
        payMethodModelList.add(new PayMethodModel(R.mipmap.icon_stripe, "stripe", ""));
        Intent intent = getIntent();

        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                price=bundle.getString("price");
                type=bundle.getString(Constant.TYPE);
                tvPrice.setText(price);
                if(!type.equals("deposit")){
                    createTaskModel = (CreateTaskModel) bundle.getSerializable(Constant.DATEBEAN);
                    if(createTaskModel.getInfo()==null){
                        CreateTaskModel.CreateTaskInfo taskInfo=new CreateTaskModel.CreateTaskInfo();
                        createTaskModel.setInfo(taskInfo);
                    }
                }


            }
        }

    }

    @Override
    public void initData() {
        payMethodAdapter=new PayMethodAdapter(payMethodModelList);
        payMethodAdapter.setSelectPosition(0);
        rvPayMethod.setAdapter(payMethodAdapter);
        PayPalHelper.getInstance().startPayPalService(this);
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
                PayPalHelper.getInstance().doPayPalPay(this, price, "支付");
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        PayPalHelper.getInstance().confirmPayResult(this, requestCode, resultCode, data, new PayPalHelper.DoResult() {
            @Override
            public void confirmSuccess(String id) {
                if(type.equals("deposit")){
                    depositNo(id);
                }else {
                    createTaskModel.setPay_no(id);
                    orderQuery();
                }

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


    public void depositNo(String pay_no) {

        showDialog();
        String url = ApiUrl.depositNoUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("pay_no",pay_no)
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
                                ToastUtils.showShort(R.string.toast_success_pay);
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

    public void orderQuery() {
        if(createTaskModel==null){
            return;
        }
        showDialog();
        String url = ApiUrl.taskCreateUrl;
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(createTaskModel))
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
                        String string = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                String task_id=jsonObject.getJSONObject("data").getString("task_id");
                                if(!TextUtils.isEmpty(task_id)){
                                     getTaskStatus(task_id);
                                }
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


    public void getTaskStatus(String task_id) {
        String url = ApiUrl.gettaskStatusUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("task_id", task_id)
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
                                ToastUtils.showShort(R.string.toast_success_pay);
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


}
