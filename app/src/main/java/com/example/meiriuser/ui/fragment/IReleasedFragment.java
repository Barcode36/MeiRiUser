package com.example.meiriuser.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.IReleasedAdapter;
import com.example.meiriuser.adapter.TaskAuthAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.IReleasedModel;
import com.example.meiriuser.model.TaskAuthModel;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.model.net.TaskStatusModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.ErrandsActivity;
import com.example.meiriuser.ui.activity.NailHairdresActivity;
import com.example.meiriuser.ui.activity.TakeOutFoodActivity;
import com.example.meiriuser.until.CommanUtils;
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
import okhttp3.Call;
import okhttp3.MediaType;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/28.
 */

public class IReleasedFragment extends BaseFragment{

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    IReleasedAdapter iReleasedAdapter;
    List<TaskListModel.DataBean> iReleasedModelList;
    int pageNo = Constant.PAGENO;

    @Override
    protected int provideContentViewId() {
        return R.layout.recyclerview_layout_refresh;
    }


    @Override
    public void initView(View rootView) {
        iReleasedModelList=new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void initData() {
        iReleasedAdapter=new IReleasedAdapter(iReleasedModelList);
        rvList.setAdapter(iReleasedAdapter);
        getReleasedList(pageNo,Constant.SHOW_DIALOG);
    }


    public void getReleasedList(int pageNo,int flag) {
        if(flag==Constant.SHOW_DIALOG){
            showDialog();
        }
        String url = ApiUrl.applytaskListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("page", String.valueOf(pageNo))
                .addParams("size", Constant.MAXCOUNT)
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
                                    iReleasedModelList.addAll(bean.getData());
                                    iReleasedAdapter.notifyDataSetChanged();
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    public void taskStatus(int task_id, int status) {
        showDialog();
        String url = ApiUrl.taskStatusUrl;
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
                                showShort(getString(R.string.toast_matching_success));
                               /* String orderId=jsonObject.getJSONObject("data").getString("order_id");
                                Intent intent=new Intent(OrderDetailsActivity.this,OnlinePaymentActivity.class);
                                intent.putExtra("netType",1);
                                intent.putExtra("order_id",orderId);
                                jumpToActivity(intent);*/
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
                                iReleasedModelList.get(pos).setStatus(7);
                                iReleasedAdapter.notifyDataSetChanged();
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



    @Override
    public void initListener() {
        setRefresh();
        iReleasedAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                int status=iReleasedModelList.get(position).getStatus();
                int isComment=iReleasedModelList.get(position).getIs_comment();
                int taskId=iReleasedModelList.get(position).getTask_id();
                switch (itemViewId) {
                    case R.id.tv_call:
                        if(iReleasedModelList.get(position).getUser()!=null){
                            /*CommanUtils.callPhone(getContext(),iReleasedModelList.get(position).getUser().get);*/
                        }

                        break;
                    case R.id.btn_state:
                        if(status==5){////已完成待确认（确认支付尾款）
                            taskStatus(taskId,6);
                        }else if(status==6 && isComment==0){//待评价

                        }
                        break;
                    case R.id.btn_cancel_order:
                        taskCancel(taskId,position);
                        break;

                }
            }
        });

        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        iReleasedModelList.clear();
                        iReleasedAdapter.notifyDataSetChanged();
                        getReleasedList(pageNo,Constant.NO_SHOW_DIALOG);
                    }
                });


        BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {
                        iReleasedModelList.clear();
                        iReleasedAdapter.notifyDataSetChanged();
                        getReleasedList(pageNo,0);
                    }
                });


    }



    public void setRefresh() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
              /*  refreshLayout.finishLoadMore(2000);
                pageNo++;
                getTaskList(pageNo);*/
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                iReleasedModelList.clear();
                iReleasedAdapter.notifyDataSetChanged();
                pageNo=Constant.PAGENO;
                getReleasedList(pageNo,Constant.SHOW_DIALOG);
            }
        });


    }
}
