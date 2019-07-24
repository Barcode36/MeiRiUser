package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.AddressListAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.dialog.IDialogCallBack;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.RefreshListEvent;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.net.ModifyAddressModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
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
import java.util.List;

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

public class ReceivingAddressActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    AddressListAdapter addressListAdapter;
    List<AddressListModel.DataBean> addressListModelList;
    String token;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_receiving_address;
    }


    @Override
    public void initView() {
        token= PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        toolbarTitle.setText(R.string.title_receiving_address);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        addressListModelList=new ArrayList<>();
     /*   addressListModelList.add(new AddressListModel("张宏韬","15766668888","浙江省杭州市西湖区文三路138号东方通信大厦7楼501室"));
        addressListModelList.add(new AddressListModel("张宏韬","15766668888","浙江省杭州市拱墅区莫干山路50号"));*/
    }


    @Override
    public void initData() {
        addressListAdapter=new AddressListAdapter(addressListModelList);
        rvList.setAdapter(addressListAdapter);

    }




    @Override
    public void initListener() {
        addressListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        //数据是使用Intent返回
                        Intent intent1 = new Intent();
                        //把返回数据存入Intent
                        intent1.putExtra("result", addressListModelList.get(position));
                        //设置返回数据
                        setResult(Constant.ADDRESSRESULT, intent1);
                        //关闭Activity
                        finish();
                        break;
                    case R.id.iv_editor_address:
                        Intent intent=new Intent(ReceivingAddressActivity.this,EditorAddressActivity.class);
                        intent.putExtra(Constant.TYPE,"modify");
                        intent.putExtra(Constant.DATEBEAN,addressListModelList.get(position));
                        jumpToActivity(intent);
                        break;

                }
            }
        });




        getAddressList(token);

        BusProvider.getBus().toObservable(RefreshListEvent.class)
                .subscribe(new Action1<RefreshListEvent>() {
                    @Override
                    public void call(RefreshListEvent event) {
                        getAddressList(token);
                    }
                });

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
                                if(bean.getResult()== HttpStatus.SUCCESS&& bean.getData()!=null){
                                    addressListModelList.clear();
                                    addressListModelList.addAll(bean.getData());
                                    addressListAdapter.notifyDataSetChanged();

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    @OnClick({R.id.icon_back, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_add_address:
                Intent intent=new Intent(ReceivingAddressActivity.this,EditorAddressActivity.class);
                intent.putExtra(Constant.TYPE,"new");
                jumpToActivity(intent);
                break;
        }
    }
}
