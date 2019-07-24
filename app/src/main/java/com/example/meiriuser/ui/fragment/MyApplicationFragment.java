package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.MyApplicationAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.MyApplicationModel;
import com.example.meiriuser.model.OrderQuetyListModel;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.model.net.OrderNetModel;
import com.example.meiriuser.model.net.TaskStatusModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.OnlinePaymentActivity;
import com.example.meiriuser.ui.activity.OrderDetailsActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.MediaType;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/23.
 */

public class MyApplicationFragment extends BaseFragment {

    MyApplicationAdapter myApplicationAdapter;
    List<TaskListModel.DataBean> myApplicationModels;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int pageNo = Constant.PAGENO;


    @Override
    protected int provideContentViewId() {
        return R.layout.recyclerview_layout_refresh;
    }


    @Override
    public void initView(View rootView) {
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        myApplicationModels = new ArrayList<>();
    }


    public void getTaskList(int pageNo,int flag) {
        if(flag==Constant.SHOW_DIALOG){
            showDialog();
        }
        String url = ApiUrl.taskListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("page", String.valueOf(pageNo))
                .addParams("size", Constant.MAXCOUNT)
                .addParams("type", 2+"")
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
                                TaskListModel bean = (TaskListModel) GsonUtil.JSONToObject(response.toString(), TaskListModel.class);
                                if (bean.getData() != null) {
                                    myApplicationModels.addAll(bean.getData());
                                    myApplicationAdapter.notifyDataSetChanged();
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }




    public void taskMutualStatus(int task_id, int status) {
        showDialog();
        String url = ApiUrl.taskMutualStatusUrl;
        TaskStatusModel taskStatusModel = new TaskStatusModel(task_id, status);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8")) //设置post的字符串为json字符串并设置编码
                .content(new Gson().toJson(taskStatusModel)) //用Gson将对象转化为Json字符串的形式作为content
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        showShort(e.getMessage());
                        dissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dissDialog();
                        String string = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int result=jsonObject.getInt("result");
                            if(result==1){
                                showShort(getString(R.string.text_confir_success));
                            }else {
                                String info= jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }


    @Override
    public void initData() {
        myApplicationAdapter = new MyApplicationAdapter(myApplicationModels);
        rvList.setAdapter(myApplicationAdapter);
        getTaskList(pageNo,Constant.SHOW_DIALOG);
    }


    @Override
    public void initListener() {
        super.initListener();
        BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {

                        getTaskList(pageNo,Constant.NO_SHOW_DIALOG);
                    }
                });

        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        myApplicationModels.clear();
                        myApplicationAdapter.notifyDataSetChanged();
                        getTaskList(pageNo,Constant.NO_SHOW_DIALOG);
                    }
                });


        myApplicationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                int status=myApplicationModels.get(position).getReceipt_status();
                int taskId=myApplicationModels.get(position).getTask_id();
                switch (itemViewId) {
                    case R.id.btn_finish:
                        if(status==1){
                            taskMutualStatus(taskId,3);
                        }else {
                            taskCancel(taskId,position);
                        }

                        break;

                }
            }
        });

        setRefresh();
    }


    public void taskCancel(int task_id, final int pos) {
        showDialog();
        String url = ApiUrl.taskCancelUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("task_id", String.valueOf(task_id))
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
                                showShort(getString(R.string.text_task_cancel_success));
                                myApplicationModels.remove(pos);
                                myApplicationAdapter.notifyDataSetChanged();
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


    public void setRefresh() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                pageNo++;
                getTaskList(pageNo,1);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                myApplicationModels.clear();
                myApplicationAdapter.notifyDataSetChanged();
                pageNo=Constant.PAGENO;
                getTaskList(pageNo,1);
            }
        });


    }
}
