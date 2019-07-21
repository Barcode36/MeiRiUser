package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2019/5/31.
 */

public class SelectBindBankCardActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_select_bind_bank_card;
    }


    @Override
    public void initView() {
        toolbarTitle.setText(R.string.title_bind_bank);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.icon_back, R.id.line_bind_china, R.id.line_bind_australia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_bind_china:
                jumpToActivity(BindChinaBankCardActivity.class);
                break;
            case R.id.line_bind_australia:
                jumpToActivity(BindAustraliaBankCardActivity.class);
                break;
        }
    }
}
