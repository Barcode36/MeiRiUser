package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.PopMenuAdater;
import com.example.meiriuser.adapter.TakeOutFoodAdapter;
import com.example.meiriuser.adapter.TaskOutFoodClassicAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.model.BannerListModel;
import com.example.meiriuser.model.TakeOutFoodModel;
import com.example.meiriuser.model.TaskOutFoodClassicModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideImageLoader;
import com.example.zhouwei.library.CustomPopWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import rx.functions.Action1;

/**
 * 外卖
 */

public class TakeOutFoodActivity extends BaseActivity {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_type)
    ImageView ivType;
    @BindView(R.id.line_type)
    LinearLayout lineType;
    @BindView(R.id.iv_sorting)
    ImageView ivSorting;
    @BindView(R.id.line_sorting)
    LinearLayout lineSorting;
    @BindView(R.id.line_screen)
    RelativeLayout lineScreen;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.view_pop)
    View viewPop;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    private List<TaskOutFoodClassicModel.DataBean> mDatas;
    TaskOutFoodClassicAdapter mAdapter;
    int pageNo = Constant.PAGENO;
    TakeOutFoodAdapter takeOutFoodAdapter;
    List<TakeOutFoodModel.DataBean> takeOutFoodModelList;
    List<String> imageUrl;
    String lat;
    String lng;
    CustomPopWindow popWindow;
    int CatId;
    int storeType;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_takeout_food;
    }

    @Override
    public void initView() {
        imageUrl = new ArrayList<>();
        mDatas = new ArrayList<>();
        tvAddress.setText(PreferenceUtil.getString(Constant.ADDRESS));
        lat = PreferenceUtil.getString(Constant.LATITUDE);
        lng = PreferenceUtil.getString(Constant.LONGITUDE);
        etSearch.setFocusable(false);
        toolbarTitle.setText(getString(R.string.title_type_food));
        takeOutFoodModelList = new ArrayList<>();
        storeType=Constant.MAIN_SORT_DEFAULT;
    }


    private void startBanner() {
        //设置banner样式(显示圆形指示器)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（指示器居右）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageUrl);
        //设置轮播时间
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void initData() {
        rvType.setNestedScrollingEnabled(false);
        rvType.setLayoutManager(new LinearLayoutManager(this));
        takeOutFoodAdapter = new TakeOutFoodAdapter(takeOutFoodModelList);
        rvType.setAdapter(takeOutFoodAdapter);
        rvClass.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new TaskOutFoodClassicAdapter(mDatas);
        rvClass.setAdapter(mAdapter);
        setRefresh();
        orderCatgoryList(Constant.ID_FOOD);
        getNoticeList();
    }

    /**
     * 获取广告列表
     */
    public void getNoticeList() {
        showDialog();
        String url = ApiUrl.bannerListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("position_id",Constant.BANNER_MAIN+"")
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
                                BannerListModel bean= (BannerListModel) GsonUtil.JSONToObject(response.toString(),BannerListModel.class);
                                if(bean.getResult()== HttpStatus.SUCCESS&& bean.getData()!=null){
                                    for(int i=0;i<bean.getData().size();i++){
                                        imageUrl.add(bean.getData().get(i).getImage_url());
                                        startBanner();
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
     * 获取商店列表
     */
    public void getStoreList(int catgory_id, String lat, String lng, int pageNo, int order) {

        Map<String, String> params = new HashMap<>();
        params.put("catgory_id", catgory_id + "");
        params.put("latitude", lat);
        params.put("longitude", lng);
        params.put("order", order + "");
        params.put("page", String.valueOf(pageNo));
        params.put("size", Constant.MAXCOUNT);
        String url = ApiUrl.storeListUrl;
        OkHttpUtils
                .post()
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
                                    takeOutFoodModelList.addAll(bean.getData());
                                    takeOutFoodAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    public void setRefresh() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                pageNo++;
                getStoreList(CatId, lat, lng, pageNo,storeType);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                takeOutFoodModelList.clear();
                takeOutFoodAdapter.notifyDataSetChanged();
                pageNo=Constant.PAGENO;
                getStoreList(CatId, lat, lng, pageNo,storeType);
            }
        });


    }


    @Override
    public void initListener() {
        takeOutFoodAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        Intent intent = new Intent(TakeOutFoodActivity.this, FoodActivity.class);
                        intent.putExtra(Constant.TYPE, takeOutFoodModelList.get(position).getStore_id());
                        jumpToActivity(intent);
                        break;

                }
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_item:
                        mAdapter.setSelectPos(position);
                        mAdapter.notifyDataSetChanged();
                        CatId=mDatas.get(position).getCat_id();
                        storeData(Constant.MAIN_SORT_DEFAULT);
                        break;

                }
            }
        });


        BusProvider.getBus().toObservable(AddressSaveChangeEvent.class)
                .subscribe(new Action1<AddressSaveChangeEvent>() {
                    @Override
                    public void call(AddressSaveChangeEvent newMessageEvent) {
                        if(newMessageEvent.getTag()==AddressSaveChangeEvent.SAVE_ADDRESS) {
                            tvAddress.setText(newMessageEvent.getTip().getAddress());
                            lat = String.valueOf(newMessageEvent.getTip().getLatitude());
                            lng = String.valueOf(newMessageEvent.getTip().getLongitude());
                            takeOutFoodModelList.clear();
                            takeOutFoodAdapter.notifyDataSetChanged();
                            pageNo=Constant.PAGENO;
                            orderCatgoryList(Constant.ID_FOOD);
                        }
                    }
                });
    }


    @OnClick({R.id.icon_back, R.id.et_search, R.id.tv_address, R.id.line_type, R.id.line_sorting, R.id.tv_sales_volume, R.id.tv_distance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.et_search:
                jumpToActivity(SearchActivity.class);
                break;
            case R.id.tv_address:
                Intent intent=new Intent(TakeOutFoodActivity.this,SearchAddressActivity.class);
                intent.putExtra(Constant.TYPE,"search");
                jumpToActivity(intent);
                break;
            case R.id.line_type:
                break;
            case R.id.line_sorting:
                showPopListView();
                break;
            case R.id.tv_sales_volume:
                storeData(Constant.MAIN_SORT_SALES_VOLUME);
                break;
            case R.id.tv_distance:
                storeData(Constant.MAIN_SORT_DISTANCE);
                break;
        }
    }


    private void showPopListView() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setBgDarkAlpha(5)
                .size(300, 400)//显示大小
                .create()
                .showAsDropDown(lineSorting, 0, 10);
    }


    private void handleListView(View contentView) {
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_menu);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        PopMenuAdater adapter = new PopMenuAdater(new PopMenuAdater.setOnItemOrderClick() {
            @Override
            public void onItemOrderClick(int position) {
                tvOrder.setText(mockData().get(position));
                if(position==0){
                    storeData(Constant.MAIN_SORT_COMPREHENSIVE);
                }else if(position==1){
                    storeData(Constant.MAIN_SORT_SCORE);
                }
                popWindow.dissmiss();
            }
        });
        adapter.setData(mockData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    /**
     * 获取商店分类
     */
    public void orderCatgoryList(int catgory_id) {

        showDialog();
        Map<String, String> params = new HashMap<>();
        params.put("catgory_id", catgory_id+"");
        String url = ApiUrl.orderCatgoryUrl;
        OkHttpUtils
                .post()
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
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                TaskOutFoodClassicModel bean = (TaskOutFoodClassicModel) GsonUtil.JSONToObject(response.toString(), TaskOutFoodClassicModel.class);
                                if (bean.getData() != null) {
                                    mDatas.addAll(bean.getData());
                                    mAdapter.setSelectPos(0);
                                    mAdapter.notifyDataSetChanged();
                                    CatId=mDatas.get(0).getCat_id();
                                    getStoreList(CatId, lat, lng, pageNo, storeType);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    private void storeData(int type){
        pageNo=Constant.PAGENO;
        takeOutFoodModelList.clear();
        takeOutFoodAdapter.notifyDataSetChanged();
        storeType=type;
        getStoreList(CatId, lat, lng, pageNo, type);
    }

    private List<String> mockData() {
        List<String> data = new ArrayList<>();
        data.add("综合排序");
        data.add("按评分排序");
        return data;
    }



}
