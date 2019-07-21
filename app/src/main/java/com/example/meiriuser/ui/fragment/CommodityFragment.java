package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.FoodClassContentAdapter;
import com.example.meiriuser.adapter.FoodClassTitleAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderPriceEvent;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.model.OrderQuetyListModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.FoodDetailsActivity;
import com.example.meiriuser.ui.activity.LoginActivity;
import com.example.meiriuser.ui.activity.OrderDetailsActivity;
import com.example.meiriuser.ui.activity.SelectDelegationAddressActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 * 商品
 */

public class CommodityFragment extends BaseFragment {
    @BindView(R.id.rv_class_title)
    RecyclerView rvClassTitle;
    @BindView(R.id.rv_class_content)
    RecyclerView rvClassContent;
    @BindView(R.id.tv_shop_cart_num)
    TextView tvShopCartNum;
    @BindView(R.id.tv_click_price)
    TextView tvClickPrice;
    @BindView(R.id.iv_shop_cart)
    ImageView ivShopCart;
    FoodClassTitleAdapter titleAdapter;
    FoodClassContentAdapter contentAdapter;
    List<FoodDetailsModel.DataBean> foodDetailsModels;
    List<FoodDetailsModel.DataBean.GoodsBean> dataBeans;
    int parentPos = 0;
    double totalPrice=0;
    private String storeID;
    private String storeName;
    private String storeAddress;
    private int foodDetailsPos=0;
    OrderQuetyListModel orderQuetyListModelList;
    List<OrderQuetyListModel.ListBean> listBeans;
    private String distribuFee;

    public void setCommodityInfo(String storeID,String storeName,String storeAddress,String distribuFee){
        this.storeID=storeID;
        this.storeName=storeName;
        this.storeAddress=storeAddress;
        this.distribuFee=distribuFee;
        this.distribuFee=distribuFee;
        getGoodsList(storeID);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_commodity;
    }


    @Override
    public void initView(View rootView) {

        rvClassTitle.setLayoutManager(new LinearLayoutManager(getContext()));
        rvClassContent.setLayoutManager(new LinearLayoutManager(getContext()));
        foodDetailsModels = new ArrayList<>();
        dataBeans = new ArrayList<>();
        listBeans=new ArrayList<>();
    }

    @Override
    public void initData() {
        titleAdapter = new FoodClassTitleAdapter(foodDetailsModels);
        contentAdapter = new FoodClassContentAdapter(dataBeans);
        rvClassTitle.setAdapter(titleAdapter);
        rvClassContent.setAdapter(contentAdapter);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==100){
            double backPrice=data.getExtras().getDouble("totalPrice");
            int count=data.getExtras().getInt("count");
            dataBeans.get(foodDetailsPos).setClick_count(count);
            totalPrice=totalPrice+backPrice;
            tvClickPrice.setText((totalPrice)>0.0?"共"+(totalPrice)+"元":"0元起送");
            tvClickPrice.setBackground(totalPrice>0.0?getResources().getDrawable(R.drawable.shape_cart_right_green_bg):getResources().getDrawable(R.drawable.shape_cart_right_gray_bg));
            contentAdapter.notifyDataSetChanged();
        }


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
                                    foodDetailsModels.addAll(bean.getData());
                                    titleAdapter.notifyDataSetChanged();
                                    dataBeans.addAll(foodDetailsModels.get(0).getGoods());
                                    contentAdapter.notifyDataSetChanged();
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
        titleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.tv_class_title:
                        parentPos = position;
                        titleAdapter.changeSelected(position);
                        dataBeans.clear();
                        dataBeans.addAll(foodDetailsModels.get(position).getGoods());
                        contentAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        contentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                int count = dataBeans.get(position).getClick_count();
                double price=Double.valueOf(dataBeans.get(position).getShop_price());
                int id=dataBeans.get(position).getGoods_id();
                switch (itemViewId) {
                    case R.id.line_item:
                        Intent intent=new Intent(getActivity(),FoodDetailsActivity.class);
                        intent.putExtra(Constant.DATEBEAN,dataBeans.get(position));
                        startActivityForResult(intent, 100);
                        foodDetailsPos=position;
                        break;
                    case R.id.line_reduce:
                        if (count > 0) {
                            count--;
                            dataBeans.get(position).setClick_count(count);
                            contentAdapter.notifyDataSetChanged();
                            totalPrice=totalPrice-price;
                            tvClickPrice.setText(totalPrice>0.0?"共"+totalPrice+"元":"0元起送");
                            tvClickPrice.setBackground(totalPrice>0.0?getResources().getDrawable(R.drawable.shape_cart_right_green_bg):getResources().getDrawable(R.drawable.shape_cart_right_gray_bg));
                            tvShopCartNum.setText(totalPrice>0.0?String.format(getString(R.string.text_shop_cart_num),count+""):String.format(getString(R.string.text_empty_shopping)));
                        }
                        break;

                    case R.id.line_add:
                        count++;
                        dataBeans.get(position).setClick_count(count);
                        contentAdapter.notifyDataSetChanged();
                        totalPrice=totalPrice+price;
                        tvClickPrice.setText(totalPrice>0.0?"共"+totalPrice+"元":"0元起送");
                        tvClickPrice.setBackground(totalPrice>0.0?getResources().getDrawable(R.drawable.shape_cart_right_green_bg):getResources().getDrawable(R.drawable.shape_cart_right_gray_bg));
                        tvShopCartNum.setText(totalPrice>0.0?String.format(getString(R.string.text_shop_cart_num),count+""):String.format(getString(R.string.text_empty_shopping)));
                        break;

                }
            }
        });
    }






    @OnClick(R.id.tv_click_price)
    public void onViewClicked() {
        String token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        if(TextUtils.isEmpty(token)){
            jumpToActivity(LoginActivity.class);
        }else {
            List<OrderQuetyListModel.ListBean>OrderQuetyListModels=new ArrayList<>();
            for(int i=0;i<foodDetailsModels.size();i++){
                List<FoodDetailsModel.DataBean.GoodsBean> dataBeanList=foodDetailsModels.get(i).getGoods();
                for(int j=0;j<dataBeanList.size();j++){
                    if(dataBeanList.get(j).getClick_count()>0){
                        OrderQuetyListModel.ListBean orderQuetyListModel=new OrderQuetyListModel.ListBean();
                        orderQuetyListModel.setId(dataBeanList.get(j).getGoods_id());
                        orderQuetyListModel.setNum(dataBeanList.get(j).getClick_count());
                        orderQuetyListModel.setFoodName(dataBeanList.get(j).getGoods_name());
                        orderQuetyListModel.setFoodUrl(dataBeanList.get(j).getGoods_thumb());
                        orderQuetyListModel.setPrice(dataBeanList.get(j).getShop_price());
                      /*  orderQuetyListModel.setDistribuFee(dataBeanList.get(j).getD());
                        orderQuetyListModel.setFullReduction(dataBeanList.get(j).getGoods_thumb());*/
                        OrderQuetyListModels.add(orderQuetyListModel);
                    }
                }
            }
            OrderQuetyListModel model=new OrderQuetyListModel();
            model.setName(OrderQuetyListModels);
            Intent intent=new Intent(getActivity(),OrderDetailsActivity.class);
            intent.putExtra("store_id",storeID);
            intent.putExtra("storeName",storeName);
            intent.putExtra("storeAddress",storeAddress);
            intent.putExtra("totalPrice",totalPrice+"");
            intent.putExtra("distribuFee",distribuFee);
            intent.putExtra("list", (Serializable) model);
            jumpToActivity(intent);
        }

    }
}
