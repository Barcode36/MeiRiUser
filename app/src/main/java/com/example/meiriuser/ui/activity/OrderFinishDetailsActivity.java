package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.OrderFinishDetailsAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderListRefresh;
import com.example.meiriuser.event.OrderPriceEvent;
import com.example.meiriuser.model.OrderDetailsModel;
import com.example.meiriuser.model.OrderModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PayCountDownUtils;
import com.example.meiriuser.until.PreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by admin on 2019/5/23.
 */

public class OrderFinishDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_details_address)
    TextView tvDetailsAddress;
    @BindView(R.id.tv_takeaway_order)
    TextView tvTakeawayOrder;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.rv_order_details)
    RecyclerView rvOrderDetails;
    @BindView(R.id.tv_distribu_fee)
    TextView tvDistribuFee;
    @BindView(R.id.tv_full_reduction_price)
    TextView tvFullReductionPrice;
    @BindView(R.id.tv_total_commodity)
    TextView tvTotalCommodity;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_delivery_state)
    TextView tvDeliveryState;
    @BindView(R.id.btn_cancel_order)
    Button btnCancelOrder;
    @BindView(R.id.tv_contact_infor)
    TextView tvContactInfor;
    @BindView(R.id.tv_delivery_person)
    TextView tvDeliveryPerson;
    @BindView(R.id.tv_contact_delivery)
    TextView tvContactDelivery;
    @BindView(R.id.tv_remaining_time)
    TextView tvRemainingTime;
    @BindView(R.id.btn_contact_merchant)
    LinearLayout btnContactMerchant;
    OrderFinishDetailsAdapter orderDetailsAdapter;
    List<OrderDetailsModel.DataBean.GoodsBean> goodsBeanList;
    @BindView(R.id.line_rider)
    LinearLayout lineRider;
    private PayCountDownUtils countDownUtils;
    String orderState;
    String order_id;
    String contactMerchantPhone="";
    String contactDeliveryPhone="";
    int isComment;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_order_finish_details;
    }


    /**
     * 开启倒数
     */
    public void startCountDown() {
        countDownUtils = new PayCountDownUtils(60000 * 15, 1000, tvRemainingTime);
        countDownUtils.setStartColor(Color.parseColor("#74D1BF"));
        countDownUtils.setEndColor(Color.parseColor("#74D1BF"));
        countDownUtils.setStartTxt("剩余送达时间:");
        countDownUtils.setEndTxt("支付超时");
        countDownUtils.start();
    }


    @Override
    public void initView() {
        orderState = getIntent().getExtras().getString(Constant.ORDERSTATE);

        if (orderState.equals("已完成")) {
            tvDeliveryState.setText("已完成");
        } else {
            startCountDown();
        }

        order_id = getIntent().getExtras().getString("order_id");
        toolbarTitle.setText(getString(R.string.title_order_details));
        rvOrderDetails.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDetails.setNestedScrollingEnabled(false);
        goodsBeanList = new ArrayList<>();

    }


    @Override
    public void initData() {
        orderDetailsAdapter = new OrderFinishDetailsAdapter(goodsBeanList);
        rvOrderDetails.setAdapter(orderDetailsAdapter);
        getOrderDetails(order_id);
    }

    public void orderCancel(final String order_id) {

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
                                finish();
                                BusProvider.getBus().post(new OrderListRefresh());
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
     * 订单详情
     */
    public void getOrderDetails(final String order_id) {

        showDialog();
        String url = ApiUrl.orderDetailsUrl;
        OkHttpUtils
                .get()
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
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                OrderDetailsModel bean = (OrderDetailsModel) GsonUtil.JSONToObject(response.toString(), OrderDetailsModel.class);
                                if (bean.getResult() == HttpStatus.SUCCESS) {
                                    OrderDetailsModel.DataBean dataBean = bean.getData();
                                    if (dataBean != null) {
                                        tvTakeawayOrder.setText(String.format(getString(R.string.text_takeaway_order_num), dataBean.getOrder_sn()));
                                        tvDistribuFee.setText(String.format(getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(dataBean.getShipping_fee())) + ""));
                                        tvFullReductionPrice.setText(String.format(getString(R.string.text_reduct_price), String.valueOf(dataBean.getDiscount())));
                                        tvTotalPrice.setText(String.format(getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(dataBean.getOrder_amount()))) + "");
                                        goodsBeanList.addAll(dataBean.getGoods());
                                        orderDetailsAdapter.notifyDataSetChanged();
                                        tvShopName.setText(dataBean.getStore().getStore_name());
                                        tvDetailsAddress.setText(dataBean.getStore().getAddress());
                                        contactMerchantPhone=dataBean.getStore().getTel();
                                        isComment=dataBean.getIs_comment();
                                        btnCancelOrder.setText(isComment==0?"立即评价":"已评价");
                                        OrderDetailsModel.DataBean.RiderBean riderBean=dataBean.getRider();
                                        if(riderBean.getId()!=0){
                                            tvDeliveryState.setText("配送中");
                                            lineRider.setVisibility(View.VISIBLE);
                                            tvContactInfor.setText("联系人:  " + dataBean.getConsignee() + "  " + dataBean.getMobile());
                                            tvDeliveryPerson.setText("配送员:  " + riderBean.getUsername());
                                            tvRemainingTime.setText("剩余送达时间:  " + "13分钟");
                                            contactDeliveryPhone=riderBean.getTel();
                                        }else {
                                            tvDeliveryState.setText("");
                                            lineRider.setVisibility(View.GONE);
                                        }
                                        List<OrderDetailsModel.DataBean.GoodsBean> goodsBean=dataBean.getGoods();
                                        int goodNum=0;
                                        for(int i=0;i<goodsBean.size();i++){
                                            goodNum+=goodsBean.get(i).getGoods_number();
                                        }
                                       tvTotalCommodity.setText(String.format(getResources().getString(R.string.text_total_comodity),goodNum+""));//商品件数
                                        CommanUtils.getDistanceTime(dataBean.getAdd_time(),dataBean.getTimestamp());
                                    }

                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    @Override
    public void initListener() {
        super.initListener();
    }


    @OnClick({R.id.icon_back, R.id.btn_cancel_order, R.id.btn_contact_merchant, R.id.tv_contact_delivery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_cancel_order:
                if (orderState.equals("已完成")) {
                    if(isComment==0){
                        Intent intent=new Intent(OrderFinishDetailsActivity.this,EvaluateOrderActivity.class);
                        intent.putExtra(Constant.TYPE, "food");
                        intent.putExtra("order_id",order_id);
                        jumpToActivity(intent);
                    }

                } else {
                    //取消订单
                    orderCancel(order_id);
                }
                break;
            case R.id.btn_contact_merchant:
                CommanUtils.callPhone(this, contactMerchantPhone);
                break;
            case R.id.tv_contact_delivery:
                CommanUtils.callPhone(this, contactDeliveryPhone);
                break;
        }
    }


}
