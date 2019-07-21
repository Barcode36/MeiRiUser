package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.ShopInfoModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.fragment.BusinessFragment;
import com.example.meiriuser.ui.fragment.CommodityFragment;
import com.example.meiriuser.ui.fragment.GroupBuyingServiceFragment;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.widget.GlideRoundTransform;
import com.flyco.tablayout.SlidingTabLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by admin on 2019/5/22.
 */

public class GroupBuyingActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_food)
    ImageView ivFood;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tl_food)
    SlidingTabLayout tlFood;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_group_state)
    TextView tvGroupState;
    int storeID;
    String shopTel;
    GroupBuyingServiceFragment serviceFragment;
    BusinessFragment businessFragment;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "服务", "商家"
    };
    private MyPagerAdapter mAdapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_group_buying;
    }


    @Override
    public void initView() {
        storeID = getIntent().getExtras().getInt(Constant.TYPE, 0);
        serviceFragment=new GroupBuyingServiceFragment();
        businessFragment=new BusinessFragment();
        mFragments.add(serviceFragment);
        mFragments.add(businessFragment);
        //new一个适配器
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        //设置ViewPager与适配器关联
        viewPager.setAdapter(mAdapter);
        //设置Tab与ViewPager关联
        tlFood.setViewPager(viewPager);
    }


    @Override
    public void initData() {
        getStoreInfo(storeID);
    }


    /**
     * 获取单个商店信息
     */
    public void getStoreInfo(final int storeID) {

        showDialog();
        String url = ApiUrl.storeUrl + "?store_id=" + storeID;
        OkHttpUtils
                .post()
                .url(url)
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
                                ShopInfoModel bean = (ShopInfoModel) GsonUtil.JSONToObject(response.toString(), ShopInfoModel.class);
                                if (bean.getShop() != null) {
                                    ShopInfoModel.ShopBean dataBean = bean.getShop();
                                    Glide.with(GroupBuyingActivity.this)
                                            .load(dataBean.getHeadImg())
                                            .transform(new CenterCrop(GroupBuyingActivity.this), new GlideRoundTransform(GroupBuyingActivity.this, 2))
                                            .into(ivFood);
                                    toolbarTitle.setText(dataBean.getShopName());
                                    ratingbar.setRating((float) dataBean.getScore());
                                    tvScore.setText(dataBean.getScore()+"");
                                    tvGroupState.setText(dataBean.getStatus()==0?"暂停营业":"正常营业"+" | "+dataBean.getYstartime()+"-"+dataBean.getYendtime());
                                    tvAddress.setText(dataBean.getAddress());
                                    shopTel = dataBean.getTel();
                                    businessFragment.setInfo(dataBean);
                                    serviceFragment.setCommodityInfo(String.valueOf(storeID),dataBean.getShopName(),dataBean.getAddress(),dataBean.getDelivery_money());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
    @OnClick({R.id.icon_back, R.id.iv_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.iv_call:
                CommanUtils.callPhone(this,shopTel);
                break;
        }
    }



    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }
}
