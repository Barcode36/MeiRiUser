package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2019/5/29.
 */

public class AddBankCardActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_bank_num)
    EditText etBankNum;
    @BindView(R.id.line_bank_num)
    FrameLayout lineBankNum;
    @BindView(R.id.et_bank_date)
    EditText etBankDate;
    @BindView(R.id.et_safety_code)
    EditText etSafetyCode;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.is_checkbox)
    CheckBox isCheckbox;
    @BindView(R.id.btn_confirm_payment)
    Button btnConfirmPayment;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_add_bank_card;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("信用卡支付");
    }


    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.icon_back, R.id.line_bank_num, R.id.btn_confirm_payment,R.id.tv_tips})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_bank_num:
                break;
            case R.id.btn_confirm_payment:
                break;
            case R.id.tv_tips:
                break;
        }
    }
}
