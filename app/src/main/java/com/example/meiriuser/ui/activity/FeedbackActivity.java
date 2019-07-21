package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.UserInfoModel;
import com.example.meiriuser.model.updatePwdModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
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

/**
 * Created by admin on 2019/5/31.
 */

public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_feedback;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("意见反馈");
    }


    @Override
    public void initData() {

    }


    @Override
    public void initListener() {
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvNumber.setText(charSequence.toString().length()+"/300");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.icon_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_submit:
                updatefeedback(etContent.getText().toString().trim());
                break;
        }
    }


    public void updatefeedback(String content) {

        if(TextUtils.isEmpty(content)){
            ToastUtils.showShort(R.string.toast_input_the_content);
            return;
        }
        showDialog();
        String url = ApiUrl.feedbackUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("content",content)
                .addHeader("token",PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
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
                            int result=jsonObject.getInt("result");
                            if(result==1){
                                finish();
                                showShort(getString(R.string.toast_submis_successful));
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
