package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.TaskAuthAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.ErrandsOrderListModel;
import com.example.meiriuser.model.TaskAuthModel;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.model.net.ApplyStatusModel;
import com.example.meiriuser.model.net.PickUpDeliveryModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.EditorAddressActivity;
import com.example.meiriuser.ui.activity.ReceivingAddressActivity;
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
 * Created by admin on 2019/5/28.
 */

public class TaskAuthFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    TaskAuthAdapter taskAuthAdapter;
    List<TaskAuthModel.DataBean> taskAuthModelList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int pageNo = Constant.PAGENO;
    @Override
    protected int provideContentViewId() {
        return R.layout.recyclerview_layout_refresh;
    }

    @Override
    public void initView(View rootView) {
        taskAuthModelList = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        taskAuthAdapter = new TaskAuthAdapter(taskAuthModelList);
        rvList.setAdapter(taskAuthAdapter);
        getApplyList(pageNo,Constant.SHOW_DIALOG);
    }


    public void getApplyList(int pageNo,int flag) {
        if(flag==Constant.SHOW_DIALOG){
            showDialog();
        }
        String url = ApiUrl.applyListUrl;
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
                                TaskAuthModel bean = (TaskAuthModel) GsonUtil.JSONToObject(response.toString(), TaskAuthModel.class);
                                if (bean.getData() != null) {
                                    taskAuthModelList.addAll(bean.getData());
                                    taskAuthAdapter.notifyDataSetChanged();
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    public void applyStatus(int task_apply_id, final int status, final int pos) {
        showDialog();
        String url = ApiUrl.applyStatusUrl;
        ApplyStatusModel applyStatusModel = new ApplyStatusModel(task_apply_id, status);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(applyStatusModel))
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
                                showShort(getString(R.string.text_matching_success));//text_task_cancel_success
                                taskAuthModelList.remove(pos);
                                taskAuthAdapter.notifyDataSetChanged();
                                BusProvider.getBus().post(new OrderFinishEvent());
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
                refreshLayout.finishLoadMore(1000);
              /*  pageNo++;
                getApplyList(pageNo);*/
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                taskAuthModelList.clear();
                taskAuthAdapter.notifyDataSetChanged();
                pageNo=Constant.PAGENO;
                getApplyList(pageNo,Constant.SHOW_DIALOG);
            }
        });

    }


    @Override
    public void initData() {

    }


    @Override
    public void initListener() {
        super.initListener();
        taskAuthAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                int task_apply_id=taskAuthModelList.get(position).getTask_apply_id();
                switch (itemViewId) {
                    case R.id.btn_sure://1 匹配 2 拒绝
                        applyStatus(task_apply_id,1,position);
                        break;

                }
            }
        });

        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        taskAuthModelList.clear();
                        taskAuthAdapter.notifyDataSetChanged();
                        getApplyList(pageNo,Constant.NO_SHOW_DIALOG);
                    }
                });

        setRefresh();
    }
}
