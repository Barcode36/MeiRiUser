package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.net.CreateTaskModel;
import com.example.meiriuser.until.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/29.
 */

public class TaskTimeActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.line_time)
    FrameLayout lineTime;
    @BindView(R.id.is_checkbox)
    CheckBox isCheckbox;
    @BindView(R.id.is_checkbox_morning)
    CheckBox isCheckboxMorning;
    @BindView(R.id.line_morning)
    LinearLayout lineMorning;
    @BindView(R.id.is_checkbox_noon)
    CheckBox isCheckboxNoon;
    @BindView(R.id.line_noon)
    LinearLayout lineNoon;
    @BindView(R.id.is_checkbox_afternoon)
    CheckBox isCheckboxAfternoon;
    @BindView(R.id.line_afternoon)
    LinearLayout lineAfternoon;
    @BindView(R.id.is_checkbox_night)
    CheckBox isCheckboxNight;
    @BindView(R.id.line_night)
    LinearLayout lineNight;
    @BindView(R.id.btn_next)
    Button btnNext;
    CreateTaskModel createTaskModel;
    List<String> frameList;
    List<String> dateList;
    @BindView(R.id.tv_date)
    TextView tvDate;
    private TimePickerView pvCustomTime;
    String complete_date="";


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_task_time;
    }

    @Override
    public void initView() {
        toolbarTitle.setText("任务时间");
        frameList = new ArrayList<>();
        dateList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                createTaskModel = (CreateTaskModel) bundle.getSerializable(Constant.DATEBEAN);
            }

        }
        initCustomTimePicker();
    }

    @Override
    public void initData() {
        super.initData();
        BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {
                        finish();
                    }
                });
    }


    @Override
    public void initListener() {
        super.initListener();

    }

    @OnClick({R.id.icon_back, R.id.line_time, R.id.line_morning, R.id.line_noon, R.id.line_afternoon, R.id.line_night, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_time:
                pvCustomTime.show();
                break;
            case R.id.line_morning:
                break;
            case R.id.line_noon:
                break;
            case R.id.line_afternoon:
                break;
            case R.id.line_night:
                break;
            case R.id.btn_next:
                if (isCheckboxMorning.isChecked()) {
                    frameList.add(1 + "");
                    dateList.add(getString(R.string.text_task_morning));
                } else if (frameList.contains(1 + "")) {
                    frameList.remove(1 + "");
                    dateList.remove(getString(R.string.text_task_morning));
                }

                if (isCheckboxNoon.isChecked()) {
                    frameList.add(2 + "");
                    dateList.add(getString(R.string.text_task_noon));
                } else if (frameList.contains(2 + "")) {
                    frameList.remove(2 + "");
                    dateList.remove(getString(R.string.text_task_noon));
                }

                if (isCheckboxAfternoon.isChecked()) {
                    frameList.add(3 + "");
                    dateList.add(getString(R.string.text_task_afternoon));
                } else if (frameList.contains(3 + "")) {
                    frameList.remove(3 + "");
                    dateList.remove(getString(R.string.text_task_afternoon));
                }

                if (isCheckboxNight.isChecked()) {
                    frameList.add(4 + "");
                    dateList.add(getString(R.string.text_task_night));
                } else if (frameList.contains(4 + "")) {
                    frameList.remove(4 + "");
                    dateList.remove(getString(R.string.text_task_night));
                }

                if(TextUtils.isEmpty(complete_date)){
                    ToastUtils.showShort(R.string.toast_select_complete_date);
                    return;
                }
                createTaskModel.setComplete_time_frame(frameList);
                createTaskModel.setComplete_date(complete_date);
                Intent intent = new Intent(TaskTimeActivity.this, BudgetActivity.class);
                intent.putExtra(Constant.DATEBEAN, createTaskModel);
                jumpToActivity(intent);
                break;
        }
    }


    private void initCustomTimePicker() {

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        //获取当前小时
        final int time =selectedDate.get(Calendar.HOUR_OF_DAY);
        //获取年
        final int year = selectedDate.get(Calendar.YEAR);
        //获取月份，0表示1月份
        final int month = selectedDate.get(Calendar.MONTH) + 1;
        //获取当前天数
        final int day = selectedDate.get(Calendar.DATE);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);


        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                complete_date=getTime(date);
                tvDate.setText(complete_date);
                String[] selectTime=complete_date.split("-");
                int selectYear=Integer.valueOf(TextUtils.isEmpty(selectTime[0])?"0":selectTime[0]);
                int selectMonth=Integer.valueOf(TextUtils.isEmpty(selectTime[1])?"0":selectTime[1]);
                int selectDate=Integer.valueOf(TextUtils.isEmpty(selectTime[2])?"0":selectTime[2]);
                isCheckboxMorning.setChecked(false);
                isCheckboxNoon.setChecked(false);
                isCheckboxAfternoon.setChecked(false);
                isCheckboxNight.setChecked(false);
                if(selectYear==year && selectMonth==month && selectDate==day){
                    selectTime(time);
                }else {
                    isCheckboxMorning.setClickable(true);
                    isCheckboxNoon.setClickable(true);
                    isCheckboxAfternoon.setClickable(true);
                }
            }
        })
                .setDate(selectedDate)
                .setRangDate(selectedDate, endDate)
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时","分","秒")
                .setLineSpacingMultiplier(1.4f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    private void selectTime(int time) {
        if(10<time && time<=14){
            isCheckboxMorning.setClickable(false);

        }else if(14<time && time<=18){
            isCheckboxMorning.setClickable(false);
            isCheckboxNoon.setClickable(false);

        }else if(time>=18){
            isCheckboxMorning.setClickable(false);
            isCheckboxNoon.setClickable(false);
            isCheckboxAfternoon.setClickable(false);
        }

    }


}
