package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2019/5/31.
 */

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about_us;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("关于我们");
    }


    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
    }
}
