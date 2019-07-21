package com.example.meiriuser.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.BaseBean;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.CountDownUtils;
import com.example.meiriuser.until.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by admin on 2019/4/28.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private CountDownUtils countDownUtils;
    String code;

    /**
     * 开启倒数
     */
    public void startCountDown() {
        countDownUtils = new CountDownUtils(60000, 1000, tvGetCode);
        countDownUtils.start();
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_register;
    }


    @Override
    public void initView() {
        super.initView();
    }


    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void initListener() {
        // 监听多个输入框
        RegisterBtnTextChange textChange = new RegisterBtnTextChange();
   /*     etPhone.addTextChangedListener(textChange);
        etCode.addTextChangedListener(textChange);
        etPwd.addTextChangedListener(textChange);
        etInvitationCode.addTextChangedListener(textChange);*/
    }

    @OnClick({R.id.tv_get_code, R.id.btn_register, R.id.tv_login,R.id.icon_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                sendVerificationCode(etPhone.getText().toString().trim());
                break;
            case R.id.btn_register:
                affirmUserPassWord(etPhone.getText().toString().trim(),etCode.getText().toString().trim(),etPwd.getText().toString().trim(),etUserName.getText().toString().trim());
                break;
            case R.id.tv_login:
                jumpToActivity(LoginActivity.class);
                finish();
                break;
            case R.id.icon_back:
                finish();
        }
    }


    class RegisterBtnTextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {
            boolean SignPhone = etPhone.getText().length() > 0;
            boolean SignCode = etCode.getText().length() > 0;
            boolean SignPwd = etPwd.getText().length() > 0;

        }

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
      /*  if (!RegexUtils.isMobileExact(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_right_phone));
            return;
        }*/
        showDialog();

        String url = ApiUrl.getCodeUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("mobileNum", phoneNumber)
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
                       /* BaseBean bean= (BaseBean) GsonUtil.JSONToObject(response.toString(),BaseBean.class);*/
                        startCountDown();
                        code=response.toString();
                        etCode.setText(code);
                       /* if(bean.getResult()== HttpStatus.SUCCESS){

                        }*/
                       /* showShort(bean.getInfo());*/
                    }
                });

    }




    /**
     * 注册
     * am phoneNumber
     */
    public void affirmUserPassWord(final String phone, String verCode, final String pswd,String userName) {

        if (TextUtils.isEmpty(phone)) {
            showShort(getString(R.string.toast_input_the_phone));
            return;
        }
        if (TextUtils.isEmpty(verCode)) {
            showShort(getString(R.string.toast_input_the_ver_code));
            return;
        }
        if (TextUtils.isEmpty(pswd)) {
            showShort(getString(R.string.toast_input_the_password));
            return;
        }
        if (pswd.length() < 6 || pswd.length() > 12) {
            showShort(getString(R.string.toast_password_length));
            return;
        }
        showDialog();
        String password=CommanUtils.md5Decode(pswd);

        String url = ApiUrl.registerUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("smsCode", "1111")
                .addParams("password", password)
                .addParams("user_name", userName)
                .addParams("mobileNum", phone)
                .addParams("groupid", "1")
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
                        BaseBean bean= (BaseBean) GsonUtil.JSONToObject(response.toString(),BaseBean.class);
                        if(bean.getResult()== HttpStatus.SUCCESS){

                        }
                        showShort(bean.getInfo());
                    }
                });

    }
}
