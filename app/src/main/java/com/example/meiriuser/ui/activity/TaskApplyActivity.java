package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.dialog.IDialogCallBack;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.TaskDetailsModel;
import com.example.meiriuser.model.net.BuyingOrderModel;
import com.example.meiriuser.model.net.TaskApplyModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import rx.functions.Action1;

/**
 * 任务申请
 */

public class TaskApplyActivity extends BaseActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_service_charge)
    TextView tvServiceCharge;
    @BindView(R.id.tv_taxation)
    TextView tvTaxation;
    @BindView(R.id.tv_get_price)
    TextView tvGetPrice;
    @BindView(R.id.tv_next)
    TextView tvNext;
    String type;
    @BindView(R.id.tv_my_advantage)
    TextView tvMyAdvantage;
    @BindView(R.id.line_my_advantage)
    LinearLayout lineMyAdvantage;
    String taskId;
    String taskPrice;
    double serviceCharge;
    double taxation;
    double obtain;
    String advantage;
  /*  你将得到的钱=任务金额-服务费（任务金额的15%）-税费（任务金额的2%）*/
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_task_apply;
    }


    @Override
    public void initView() {
        toolbarTitle.setText(R.string.title_task_apply);
        type = getIntent().getExtras().getString(Constant.TYPE);
        if (type.equals("two")) {
            advantage=getIntent().getExtras().getString("advantage");
            lineMyAdvantage.setVisibility(View.VISIBLE);
            tvMyAdvantage.setText("    "+advantage);
            tvNext.setText("确认申请");
        }

        taskId=getIntent().getStringExtra("task_id");
        taskPrice=getIntent().getStringExtra("price");
        if(!TextUtils.isEmpty(taskPrice)){
            double price=Double.valueOf(taskPrice);
            serviceCharge=price*0.15;
            taxation=price*0.02;
            obtain=price-serviceCharge-taxation;
            tvServiceCharge.setText(String.format(getString(R.string.text_reduct_price02), CommanUtils.doubleTrans(serviceCharge)));
            tvTaxation.setText(String.format(getString(R.string.text_reduct_price02), CommanUtils.doubleTrans(taxation)));
            tvGetPrice.setText(String.format(getString(R.string.text_price2),CommanUtils.doubleTrans(obtain)));
        }
        tvPrice.setText(String.format(getString(R.string.text_price2), CommanUtils.doubleTrans(Double.valueOf(taskPrice))));


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

    @OnClick({R.id.icon_back, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_next:
                if (type.equals("two")) {
                    DialogUtil dialogUtil =new DialogUtil();
                    dialogUtil.showSimpleDialogOKCancel(this, getWindowManager(), getString(R.string.text_apply_task), getString(R.string.text_apply_task_content), new IDialogCallBack() {
                        @Override
                        public void setPositiveButton() {
                            taskApply(taskId,advantage);
                        }

                        @Override
                        public void setNegativeButton() {

                        }
                    });
                }else {
                    Intent intent=new Intent(TaskApplyActivity.this,TaskApplyTwoActivity.class);
                    intent.putExtra("price",taskPrice);
                    intent.putExtra("task_id",taskId);
                    jumpToActivity(intent);
                }

                break;
        }
    }



    public void taskApply(String task_id,String advantage) {
        showDialog();
        String url = ApiUrl.taskApplyUrl;
        TaskApplyModel applyModel = new TaskApplyModel(task_id,advantage);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(applyModel))
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
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
                                ToastUtils.showShort(R.string.text_task_apply_success);
                                BusProvider.getBus().post(new OrderFinishEvent());
                                finish();

                            } else {
                                String info = jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

}
