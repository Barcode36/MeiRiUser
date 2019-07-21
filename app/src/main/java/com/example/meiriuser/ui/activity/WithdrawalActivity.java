package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.dialog.IDialogCallBack;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.UserInfoEvent;
import com.example.meiriuser.model.UserInfoModel;
import com.example.meiriuser.model.net.CashoutModel;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by admin on 2019/5/30.
 */

public class WithdrawalActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_transfer_account)
    EditText etTransferAccount;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.line_bank)
    RelativeLayout lineBank;
    @BindView(R.id.line_bank_card)
    LinearLayout lineBankCard;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_amount)
    EditText etAmount;
    DialogUtil dialogUtil;
    String userMoney;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_withdrawal;
    }


    @Override
    public void initView() {
        toolbarTitle.setText(R.string.title_withdrawal);
     /*   tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.text_unbound);*/
        dialogUtil=new DialogUtil();
        userMoney= PreferenceUtil.getString(Constant.PF_USER_MONEY);
     /*   Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559209752465&di=d88879d7ef295dcd8fa5055607829763&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170512%2F5b8c492ede334d93b29d6383c964da23_th.png")
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(this))
                .into(ivBank);
        tvBankNum.setText("尾号" + "4321" + "储蓄卡");*/
    }


    @Override
    public void initData() {
        super.initData();
        getInfo();
    }

    @OnClick({R.id.icon_back, R.id.btn_submit, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_submit:
                dialogUtil.showSimpleDialogOKCancel(this, getWindowManager(), getString(R.string.tips), getString(R.string.tips_withdrawal), new IDialogCallBack() {
                    @Override
                    public void setPositiveButton() {
                        cashoutBank();
                    }

                    @Override
                    public void setNegativeButton() {

                    }
                });
                break;
            case R.id.tv_right:
                dialogUtil.showSimpleDialogOKCancel(this, getWindowManager(),  getString(R.string.tips), "是否解除绑定?", new IDialogCallBack() {
                    @Override
                    public void setPositiveButton() {


                    }

                    @Override
                    public void setNegativeButton() {

                    }
                });
                break;
        }
    }

    /**
     提现
     */
    public void cashoutBank() {
        String amount=etAmount.getText().toString().trim();
        String account=etTransferAccount.getText().toString().trim();
        String bankName=etBankName.getText().toString().trim();
        String bank=etBank.getText().toString().trim();

        if(TextUtils.isEmpty(amount)){
            showShort(getString(R.string.toast_input_amount));
            return;
        }

    /*    if(Integer.valueOf(amount)>Integer.valueOf(userMoney)){
            showShort(getString(R.string.toast_amount_low));
            return;
        }*/

        if(TextUtils.isEmpty(account)){
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
        String url = ApiUrl.cashoutBankUrl;
        CashoutModel cashoutModel = new CashoutModel(Float.valueOf(amount), account, bankName,bank);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(cashoutModel))
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
                                showShort(getString(R.string.text_withdrawal_suffcess));
                               /* BusProvider.getBus().post(new UserInfoEvent());*/
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
                                    etTransferAccount.setText(bean.getData().getBanknum());
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


}
