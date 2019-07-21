package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.BillingInfoModel;
import com.example.meiriuser.model.net.ApplyStatusModel;
import com.example.meiriuser.model.net.BillingAddressModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by admin on 2019/5/30.
 */

public class ProvideBillingAddressActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_address01)
    EditText etAddress01;
    @BindView(R.id.et_address02)
    EditText etAddress02;
    @BindView(R.id.et_region)
    EditText etRegion;
    @BindView(R.id.et_continent)
    EditText etContinent;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_country)
    EditText etCountry;
    @BindView(R.id.btn_sumbit)
    Button btnSumbit;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_provide_billing_address;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("账单地址");
    }

    @Override
    public void initData() {
        super.initData();
        getBillingInfo();
    }

    @OnClick({R.id.icon_back, R.id.btn_sumbit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_sumbit:
                billingAddressUrl();
                break;
        }
    }

    public void getBillingInfo() {
        showDialog();
        String url = ApiUrl.billAddressUrl;
        OkHttpUtils
                .get()
                .url(url)
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
                            JSONArray dateObject =jsonObject.getJSONArray("data");
                            if (result == 1 && dateObject.length()>0) {
                                BillingInfoModel bean = (BillingInfoModel) GsonUtil.JSONToObject(response.toString(), BillingInfoModel.class);
                                if (bean.getData() != null) {
                                    BillingInfoModel.DataBean dataBean=bean.getData();
                                    etCountry.setText(dataBean.getCountry()+"");
                                    etContinent.setText(dataBean.getProvince()+"");
                                    etRegion.setText(dataBean.getCity()+"");
                                    etCode.setText(dataBean.getZipcode()+"");
                                    etAddress01.setText(dataBean.getAddress_1()+"");
                                    etAddress02.setText(dataBean.getAddress_2()+"");
                                }
                            } else {

                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

    }


    public void billingAddressUrl() {
        String country=etCountry.getText().toString().trim();
        String province=etContinent.getText().toString().trim();
        String city=etRegion.getText().toString().trim();
        String zipcode=etCode.getText().toString().trim();
        String address_1=etAddress01.getText().toString().trim();
        String address_2=etAddress02.getText().toString().trim();
        showDialog();
        String url = ApiUrl.billAddressUrl;
        BillingAddressModel billingAddressModel = new BillingAddressModel(country, province,city,zipcode,address_1,address_2);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(billingAddressModel))
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
                                showShort(getString(R.string.toast_submis_successful));
                                finish();
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

}
