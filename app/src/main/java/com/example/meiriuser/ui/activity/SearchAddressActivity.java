package com.example.meiriuser.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.AddressAdapter;
import com.example.meiriuser.adapter.HistorySearchAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.MapAddressDetailsModel;
import com.example.meiriuser.model.MapAddressModel;
import com.example.meiriuser.model.TakeOutFoodModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by admin on 2019/5/13.
 */

public class SearchAddressActivity extends BaseActivity implements
       AdapterView.OnItemClickListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    @BindView(R.id.image_search)
    ImageView imageSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_history_search)
    RecyclerView rvHistorySearch;
    @BindView(R.id.inputtip_list)
    ListView inputtipList;
    @BindView(R.id.line_history_search)
    LinearLayout lineHistorySearch;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_relocation)
    TextView tvRelocation;
    private List<MapAddressModel.PredictionsBean> mCurrentTipList;
    private AddressAdapter mIntipAdapter;
    public static final int REQUEST_SUC = 1000;
    HistorySearchAdapter historySearchAdapter;
    List<AddressListModel.DataBean> mVals;
    String token;
   /* List<AddressModel> addressModelList;*/

    //定位
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public static final int UPDATE_INTERVAL = 5000;
    public static final int FASTEST_UPDATE_INTNERVAL = 2000;
    private LocationRequest locationRequest;
    String jumpType;
    AddressModel addressModel;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search_address;
    }


    @Override
    public void initView() {
        etSearch.setHint(R.string.toast_input_address);
    }

    @Override
    public void initData() {
        //定位
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_UPDATE_INTNERVAL);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(SearchAddressActivity.this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(SearchAddressActivity.this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mCurrentTipList=new ArrayList<>();
        token=PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        tvLocation.setText(PreferenceUtil.getString(Constant.ADDRESS));
        mVals = new ArrayList<>();
    /*    addressModelList=new ArrayList<>();*/
        rvHistorySearch.setLayoutManager(new LinearLayoutManager(this));
        historySearchAdapter = new HistorySearchAdapter(mVals);
        rvHistorySearch.setAdapter(historySearchAdapter);
        lineHistorySearch.setVisibility(View.VISIBLE);
        inputtipList.setVisibility(View.GONE);
        jumpType=getIntent().getStringExtra(Constant.TYPE);
        getAddressList(token);
    }

    @OnClick({R.id.image_search, R.id.tv_search,R.id.tv_relocation,R.id.tv_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_search:
                break;
            case R.id.tv_search:
                finish();
                break;
            case R.id.tv_relocation:
                startLocation();
                break;
            case R.id.tv_location:
                String location_longitude=PreferenceUtil.getString(Constant.LONGITUDE);
                String location_latitude=PreferenceUtil.getString(Constant.LATITUDE);
                String location_address=PreferenceUtil.getString(Constant.ADDRESS);
                AddressModel addressModel = new AddressModel(location_address, Double.valueOf(location_latitude),Double.valueOf(location_longitude));
                if(jumpType.equals("search")){
                    BusProvider.getBus().post(new AddressSaveChangeEvent(AddressSaveChangeEvent.SAVE_ADDRESS,addressModel));
                }else {
                    BusProvider.getBus().post(new AddressSaveChangeEvent(AddressSaveChangeEvent.DISPLAY_ADDRESS,addressModel));
                }
                finish();
                break;
        }
    }





    /**
     * 获取用户收货地址列表
     */
    public void getAddressList(String token) {

        showDialog();
        String url = ApiUrl.addressListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("token",token)
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
                                AddressListModel bean= (AddressListModel) GsonUtil.JSONToObject(response.toString(),AddressListModel.class);
                                if(bean.getResult()== HttpStatus.SUCCESS){
                                    if(bean.getData()!=null){
                                        mVals.addAll(bean.getData());
                                        historySearchAdapter.notifyDataSetChanged();
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
        inputtipList.setOnItemClickListener(this);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    getSearchAddress(charSequence.toString());
                    lineHistorySearch.setVisibility(View.GONE);
                    inputtipList.setVisibility(View.VISIBLE);
                } else {
                    // 如果输入为空  则清除 listView 数据
                    if (mIntipAdapter != null && mCurrentTipList != null) {
                        mCurrentTipList.clear();
                        mIntipAdapter.notifyDataSetChanged();
                    }
                    lineHistorySearch.setVisibility(View.VISIBLE);
                    inputtipList.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        historySearchAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        AddressModel addressModel = new AddressModel(mVals.get(position).getAddress(),Double.parseDouble(mVals.get(position).getLat()),Double.parseDouble( mVals.get(position).getLng()));
                        if(jumpType.equals("search")){
                            BusProvider.getBus().post(new AddressSaveChangeEvent(AddressSaveChangeEvent.SAVE_ADDRESS,addressModel));
                        }else {
                            BusProvider.getBus().post(new AddressSaveChangeEvent(AddressSaveChangeEvent.DISPLAY_ADDRESS,addressModel));
                        }
                        finish();
                        break;

                }
            }
        });

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mCurrentTipList != null) {
            MapAddressModel.PredictionsBean predictionsBean = (MapAddressModel.PredictionsBean) adapterView.getItemAtPosition(i);
            getSearchAddressDetails(predictionsBean.getPlace_id());

        }

    }



    /**
     * 以下是定位部分
     * @param
     */
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        mGoogleApiClient.disconnect();
        super.onDestroy();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"Permission to access the location is missing.", Toast.LENGTH_LONG).show();
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
           /* LatLng lastLatLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());*/
            if (!Geocoder.isPresent()) {
                Toast.makeText(this, "No geocoder available", Toast.LENGTH_LONG).show();
                return;
            }
            startLocation();
        }
    }

    //开始定位
    public void startLocation() {

        if (mGoogleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(SearchAddressActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(SearchAddressActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),"Permission to access the location is missing.", Toast.LENGTH_LONG).show();
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

            getLocationAddress(location.getLatitude(),location.getLongitude());
        } else {
            ToastUtils.showShort("", getString(R.string.toast_map_location_failed));
        }

    }


    /**
     * 获取搜索地址
     */
    protected void getSearchAddress(final String inputText) {
        String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+inputText+"&types=geocode&language=en_us&key="+Constant.mapKey;
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
                        dissDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            String status=jsonObject.getString("status");
                            if(status.equals("OK")){
                                MapAddressModel bean= (MapAddressModel) GsonUtil.JSONToObject(response.toString(),MapAddressModel.class);
                                if(bean.getPredictions()!=null){
                                    mCurrentTipList.clear();
                                    mCurrentTipList.addAll(bean.getPredictions());
                                    mIntipAdapter = new AddressAdapter(getApplicationContext(), mCurrentTipList);
                                    inputtipList.setAdapter(mIntipAdapter);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 获取搜索地址详情
     */
    protected void getSearchAddressDetails(final String placeid) {
        String url = " https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeid+"&language=en_us&key="+Constant.mapKey;
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
                        dissDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            String status=jsonObject.getString("status");
                            if(status.equals("OK")){
                                MapAddressDetailsModel bean= (MapAddressDetailsModel) GsonUtil.JSONToObject(response.toString(),MapAddressDetailsModel.class);
                                if(bean.getResult()!=null){
                                    MapAddressDetailsModel.ResultBean resultBean=bean.getResult();
                                    addressModel = new AddressModel(resultBean.getFormatted_address(),resultBean.getGeometry().getLocation().getLat(), resultBean.getGeometry().getLocation().getLng());
                                    if(jumpType.equals("search")){
                                        BusProvider.getBus().post(new AddressSaveChangeEvent(AddressSaveChangeEvent.SAVE_ADDRESS,addressModel));
                                    }else {
                                        BusProvider.getBus().post(new AddressSaveChangeEvent(AddressSaveChangeEvent.DISPLAY_ADDRESS,addressModel));
                                    }
                                    finish();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }





    /**
     * 获取经纬度
     */
    protected void getLocationAddress(final double latitude, final double longitude) {
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
                        AddressModel addressModel=new AddressModel();
                        if(!TextUtils.isEmpty(address)){
                            PreferenceUtil.commitString(Constant.LONGITUDE,longitude+"");
                            PreferenceUtil.commitString(Constant.LATITUDE,latitude+"");
                            PreferenceUtil.commitString(Constant.ADDRESS,address);
                            addressModel.setAddress(address);
                            addressModel.setLatitude(latitude);
                            addressModel.setLongitude(longitude);
                        }else {
                            PreferenceUtil.commitString(Constant.LONGITUDE,"");
                            PreferenceUtil.commitString(Constant.LATITUDE,"");
                            PreferenceUtil.commitString(Constant.ADDRESS,getString(R.string.text_location_fail));
                            addressModel.setAddress(getString(R.string.text_location_fail));
                        }
                        tvLocation.setText(address);
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

}
