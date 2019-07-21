package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.OrderListRefresh;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by admin on 2019/5/30.
 */

public class SystemSettingActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    String token;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_system_setting;
    }


    @Override
    public void initView() {
        token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        if (TextUtils.isEmpty(token)) {
            btnLogout.setVisibility(View.GONE);
        }
        toolbarTitle.setText("系统设置");
    }


    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.icon_back, R.id.tv_change_password, R.id.tv_feedback, R.id.tv_about_us, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_change_password:
                String token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
                if(TextUtils.isEmpty(token)){
                    jumpToActivity(LoginActivity.class);
                }else {
                    jumpToActivity(ModifyPasswordActivity.class);
                }
                break;
            case R.id.tv_feedback:
                jumpToActivity(FeedbackActivity.class);
                break;
            case R.id.tv_about_us:
                jumpToActivity(AboutUsActivity.class);
                break;
            case R.id.btn_logout:
                showShort(getString(R.string.toast_login_out_success));
                PreferenceUtil.removeKey(Constant.PF_USER_NAME);
                PreferenceUtil.removeKey(Constant.PF_TOKEN_KEY);
                PreferenceUtil.removeKey(Constant.PF_MOBILE_NUM);
                PreferenceUtil.removeKey(Constant.PF_NICK_NAME);
                PreferenceUtil.removeKey(Constant.PF_HEAD_IMG);
                PreferenceUtil.removeKey(Constant.PF_BANK_NUM);
                PreferenceUtil.removeKey(Constant.PF_BANK_TYPE);
                PreferenceUtil.removeKey(Constant.PF_SEX);
                BusProvider.getBus().post(new LoginRefreshEvent());
                finish();
               /* logout();*/
                break;
        }
    }

    public void logout() {

        showDialog();
        String url = ApiUrl.logoutUrl;
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
                            int result=jsonObject.getInt("result");
                            if(result==1){


                                Message message = new Message();
                                message.what =1;
                                myHandler.sendMessage(message);


                            }else {
                                String info= jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showShort(getString(R.string.toast_login_out_success));
                    PreferenceUtil.removeKey(Constant.PF_USER_NAME);
                    PreferenceUtil.removeKey(Constant.PF_TOKEN_KEY);
                    PreferenceUtil.removeKey(Constant.PF_MOBILE_NUM);
                    PreferenceUtil.removeKey(Constant.PF_NICK_NAME);
                    PreferenceUtil.removeKey(Constant.PF_HEAD_IMG);
                    PreferenceUtil.removeKey(Constant.PF_BANK_NUM);
                    PreferenceUtil.removeKey(Constant.PF_BANK_TYPE);
                    PreferenceUtil.removeKey(Constant.PF_SEX);
                    BusProvider.getBus().post(new LoginRefreshEvent());
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
