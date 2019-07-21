package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.ErrandsOrderListAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.ErrandsOrderListModel;
import com.example.meiriuser.net.ApiUrl;
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
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by admin on 2019/6/27.
 */

public class ErrandsOrderListActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    ErrandsOrderListAdapter orderAdapter;
    List<ErrandsOrderListModel.DataBeanX.DataBean> orderModelList;
    int pageNo = Constant.PAGENO;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;


    @Override
    protected int provideContentViewId() {
        return R.layout.title_recyclerview_layout_refresh;
    }


    @Override
    public void initView() {
        super.initView();
        orderModelList = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new ErrandsOrderListAdapter(orderModelList);
        toolbarTitle.setText("订单列表");

    }


    @Override
    public void initData() {

        rvList.setAdapter(orderAdapter);
        getOrderList(pageNo);


    }


    public void orderCancel(final String order_id, final int postion) {

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
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                showShort(getString(R.string.text_order_cancel));
                               /* refreshLayout.setEnableRefresh(true)*/
                                orderModelList.remove(postion);
                                orderAdapter.notifyDataSetChanged();
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

    /**
     * 获取订单列表
     */
    public void getOrderList(int pageNo) {
        showDialog();
        String url = ApiUrl.errandsList;
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
                                ErrandsOrderListModel bean = (ErrandsOrderListModel) GsonUtil.JSONToObject(response.toString(), ErrandsOrderListModel.class);
                                if (bean.getData() != null) {
                                    orderModelList.addAll(bean.getData().getData());
                                    orderAdapter.notifyDataSetChanged();
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
    public void initListener() {
        super.initListener();
        orderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        String orderId=orderModelList.get(position).getOrder_id()+"";
                        Intent intent=new Intent(ErrandsOrderListActivity.this,ErrandsOrderDetailActivity.class);
                        intent.putExtra("order_id",orderId);
                        jumpToActivity(intent);
                        break;
                    case R.id.btn_cancel_order:
                        orderCancel(orderModelList.get(position).getOrder_id()+"",position);
                        break;
                    case R.id.btn_contact_riders:

                        break;
                }
            }
        });

        setRefresh();

    }

    public void setRefresh() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                pageNo++;
                getOrderList(pageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                orderModelList.clear();
                orderAdapter.notifyDataSetChanged();
                getOrderList(Constant.PAGENO);
            }
        });


    }



    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
    }
}
