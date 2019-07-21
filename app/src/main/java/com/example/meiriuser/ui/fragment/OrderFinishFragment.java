package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.OrderAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.MainRefreshEvent;
import com.example.meiriuser.event.OrderListRefresh;
import com.example.meiriuser.event.OrderPriceEvent;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.model.OrderModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.EvaluateOrderActivity;
import com.example.meiriuser.ui.activity.FoodActivity;
import com.example.meiriuser.ui.activity.OrderDetailsActivity;
import com.example.meiriuser.ui.activity.OrderFinishDetailsActivity;
import com.example.meiriuser.ui.activity.SeeCouponCodeActivity;
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
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/22.
 */

public class OrderFinishFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    OrderAdapter orderAdapter;
    List<OrderModel.DataBeanX.DataBean> orderModelList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int pageNo = Constant.PAGENO;


    @Override
    protected int provideContentViewId() {
        return R.layout.recyclerview_layout_refresh;
    }


    @Override
    public void initView(View rootView) {
        orderModelList = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initData() {
        orderAdapter = new OrderAdapter(getContext(), orderModelList, "已完成", new OrderAdapter.onItemOrderClick() {
            @Override
            public void onSeeBusClick(int storeID) {
                Intent intent = new Intent(getActivity(), FoodActivity.class);
                intent.putExtra(Constant.TYPE,storeID);
                jumpToActivity(intent);
            }

            @Override
            public void onSeeOrderClick(String order_id,String payStatus,String orderStatus,String shipping) {//查看订单详情
                Intent intent = new Intent(getActivity(), OrderFinishDetailsActivity.class);
                intent.putExtra(Constant.ORDERSTATE, "已完成");
                intent.putExtra("order_id", order_id);
                jumpToActivity(intent);
            }

            @Override
            public void onEvaluateOrderClick(String type,String order_id) {
                Intent intent = new Intent(getActivity(), EvaluateOrderActivity.class);
                intent.putExtra("order_id",order_id);
                intent.putExtra(Constant.TYPE, type);
                jumpToActivity(intent);
            }


            @Override
            public void onCancelOrderClick(String order_id,int postion) {
                orderCancel(order_id,postion);
            }

            @Override
            public void onCouponCodeClick(int postion) {//查看劵码
                Intent intent = new Intent(getActivity(), SeeCouponCodeActivity.class);
                intent.putExtra(Constant.DATEBEAN,orderModelList.get(postion));
                intent.putExtra(Constant.ORDERSTATE, "已完成");
                jumpToActivity(intent);

            }
        });
        rvList.setAdapter(orderAdapter);

        getOrderList(pageNo,Constant.SHOW_DIALOG);
    }


    public void orderCancel(final String order_id,final int postion) {

        showDialog();
        String url = ApiUrl.orderCancelUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("order_id", order_id)
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
                            int result=jsonObject.getInt("result");
                            if(result==1){
                                showShort(getString(R.string.text_order_cancel));
                                orderModelList.remove(postion);
                                orderAdapter.notifyDataSetChanged();
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

    /**
     * 获取订单列表
     */
    public void getOrderList(int pageNo,int flag) {
        if(flag==Constant.SHOW_DIALOG){
            showDialog();
        }

        String url = ApiUrl.orderListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("status", Constant.ORDER_FINISH)
                .addParams("page", String.valueOf(pageNo))
                .addParams("size", Constant.MAXCOUNT)
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
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                OrderModel bean = (OrderModel) GsonUtil.JSONToObject(response.toString(), OrderModel.class);
                                if (bean.getData().getData() != null) {
                                    orderModelList.addAll(bean.getData().getData());
                                    orderAdapter.notifyDataSetChanged();
                                }
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
                getOrderList(pageNo,Constant.SHOW_DIALOG);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                orderModelList.clear();
                orderAdapter.notifyDataSetChanged();
                getOrderList(Constant.PAGENO,Constant.SHOW_DIALOG);
            }
        });
    }


    @Override
    public void initListener() {
        super.initListener();
        setRefresh();
        BusProvider.getBus().toObservable(OrderListRefresh.class)
                .subscribe(new Action1<OrderListRefresh>() {
                    @Override
                    public void call(OrderListRefresh newMessageEvent) {
                        refreshLayout.setEnableRefresh(true);
                    }
                });

        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        orderModelList.clear();
                        getOrderList(Constant.PAGENO,Constant.NO_SHOW_DIALOG);
                    }
                });

    }
}


