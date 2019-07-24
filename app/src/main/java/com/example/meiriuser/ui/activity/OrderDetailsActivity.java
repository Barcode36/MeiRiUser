package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.adapter.OrderDetailsAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.DefaultAddressModel;
import com.example.meiriuser.model.OrderQuetyListModel;
import com.example.meiriuser.model.net.OrderNetModel;
import com.example.meiriuser.model.net.OrderPriceModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import rx.functions.Action1;

/**
 * 订单详情
 */

public class OrderDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_details_address)
    TextView tvDetailsAddress;
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
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_contact_infor)
    TextView tvContactInfor;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.tv_remarks_num)
    TextView tvRemarksNum;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.btn_pay)
    Button btnPay;
    OrderDetailsAdapter orderDetailsAdapter;
    List<OrderQuetyListModel.ListBean> orderDetailsModelList;
    List<String> mVals;
    List<String>mValsStr;
    AddressListModel.DataBean addressListModel;
    String token;
    OrderQuetyListModel orderQuetyListModel;
    private String storeID;
    private String addressId;
    private String storeName;
    private String storeAddress;
    private String totalPrice;
    Gson gson;
    private String distribuFee;
    String remarkStr;
    int prome;
    List<OrderPriceModel.OtherInfoBean> infoBeanList;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initView() {
        toolbarTitle.setText(R.string.title_order_details);
        gson=new Gson();
        infoBeanList=new ArrayList<>();
        storeID=getIntent().getExtras().getString("store_id");
        storeName=getIntent().getExtras().getString("storeName");
        storeAddress=getIntent().getExtras().getString("storeAddress");
        totalPrice=getIntent().getExtras().getString("totalPrice");
        distribuFee=getIntent().getExtras().getString("distribuFee");
        orderQuetyListModel = (OrderQuetyListModel) getIntent().getSerializableExtra("list");
        int goodNum=0;
        for(int i=0;i<orderQuetyListModel.getName().size();i++){
            goodNum+=orderQuetyListModel.getName().get(i).getNum();
            infoBeanList.add(new OrderPriceModel.OtherInfoBean(orderQuetyListModel.getName().get(i).getId(),orderQuetyListModel.getName().get(i).getNum()));
        }
        tvTotalCommodity.setText(String.format(getResources().getString(R.string.text_total_comodity),goodNum+""));//商品件数

        tvShopName.setText(storeName);
        tvDetailsAddress.setText(storeAddress);
        rvOrderDetails.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDetails.setNestedScrollingEnabled(false);
        orderDetailsModelList=new ArrayList<>();
        orderDetailsModelList.addAll(orderQuetyListModel.getName());

        mVals=new ArrayList<>();
        mValsStr=new ArrayList<>();
        mVals.add(getString(R.string.text_remark01));
        mVals.add(getString(R.string.text_remark02));
        mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_small_tv_flow,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });


    }

    @Override
    public void initData() {
        orderDetailsAdapter=new OrderDetailsAdapter(orderDetailsModelList);
        rvOrderDetails.setAdapter(orderDetailsAdapter);
        token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        getDefaultAddressList(token);
    }

    @Override
    public void initListener() {
        etRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textChange=charSequence.toString();
                tvRemarksNum.setText(textChange.length()+"/200");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if(TextUtils.isEmpty(etRemarks.getText().toString().trim())){
                    remarkStr=mVals.get(position);
                }else {
                    remarkStr=etRemarks.getText().toString()+" "+mVals.get(position);
                }
                etRemarks.setText(remarkStr);
                etRemarks.setSelection(remarkStr.length());

                return true;
            }
        });


        BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {
                        finish();
                    }
                });
    }


    /**
     获取订单价格
     */
    public void getOrderPrice(int store_id,List<OrderPriceModel.OtherInfoBean> otherInfoBeanList,float longitude,float latitude) {

        showDialog();
        String url = ApiUrl.orderPriceUrl;
        OrderPriceModel orderPriceModel = new OrderPriceModel(store_id, otherInfoBeanList, longitude,latitude);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(orderPriceModel))
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
                                JSONObject data =jsonObject.getJSONObject("data");
                                prome=data.getInt("prome");
                                int price=data.getInt("price");
                                int delivery_money=data.getInt("delivery_money");
                                tvDistribuFee.setText(String.format(getString(R.string.text_price),delivery_money+""));
                                tvFullReductionPrice.setText(String.format(getString(R.string.text_reduct_price),prome+""));
                                tvTotalPrice.setText(price+"");
                                tvOrderPrice.setText(price+"");
                            } else {

                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

    }



    public void orderQuery(final String store_id, String address_id,float prome, String note, List<OrderQuetyListModel.ListBean>list) {

        if (TextUtils.isEmpty(store_id)) {
            return;
        }
        if (TextUtils.isEmpty(address_id)) {
            showShort("收货地址不能为空");
            return;
        }

        showDialog();
        String url = ApiUrl.orderQueryUrl;
        OrderNetModel orderNetModel = new OrderNetModel(Integer.valueOf(store_id), Integer.valueOf(address_id),1,  prome,note,list);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8")) //设置post的字符串为json字符串并设置编码
                .content(new Gson().toJson(orderNetModel)) //用Gson将对象转化为Json字符串的形式作为content
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
                                String orderId=jsonObject.getJSONObject("data").getString("order_id");
                                Intent intent=new Intent(OrderDetailsActivity.this,OnlinePaymentActivity.class);
                                intent.putExtra("netType",1);
                                intent.putExtra("order_id",orderId);
                                jumpToActivity(intent);
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



    @OnClick({R.id.icon_back, R.id.btn_pay, R.id.line_shop_name, R.id.line_select_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_pay:
                orderQuery(storeID,addressId,prome,etRemarks.getText().toString().trim(),orderDetailsModelList);
                break;
            case R.id.line_shop_name:
                Intent intent = new Intent(OrderDetailsActivity.this, FoodActivity.class);
                intent.putExtra(Constant.TYPE,Integer.valueOf(storeID));
                jumpToActivity(intent);
                break;
            case R.id.line_select_address:
                startActivityForResult(new Intent(OrderDetailsActivity.this, ReceivingAddressActivity.class), Constant.ADDRESSRESULT);
                break;
        }
    }


    /**
     * 获取用户默认收货地址
     */
    public void getDefaultAddressList(String token) {

        showDialog();
        String url = ApiUrl.getDefaultAddressUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("token", token)
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
                                DefaultAddressModel bean= (DefaultAddressModel) GsonUtil.JSONToObject(response.toString(),DefaultAddressModel.class);
                                if(bean.getData()!=null){
                                    tvAddress.setText(bean.getData().getAddress());
                                    tvContactInfor.setText(bean.getData().getConsignee()+"    "+bean.getData().getMobile());
                                    addressId=String.valueOf(bean.getData().getAddress_id());
                                    getOrderPrice(Integer.valueOf(storeID),infoBeanList,Float.valueOf(bean.getData().getLat()),Float.valueOf(bean.getData().getLng()));
                                }
                            }else {
                                dissDialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null){
            addressListModel= (AddressListModel.DataBean) data.getExtras().getSerializable(Constant.RESULT);
            tvAddress.setText(addressListModel.getAddress());
            tvContactInfor.setText(addressListModel.getConsignee()+"    "+addressListModel.getMobile());
            addressId=String.valueOf(addressListModel.getAddress_id());
            if(!TextUtils.isEmpty(addressListModel.getLat()) && !TextUtils.isEmpty(addressListModel.getLng())){
                getOrderPrice(Integer.valueOf(storeID),infoBeanList,Float.valueOf(addressListModel.getLat()),Float.valueOf(addressListModel.getLng()));
            }

        }

    }
}
