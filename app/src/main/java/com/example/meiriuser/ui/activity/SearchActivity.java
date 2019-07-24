package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.AddressAdapter;
import com.example.meiriuser.adapter.HistorySearchAdapter;
import com.example.meiriuser.adapter.HistorySearchAddressAdapter;
import com.example.meiriuser.adapter.SearchShopAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.HotSearchListModel;
import com.example.meiriuser.model.MapAddressModel;
import com.example.meiriuser.model.TakeOutFoodModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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
 * Created by admin on 2019/5/9.
 */

public class SearchActivity extends BaseActivity implements
        AdapterView.OnItemClickListener{
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.rv_history_search)
    RecyclerView rvHistorySearch;
    List<String> mVals;
    @BindView(R.id.inputtip_list)
    ListView inputtipList;
    @BindView(R.id.line_search)
    LinearLayout lineSearch;
    HistorySearchAddressAdapter historySearchAdapter;
    private List<TakeOutFoodModel.DataBean> mCurrentTipList;
    private SearchShopAdapter mIntipAdapter;
    String lat;
    String lng;
    List<TakeOutFoodModel.DataBean> mSPVals;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search;
    }


    @Override
    public void initView() {
        mCurrentTipList=new ArrayList<>();
        mVals=new ArrayList<>();
        lat = PreferenceUtil.getString(Constant.LATITUDE);
        lng = PreferenceUtil.getString(Constant.LONGITUDE);
        gethotList();
    }


    /**
     * 获取商店列表
     */
    public void getStoreList(String search) {

        Map<String, String> params = new HashMap<>();
        params.put("latitude", lat);
        params.put("longitude", lng);
        params.put("order", Constant.MAIN_SORT_DEFAULT + "");
        params.put("search", search);
        String url = ApiUrl.storeListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .params(params)
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
                                TakeOutFoodModel bean = (TakeOutFoodModel) GsonUtil.JSONToObject(response.toString(), TakeOutFoodModel.class);
                                if (bean.getData() != null) {
                                    if(bean.getData().size()>0){
                                        mCurrentTipList.clear();
                                        mCurrentTipList.addAll(bean.getData());
                                        mIntipAdapter = new SearchShopAdapter(getApplicationContext(), mCurrentTipList);
                                        inputtipList.setAdapter(mIntipAdapter);
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    /**
     * 获取热门搜索列表
     */
    public void gethotList() {

        showDialog();
        String url = ApiUrl.hotListUrl;
        OkHttpUtils
                .get()
                .url(url)
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
                                HotSearchListModel bean= (HotSearchListModel) GsonUtil.JSONToObject(response.toString(),HotSearchListModel.class);
                                if(bean.getResult()== HttpStatus.SUCCESS&& bean.getData()!=null){
                                    mVals.addAll(bean.getData().getList());
                                    mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
                                    {
                                        @Override
                                        public View getView(FlowLayout parent, int position, String s)
                                        {
                                            TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tv_flow,
                                                    mFlowLayout, false);
                                            tv.setText(s);
                                            return tv;
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mCurrentTipList != null) {
            if(mSPVals==null){
                mSPVals=new ArrayList<>();
            }
            mSPVals.add(mCurrentTipList.get(i));
            historySearchAdapter.notifyDataSetChanged();
            PreferenceUtil.saveObject("shopList",mSPVals);
            int storeID=mCurrentTipList.get(i).getStore_id();
            Intent intent = new Intent(SearchActivity.this, FoodActivity.class);
            intent.putExtra(Constant.TYPE,storeID);
            jumpToActivity(intent);

        }

    }


    @Override
    public void initData() {
        rvHistorySearch.setNestedScrollingEnabled(false);
        mSPVals=new ArrayList<>();
        rvHistorySearch.setLayoutManager(new LinearLayoutManager(this));
        historySearchAdapter=new HistorySearchAddressAdapter(mSPVals);
        rvHistorySearch.setAdapter(historySearchAdapter);
        List<TakeOutFoodModel.DataBean>spVals= (List<TakeOutFoodModel.DataBean>) PreferenceUtil.readObject("shopList");
        if(spVals!=null){
            mSPVals.addAll(spVals);
            historySearchAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void initListener() {
        super.initListener();
        inputtipList.setOnItemClickListener(this);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    getStoreList(charSequence.toString());
                    lineSearch.setVisibility(View.GONE);
                    inputtipList.setVisibility(View.VISIBLE);
                } else {
                    // 如果输入为空  则清除 listView 数据
                    if (mIntipAdapter != null && mCurrentTipList != null) {
                        mCurrentTipList.clear();
                        mIntipAdapter.notifyDataSetChanged();
                    }
                    lineSearch.setVisibility(View.VISIBLE);
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
                        int storeID=mCurrentTipList.get(position).getStore_id();
                        Intent intent = new Intent(SearchActivity.this, FoodActivity.class);
                        intent.putExtra(Constant.TYPE,storeID);
                        jumpToActivity(intent);
                        break;
                    case R.id.iv_cancel:
                        mSPVals.remove(position);
                        historySearchAdapter.notifyDataSetChanged();
                        PreferenceUtil.saveObject("shopList",mSPVals);
                        break;
                }
            }
        });


        /*     etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                *//*判断是否是“搜索”键*//*
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String key = etSearch.getText().toString().trim();
                    if(TextUtils.isEmpty(key)){
                        return true;
                    }

                    return true;
                }
                return false;
            }
        });*/
    }

    @OnClick({R.id.image_search, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_search:
                break;
            case R.id.tv_search:
                finish();
                break;
        }
    }
}
