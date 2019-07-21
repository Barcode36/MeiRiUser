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
 * Created by admin on 2019/5/30.
 */

public class BillingAddressActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_upload_id)
    TextView tvUploadId;
    @BindView(R.id.tv_billing_address)
    TextView tvBillingAddress;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_billing_address;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("账单地址");
    }


    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.icon_back, R.id.tv_upload_id, R.id.tv_billing_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_upload_id:
                jumpToActivity(UploadIdActivity.class);
                break;
            case R.id.tv_billing_address:
                jumpToActivity(ProvideBillingAddressActivity.class);
                break;
        }
    }
}
