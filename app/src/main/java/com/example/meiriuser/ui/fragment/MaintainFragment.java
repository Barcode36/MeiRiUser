package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.MainRecommendAdapter;
import com.example.meiriuser.adapter.MainTypeAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.BannerListModel;
import com.example.meiriuser.model.MainRecommendModel;
import com.example.meiriuser.model.MainTypeModel;
import com.example.meiriuser.model.TaskOutFoodClassicModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.net.HttpStatus;
import com.example.meiriuser.ui.activity.ErrandsActivity;
import com.example.meiriuser.ui.activity.FoodActivity;
import com.example.meiriuser.ui.activity.OrderDetailsActivity;
import com.example.meiriuser.ui.activity.TakeOutFoodActivity;
import com.example.meiriuser.ui.activity.NailHairdresActivity;
import com.example.meiriuser.ui.activity.SearchActivity;
import com.example.meiriuser.ui.activity.SearchAddressActivity;
import com.example.meiriuser.ui.activity.TaskCleanActivity;
import com.example.meiriuser.ui.activity.TaskTwoActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideImageLoader;
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
 * Created by admin on 2019/5/9.
 */

public class MaintainFragment extends BaseFragment{

    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.et_search)
    EditText etSearch;
    MainTypeAdapter mainTypeAdapter;
    List<MainTypeModel> typeModelList;
    MainRecommendAdapter recommendAdapter;
    List<MainRecommendModel.DataBean.ListBean> recommendModelList;
    List<String> imageUrl;


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_main;
    }


    @Override
    public void initView(View rootView) {
        imageUrl=new ArrayList<>();
        tvAddress.setText(PreferenceUtil.getString(Constant.ADDRESS));
        etSearch.setFocusable(false);
        rvType.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvRecommend.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvType.setNestedScrollingEnabled(false);
        rvRecommend.setNestedScrollingEnabled(false);
        typeModelList = new ArrayList<>();
        recommendModelList = new ArrayList<>();
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_food, getString(R.string.title_type_food)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_errands, getString(R.string.title_type_errands)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_flowers_distribute, getString(R.string.title_type_flowers_distribute)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_nail_hairdres, getString(R.string.title_type_nail_hairdres)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_ktv, getString(R.string.title_type_ktv)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_massage, getString(R.string.title_type_massage)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_cosmetology, getString(R.string.title_type_cosmetology)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_photography, getString(R.string.title_type_photography)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_clean, getString(R.string.title_type_clean)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_house_move, getString(R.string.title_type_house_move)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_transfer, getString(R.string.title_type_transfer)));
        typeModelList.add(new MainTypeModel(R.mipmap.icon_type_repair_install, getString(R.string.title_type_repair_install)));


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
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    public void initData() {
        mainTypeAdapter = new MainTypeAdapter(typeModelList);
        recommendAdapter = new MainRecommendAdapter(recommendModelList);
        rvType.setAdapter(mainTypeAdapter);
        rvRecommend.setAdapter(recommendAdapter);
        getNoticeList();
        getRecommendList();
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


    public void getRecommendList() {
        String url = ApiUrl.recommendListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
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
                                MainRecommendModel bean = (MainRecommendModel) GsonUtil.JSONToObject(response.toString(), MainRecommendModel.class);
                                if (bean.getData() != null) {
                                    recommendModelList.clear();
                                    recommendModelList.addAll(bean.getData().getList());
                                    recommendAdapter.notifyDataSetChanged();
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
        mainTypeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.line_type:
                        if(position==0){
                            jumpToActivity(TakeOutFoodActivity.class);
                        }else if(position==1) {
                            Intent intent=new Intent(getActivity(),ErrandsActivity.class);
                            intent.putExtra(Constant.TYPE,"main");
                            jumpToActivity(intent);
                        }else if(position==2) {//鲜花配送
                            jumeGroup(typeModelList.get(position).getTitle(),Constant.ID_FLOWERS_DISTRIBUTE);

                        }else if(position==3) {//美甲美发
                            jumeGroup(typeModelList.get(position).getTitle(),Constant.ID_MANICURE_HAIRDRESSING);

                        }else if(position==4) {//娱乐
                            jumeGroup(typeModelList.get(position).getTitle(),Constant.ID_KTY);
                        }
                        else if(position==5) {//按摩足疗
                            jumeGroup(typeModelList.get(position).getTitle(),Constant.ID_MESSAGE);
                        }
                        else if(position==6) {//美容
                            jumeGroup(typeModelList.get(position).getTitle(),Constant.ID_COSMETOLOGY);
                        }
                        else if(position==7) {//其他
                            jumeGroup(typeModelList.get(position).getTitle(),Constant.ID_PHOTOGRAPHY);

                        } else if(position==8) {
                            Intent intent=new Intent(getActivity(),TaskCleanActivity.class);
                            intent.putExtra(Constant.TYPE,position);
                            jumpToActivity(intent);
                        }else {
                            Intent intent=new Intent(getActivity(),TaskTwoActivity.class);
                            intent.putExtra(Constant.TYPE,position);
                            jumpToActivity(intent);
                        }
                        break;

                }
            }
        });
        recommendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                int storeID=recommendModelList.get(position).getId();
                switch (itemViewId) {
                    case R.id.cv_cardview:
                        Intent intent = new Intent(getActivity(), FoodActivity.class);
                        intent.putExtra(Constant.TYPE,storeID);
                        jumpToActivity(intent);
                        break;

                }
            }
        });

        BusProvider.getBus().toObservable(AddressSaveChangeEvent.class)
                .subscribe(new Action1<AddressSaveChangeEvent>() {
                    @Override
                    public void call(AddressSaveChangeEvent newMessageEvent) {
                        if(newMessageEvent.getTag()==AddressSaveChangeEvent.SAVE_ADDRESS){
                            double longitude=newMessageEvent.getTip().getLongitude();
                            double latitude=newMessageEvent.getTip().getLatitude();
                            String address=newMessageEvent.getTip().getAddress();
                            PreferenceUtil.commitString(Constant.LONGITUDE,longitude+"");
                            PreferenceUtil.commitString(Constant.LATITUDE,latitude+"");
                            PreferenceUtil.commitString(Constant.ADDRESS,address);
                            tvAddress.setText(address);
                        }

                    }
                });



    }


    @OnClick({R.id.et_search, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                jumpToActivity(SearchActivity.class);
                break;
            case R.id.tv_address:
                Intent intent=new Intent(getActivity(),SearchAddressActivity.class);
                intent.putExtra(Constant.TYPE,"search");
                jumpToActivity(intent);
                break;
        }
    }


    public void jumeGroup(String title,int cat_id){
        Intent intent=new Intent(getActivity(),NailHairdresActivity.class);
        intent.putExtra(Constant.TYPE,title);
        intent.putExtra("cat_id",cat_id);
        jumpToActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
