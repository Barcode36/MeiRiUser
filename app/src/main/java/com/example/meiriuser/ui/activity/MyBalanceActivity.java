package com.example.meiriuser.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.adapter.MyBalanceAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.event.UserInfoEvent;
import com.example.meiriuser.model.BillingInfoModel;
import com.example.meiriuser.model.MyBalanceModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.yanzhenjie.sofia.Sofia;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/30.
 */

public class MyBalanceActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_click_bank)
    TextView tvClickBank;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.line_payment_details)
    LinearLayout linePaymentDetails;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    MyBalanceAdapter balanceAdapter;
    List<MyBalanceModel> balanceModelList;
    String userMoney;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_balance;
    }

    @Override
    public void initView() {
        userMoney= PreferenceUtil.getString(Constant.PF_USER_MONEY);
        Sofia.with(this)
                .statusBarDarkFont()
                .invasionStatusBar()//内容入侵状态栏
                .statusBarBackground(Color.TRANSPARENT);//状态栏背景色
        toolbarTitle.setText("我的余额");
        tvPrice.setText(CommanUtils.doubleTrans(Double.valueOf(userMoney)));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        balanceModelList=new ArrayList<>();
        balanceModelList.add(new MyBalanceModel("买包烟收入","+10.00","2019-03-07 14：20"));
        balanceModelList.add(new MyBalanceModel("阿汤厨房（车陂店）支出","-20.00","2019-03-07 14：20"));
        balanceModelList.add(new MyBalanceModel("买袋米支出","-42.00","2019-03-07 14：20"));
    }


    public void getAccountInfo() {
        showDialog();
        String url = ApiUrl.accountUrl;
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
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                             /*   BillingInfoModel bean = (BillingInfoModel) GsonUtil.JSONToObject(response.toString(), BillingInfoModel.class);
                                if (bean.getData() != null) {
                                    BillingInfoModel.DataBean dataBean=bean.getData();
                                    etCountry.setText(dataBean.getCountry()+"");
                                    etContinent.setText(dataBean.getProvince()+"");
                                    etRegion.setText(dataBean.getCity()+"");
                                    etCode.setText(dataBean.getZipcode()+"");
                                    etAddress01.setText(dataBean.getAddress_1()+"");
                                    etAddress02.setText(dataBean.getAddress_2()+"");
                                }*/
                            } else {

                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

    }

    @Override
    public void initData() {
        balanceAdapter=new MyBalanceAdapter(balanceModelList);
        rvList.setAdapter(balanceAdapter);
        getAccountInfo();
    }


    @OnClick({R.id.icon_back, R.id.tv_click_bank, R.id.btn_withdrawal, R.id.line_payment_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_click_bank:
                jumpToActivity(SelectBindBankCardActivity.class);
                break;
            case R.id.btn_withdrawal:
                jumpToActivity(WithdrawalActivity.class);
                break;
            case R.id.line_payment_details:
                jumpToActivity(BillingAddressActivity.class);
                break;
        }
    }


    @Override
    public void initListener() {
        super.initListener();
      /*  BusProvider.getBus().toObservable(UserInfoEvent.class)
                .subscribe(new Action1<UserInfoEvent>() {
                    @Override
                    public void call(UserInfoEvent newMessageEvent) {
                        getAccountInfo();
                    }
                });*/

    }
}
