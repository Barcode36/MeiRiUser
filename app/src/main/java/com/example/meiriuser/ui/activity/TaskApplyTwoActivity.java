package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 任务申请第二步
 */

public class TaskApplyTwoActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.is_switch)
    ToggleButton isSwitch;
    @BindView(R.id.tv_next)
    TextView tvNext;
    boolean isswitch=false;
    boolean SignEt=false;
    String taskId;
    String taskPrice;
    String advantage;
    boolean advantage_check;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_task_apply_two;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("任务申请");
        taskId=getIntent().getStringExtra("task_id");
        taskPrice=getIntent().getStringExtra("price");
        advantage = PreferenceUtil.getString(Constant.PERSONAL_ADVANTAGE);
        advantage_check= PreferenceUtil.getBoolean(Constant.PERSONAL_CHECK,false);
        isSwitch.setChecked(advantage_check);
        etContent.setText(advantage);
        etContent.setSelection(advantage.length());
        if(advantage.length()>0){
            tvNext.setBackgroundResource(R.drawable.shape_fillet_green_18dp);
            tvNext.setEnabled(true);
        }
    }


    @Override
    public void initData() {
        etContent.addTextChangedListener(new textChange());
        isSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isswitch=isChecked;
                if (isswitch) {
                    PreferenceUtil.commitString(Constant.PERSONAL_ADVANTAGE,etContent.getText().toString().trim());
                    PreferenceUtil.commitBoolean(Constant.PERSONAL_CHECK,true);
                }
                else {
                    PreferenceUtil.removeKey(Constant.PERSONAL_ADVANTAGE);
                    PreferenceUtil.commitBoolean(Constant.PERSONAL_CHECK,false);
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

    @OnClick({R.id.icon_back, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_next:
                Intent intent=new Intent(TaskApplyTwoActivity.this,TaskApplyActivity.class);
                intent.putExtra(Constant.TYPE,"two");
                intent.putExtra("price",taskPrice);
                intent.putExtra("task_id",taskId);
                intent.putExtra("advantage",etContent.getText().toString().trim());
                jumpToActivity(intent);
                break;
        }
    }


    class textChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {
            SignEt = etContent.getText().length() > 0;
            tvNumber.setText(etContent.getText().toString().trim().length()+"/500");
            if (SignEt) {
                tvNext.setBackgroundResource(R.drawable.shape_fillet_green_18dp);
                tvNext.setEnabled(true);
            }
            else {
                tvNext.setBackgroundResource(R.drawable.shape_fillet_gray_18dp);
                tvNext.setEnabled(false);
            }
        }

    }

}
