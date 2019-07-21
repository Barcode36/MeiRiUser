package com.example.meiriuser.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.SelectDelegationAddressAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.AreaModel;
import com.example.meiriuser.model.NearbySearchAddressModel;
import com.example.meiriuser.net.ApiUrl;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by admin on 2019/6/25.
 */

public class SelectDelegationAddressActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    @BindView(R.id.image_search)
    ImageView imageSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_address_search)
    RecyclerView rvAddressSearch;
    @BindView(R.id.tv_select_address)
    TextView tvSelectAddress;
    SelectDelegationAddressAdapter mAdapter;
    List<NearbySearchAddressModel.DataBean.ResultsBean> addressModels;
    private OptionsPickerView pvOptions;
    private ArrayList<AreaModel.DataBean> options1Items = new ArrayList<>();
    private ArrayList<String> options1ItemsString = new ArrayList<>();
    //定位
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public static final int UPDATE_INTERVAL = 5000;
    public static final int FASTEST_UPDATE_INTNERVAL = 2000;
    private LocationRequest locationRequest;
    String str_address;
    double current_lat;
    double current_lng;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_select_delegation_address;
    }


    @Override
    public void initView() {
        super.initView();
        rvAddressSearch.setLayoutManager(new LinearLayoutManager(this));
        addressModels=new ArrayList<>();
        mAdapter=new SelectDelegationAddressAdapter(addressModels);
        rvAddressSearch.setAdapter(mAdapter);
        tvSelectAddress.setVisibility(View.VISIBLE);
        etSearch.setHint(R.string.hint_task_address);
        //定位
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_UPDATE_INTNERVAL);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(SelectDelegationAddressActivity.this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(SelectDelegationAddressActivity.this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    public void initData() {
        /*addressModels.add(new SelectDelegationAddressModel("粤保中心广场","粤保中心广场","23m"));
        addressModels.add(new SelectDelegationAddressModel("粤保中心广场","粤保中心广场","23m"));
        addressModels.add(new SelectDelegationAddressModel("粤保中心广场","粤保中心广场","23m"));*/

        areaList();
    }




    /**
     * 获取地区列表
     */
    public void areaList() {
        showDialog();
        String url = ApiUrl.areaUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("pid",0+"")
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
                                AreaModel bean = (AreaModel) GsonUtil.JSONToObject(response.toString(), AreaModel.class);
                                if (bean.getData()!= null) {
                                    options1Items.addAll(bean.getData());
                                    for(int i=0;i<bean.getData().size();i++){
                                        options1ItemsString.add(bean.getData().get(i).getName());
                                    }
                                    initOptionPicker();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });

    }


    private void initOptionPicker() {//条件选择器初始化

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getName();
                AddressModel addressModel=new AddressModel(tx,23.1798640,113.2508470);
                str_address=tx;
                current_lat=23.1798640;
                current_lng=113.2508470;
                tvSelectAddress.setText(str_address);

            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.BLACK)
                .setTextColorCenter(Color.LTGRAY)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                       /* String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();*/
                    }
                })
                .build();

        pvOptions.setPicker(options1ItemsString);//一级选择器
    }


    public void geNearbySearchInfo(double lat,double lng,String keyword,String language) {
        String url = ApiUrl.nearbysearchUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("location",lat+","+lng)
                .addParams("keyword",keyword)
                .addParams("language",language)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                NearbySearchAddressModel bean = (NearbySearchAddressModel) GsonUtil.JSONToObject(response.toString(), NearbySearchAddressModel.class);
                                if (bean.getData().getResults() != null) {
                                    addressModels.clear();
                                    addressModels.addAll(bean.getData().getResults());
                                    mAdapter.notifyDataSetChanged();
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

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    geNearbySearchInfo(current_lat,current_lng,charSequence.toString(),"en_us");
                } else {


                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        AddressModel addressModel=new AddressModel();
                        NearbySearchAddressModel.DataBean.ResultsBean.GeometryBean.LocationBean locationBean=addressModels.get(position).getGeometry().getLocation();
                        addressModel.setLongitude(locationBean.getLng());
                        addressModel.setLatitude(locationBean.getLat());
                        addressModel.setAddress(addressModels.get(position).getName());
                        Intent intent1 = new Intent();
                        //把返回数据存入Intent
                        intent1.putExtra("result", addressModel);
                        //设置返回数据
                        setResult(2, intent1);
                        //关闭Activity
                        finish();
                        break;
                }
            }
        });
    }

    @OnClick({R.id.tv_select_address, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_address:
                KeyboardUtils.hideSoftInput(tvSelectAddress);
                pvOptions.show();
                break;
            case R.id.tv_search:
                finish();
                break;
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

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
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
            LatLng lastLatLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
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
            if (ActivityCompat.checkSelfPermission(SelectDelegationAddressActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(SelectDelegationAddressActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                            str_address=address;
                            current_lat=latitude;
                            current_lng=longitude;
                            tvSelectAddress.setText(str_address);
                        }else {
                            str_address="";
                            current_lat=0.0;
                            current_lng=0.0;
                            tvSelectAddress.setText(str_address);
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
}
