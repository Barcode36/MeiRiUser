package com.example.meiriuser.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yanzhenjie.sofia.Sofia;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/9.
 */

public class TaskFragment extends BaseFragment {

    @BindView(R.id.tl)
    SlidingTabLayout tl;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String[] mTitles = {"发布任务", "任务授权", "我发布的"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;


    public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();
        return fragment;
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_task;
    }


    @Override
    public void initView(View rootView) {
        mFragments.add(new ReleaseTaskFragment());
        mFragments.add(new TaskAuthFragment());
        mFragments.add(new IReleasedFragment());
        //new一个适配器
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        //设置ViewPager与适配器关联
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(mFragments.size() - 1);
        tl.setViewPager(viewPager, mTitles);

    }


    @Override
    public void initData() {
        super.initData();
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
