package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.MainActivity;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.model.doLoginBean;
import com.example.meiriuser.model.net.LoginCodeModel;
import com.example.meiriuser.model.net.LoginPwdModel;
import com.example.meiriuser.model.net.OrderNetModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.yanzhenjie.sofia.Sofia;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
import rx.functions.Action1;

/**
 * Created by admin on 2019/6/11.
 */

public class LoginPwdActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login_pwd;
    }



    @Override
    public void initView() {
        //状态栏
        Sofia.with(this).statusBarDarkFont().statusBarBackgroundAlpha(0);
    }


    @OnClick({R.id.btn_login, R.id.tv_login,R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login(etPhone.getText().toString().trim(), etPwd.getText().toString().trim());
                break;
            case R.id.tv_login:
                jumpToActivity(LoginActivity.class);
                break;
            case R.id.tv_forget_password:
                jumpToActivity(ForgetPasswordActivity.class);
                break;
        }
    }

    @Override
    public void initListener() {
        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        finish();
                    }
                });
    }

    public void login(final String phoneNumber, final String password) {

        if (TextUtils.isEmpty(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_phone));
            return;
        }
       /* if (!RegexUtils.isMobileExact(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_right_phone));
            return;
        }*/
        if (TextUtils.isEmpty(password)) {
            showShort(getString(R.string.toast_input_the_password));
            return;
        }
     /*   if (password.length() < 6 || password.length() > 12) {
            showShort(getString(R.string.toast_password_length));
            return;
        }*/
        showDialog();
        String url = ApiUrl.loginUrl;
        LoginPwdModel loginParam = new LoginPwdModel(phoneNumber, CommanUtils.md5Decode(password));
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8")) //设置post的字符串为json字符串并设置编码
                .content(new Gson().toJson(loginParam)) //用Gson将User对象转化为Json字符串的形式作为content
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        showShort(e.getMessage());
                        dissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dissDialog();
                        String strresponse = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(strresponse);
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                doLoginBean bean = (doLoginBean) GsonUtil.JSONToObject(strresponse, doLoginBean.class);
                                if (bean.getData() != null) {
                                    PreferenceUtil.commitString(Constant.PF_TOKEN_KEY, bean.getData().getToken());
                                    PreferenceUtil.commitString(Constant.PF_PWD_KEY,password);
                                    Message message = new Message();
                                    message.what = 1;
                                    myHandler.sendMessage(message);

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


    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BusProvider.getBus().post(new LoginRefreshEvent());
                    showShort(getString(R.string.toast_login_success));
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };


}
