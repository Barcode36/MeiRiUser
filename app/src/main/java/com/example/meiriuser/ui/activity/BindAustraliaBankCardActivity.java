package com.example.meiriuser.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.MainRefreshEvent;
import com.example.meiriuser.event.RefreshListEvent;
import com.example.meiriuser.model.BaseBean;
import com.example.meiriuser.model.UserInfoModel;
import com.example.meiriuser.model.doLoginBean;
import com.example.meiriuser.model.net.OrderPriceModel;
import com.example.meiriuser.model.net.ResetBankModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by admin on 2019/5/31.
 */

public class BindAustraliaBankCardActivity extends BaseActivity{

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_bank_card)
    EditText etBankCard;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.line_bank)
    RelativeLayout lineBank;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.line_bank_card)
    LinearLayout lineBankCard;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    String token;
    @BindView(R.id.tv_right)
    TextView tvRight;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_bind_australia_bank_card;
    }

    @Override
    public void initView() {
        toolbarTitle.setText(R.string.title_bind_australia_bank);
        token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.text_unbound);
    }

    @Override
    public void initData() {
        super.initData();
        getInfo();
    }

    @OnClick({R.id.icon_back, R.id.line_bank, R.id.line_bank_card, R.id.btn_submit,R.id.tv_right})
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
                resetBank();
                break;
            case R.id.tv_right:
                deleteBank();
                break;
        }
    }


    /**
     设置用户银行卡
     */
    public void resetBank() {
        String bankName=etBankName.getText().toString().trim();
        String bank=etBank.getText().toString().trim();
        String bankCard=etBankCard.getText().toString().trim();
        if(TextUtils.isEmpty(bankCard)){
            showShort(getString(R.string.toast_input_account));
            return;
        }
        if(TextUtils.isEmpty(bankName)){
            showShort(getString(R.string.toast_input_bank_name));
            return;
        }

        if(TextUtils.isEmpty(bank)){
            showShort(getString(R.string.toast_input_bank));
            return;
        }

        showDialog();
        String url = ApiUrl.resetBankUrl;
        ResetBankModel resetBankModel = new ResetBankModel(bankCard, bank, bankName);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(resetBankModel))
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
                                showShort(getString(R.string.text_bind_success));
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



    /**
     * 获取用户信息
     */
    public void getInfo() {

        showDialog();
        String url = ApiUrl.infoUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
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
                                UserInfoModel bean= (UserInfoModel) GsonUtil.JSONToObject(response.toString(),UserInfoModel.class);
                                if(bean.getResult()== HttpStatus.SUCCESS && bean.getData()!=null){
                                    etBankCard.setText(bean.getData().getBanknum());
                                    etBank.setText(bean.getData().getBankType());
                                    etBankName.setText(bean.getData().getBank_name());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    /**
     解除绑定银行卡
     */
    public void deleteBank() {
        String url = ApiUrl.deleteBankUrl;
        OkHttpUtils
                .delete()
                .url(url)
                .addHeader("token",token)
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
                                showShort(getString(R.string.text_unbind_success));
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
