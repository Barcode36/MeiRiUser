package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.TaskDetailsIvAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.dialog.IDialogCallBack;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.PayDepositModel;
import com.example.meiriuser.model.TaskDetailsModel;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/24.
 */

public class TaskDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_task_content)
    TextView tvTaskContent;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_publisher_name)
    TextView tvPublisherName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.btn_apply)
    TextView btnApply;
    @BindView(R.id.btn_more)
    TextView btnMore;
    @BindView(R.id.line_more)
    LinearLayout lineMore;
    TaskDetailsModel dataBean;
    @BindView(R.id.tv_say_content)
    TextView tvSayContent;
    List<String> stringList;
    TaskDetailsIvAdapter ivAdapter;
    String taskId;
    String taskPrice;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_task_details;
    }

    @Override
    public void initView() {
        stringList=new ArrayList<>();
        toolbarTitle.setText("任务详情");
        rvImg.setNestedScrollingEnabled(false);
        rvImg.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        taskId = getIntent().getExtras().getInt("task_id")+"";
    }


    @Override
    public void initData() {
        ivAdapter=new TaskDetailsIvAdapter(stringList);
        rvImg.setAdapter(ivAdapter);
        getTaskDetails(taskId);
     /*   BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {
                        finish();
                    }
                });*/
    }


    public void getTaskDetails(String task_id) {
        showDialog();
        String url = ApiUrl.taskDetailUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("task_id",task_id)
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
                                TaskDetailsModel bean = (TaskDetailsModel) GsonUtil.JSONToObject(response.toString(), TaskDetailsModel.class);
                                if (bean.getData() != null) {
                                    TaskDetailsModel.DataBean dataBean=bean.getData();
                                    stringList.addAll(dataBean.getImages());
                                    ivAdapter.notifyDataSetChanged();
                                    TaskDetailsModel.DataBean.UserBean userBean=dataBean.getUser();
                                    if(userBean!=null){
                                        tvPublisherName.setText(userBean.getUsername());
                                        Glide.with(TaskDetailsActivity.this)
                                                .load(userBean.getHeadImg())
                                                .placeholder(R.mipmap.icon_logo_oval)
                                                .centerCrop()
                                                .bitmapTransform(new GlideCircleTransform(TaskDetailsActivity.this))
                                                .into(ivHead);
                                    }
                                    tvTaskContent.setText(dataBean.getTitle());
                                    tvTime.setText(dataBean.getAddtime());
                                    tvAddress.setText(dataBean.getAddress());
                                    tvDate.setText(dataBean.getComplete_date());
                                    tvSayContent.setText(dataBean.getDesc());
                                    tvPrice.setText(String.format(getString(R.string.text_price2),dataBean.getMoney()));
                                    taskPrice=dataBean.getMoney();
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    @OnClick({R.id.icon_back, R.id.btn_apply, R.id.btn_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_apply:
                depositStatus();
                break;
            case R.id.btn_more:
                lineMore.setVisibility(View.VISIBLE);
                break;
        }
    }


    public void depositStatus() {
        showDialog();
        String url = ApiUrl.depositStatusUrl;
        OkHttpUtils
                .get()
                .url(url)
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
                        String string = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                PayDepositModel bean = (PayDepositModel) GsonUtil.JSONToObject(response.toString(), PayDepositModel.class);
                                PreferenceUtil.commitInt(Constant.PF_PREMIUM, bean.getData().getStatus());
                                int status=bean.getData().getStatus();
                                if(status==1 ||status==6){//已缴纳
                                    Intent intent=new Intent(TaskDetailsActivity.this,TaskApplyActivity.class);
                                    intent.putExtra(Constant.TYPE,"one");
                                    intent.putExtra("task_id",taskId);
                                    intent.putExtra("price",taskPrice);
                                    jumpToActivity(intent);
                                }else{
                                   showPayDepositDialog();
                                }
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


    public void showPayDepositDialog(){
        DialogUtil dialogUtil=new DialogUtil();
        dialogUtil.showPayDepositOKCancel(this, getWindowManager(), "", new IDialogCallBack() {
            @Override
            public void setPositiveButton() {
                jumpToActivity(PayDepositActivity.class);

            }

            @Override
            public void setNegativeButton() {

            }
        });

    }



}
