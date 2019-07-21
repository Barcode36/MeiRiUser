package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.PayDepositModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/27.
 */

public class PayDepositActivity extends BaseActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_explain)
    TextView tvExplain;
    @BindView(R.id.cb_explain)
    CheckBox cbExplain;
    @BindView(R.id.tv_to_pay)
    TextView tvToPay;
    boolean isPay=false;
    int status;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_pay_deposit;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("保证金");
    }


    @Override
    public void initData() {
        tvPrice.setText(String.format(getResources().getString(R.string.text_price),"60"));
        depositStatus();
    }


    @Override
    public void initListener() {
        super.initListener();
        BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {
                        tvToPay.setBackgroundResource(R.drawable.shape_fillet_yellow_18dp);
                        tvToPay.setText("退款");
                        cbExplain.setVisibility(View.GONE);
                        isPay=true;
                    }
                });

    }

    public void depositStatus() {
        showDialog();
        String url = ApiUrl.depositStatusUrl;
        OkHttpUtils
                .get()
                .url(url)
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
                                PayDepositModel bean = (PayDepositModel) GsonUtil.JSONToObject(response.toString(), PayDepositModel.class);
                                PreferenceUtil.commitInt(Constant.PF_PREMIUM, bean.getData().getStatus());

                                status=bean.getData().getStatus();
                              /*  0 未缴纳 1 已缴纳 2 申请退款 3 退款中 4 退款了 5 付款中 6 付款成功 7 付款失败*/
                                if(status==0 ||status==4){//未缴纳
                                    tvToPay.setBackgroundResource(R.drawable.shape_fillet_green_18dp);
                                    tvToPay.setText(R.string.text_confirmation_payment);
                                    cbExplain.setVisibility(View.VISIBLE);
                                    cbExplain.setChecked(false);
                                    isPay=false;
                                }else if(status==1 ||status==6){//已缴纳
                                    tvToPay.setBackgroundResource(R.drawable.shape_fillet_yellow_18dp);
                                    tvToPay.setText(R.string.text_refund);
                                    cbExplain.setVisibility(View.GONE);
                                    isPay=true;
                                }else if(status==2|| status==3){//退款中
                                    tvToPay.setBackgroundResource(R.drawable.shape_fillet_yellow_18dp);
                                    tvToPay.setText(R.string.text_in_refund);
                                    cbExplain.setVisibility(View.GONE);
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


    public void depositRefund() {
        showDialog();
        String url = ApiUrl.depositRefundUrl;
        OkHttpUtils
                .post()
                .url(url)
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
                                ToastUtils.showShort(R.string.text_successful_refund);
                                tvToPay.setBackgroundResource(R.drawable.shape_fillet_yellow_18dp);
                                tvToPay.setText(R.string.text_in_refund);
                                cbExplain.setVisibility(View.GONE);
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

    @OnClick({R.id.icon_back, R.id.tv_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_to_pay:
                if(status==0 ||status==4) {//未缴纳
                    Intent intent=new Intent(PayDepositActivity.this,TaskPayActivity.class);
                    intent.putExtra("price",60+"");
                    intent.putExtra(Constant.TYPE,"deposit");
                    jumpToActivity(intent);
                }else if(status==1 ||status==6){
                    depositRefund();
                }
                break;
        }
    }
}
