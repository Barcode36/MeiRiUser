package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.TaskListAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.ErrandsOrderListModel;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.ErrandsActivity;
import com.example.meiriuser.ui.activity.NailHairdresActivity;
import com.example.meiriuser.ui.activity.ScreenActivity;
import com.example.meiriuser.ui.activity.SelectDelegationAddressActivity;
import com.example.meiriuser.ui.activity.TakeOutFoodActivity;
import com.example.meiriuser.ui.activity.TaskDetailsActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/23.
 */

public class TaskListFragment extends BaseFragment {

    TaskListAdapter taskListAdapter;
    List<TaskListModel.DataBean> taskListModelList;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int pageNo = Constant.PAGENO;
    AddressModel changeaddressModel;
    String priceTask="";
    String distanceTask="";
    String longitudeTask="";
    String latitudeTask="";

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_task_list;
    }


    @Override
    public void initView(View rootView) {
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        taskListModelList = new ArrayList<>();
    }


    public void getTaskList(int pageNo,int flag,String price,String distance,String longitude, String latitude) {
        if(flag==Constant.SHOW_DIALOG){
            showDialog();
        }
        String url = ApiUrl.taskListUrl;
        Map<String,String> param=new HashMap<>();
        param.put("page", String.valueOf(pageNo));
        param.put("size", Constant.MAXCOUNT);
        param.put("price", price);
        param.put("distance", distance);
        param.put("longitude", longitude);
        param.put("latitude", latitude);
        param.put("type", 1+"");

        OkHttpUtils
                .get()
                .url(url)
                .params(param)
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
                                    taskListModelList.clear();
                                    taskListModelList.addAll(bean.getData());
                                    taskListAdapter.notifyDataSetChanged();
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }



    @Override
    public void initData() {
        taskListAdapter = new TaskListAdapter(taskListModelList);
        rvList.setAdapter(taskListAdapter);
        getTaskList(pageNo,Constant.SHOW_DIALOG,priceTask,distanceTask,longitudeTask,latitudeTask);
    }


    @Override
    public void initListener() {
        setRefresh();
        taskListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        Intent intent=new Intent(getActivity(), TaskDetailsActivity.class);
                        intent.putExtra("task_id",taskListModelList.get(position).getTask_id());
                        jumpToActivity(intent);
                        break;

                }
            }
        });

        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        taskListModelList.clear();
                        taskListAdapter.notifyDataSetChanged();
                        getTaskList(pageNo,Constant.NO_SHOW_DIALOG,priceTask,distanceTask,longitudeTask,latitudeTask);
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
                taskListModelList.clear();
                taskListAdapter.notifyDataSetChanged();
                pageNo=Constant.PAGENO;
                getTaskList(pageNo,Constant.SHOW_DIALOG,priceTask,distanceTask,longitudeTask,latitudeTask);
            }
        });


    }




    @OnClick({R.id.iv_setting, R.id.iv_search, R.id.tv_distance, R.id.tv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                break;
            case R.id.iv_search:
                break;
            case R.id.tv_distance:
                startActivityForResult(new Intent(getActivity(), ScreenActivity.class), 1);
                break;
            case R.id.tv_content:
                startActivityForResult(new Intent(getActivity(), ScreenActivity.class), 1);
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            if (data != null) {
                changeaddressModel = (AddressModel) data.getExtras().getSerializable("address");
                distanceTask=data.getExtras().getInt("distance")+"";
                priceTask=data.getExtras().getString("price_tasks");
                if(changeaddressModel!=null){
                    longitudeTask=changeaddressModel.getLongitude()+"";
                    latitudeTask=changeaddressModel.getLatitude()+"";
                }
                tvDistance.setText(distanceTask+"km内");
                tvContent.setText(priceTask);
                getTaskList(pageNo,Constant.SHOW_DIALOG,priceTask,distanceTask,longitudeTask,latitudeTask);
            }
        }

    }
}
