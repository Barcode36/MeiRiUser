package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.model.net.EditInfoModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideCircleTransform;
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
 * Created by admin on 2019/7/18.
 */

public class ModifyNameActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_account_name)
    EditText etAccountName;
    String modifyname;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_modify_name;
    }

    @Override
    public void initData() {
        super.initData();
        toolbarTitle.setText(R.string.title_modify_name);
        modifyname=getIntent().getExtras().getString("modifyname");
        etAccountName.setText(modifyname);
    }

    @OnClick({R.id.icon_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_submit:
                editInfo("username",etAccountName.getText().toString().trim());
                break;
        }
    }



    /**
     * 修改用户信息
     */
    public void editInfo(final String name, final String value) {

        showDialog();
        String url = ApiUrl.editInfoUrl;
        EditInfoModel updatePwdModel = new EditInfoModel(name, value);
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
                String strresponse = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(strresponse);
                    int result = jsonObject.getInt("result");
                    if (result == 1) {
                        Message message = new Message();
                        message.what = 1;
                        myHandler.sendMessage(message);
                        PreferenceUtil.commitString(Constant.PF_USER_NAME,value);
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
                    showShort(getString(R.string.toast_modify_successful));
                    finish();
                    BusProvider.getBus().post(new LoginRefreshEvent(LoginRefreshEvent.MODIFY_PERSON_INFO));

                    break;
            }
            super.handleMessage(msg);
        }
    };

}
