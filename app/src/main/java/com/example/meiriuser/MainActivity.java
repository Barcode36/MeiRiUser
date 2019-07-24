package com.example.meiriuser;

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
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.base.BaseApp;
import com.example.meiriuser.base.XFragmentAdapter;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.MainRefreshEvent;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.UserInfoModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.ui.fragment.BrowseFragment;
import com.example.meiriuser.ui.fragment.MaintainFragment;
import com.example.meiriuser.ui.fragment.MysFragment;
import com.example.meiriuser.ui.fragment.OrderFragment;
import com.example.meiriuser.ui.fragment.TaskFragment;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.NoScrollViewPager;
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
import rx.functions.Action1;

public class MainActivity extends BaseActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    @BindView(R.id.noScrollViewPager)
    NoScrollViewPager noScrollViewPager;
    @BindView(R.id.rb_maintain)
    RadioButton rbMaintain;
    @BindView(R.id.rb_order)
    RadioButton rbOrder;
    @BindView(R.id.rb_browse)
    RadioButton rbBrowse;
    @BindView(R.id.rb_task)
    RadioButton rbTask;
    @BindView(R.id.rb_mys)
    RadioButton rbMys;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private List<Fragment> mBaseFragment;
    private XFragmentAdapter mXFragmentAdapter;
    boolean isExit = false;
    private Handler handler;
   /* MapLocationUntil mapLocationUntil;*/
    MaintainFragment maintainFragment;
    String token;
    MysFragment mysFragment;
    OrderFragment orderFragment;
    //定位
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public static final int UPDATE_INTERVAL = 5000;
    public static final int FASTEST_UPDATE_INTNERVAL = 2000;
    private LocationRequest locationRequest;




    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        token=PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        maintainFragment=new MaintainFragment();
        getRxPermissions().request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {

                        } else {

                        }
                    }
                });


        //定位
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_UPDATE_INTNERVAL);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                    .addConnectionCallbacks(MainActivity.this)
                    .addOnConnectionFailedListener(MainActivity.this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
        orderFragment=OrderFragment.newInstance();
        mysFragment=MysFragment.newInstance();
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(maintainFragment);
        mBaseFragment.add(orderFragment);
        mBaseFragment.add(BrowseFragment.newInstance());
        mBaseFragment.add(TaskFragment.newInstance());
        mBaseFragment.add(mysFragment);

        if (null == mXFragmentAdapter) {
            mXFragmentAdapter = new XFragmentAdapter(getSupportFragmentManager(), mBaseFragment, null);
        }
        noScrollViewPager.setAdapter(mXFragmentAdapter);
        noScrollViewPager.setNoScroll(true);
        noScrollViewPager.setOffscreenPageLimit(mBaseFragment.size() - 1);
        noScrollViewPager.setCurrentItem(0, false);
        rgMain.check(R.id.rb_maintain);
    }





    @Override
    public void initData() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isExit = false;
            }
        };

        getInfo(token,0);
    }




    /**
     * 获取用户信息
     */
    public void getInfo(final String token, final int falg) {


        showDialog();
        String url = ApiUrl.infoUrl;
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
                                UserInfoModel bean= (UserInfoModel) GsonUtil.JSONToObject(response.toString(),UserInfoModel.class);
                                if(bean.getResult()== HttpStatus.SUCCESS && bean.getData()!=null){
                                    PreferenceUtil.commitString(Constant.PF_USER_NAME,bean.getData().getUsername());
                                    PreferenceUtil.commitString(Constant.PF_MOBILE_NUM,bean.getData().getMobile_phone());
                                    PreferenceUtil.commitString(Constant.PF_NICK_NAME,bean.getData().getNickName());
                                    PreferenceUtil.commitString(Constant.PF_HEAD_IMG,bean.getData().getHeadImg());
                                    PreferenceUtil.commitString(Constant.PF_BANK_NUM,bean.getData().getBanknum());
                                    PreferenceUtil.commitString(Constant.PF_BANK_TYPE,bean.getData().getBankType());
                                    PreferenceUtil.commitString(Constant.PF_USER_MONEY,bean.getData().getUser_money());
                                    PreferenceUtil.commitInt(Constant.PF_SEX,bean.getData().getSex());
                                    mysFragment.setInfo();
                                    if(falg==1){
                                        if(TextUtils.isEmpty(token)){
                                            BusProvider.getBus().post(new MainRefreshEvent(MainRefreshEvent.LOGOUTREFRESH));
                                        }else {
                                            BusProvider.getBus().post(new MainRefreshEvent(MainRefreshEvent.LOGINREFRESH));
                                        }
                                    }
                                }
                            } else {
                                int code=jsonObject.getInt("code");
                                if(code==401){
                                    PreferenceUtil.removeKey(Constant.PF_USER_NAME);
                                    PreferenceUtil.removeKey(Constant.PF_TOKEN_KEY);
                                    PreferenceUtil.removeKey(Constant.PF_MOBILE_NUM);
                                    PreferenceUtil.removeKey(Constant.PF_NICK_NAME);
                                    PreferenceUtil.removeKey(Constant.PF_HEAD_IMG);
                                    PreferenceUtil.removeKey(Constant.PF_BANK_NUM);
                                    PreferenceUtil.removeKey(Constant.PF_BANK_TYPE);
                                    PreferenceUtil.removeKey(Constant.PF_SEX);
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
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_maintain:
                        noScrollViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_order:
                        noScrollViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_browse:

                        noScrollViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_task:

                        noScrollViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_mys:

                        noScrollViewPager.setCurrentItem(4, false);
                        break;

                }

            }
        });


        BusProvider.getBus().toObservable(AddressSaveChangeEvent.class)
                .subscribe(new Action1<AddressSaveChangeEvent>() {
                    @Override
                    public void call(AddressSaveChangeEvent newMessageEvent) {
                        if(newMessageEvent.getTag()==AddressSaveChangeEvent.SAVE_ADDRESS){
                            stopLocationUpdates();
                            double longitude=newMessageEvent.getTip().getLongitude();
                            double latitude=newMessageEvent.getTip().getLatitude();
                            String address=newMessageEvent.getTip().getAddress();
                            PreferenceUtil.commitString(Constant.LONGITUDE,longitude+"");
                            PreferenceUtil.commitString(Constant.LATITUDE,latitude+"");
                            PreferenceUtil.commitString(Constant.ADDRESS,address);
                        }

                    }
                });


        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        token=PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
                        getInfo(token,1);
                    }
                });
    }


    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                handler.sendEmptyMessageDelayed(0, 1500);
                ToastUtils.showShort(R.string.toast_keycode_back);
                return false;
            } else {
                BaseApp.exit();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
        PreferenceUtil.removeKey(Constant.LONGITUDE);
        PreferenceUtil.removeKey(Constant.LATITUDE);
        PreferenceUtil.removeKey(Constant.ADDRESS);
    }


    /**
     * 以下是定位部分
     * @param
     */

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
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                        BusProvider.getBus().post(new AddressSaveChangeEvent(AddressSaveChangeEvent.SAVE_ADDRESS,addressModel));
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
        }
        return result;
    }

    //停止位置更新
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

}
