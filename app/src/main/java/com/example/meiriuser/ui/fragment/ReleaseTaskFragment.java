package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.ui.activity.TaskCleanActivity;
import com.example.meiriuser.ui.activity.TaskTwoActivity;
import com.example.meiriuser.until.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/28.
 */

public class ReleaseTaskFragment extends BaseFragment {
    @BindView(R.id.line_task_clean)
    LinearLayout lineTaskClean;
    @BindView(R.id.line_task_install)
    LinearLayout lineTaskInstall;
    @BindView(R.id.line_task_repair)
    LinearLayout lineTaskRepair;
    @BindView(R.id.line_ask_delivery)
    LinearLayout lineAskDelivery;
    @BindView(R.id.line_task_gardening)
    LinearLayout lineTaskGardening;
    @BindView(R.id.line_task_moving)
    LinearLayout lineTaskMoving;
    @BindView(R.id.line_task_finance)
    LinearLayout lineTaskFinance;
    @BindView(R.id.line_task_computer)
    LinearLayout lineTaskComputer;
    @BindView(R.id.line_task_photography)
    LinearLayout lineTaskPhotography;
    @BindView(R.id.line_task_more)
    LinearLayout lineTaskMore;


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_release_task;
    }



    @OnClick({R.id.line_task_clean, R.id.line_task_install, R.id.line_task_repair, R.id.line_ask_delivery, R.id.line_task_gardening, R.id.line_task_moving, R.id.line_task_finance, R.id.line_task_computer, R.id.line_task_photography, R.id.line_task_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_task_clean:
                jumpToActivity(TaskCleanActivity.class);
                break;
            case R.id.line_task_install:
                jumpTaskTwo(1);
                break;
            case R.id.line_task_repair:
                jumpTaskTwo(1);
                break;
            case R.id.line_ask_delivery:
                jumpTaskTwo(1);
                break;
            case R.id.line_task_gardening:
                jumpTaskTwo(1);
                break;
            case R.id.line_task_moving:
                jumpTaskTwo(1);
                break;
            case R.id.line_task_finance:
                jumpTaskTwo(1);
                break;
            case R.id.line_task_computer:
                jumpTaskTwo(1);
                break;
            case R.id.line_task_photography:
                jumpTaskTwo(1);
                break;
            case R.id.line_task_more:
                jumpTaskTwo(1);
                break;
        }
    }


    public void jumpTaskTwo(int position){
        Intent intent=new Intent(getActivity(),TaskTwoActivity.class);
        intent.putExtra(Constant.TYPE,position);
        jumpToActivity(intent);
    }
}
