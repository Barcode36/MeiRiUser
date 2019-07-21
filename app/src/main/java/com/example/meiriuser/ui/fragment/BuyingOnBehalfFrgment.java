package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.AreaModel;
import com.example.meiriuser.model.DefaultAddressModel;
import com.example.meiriuser.model.net.BuyingOrderModel;
import com.example.meiriuser.model.net.OrderNetModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.activity.ErrandsActivity;
import com.example.meiriuser.ui.activity.ErrandsOrderDetailActivity;
import com.example.meiriuser.ui.activity.LoginActivity;
import com.example.meiriuser.ui.activity.OnlinePaymentActivity;
import com.example.meiriuser.ui.activity.OrderDetailsActivity;
import com.example.meiriuser.ui.activity.ReceivingAddressActivity;
import com.example.meiriuser.ui.activity.SearchAddressActivity;
import com.example.meiriuser.ui.activity.SelectDelegationAddressActivity;
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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.MediaType;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/14.
 */

public class BuyingOnBehalfFrgment extends BaseFragment {
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
    @BindView(R.id.line_buying_select_address)
    RelativeLayout lineBuyingSelectAddress;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.btn_place_order)
    Button btnPlaceOrder;
    @BindView(R.id.et_contacts)
    EditText etContacts;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.et_estimated_price)
    EditText etEstimatedPrice;
    @BindView(R.id.et_fee)
    EditText etFee;
    public List<String> mVals;
    AddressListModel.DataBean addressListModel;
    String token;
    BuyingOrderModel.OtherInfoBean otherInfoBean;
    int addressId=0;
    int shippingId=3;//代购
    boolean isContact=false;
    boolean isBill=false;
    float gratuity;
    static String type;
    String commodityRequireStr;
    ErrandsActivity mActivity;


    public static BuyingOnBehalfFrgment newInstance(String jumptype) {
        BuyingOnBehalfFrgment fragment = new BuyingOnBehalfFrgment();
        type=jumptype;
        return fragment;
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_buying_on_behalf;
    }




    @Override
    public void initView(View rootView) {
        mVals = new ArrayList<>();
        otherInfoBean=new BuyingOrderModel.OtherInfoBean();
        otherInfoBean.setBuy_type(1);
        tvTotalPrice.setText(String.format(getString(R.string.text_price),8+""));
        initValData();
        mActivity= (ErrandsActivity) getActivity();

    }


    public void initValData(){
        switch (type){
            case "drinks":
                mVals.add(getString(R.string.text_drinks1));
                mVals.add(getString(R.string.text_drinks2));
                mVals.add(getString(R.string.text_drinks3));
                mVals.add(getString(R.string.text_drinks5));
                mVals.add(getString(R.string.text_drinks6));
                mVals.add(getString(R.string.text_drinks7));
                mVals.add(getString(R.string.text_drinks8));
                mVals.add(getString(R.string.text_drinks9));
                mVals.add(getString(R.string.text_drinks10));
                mVals.add(getString(R.string.text_drinks11));
                mVals.add(getString(R.string.text_drinks12));
                mVals.add(getString(R.string.text_drinks13));
                mVals.add(getString(R.string.text_drinks14));
                mVals.add(getString(R.string.text_drinks15));
                mVals.add(getString(R.string.text_drinks16));
                mVals.add(getString(R.string.text_drinks17));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "snack":
                mVals.add(getString(R.string.toast_snack1));
                mVals.add(getString(R.string.toast_snack2));
                mVals.add(getString(R.string.toast_snack3));
                mVals.add(getString(R.string.toast_snack4));
                mVals.add(getString(R.string.toast_snack5));
                mVals.add(getString(R.string.toast_snack6));
                mVals.add(getString(R.string.toast_snack7));
                mVals.add(getString(R.string.toast_snack8));
                mVals.add(getString(R.string.toast_snack9));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "coffee":
                mVals.add(getString(R.string.text_coffee1));
                mVals.add(getString(R.string.text_coffee2));
                mVals.add(getString(R.string.text_coffee3));
                mVals.add(getString(R.string.text_coffee4));
                mVals.add(getString(R.string.text_coffee5));
                mVals.add(getString(R.string.text_coffee6));
                mVals.add(getString(R.string.text_coffee7));
                mVals.add(getString(R.string.text_coffee8));
                mVals.add(getString(R.string.text_coffee9));
                mVals.add(getString(R.string.text_coffee10));
                mVals.add(getString(R.string.text_coffee11));
                mVals.add(getString(R.string.text_coffee12));
                mVals.add(getString(R.string.text_coffee13));
                mVals.add(getString(R.string.text_coffee14));
                mVals.add(getString(R.string.text_coffee15));
                mVals.add(getString(R.string.text_coffee16));
                mVals.add(getString(R.string.text_coffee17));
                mVals.add(getString(R.string.text_coffee18));
                mVals.add(getString(R.string.text_coffee19));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "drugs":
                mVals.add(getString(R.string.text_drugs1));
                mVals.add(getString(R.string.text_drugs2));
                mVals.add(getString(R.string.text_drugs3));
                mVals.add(getString(R.string.text_drugs4));
                mVals.add(getString(R.string.text_drugs5));
                mVals.add(getString(R.string.text_drugs6));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "wine":
                mVals.add(getString(R.string.text_other1));
                mVals.add(getString(R.string.text_other2));
                mVals.add(getString(R.string.text_other3));
                mVals.add(getString(R.string.text_other4));
                mVals.add(getString(R.string.text_other5));
                mVals.add(getString(R.string.text_other6));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "breakfast":
                mVals.add(getString(R.string.text_breakfast1));
                mVals.add(getString(R.string.text_breakfast2));
                mVals.add(getString(R.string.text_breakfast3));
                mVals.add(getString(R.string.text_breakfast4));
                mVals.add(getString(R.string.text_breakfast5));
                mVals.add(getString(R.string.text_breakfast6));
                mVals.add(getString(R.string.text_breakfast7));
                mVals.add(getString(R.string.text_breakfast8));
                mVals.add(getString(R.string.text_breakfast9));
                mVals.add(getString(R.string.text_breakfast10));
                mVals.add(getString(R.string.text_breakfast11));
                mVals.add(getString(R.string.text_breakfast12));
                mVals.add(getString(R.string.text_breakfast13));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "expenses":
                mVals.add(getString(R.string.text_expenses1));
                mVals.add(getString(R.string.text_expenses2));
                mVals.add(getString(R.string.text_expenses3));
                mVals.add(getString(R.string.text_expenses4));
                mVals.add(getString(R.string.text_expenses5));
                mVals.add(getString(R.string.text_expenses6));
                mVals.add(getString(R.string.text_expenses7));
                mVals.add(getString(R.string.text_expenses8));
                mVals.add(getString(R.string.text_expenses9));
                mVals.add(getString(R.string.text_expenses10));
                mVals.add(getString(R.string.text_expenses11));
                mVals.add(getString(R.string.text_expenses12));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "other":
                mVals.add(getString(R.string.text_other1));
                mVals.add(getString(R.string.text_other2));
                mVals.add(getString(R.string.text_other3));
                mVals.add(getString(R.string.text_other4));
                mVals.add(getString(R.string.text_other5));
                mVals.add(getString(R.string.text_other6));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
            case "fruits":
                mVals.add(getString(R.string.text_fruits1));
                mVals.add(getString(R.string.text_fruits2));
                mVals.add(getString(R.string.text_fruits3));
                mVals.add(getString(R.string.text_fruits4));
                mVals.add(getString(R.string.text_fruits5));
                mVals.add(getString(R.string.text_fruits6));
                mVals.add(getString(R.string.text_fruits7));
                mVals.add(getString(R.string.text_fruits8));
                mVals.add(getString(R.string.text_fruits9));
                mVals.add(getString(R.string.text_fruits10));
                mVals.add(getString(R.string.text_fruits11));
                mVals.add(getString(R.string.text_fruits12));
                mVals.add(getString(R.string.text_fruits13));
                mVals.add(getString(R.string.text_drinks18));
                mVals.add(getString(R.string.text_drinks19));
                mVals.add(getString(R.string.text_drinks20));
                mVals.add(getString(R.string.text_drinks21));
                mVals.add(getString(R.string.text_drinks22));
                break;
        }

        requireFlow.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_selectgreen_flow,
                        requireFlow, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    @Override
    public void initData() {
        token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        getDefaultAddressList(token);
    }


    @Override
    public void initListener() {
        super.initListener();
        rgAddress.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_desig_address:
                        lineBuyingSelectAddress.setVisibility(View.VISIBLE);
                        view.setVisibility(View.VISIBLE);
                        otherInfoBean.setBuy_type(1);
                        break;
                    case R.id.rb_purchase_nearby:
                        lineBuyingSelectAddress.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        otherInfoBean.setBuy_type(2);
                        break;
                }

            }
        });

        requireFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                if(mVals.get(position).equals(getString(R.string.text_drinks18))){
                    isContact=true;
                }else if(mVals.get(position).equals(getString(R.string.text_drinks19))){
                    isBill=true;
                }

                if(TextUtils.isEmpty(etCommodityRequire.getText().toString().trim())){
                    commodityRequireStr=mVals.get(position);
                }else {
                    commodityRequireStr=etCommodityRequire.getText().toString()+" "+mVals.get(position);
                }
                etCommodityRequire.setText(commodityRequireStr);
                etCommodityRequire.setSelection(commodityRequireStr.length());
                return true;
            }
        });
        etEstimatedPrice.addTextChangedListener(new PriceTextChange());
        etFee.addTextChangedListener(new PriceTextChange());

        BusProvider.getBus().toObservable(AddressSaveChangeEvent.class)
                .subscribe(new Action1<AddressSaveChangeEvent>() {
                    @Override
                    public void call(AddressSaveChangeEvent newMessageEvent) {
                        if(newMessageEvent.getTag()==AddressSaveChangeEvent.DISPLAY_ADDRESS) {
                            AddressModel addressModel = newMessageEvent.getTip();
                            tvSelectAddress.setText(addressModel.getAddress());
                            otherInfoBean.setBuy_address_name(addressModel.getAddress());
                            otherInfoBean.setBuy_address_lat(addressModel.getLatitude());
                            otherInfoBean.setBuy_address_lng(addressModel.getLongitude());
                        }
                    }
                });

    }

    @OnClick({R.id.relative_commodity_fee, R.id.tv_running_expenses, R.id.relative_gratuity, R.id.btn_place_order, R.id.line_buying_select_address,R.id.line_select_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_buying_select_address:
                Intent intent=new Intent(getContext(),SearchAddressActivity.class);
                intent.putExtra(Constant.TYPE,"receiving");
                jumpToActivity(intent);
                /*startActivityForResult(new Intent(getActivity(), SelectDelegationAddressActivity.class), 2);*/
                break;
            case R.id.relative_commodity_fee:
                break;
            case R.id.tv_running_expenses:
                break;
            case R.id.relative_gratuity:
                break;
            case R.id.btn_place_order:
                String token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
                if(TextUtils.isEmpty(token)){
                    jumpToActivity(LoginActivity.class);
                }else {
                    String info = etCommodityRequire.getText().toString().trim();
                    if (TextUtils.isEmpty(info)) {
                        showShort(getString(R.string.toast_input_commodity_info));
                        return;
                    }
                    if (info.length() > 0) {
                        String[] otherInfoList = info.split(" ");
                        otherInfoBean.setBuy_list(Arrays.asList(otherInfoList));
                    }
                    String estimatedPrice = etEstimatedPrice.getText().toString().trim();
                    Float totalPrice = Float.valueOf(TextUtils.isEmpty(estimatedPrice) ? "0" : estimatedPrice);
                    otherInfoBean.setBuy_price(totalPrice);
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

    public void orderQuery(int address_id,int shipping_id,boolean is_contact,boolean is_bill, float gratuity) {

        if(otherInfoBean.getBuy_list().size()==0){
            showShort(getString(R.string.toast_input_commodity_info));
            return;
        }

        if(rbDesigAddress.isChecked()&&TextUtils.isEmpty(tvSelectAddress.getText().toString().trim())){
            showShort(getString(R.string.toast_input_buying_address));
            return;
        }

        if (TextUtils.isEmpty(address_id+"")) {
            showShort(getString(R.string.toast_input_the_address));
            return;
        }
        showDialog();
        String url = ApiUrl.errandsCreateUrl;
        BuyingOrderModel buyingOrderModel = new BuyingOrderModel(address_id,shipping_id, is_contact, is_bill,gratuity,otherInfoBean);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(buyingOrderModel))
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
                                Intent intent=new Intent(getActivity(),ErrandsOrderDetailActivity.class);
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
                                    addressId=bean.getData().getAddress_id();
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
        if (data != null) {
            addressListModel = (AddressListModel.DataBean) data.getExtras().getSerializable(Constant.RESULT);
            tvAddress.setText(addressListModel.getAddress());
            tvNickname.setText(addressListModel.getConsignee());
            tvPhone.setText(addressListModel.getMobile());
            addressId=addressListModel.getAddress_id();
        }


    /*    if(resultCode==2){
            if (data != null) {

            }
        }else {

        }*/


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
            String estimatedPrice=etEstimatedPrice.getText().toString().trim();
            String fee=etFee.getText().toString().trim();
            String totalPrice=Float.valueOf(TextUtils.isEmpty(estimatedPrice)?"0":estimatedPrice)+Float.valueOf(TextUtils.isEmpty(fee)?"0":fee)+8+"";
            tvTotalPrice.setText(String.format(getString(R.string.text_price),totalPrice));
        }

    }



}
