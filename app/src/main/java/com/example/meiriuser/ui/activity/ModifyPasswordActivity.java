package com.example.meiriuser.ui.activity;

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
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.model.doLoginBean;
import com.example.meiriuser.model.net.LoginCodeModel;
import com.example.meiriuser.model.updatePwdModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

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

/**
 * Created by admin on 2019/5/31.
 */

public class ModifyPasswordActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_again_pwd)
    EditText etAgainPwd;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_modify_password;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("修改密码");
    }


    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.icon_back, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_logout:
                updatePwd(etOldPwd.getText().toString().trim(),etNewPwd.getText().toString().trim(),etAgainPwd.getText().toString().trim());
                break;
        }
    }


    public void updatePwd(String password_old, final String password_new1, String password_new2) {

        if (TextUtils.isEmpty(password_old)) {
            showShort(getString(R.string.toast_input_the_old_password));
            return;
        }

        if (TextUtils.isEmpty(password_new1)) {
            showShort(getString(R.string.toast_input_the_new_password));
            return;
        }

        if (TextUtils.isEmpty(password_new2)) {
            showShort(getString(R.string.toast_input_the_sure_password));
            return;
        }

        if (!password_new1.equals(password_new2)) {
            showShort(getString(R.string.toast_input_the_equre_password));
            return;
        }

        showDialog();
        String url = ApiUrl.updatePwdUrl;
        updatePwdModel updatePwdModel = new updatePwdModel(CommanUtils.md5Decode(password_old),CommanUtils.md5Decode( password_new1));
        Gson gson = new Gson();
        final String s = gson.toJson(updatePwdModel);
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
                String strresponse=response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(strresponse);
                    int result = jsonObject.getInt("result");
                    if (result == 1) {
                        showShort(getString(R.string.toast_modify_pwd));
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
