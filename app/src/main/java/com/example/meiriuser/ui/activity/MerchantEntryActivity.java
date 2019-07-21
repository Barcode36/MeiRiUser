package com.example.meiriuser.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.until.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2019/5/30.
 */

public class MerchantEntryActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    String type;
    @BindView(R.id.tv_down_tips)
    TextView tvDownTips;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.tv_three)
    TextView tvThree;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_merchant_entry;
    }

    @Override
    public void initView() {

        type = getIntent().getExtras().getString(Constant.TYPE);
        if (type.equals("become_rider")) {//成为骑手
            toolbarTitle.setText(R.string.title_become_rider);
            btnDownload.setText(R.string.text_rider_down_tips);
            tvDownTips.setText(R.string.text_rider_down_tips);
            tvThree.setText(R.string.text_become_rider_three);

        } else if (type.equals("merchant_entry")) {//商家入驻
            toolbarTitle.setText(R.string.title_merchant_entry);
            btnDownload.setText(R.string.text_merchat_down_tips);
            tvDownTips.setText(R.string.text_merchat_down_tips);
        }
    }

    @Override
    public void initData() {
        super.initData();
    }


    @OnClick({R.id.icon_back, R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_download:
                openBrowser(MerchantEntryActivity.this, "https://www.pgyer.com/17Uu");
                break;
        }
    }


    /**
     * 调用第三方浏览器打开
     *
     * @param context
     * @param url     要浏览的资源地址
     */
    public void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager()); // 打印Log   ComponentName到底是什么 L.d("componentName = " + componentName.getClassName());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            showShort("请下载浏览器");
        }
    }


}
