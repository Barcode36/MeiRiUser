package com.example.meiriuser.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.BaseBean;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2019/5/30.
 */

public class BindChinaBankCardActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_bank_card)
    EditText etBankCard;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.line_bank)
    RelativeLayout lineBank;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.line_bank_card)
    LinearLayout lineBankCard;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    String token;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_bind_china_bank_card;
    }


    @Override
    public void initView() {
        toolbarTitle.setText(R.string.title_bind_bank);
        token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.icon_back, R.id.line_bank, R.id.line_bank_card, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_bank:
                break;
            case R.id.line_bank_card:
                break;
            case R.id.btn_submit:
                editInfo(etBank.getText().toString().trim(),etBankCard.getText().toString().trim(),token);
                break;
        }
    }



    /**
     * 修改用户信息
     */
    public void editInfo(String bankType,String banknum,String token) {

        Map<String, String> params=new HashMap<>();
        params.put("bankType", bankType);
        params.put("banknum", banknum);
        params.put("token", token);
        showDialog();
        String url = ApiUrl.editInfoUrl;
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
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
                                finish();
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
