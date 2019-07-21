package com.example.meiriuser.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.model.CodeModel;
import com.example.meiriuser.model.doLoginBean;
import com.example.meiriuser.model.net.LoginCodeModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.CountDownUtils;
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
 * Created by admin on 2019/4/28.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_code)
    Button btnCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_login)
    TextView tvPwdLogin;
    private CountDownUtils countDownUtils;
    String code;


    /**
     * 开启倒数
     */
    public void startCountDown() {
        countDownUtils = new CountDownUtils(60000, 1000, btnCode);
        countDownUtils.start();
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login_code;
    }


    @Override
    public void initView() {
        //状态栏
        Sofia.with(this).statusBarDarkFont().statusBarBackgroundAlpha(0);
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


    public void login(final String phoneNumber, final String code) {

        if (TextUtils.isEmpty(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_phone));
            return;
        }
 /*       if (!RegexUtils.isMobileExact(phoneNumber)) {
            showShort(getString(R.string.toast_input_the_right_phone));
            return;
        }*/
        if (TextUtils.isEmpty(code)) {
            showShort(getString(R.string.toast_input_the_ver_code));
            return;
        }

        showDialog();
        String url = ApiUrl.smsLoginUrl;
        LoginCodeModel loginParam = new LoginCodeModel(phoneNumber, Integer.valueOf(code));
        Gson gson = new Gson();
        final String s = gson.toJson(loginParam);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
        OkHttpClient okHttpClient1 = OkHttpUtils.getInstance().getOkHttpClient();
        Request request = new Request.Builder().url(url)
                .post(requestBody).build();
        okHttpClient1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dissDialog();
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dissDialog();
                String strresponse=response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(strresponse);
                    int result = jsonObject.getInt("result");
                    if (result == 1) {
                        doLoginBean bean = (doLoginBean) GsonUtil.JSONToObject(strresponse, doLoginBean.class);
                        if(bean.getData()!=null){
                            PreferenceUtil.commitString(Constant.PF_TOKEN_KEY, bean.getData().getToken());
                            Message message = new Message();
                            message.what =1;
                            myHandler.sendMessage(message);
                            showShort(getString(R.string.toast_login_success));
                            finish();
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

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BusProvider.getBus().post(new LoginRefreshEvent());
                    break;
            }
            super.handleMessage(msg);
        }
    };

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
                                    code=String.valueOf(bean.getData().getSms());
                                  /*  etCode.setText(code);*/
                                }
                            }else {
                                String info= jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                       /* if(bean.getResult()== HttpStatus.SUCCESS){

                        }*/
                       /* showShort(bean.getInfo());*/
                    }
                });

    }






    @OnClick({R.id.btn_code, R.id.btn_login, R.id.tv_login, R.id.icon_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                sendVerificationCode(etPhone.getText().toString().trim());
                break;
            case R.id.btn_login:
                login(etPhone.getText().toString().trim(), etCode.getText().toString().trim());
                break;
            case R.id.tv_login:
                jumpToActivity(LoginPwdActivity.class);
                break;
            case R.id.icon_back:
                finish();
        }
    }
}
