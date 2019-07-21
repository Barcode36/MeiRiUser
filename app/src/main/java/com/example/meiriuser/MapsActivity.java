package com.example.meiriuser;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.location.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.utils.L;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import rx.functions.Action1;

public class MapsActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    /*private AddressResultReceiver mResultReceiver;*/
    private boolean mAddressRequested;
    TextView tvLat;
    private RxPermissions rxPermissions;
    TextView tvLng;
    TextView tvAddress;

    public static final int UPDATE_INTERVAL = 5000;
    public static final int FASTEST_UPDATE_INTNERVAL = 2000;
    public static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest locationRequest;


    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        return rxPermissions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        tvLat= (TextView) findViewById(R.id.tv_lat);
        tvLng= (TextView) findViewById(R.id.tv_lng);
        tvAddress=(TextView) findViewById(R.id.tv_address);
        locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(UPDATE_INTERVAL).setFastestInterval(FASTEST_UPDATE_INTNERVAL);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        startIntentService();
        //接收FetchAddressIntentService返回的结果
       /* mResultReceiver = new AddressResultReceiver(new Handler());*/
        getRxPermissions().request(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {

                        } else {

                        }
                    }
                });

        //创建GoogleAPIClient实例
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(MapsActivity.this)
                    .addConnectionCallbacks(MapsActivity.this)
                    .addOnConnectionFailedListener(MapsActivity.this)
                    .addApi(LocationServices.API)
                    .build();
        }
       /* if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(MapsActivity.this, "Please enable GPS!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }*/
     /*   checkIsGooglePlayConn();*/

    }





    /**
     * 启动地址搜索Service
     */
    protected void startIntentService() {
        String url = "https://maps.google.com/maps/api/geocode/json?latlng="+
                "33.8791748,151.2049694"+"&language=en_us&sensor=false"+"&key="+"AIzaSyBpYZc0DbXta1x5gtdu_USc43S-g6wxc_4";
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
                       String st=response;
                        String address=showResponse(response);
                        tvAddress.setText(address);
                      /*  try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result=jsonObject.getInt("result");
                            if(result==1){
                                finish();
                                showShort(getString(R.string.toast_submis_successful));
                            }else {
                                String info= jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/


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
            tvLat.setText(mLastLocation.getLatitude()+"");
            tvLng.setText(mLastLocation.getLongitude()+"");
            if (!Geocoder.isPresent()) {
                Toast.makeText(this, "No geocoder available", Toast.LENGTH_LONG).show();
                return;
            }
            startLocation();
            if (mGoogleApiClient.isConnected()) {
               /* startIntentService(new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude()));*/
            }

        }
    }




    //开始定位
    public void startLocation() {

        if (mGoogleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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


    //定位成功会回调onLocationChanged()
    @Override
    public void onLocationChanged(Location amapLocation) {
        Log.e("", "onLocationChanged");
        if (amapLocation != null) {
            ToastUtils.showShort("onLocationChanged 定位成功,getLatitude:" + amapLocation.getLatitude() + ",getLongitude:" + amapLocation.getLongitude());

          /*  updateToNewLocation(amapLocation);*/
        } else {
            ToastUtils.showShort("", "定位失败.....");
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
       /* if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            new AlertDialog.Builder(getActivity()).setMessage("Connection failed.Error code:" + connectionResult.getErrorCode()).show();

        }*/
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    /*class AddressResultReceiver extends ResultReceiver {
        private String mAddressOutput;

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            mAddressOutput = resultData.getString(FetchAddressIntentService.RESULT_DATA_KEY);
            if (resultCode == FetchAddressIntentService.SUCCESS_RESULT) {
                Log.i("MapsActivity", "mAddressOutput-->" + mAddressOutput);
                new AlertDialog.Builder(MapsActivity.this)
                        .setTitle("Position")
                        .setMessage(mAddressOutput)
                        .create()
                        .show();
            }

        }
    }*/

}
