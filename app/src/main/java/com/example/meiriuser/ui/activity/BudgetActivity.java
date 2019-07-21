package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.net.CreateTaskModel;
import com.example.meiriuser.until.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/29.
 */

public class BudgetActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_price)
    EditText tvPrice;
    @BindView(R.id.tv_sumbit)
    TextView tvSumbit;
    CreateTaskModel createTaskModel;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_budget;
    }

    @Override
    public void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                createTaskModel = (CreateTaskModel) bundle.getSerializable(Constant.DATEBEAN);
            }
        }
        toolbarTitle.setText(R.string.title_budget);
        createTaskModel.setMoney(Float.valueOf(tvPrice.getText().toString().trim()));
        tvPrice.setSelection(tvPrice.length());
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


    @OnClick({R.id.icon_back, R.id.tv_sumbit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_sumbit:
                if(TextUtils.isEmpty(tvPrice.getText().toString().trim())){
                    ToastUtils.showShort(R.string.toast_input_task_price);
                    return;
                }
                Intent intent=new Intent(BudgetActivity.this,TaskPayActivity.class);
                intent.putExtra("price",tvPrice.getText().toString().trim());
                intent.putExtra(Constant.TYPE,"play");
                intent.putExtra(Constant.DATEBEAN,createTaskModel);
                jumpToActivity(intent);
                break;
        }
    }
}
