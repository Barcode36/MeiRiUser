package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.RefreshListEvent;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.AreaModel;
import com.example.meiriuser.model.net.ModifyAddressModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/31.
 */

public class EditorAddressActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rb_sir)
    CheckBox rbSir;
    @BindView(R.id.rb_am)
    CheckBox rbAm;
    @BindView(R.id.rg_label01)
    CheckBox rgLabel01;
    @BindView(R.id.rg_label02)
    CheckBox rgLabel02;
    @BindView(R.id.rg_label03)
    CheckBox rgLabel03;
    @BindView(R.id.et_contacts_name)
    EditText etContactsName;
    @BindView(R.id.et_contact_phone)
    EditText etContactPhone;
    @BindView(R.id.et_details_address)
    EditText etDetailsAddress;
    @BindView(R.id.tv_contact_address)
    TextView tvContactAddress;
    public String addAddress = "add";
    public String updateAddress = "update";
    public String deleteAddress = "delete";
    public String setDefaultAddress = "setDefault";
    String type;
    String token;
    int addressID;
    String sign_building = "";
    int sex = 0;
    AddressListModel.DataBean infoBean;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private ArrayList<AreaModel.DataBean> options1Items = new ArrayList<>();
    private ArrayList<String> options1ItemsString = new ArrayList<>();
    String sel_address_str;
    double sel_address_latitude;
    double sel_address_longitude;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_editor_address;
    }


    @Override
    public void initView() {
        token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        type = getIntent().getStringExtra(Constant.TYPE);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.text_set_default);

        if (type.equals("modify")) {
            toolbarTitle.setText(R.string.title_modify_address);
            infoBean = (AddressListModel.DataBean) getIntent().getSerializableExtra(Constant.DATEBEAN);
            if (infoBean != null) {
                addressID =infoBean.getAddress_id();
                etContactsName.setText(infoBean.getConsignee());
                etContactPhone.setText(infoBean.getTel());
                tvContactAddress.setText(infoBean.getCountry() + "" + infoBean.getProvince() + "" + infoBean.getCity() + "" + infoBean.getDistrict());
                etDetailsAddress.setText(infoBean.getAddress());
                String sign_building = infoBean.getNote();
                if (sign_building.equals(getString(R.string.text_company))) {
                    rgLabel01.setChecked(true);
                } else if (sign_building.equals(getString(R.string.text_home))) {
                    rgLabel02.setChecked(true);
                } else if (sign_building.equals(getString(R.string.text_school))) {
                    rgLabel03.setChecked(true);
                }

                if (infoBean.getSex() == 1) {
                    rbSir.setChecked(true);
                } else if (infoBean.getSex() == 2) {
                    rbAm.setChecked(true);
                }

            }
            type = updateAddress;
        } else {
            toolbarTitle.setText(R.string.title_new_address);
            type = addAddress;
        }

    }

    @Override
    public void initData() {
        super.initData();
        areaList();
    }


    @Override
    public void initListener() {
        sexChecked(rbSir, rbAm);
        sexChecked(rbAm, rbSir);
        checked(rgLabel01, rgLabel02, rgLabel03);
        checked(rgLabel02, rgLabel01, rgLabel03);
        checked(rgLabel03, rgLabel02, rgLabel01);

        BusProvider.getBus().toObservable(AddressSaveChangeEvent.class)
                .subscribe(new Action1<AddressSaveChangeEvent>() {
                    @Override
                    public void call(AddressSaveChangeEvent newMessageEvent) {
                        AddressModel addressModel=newMessageEvent.getTip();
                        sel_address_str=addressModel.getAddress();
                        sel_address_latitude=addressModel.getLatitude();
                        sel_address_longitude=addressModel.getLongitude();
                        tvContactAddress.setText(sel_address_str);
                    }
                });


    }


    private void checked(final CheckBox selectCheckBox, final CheckBox changeCheckBox1, final CheckBox changeCheckBox2) {
        selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (selectCheckBox.isChecked()) {
                    changeCheckBox1.setChecked(false);
                    changeCheckBox2.setChecked(false);
                }

            }
        });
    }


    private void sexChecked(final CheckBox selectCheckBox, final CheckBox changeCheckBox1) {
        selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (selectCheckBox.isChecked()) {
                    changeCheckBox1.setChecked(false);
                }
            }
        });
    }

    /**
     * 操作用户收货地址
     */
    public void addressToDo(String token, String type, int addressID, String address,
                            String mobile, String consignee, String sign_building, int sex, float lat, float lng) {

        if (showToastReturn()) {
            return;
        }

        showDialog();
        if (type.equals("add")) {
            String url = ApiUrl.addressToDoUrl;
            ModifyAddressModel modifyAddressModel = new ModifyAddressModel(consignee,sex,address,mobile,lat,lng,sign_building);
            Gson gson = new Gson();
            final String s = gson.toJson(modifyAddressModel);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
            OkHttpClient okHttpClient1 = OkHttpUtils.getInstance().getOkHttpClient();
            Request request = new Request.Builder().url(url)
                    .post(requestBody).addHeader("token",token).build();
            okHttpClient1.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    dissDialog();
                    ToastUtils.showShort(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    dissDialog();
                    String string = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        int result=jsonObject.getInt("result");
                        if(result==1){
                            finish();
                            BusProvider.getBus().post(new RefreshListEvent());
                        }else {
                            String info= jsonObject.getString("info");
                            showShort(info);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } else if (type.equals("update")) {
            String url = ApiUrl.addressToDoUrl;
            ModifyAddressModel modifyAddressModel = new ModifyAddressModel(addressID, consignee, sex, address, mobile, lat, lng, sign_building);
            Gson gson = new Gson();
            final String s = gson.toJson(modifyAddressModel);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
            OkHttpClient okHttpClient1 = OkHttpUtils.getInstance().getOkHttpClient();
            Request request = new Request.Builder().url(url)
                    .post(requestBody).addHeader("token", token).build();
            okHttpClient1.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    dissDialog();
                    ToastUtils.showShort(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    dissDialog();
                    String string = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        int result = jsonObject.getInt("result");
                        if (result == 1) {
                            finish();
                            BusProvider.getBus().post(new RefreshListEvent());
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

    }


    /**
     * 操作用户收货地址
     */
    public void addressDeleteToDo(String token, String type, int addressID) {

        showDialog();
    if (type.equals("delete")) {
        String url = ApiUrl.addressToDoUrl+"?address_id="+addressID;
        OkHttpUtils
                .delete()
                .url(url)
                .addHeader("token",token)
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
                                finish();
                                BusProvider.getBus().post(new RefreshListEvent());
                            }else {
                                String info= jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        }else if(type.equals("setDefault")){
            String url = ApiUrl.getDefaultAddressUrl;
            OkHttpUtils
                    .get()
                    .url(url)
                    .addHeader("token", token)
                    .addParams("address_id", addressID+"")
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
                                    finish();
                                    BusProvider.getBus().post(new RefreshListEvent());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

        }

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
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });

    }



    private boolean showToastReturn() {
        if (TextUtils.isEmpty(etContactsName.getText().toString().trim())) {
            ToastUtils.showShort(R.string.toast_input_contacts_name);
            return true;
        }
        if (TextUtils.isEmpty(etContactPhone.getText().toString().trim())) {
            ToastUtils.showShort(R.string.toast_input_contacts_phone);
            return true;
        }
        if(TextUtils.isEmpty(tvContactAddress.getText().toString().trim())){
            ToastUtils.showShort(R.string.toast_input_contacts_address);
            return true;
        }
        if (TextUtils.isEmpty(etDetailsAddress.getText().toString().trim())) {
            ToastUtils.showShort(R.string.toast_input_contacts_details_address);
            return true;
        }
        return false;
    }

    @OnClick({R.id.icon_back, R.id.btn_save_address, R.id.tv_contact_address,R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_contact_address:
                Intent intent=new Intent(EditorAddressActivity.this,SearchAddressActivity.class);
                intent.putExtra(Constant.TYPE,"receiving");
                jumpToActivity(intent);
                break;
            case R.id.btn_save_address:
                if (rgLabel01.isChecked()) {
                    sign_building = getString(R.string.text_company);
                } else if (rgLabel02.isChecked()) {
                    sign_building = getString(R.string.text_home);
                } else if (rgLabel03.isChecked()) {
                    sign_building = getString(R.string.text_school);
                }
                if (rbSir.isChecked()) {
                    sex = 1;
                } else if (rbAm.isChecked()) {
                    sex = 2;
                } else {
                    sex = 0;
                }
                addressToDo(token, type, addressID, sel_address_str+etDetailsAddress.getText().toString().trim(), etContactPhone.getText().toString().trim(),
                        etContactsName.getText().toString().trim(), sign_building, sex, (float) sel_address_latitude, (float) sel_address_longitude);
                break;
            case R.id.tv_right:
                type = setDefaultAddress;
                addressDeleteToDo(token, type, addressID);
                break;

        }
    }


   /* private void initOptionPicker() {//条件选择器初始化

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getName();
                codestring=options1Items.get(options1).getCode();
                tvContactAddress.setText(tx);
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
                       *//* String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();*//*
                    }
                })
                .build();

        pvOptions.setPicker(options1ItemsString);//一级选择器
    }*/



}
