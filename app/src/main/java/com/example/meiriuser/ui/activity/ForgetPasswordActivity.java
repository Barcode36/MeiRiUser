package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.BaseBean;
import com.example.meiriuser.model.CodeModel;
import com.example.meiriuser.model.ResetPasswordModel;
import com.example.meiriuser.model.net.OrderPriceModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.CountDownUtils;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by admin on 2019/7/12.
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_again_pwd)
    EditText etAgainPwd;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.btn_code)
    Button btnCode;
    private CountDownUtils countDownUtils;
    int code;


    /**
     * 开启倒数
     */
    public void startCountDown() {
        countDownUtils = new CountDownUtils(60000, 1000, btnCode);
        countDownUtils.start();
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_forget_password;
    }


    @Override
    public void initView() {
        super.initView();
        toolbarTitle.setText(R.string.title_forget_password);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @OnClick({R.id.icon_back, R.id.btn_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_code:
                sendVerificationCode(etPhone.getText().toString().trim());
                break;
            case R.id.btn_submit:
                resetPassword(etPhone.getText().toString().trim(),61,code,etNewPwd.getText().toString().trim());
                break;
        }
    }


    /**
     重置密码
     */
    public void resetPassword(String tel, int country, int sms_code, String password) {

        if (TextUtils.isEmpty(tel)) {
            showShort(getString(R.string.toast_input_the_phone));
            return;
        }
       /* if (!RegexUtils.isMobileExact(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_right_phone));
            return;
        }*/
        if (!password.equals(etAgainPwd.getText().toString().trim())) {
            showShort(getString(R.string.toast_input_the_equre_password));
            return;
        }

        if (TextUtils.isEmpty(password)) {
            showShort(getString(R.string.toast_input_the_password));
            return;
        }

        showDialog();
        String url = ApiUrl.resetPasswordUrl;
        ResetPasswordModel orderPriceModel = new ResetPasswordModel(tel, country, sms_code,CommanUtils.md5Decode(password));
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(orderPriceModel))
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
                                finish();
                                showShort(getString(R.string.toast_modify_successful));
                            } else {

                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 获取验证码
     * am phoneNumber
     */
    public void sendVerificationCode(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_phone));
            return;
        }
     /*   if (!RegexUtils.isMobileExact(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_right_phone));
            return;
        }*/
        showDialog();
        String url = ApiUrl.getCodeUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("tel", phoneNumber)
                .addParams("country", 61+"")
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
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                CodeModel bean = (CodeModel) GsonUtil.JSONToObject(response.toString(), CodeModel.class);
                                if (bean.getData() != null) {
                                    startCountDown();
                                    code=bean.getData().getSms();
                                  /*  etCode.setText(code);*/
                                }
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






}
