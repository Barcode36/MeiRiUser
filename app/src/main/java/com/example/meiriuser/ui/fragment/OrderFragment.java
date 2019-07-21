package com.example.meiriuser.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yanzhenjie.sofia.Sofia;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/9.
 */

public class OrderFragment extends BaseFragment {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private String[] mTitles = {"未完成", "已完成"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }



    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView(View rootView) {
        iconBack.setVisibility(View.GONE);
        toolbarTitle.setText("订单");
        mFragments.add(new OrderNoFinishFragment());
        mFragments.add(new OrderFinishFragment());
        //new一个适配器
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        //设置ViewPager与适配器关联
        viewPager.setAdapter(mAdapter);
        tl1.setTabData(mTitles);
        viewPager.setOffscreenPageLimit(mFragments.size() - 1);
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
