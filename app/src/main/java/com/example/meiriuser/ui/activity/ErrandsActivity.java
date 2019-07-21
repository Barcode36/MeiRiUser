package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.ui.fragment.BuyingOnBehalfFrgment;
import com.example.meiriuser.ui.fragment.MainBehalfFragment;
import com.example.meiriuser.ui.fragment.PickUpDeliveryPartsFrgment;
import com.example.meiriuser.until.Constant;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yanzhenjie.sofia.Sofia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 跑腿
 */

public class ErrandsActivity extends BaseActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List titleslist = new ArrayList();
    @BindView(R.id.tv_order_detail)
    TextView tvOrderDetail;
    private String[] mTitles;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    String type;
    String jumptype;



    @Override
    protected int provideContentViewId() {
        return R.layout.activity_errands;
    }


    @Override
    public void initView() {
        //状态栏
        Sofia.with(this).statusBarDarkFont().statusBarBackgroundAlpha(0);
        titleslist.add(getString(R.string.text_behalf));
        titleslist.add(getString(R.string.text_pick_up_delivery_parts));
        mTitles = (String[]) titleslist.toArray(new String[]{});
        type = getIntent().getStringExtra(Constant.TYPE);
        if (type.equals("main")) {
            tvOrderDetail.setVisibility(View.VISIBLE);
            mFragments.add(new MainBehalfFragment());
            mFragments.add(new PickUpDeliveryPartsFrgment());
        } else {
            tvOrderDetail.setVisibility(View.GONE);
            jumptype = getIntent().getStringExtra("jumptype");
            mFragments.add(BuyingOnBehalfFrgment.newInstance(jumptype));
            mFragments.add(new PickUpDeliveryPartsFrgment());
        }

        //new一个适配器
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        //设置ViewPager与适配器关联
        viewPager.setAdapter(mAdapter);
        tl1.setTabData(mTitles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tl1.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void initData() {

    }



    @OnClick({R.id.icon_back, R.id.tv_order_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_order_detail:
                jumpToActivity(ErrandsOrderListActivity.class);
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
