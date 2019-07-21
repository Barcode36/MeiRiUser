package com.example.meiriuser.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.OrderModel;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideRoundTransform;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2019/5/23.
 */

public class SeeCouponCodeActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_shape_name)
    TextView tvShapeName;
    @BindView(R.id.tv_group_name)
    TextView tvGroupName;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    String orderState;
    OrderModel.DataBeanX.DataBean dataBean;
    Bitmap bitmapCode = null;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_see_coupon_code;
    }

    @Override
    public void initView() {
        orderState=getIntent().getExtras().getString(Constant.ORDERSTATE);
        dataBean= (OrderModel.DataBeanX.DataBean) getIntent().getExtras().get(Constant.DATEBEAN);
        toolbarTitle.setText("查看券码");



        if(dataBean!=null){
            tvShapeName.setText(dataBean.getStore().getName());
            tvGroupName.setText(dataBean.getGoods().get(0).getGoods_name());
            tvHour.setText(dataBean.getAdd_time());
            tvCode.setText(dataBean.getTask_code()+"");

            String textContent =dataBean.getTask_code();
            bitmapCode = CodeUtils.createImage(textContent, 168, 168, null);
            ivCode.setImageBitmap(bitmapCode);
           /* Glide.with(this)
                    .load(dataBean.getZipcode())
                    .into(ivCode);*/
        }
        if(orderState.equals("已完成")){
            tvState.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void initData() {
        super.initData();
    }

    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
    }
}
