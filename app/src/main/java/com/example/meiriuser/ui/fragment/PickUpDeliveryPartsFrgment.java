package com.example.meiriuser.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.DefaultAddressModel;
import com.example.meiriuser.model.net.PickUpDeliveryModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.ErrandsActivity;
import com.example.meiriuser.ui.activity.ErrandsOrderDetailActivity;
import com.example.meiriuser.ui.activity.LoginActivity;
import com.example.meiriuser.ui.activity.ReceivingAddressActivity;
import com.example.meiriuser.ui.activity.SearchAddressActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONArray;
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
 * Created by admin on 2019/5/14.
 */

public class PickUpDeliveryPartsFrgment extends BaseFragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_name)
    TextView tvAddress;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_commodity_require)
    EditText etCommodityRequire;
    @BindView(R.id.require_flow)
    TagFlowLayout requireFlow;
    @BindView(R.id.et_contacts)
    EditText etContacts;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.line_delivery_parts)
    LinearLayout lineDeliveryParts;
    @BindView(R.id.rb_desig_address)
    RadioButton rbDesigAddress;
    @BindView(R.id.rb_purchase_nearby)
    RadioButton rbPurchaseNearby;
    @BindView(R.id.rg_address)
    RadioGroup rgAddress;
    @BindView(R.id.tv_select_address)
    TextView tvSelectAddress;
    @BindView(R.id.tv_running_expenses)
    TextView tvRunningExpenses;
    @BindView(R.id.relative_gratuity)
    RelativeLayout relativeGratuity;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.btn_place_order)
    Button btnPlaceOrder;
    List<String> mVals;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    AddressListModel.DataBean addressListModel;
    String token;
    @BindView(R.id.line_buy_address)
    LinearLayout lineBuyAddress;
    PickUpDeliveryModel.OtherInfoBean otherInfoBean;
    int addressId = 0;
    int shippingId = 4;//取送件
    boolean isContact = false;
    float gratuity;
    boolean isBill = false;
    @BindView(R.id.et_fee)
    EditText etFee;
    String commodityRequireStr;
    ErrandsActivity mActivity;
    @BindView(R.id.tv_sending_address)
    TextView tvSendingAddress;
    @BindView(R.id.tv_receipt_address)
    TextView tvReceiptAddress;
    String display_address="";

    //定位
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public static final int UPDATE_INTERVAL = 5000;
    public static final int FASTEST_UPDATE_INTNERVAL = 2000;
    private LocationRequest locationRequest;
    String sel_address_str_sending;
    double sel_address_latitude_sending;
    double sel_address_longitude_sending;
    String sel_address_str_receipt;
    double sel_address_latitude_receipt;
    double sel_address_longitude_receipt;



    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_buying_on_behalf;
    }

    @Override
    public void initView(View rootView) {
        tvTips.setText(R.string.text_input_food_infor);
        lineBuyAddress.setVisibility(View.GONE);
        otherInfoBean = new PickUpDeliveryModel.OtherInfoBean();
        tvTotalPrice.setText(String.format(getString(R.string.text_price), 8 + ""));
        etCommodityRequire.setHint(R.string.hint_commodity_require);
        lineDeliveryParts.setVisibility(View.VISIBLE);
        mVals = new ArrayList<>();
        mVals.add(getString(R.string.text_drinks18));
        mVals.add(getString(R.string.text_drinks19));
        requireFlow.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_selectgreen_flow,
                        requireFlow, false);
                tv.setText(s);
                return tv;
            }
        });
        mActivity = (ErrandsActivity) getActivity();

        //定位
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_UPDATE_INTNERVAL);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    public void initData() {
        super.initData();
        mGoogleApiClient.connect();
        token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        getDefaultAddressList(token);
    }

    @Override
    public void initListener() {
        super.initListener();
        requireFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                if (mVals.get(position).equals(getString(R.string.text_drinks18))) {
                    isContact = true;
                } else if (mVals.get(position).equals(getString(R.string.text_drinks19))) {
                    isBill = true;
                }

                if (TextUtils.isEmpty(etCommodityRequire.getText().toString().trim())) {
                    commodityRequireStr = mVals.get(position);
                } else {
                    commodityRequireStr = etCommodityRequire.getText().toString() + " " + mVals.get(position);
                }
                etCommodityRequire.setText(commodityRequireStr);
                etCommodityRequire.setSelection(commodityRequireStr.length());
                return true;
            }
        });

        etFee.addTextChangedListener(new PriceTextChange());

        BusProvider.getBus().toObservable(AddressSaveChangeEvent.class)
                .subscribe(new Action1<AddressSaveChangeEvent>() {
                    @Override
                    public void call(AddressSaveChangeEvent newMessageEvent) {
                        if(newMessageEvent.getTag()==AddressSaveChangeEvent.DISPLAY_ADDRESS){
                            AddressModel addressModel=newMessageEvent.getTip();
                            stopLocationUpdates();
                            if(display_address.equals("sending")){
                                sel_address_str_sending=addressModel.getAddress();
                                sel_address_latitude_sending=addressModel.getLatitude();
                                sel_address_longitude_sending=addressModel.getLongitude();
                                tvSendingAddress.setText(sel_address_str_sending);

                            }else  if(display_address.equals("receipt")){
                                sel_address_str_receipt=addressModel.getAddress();
                                sel_address_latitude_receipt=addressModel.getLatitude();
                                sel_address_longitude_receipt=addressModel.getLongitude();
                                tvReceiptAddress.setText(sel_address_str_receipt);
                            }
                        }

                    }
                });

    }

    @OnClick({R.id.line_delivery_parts,R.id.relative_commodity_fee, R.id.tv_running_expenses,
            R.id.relative_gratuity, R.id.btn_place_order, R.id.line_select_address,R.id.tv_sending_address,R.id.tv_receipt_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_delivery_parts:
                break;
            case R.id.relative_commodity_fee:
                break;
            case R.id.tv_running_expenses:
                break;
            case R.id.relative_gratuity:
                break;
            case R.id.tv_sending_address://发件地址
                display_address="sending";
                Intent intent=new Intent(getContext(),SearchAddressActivity.class);
                intent.putExtra(Constant.TYPE,"receiving");
                jumpToActivity(intent);
                break;
            case R.id.tv_receipt_address://收件地址
                display_address="receipt";
                Intent intent2=new Intent(getContext(),SearchAddressActivity.class);
                intent2.putExtra(Constant.TYPE,"receiving");
                jumpToActivity(intent2);
                break;
            case R.id.btn_place_order:
                String token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
                if (TextUtils.isEmpty(token)) {
                    jumpToActivity(LoginActivity.class);
                } else {
                    String contacts = etContacts.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String info = etCommodityRequire.getText().toString().trim();
                    if (TextUtils.isEmpty(contacts)) {
                        showShort(getString(R.string.toast_input_contacts_person));
                        return;
                    }
                    if (TextUtils.isEmpty(phone)) {
                        showShort(getString(R.string.toast_input_contacts_method));
                        return;
                    }
                    if (TextUtils.isEmpty(info)) {
                        showShort(getString(R.string.toast_input_commodity_info));
                        return;
                    }
                    List<String> stringList = new ArrayList<>();
                    stringList.add(info);
                    otherInfoBean.setContact_name(contacts);
                    otherInfoBean.setContact_tel(phone);
                    otherInfoBean.setBuy_list(stringList);
                    String fee = etFee.getText().toString().trim();
                    gratuity = Float.valueOf(TextUtils.isEmpty(fee) ? "0" : fee);
                    orderQuery(addressId, shippingId, isContact, isBill, gratuity);
                }
                break;
            case R.id.line_select_address:
                startActivityForResult(new Intent(getActivity(), ReceivingAddressActivity.class), Constant.ADDRESSRESULT);
                break;
        }
    }


    public void orderQuery(int address_id, int shipping_id, boolean is_contact, boolean is_bill, float gratuity) {

        if (TextUtils.isEmpty(address_id + "")) {
            showShort(getString(R.string.toast_input_the_address));
            return;
        }
        showDialog();
        String url = ApiUrl.errandsCreateUrl;
        PickUpDeliveryModel buyingOrderModel = new PickUpDeliveryModel(address_id, shipping_id, is_contact, is_bill, gratuity, otherInfoBean);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(buyingOrderModel))
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
                                String orderId=jsonObject.getJSONObject("data").getString("order_id");
                                Intent intent=new Intent(getActivity(),ErrandsOrderDetailActivity.class);
                                intent.putExtra("order_id",orderId);
                                jumpToActivity(intent);

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
                                DefaultAddressModel bean = (DefaultAddressModel) GsonUtil.JSONToObject(response.toString(), DefaultAddressModel.class);
                                if (bean.getData() != null) {
                                    tvAddress.setText(bean.getData().getAddress());
                                    tvNickname.setText(bean.getData().getConsignee());
                                    tvPhone.setText(bean.getData().getMobile());
                                    addressId = bean.getData().getAddress_id();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (data != null) {
                AddressModel addressModel = (AddressModel) data.getExtras().getSerializable(Constant.RESULT);
                tvSelectAddress.setText(addressModel.getAddress());
            }
        } else {
            if (data != null) {
                addressListModel = (AddressListModel.DataBean) data.getExtras().getSerializable(Constant.RESULT);
                tvAddress.setText(addressListModel.getAddress());
                tvNickname.setText(addressListModel.getConsignee());
                tvPhone.setText(addressListModel.getMobile());
                addressId = addressListModel.getAddress_id();
            }
        }


    }



    class PriceTextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {
            String fee = etFee.getText().toString().trim();
            String totalPrice = Float.valueOf(Float.valueOf(TextUtils.isEmpty(fee) ? "0" : fee) + 8) + "";
            tvTotalPrice.setText(String.format(getString(R.string.text_price), totalPrice));
        }

    }

    @Override
    public void onDestroy() {
        mGoogleApiClient.disconnect();
        super.onDestroy();
    }

    /**
     * 以下是定位部分
     * @param
     */


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(),"Permission to access the location is missing.", Toast.LENGTH_LONG).show();
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            LatLng lastLatLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            if (!Geocoder.isPresent()) {
                Toast.makeText(getContext(), "No geocoder available", Toast.LENGTH_LONG).show();
                return;
            }
            startLocation();
        }
    }

    //开始定位
    public void startLocation() {

        if (mGoogleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(),"Permission to access the location is missing.", Toast.LENGTH_LONG).show();
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, locationRequest, this);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //定位成功会回调onLocationChanged()
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            getAddress(location.getLatitude(),location.getLongitude());
        } else {
            ToastUtils.showShort("", "定位失败.....");
        }

    }


    /**
     * 获取经纬度
     */
    protected void getAddress(final double latitude, final double longitude) {
        String url = "https://maps.google.com/maps/api/geocode/json?latlng="+
                latitude+","+longitude+"&language=en_us&sensor=false"+"&key="+Constant.mapKey;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        String st=e.getMessage();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String address=showResponse(response);
                        if(!TextUtils.isEmpty(address)){
                           tvSendingAddress.setText(address);
                           tvReceiptAddress.setText(address);
                        }else {
                            tvSendingAddress.setText(getString(R.string.text_location_fail));
                            tvReceiptAddress.setText(getString(R.string.text_location_fail));
                        }

                    }
                });
    }


    private String showResponse(String response) {
        JSONObject jsonObj = null;
        String result = "";
        try {
            // 把服务器相应的字符串转换为JSONObject
            jsonObj = new JSONObject(response);
            // 解析出响应结果中的地址数据
            JSONArray jsonArray = jsonObj.getJSONArray("results");
            result = jsonArray.getJSONObject(0).getString("formatted_address");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("GPS", "address json result error");
        }
        return result;
    }

    //停止位置更新
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }
}
