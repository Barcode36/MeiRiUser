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
import com.example.meiriuser.adapter.GroupBuyingServiceAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.model.GroupBuyingServiceModel;
import com.example.meiriuser.model.OrderQuetyListModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.FoodDetailsActivity;
import com.example.meiriuser.ui.activity.GroupBuyingDetailsActivity;
import com.example.meiriuser.ui.activity.OrderDetailsActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/22.
 */

public class GroupBuyingServiceFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    GroupBuyingServiceAdapter serviceAdapter;
  /*  List<GroupBuyingServiceModel> serviceModels;*/
    List<FoodDetailsModel.DataBean.GoodsBean> dataBeans;
    private String storeID;
    private String storeName;
    private String storeAddress;
    private String distribuFee;

    public void setCommodityInfo(String storeID,String storeName,String storeAddress,String distribuFee){
        this.storeID=storeID;
        this.storeName=storeName;
        this.storeAddress=storeAddress;
        this.distribuFee=distribuFee;
        getGoodsList(storeID);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.recyclerview_layout;
    }


    @Override
    public void initView(View rootView) {
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    @Override
    public void initData() {
        dataBeans=new ArrayList<>();
        serviceAdapter=new GroupBuyingServiceAdapter(dataBeans);
        rvList.setAdapter(serviceAdapter);
    }



    /**
     * 查询商品列表
     */
    public void getGoodsList(String storeID) {

        showDialog();
        String url = ApiUrl.goodsListUrl+ "?storeID=" + storeID;
        OkHttpUtils
                .post()
                .url(url)
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
                            int result=jsonObject.getInt("result");
                            if(result==1){
                                FoodDetailsModel bean= (FoodDetailsModel) GsonUtil.JSONToObject(response.toString(),FoodDetailsModel.class);
                                if(bean.getData()!=null){
                                    for(int i=0;i<bean.getData().size();i++){
                                        List<FoodDetailsModel.DataBean.GoodsBean> goodsBeans=bean.getData().get(i).getGoods();
                                        if(dataBeans!=null){
                                            dataBeans.addAll(goodsBeans);
                                        }
                                    }
                                    if(serviceAdapter!=null){
                                        serviceAdapter.notifyDataSetChanged();
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }



    @Override
    public void initListener() {
        serviceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        Intent intent1=new Intent(getActivity(),GroupBuyingDetailsActivity.class);
                        intent1.putExtra(Constant.DATEBEAN,dataBeans.get(position));
                        jumpToActivity(intent1);
                        break;
                    case R.id.btn_buy:
                        List<OrderQuetyListModel.ListBean>OrderQuetyListModels=new ArrayList<>();
                        OrderQuetyListModel.ListBean orderQuetyListModel=new OrderQuetyListModel.ListBean();
                        orderQuetyListModel.setId(dataBeans.get(position).getGoods_id());
                        orderQuetyListModel.setNum(1);
                        orderQuetyListModel.setFoodName(dataBeans.get(position).getGoods_name());
                        orderQuetyListModel.setFoodUrl(dataBeans.get(position).getGoods_thumb());
                        orderQuetyListModel.setPrice(dataBeans.get(position).getShop_price());
                        OrderQuetyListModels.add(orderQuetyListModel);
                        OrderQuetyListModel model=new OrderQuetyListModel();
                        model.setName(OrderQuetyListModels);
                        Intent intent=new Intent(getActivity(),OrderDetailsActivity.class);
                        intent.putExtra("store_id",storeID);
                        intent.putExtra("storeName",storeName);
                        intent.putExtra("storeAddress",storeAddress);
                        intent.putExtra("distribuFee",distribuFee);
                        intent.putExtra("totalPrice",dataBeans.get(position).getShop_price());
                        intent.putExtra("list", (Serializable) model);
                        jumpToActivity(intent);

                        break;



                }
            }
        });
    }
}
