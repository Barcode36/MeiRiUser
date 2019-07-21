package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.LogoutEvent;
import com.example.meiriuser.ui.activity.BecomeRiderActivity;
import com.example.meiriuser.ui.activity.LoginActivity;
import com.example.meiriuser.ui.activity.MerchantEntryActivity;
import com.example.meiriuser.ui.activity.MyBalanceActivity;
import com.example.meiriuser.ui.activity.NotifiCenterActivity;
import com.example.meiriuser.ui.activity.PersonInfoActivity;
import com.example.meiriuser.ui.activity.SystemSettingActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.yanzhenjie.sofia.Sofia;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/9.
 */

public class MysFragment extends BaseFragment {

    @BindView(R.id.iv_customer)
    ImageView ivCustomer;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_my_balance)
    TextView tvMyBalance;
    @BindView(R.id.tv_notifi_center)
    TextView tvNotifiCenter;
    @BindView(R.id.tv_become_rider)
    TextView tvBecomeRider;
    @BindView(R.id.tv_merchant_entry)
    TextView tvMerchantEntry;
    @BindView(R.id.tv_system_setting)
    TextView tvSystemSetting;
    String account;
    String token;

    public static MysFragment newInstance() {
        MysFragment fragment = new MysFragment();
        return fragment;
    }

    public void setInfo(){
        display();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mys;
    }


    @Override
    public void initView(View rootView) {
        Sofia.with(getActivity())
                .statusBarDarkFont()
                .invasionStatusBar()//内容入侵状态栏
                .statusBarBackground(Color.TRANSPARENT);//状态栏背景色
    }

    @Override
    public void initData() {
        display();
    }


    @Override
    public void initListener() {
        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        display();
                    }
                });
    }

    public void display(){
        token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        account = PreferenceUtil.getString(Constant.PF_MOBILE_NUM);
        String image = PreferenceUtil.getString(Constant.PF_HEAD_IMG);
        Glide.with(this)
                .load(image)
                .placeholder(R.mipmap.icon_logo_oval)
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(getActivity()))
                .into(ivHead);
        if(TextUtils.isEmpty(token)){
            tvName.setText(R.string.text_log_immediately);
            tvPhone.setText(R.string.text_log_immediately_tips);
            tvPhone.setCompoundDrawables(null, null, null, null);
        }else {
            String name = PreferenceUtil.getString(Constant.PF_USER_NAME);
            if(account.length()>0){
                tvPhone.setText(account.substring(0,3)+"***"+account.substring(account.length()-4,account.length()));
            }
            Drawable dra= getResources().getDrawable(R.mipmap.icon_phone_white);
            dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
            tvPhone.setCompoundDrawables(dra, null, null, null);
            tvName.setText(name);

        }
    }


    @OnClick({R.id.line_person_info,R.id.iv_customer, R.id.tv_my_balance, R.id.tv_notifi_center, R.id.tv_become_rider, R.id.tv_merchant_entry, R.id.tv_system_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_person_info:
                if(TextUtils.isEmpty(token)){
                    jumpToActivity(LoginActivity.class);
                }else {
                    jumpToActivity(PersonInfoActivity.class);
                }
                break;
            case R.id.iv_customer:
                break;
            case R.id.tv_my_balance:
                String token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
                if(TextUtils.isEmpty(token)){
                    jumpToActivity(LoginActivity.class);
                }else {
                    jumpToActivity(MyBalanceActivity.class);
                }
                break;
            case R.id.tv_notifi_center:
                jumpToActivity(NotifiCenterActivity.class);
                break;
            case R.id.tv_become_rider:
                Intent intent=new Intent(getContext(),MerchantEntryActivity.class);
                intent.putExtra(Constant.TYPE,"become_rider");
                jumpToActivity(intent);
                break;
            case R.id.tv_merchant_entry:
                Intent intent1=new Intent(getContext(),MerchantEntryActivity.class);
                intent1.putExtra(Constant.TYPE,"merchant_entry");
                jumpToActivity(intent1);
              /*  jumpToActivity(MerchantEntryActivity.class);*/
                break;
            case R.id.tv_system_setting:
                jumpToActivity(SystemSettingActivity.class);
                break;
        }
    }
}
