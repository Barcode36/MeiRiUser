package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.BedroomNumAdapetr;
import com.example.meiriuser.adapter.CleanMoreContentAdapeter;
import com.example.meiriuser.adapter.RestroomAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.CleanMoreContentModel;
import com.example.meiriuser.model.net.CreateTaskModel;
import com.example.meiriuser.until.Constant;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/29.
 */

public class TaskCleanActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tl_segment)
    SegmentTabLayout tlSegment;
    @BindView(R.id.rv_bedroom_num)
    RecyclerView rvBedroomNum;
    @BindView(R.id.is_switch)
    ToggleButton isSwitch;
    @BindView(R.id.rv_clean_content)
    RecyclerView rvCleanContent;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.rv_restroom_num)
    RecyclerView rvRestroomNum;
    private String[] mTitles = {"别墅", "公寓"};
    BedroomNumAdapetr numAdapetr;
    RestroomAdapter restroomAdapter;
    List<String> numList;
    List<String>restroomList;
    CleanMoreContentAdapeter cleanMoreContentAdapeter;
    List<CleanMoreContentModel> cleanMoreContentModelList;
    CreateTaskModel.CreateTaskInfo createTaskInfo;
    List<String> otherInfo = new ArrayList<>();


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_task_clean;
    }

    @Override
    public void initView() {
        numList = new ArrayList<>();
        cleanMoreContentModelList = new ArrayList<>();
        restroomList=new ArrayList<>();
        createTaskInfo = new CreateTaskModel.CreateTaskInfo();
        toolbarTitle.setText("清洁任务");
        rvBedroomNum.setNestedScrollingEnabled(false);
        rvBedroomNum.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRestroomNum.setNestedScrollingEnabled(false);
        rvRestroomNum.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCleanContent.setNestedScrollingEnabled(false);
        rvCleanContent.setLayoutManager(new GridLayoutManager(this, 3));
        tlSegment.setTabData(mTitles);
        numList.add("0");
        numList.add("1");
        numList.add("2");
        numList.add("3");
        numList.add("4");
        numList.add("5");
        numList.add("6");
        restroomList.add("0");
        restroomList.add("1");
        restroomList.add("2");
        restroomList.add("3");
        restroomList.add("4");
        restroomList.add("5");
        restroomList.add("6");
        cleanMoreContentModelList.add(new CleanMoreContentModel(getString(R.string.text_laundry), R.mipmap.icon_task_laundry, false));
        cleanMoreContentModelList.add(new CleanMoreContentModel(getString(R.string.text_oven), R.mipmap.icon_task_oven, false));
        cleanMoreContentModelList.add(new CleanMoreContentModel(getString(R.string.text_cupboard), R.mipmap.icon_task_cupboard, false));
        cleanMoreContentModelList.add(new CleanMoreContentModel(getString(R.string.text_window), R.mipmap.icon_task_window, false));
        cleanMoreContentModelList.add(new CleanMoreContentModel(getString(R.string.text_carpet), R.mipmap.icon_task_carpet, false));
    }

    @Override
    public void initData() {
        numAdapetr = new BedroomNumAdapetr(numList);
        restroomAdapter=new RestroomAdapter(restroomList);
        rvBedroomNum.setAdapter(numAdapetr);
        rvRestroomNum.setAdapter(restroomAdapter);
        cleanMoreContentAdapeter = new CleanMoreContentAdapeter(cleanMoreContentModelList);
        rvCleanContent.setAdapter(cleanMoreContentAdapeter);
        numAdapetr.setSelectPos(0);
        restroomAdapter.setSelectPos(0);
    }

    @Override
    public void initListener() {
        createTaskInfo.setTasks(false);
        createTaskInfo.setProperty(mTitles[0]);
        createTaskInfo.setBedroomNum(Integer.valueOf(numList.get(0)));
        tlSegment.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                createTaskInfo.setProperty(mTitles[position]);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        isSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                createTaskInfo.setTasks(b);
            }
        });
        numAdapetr.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                numAdapetr.setSelectPos(position);
                switch (itemViewId) {
                    case R.id.tv_bedroom_num:
                        createTaskInfo.setBedroomNum(Integer.valueOf(numList.get(position)));
                        break;

                }
            }
        });

        restroomAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                restroomAdapter.setSelectPos(position);
                switch (itemViewId) {
                    case R.id.tv_bedroom_num:
                        createTaskInfo.setRestroomNum(Integer.valueOf(restroomList.get(position)));
                        break;

                }
            }
        });
        cleanMoreContentAdapeter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_task_clean:
                        boolean isCheck = cleanMoreContentModelList.get(position).isCheck();
                        if (isCheck) {
                            cleanMoreContentModelList.get(position).setCheck(false);
                            otherInfo.remove(cleanMoreContentModelList.get(position).getTitle());
                        } else {
                            cleanMoreContentModelList.get(position).setCheck(true);
                            otherInfo.add(cleanMoreContentModelList.get(position).getTitle());
                        }
                        cleanMoreContentAdapeter.notifyDataSetChanged();
                        break;

                }
            }
        });

        BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {
                        finish();
                    }
                });


    }

    @OnClick({R.id.icon_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_next:
                createTaskInfo.setOtherInfo(otherInfo);
                Intent intent = new Intent(TaskCleanActivity.this, TaskTwoActivity.class);
                intent.putExtra(Constant.TYPE, "2");
                intent.putExtra(Constant.DATEBEAN, createTaskInfo);
                jumpToActivity(intent);
                break;
        }
    }


}
