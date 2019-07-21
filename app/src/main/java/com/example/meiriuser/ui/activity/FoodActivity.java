package com.example.meiriuser.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.ShopInfoModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.ui.fragment.BusinessFragment;
import com.example.meiriuser.ui.fragment.CommodityFragment;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.widget.GlideRoundTransform;
import com.flyco.tablayout.SlidingTabLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 *
 */

public class FoodActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_food)
    ImageView ivFood;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.tv_monthly_sale)
    TextView tvMonthlySale;
    @BindView(R.id.line_addview)
    LinearLayout lineAddview;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.tl_food)
    SlidingTabLayout tlFood;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    ArrayList titleslist = new ArrayList();
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout mFlowLayout;
    private String[] mTitles;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    int storeID;
    String shopTel;
    BusinessFragment businessFragment;
    CommodityFragment commodityFragment;
    List<String> mVals;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_food;
    }

    @Override
    public void initView() {
        toolbarTitle.setText(getString(R.string.title_food_details));
        mVals=new ArrayList<>();

        storeID = getIntent().getExtras().getInt(Constant.TYPE, 0);
        titleslist.add(getString(R.string.text_commodity));
        titleslist.add(getString(R.string.text_merchant));
        mTitles = (String[]) titleslist.toArray(new String[titleslist.size()]);
        businessFragment = new BusinessFragment();
        commodityFragment=new CommodityFragment();
        mFragments.add(commodityFragment);
        mFragments.add(businessFragment);
        //new一个适配器
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        //设置ViewPager与适配器关联
        viewPager.setAdapter(mAdapter);
        //设置Tab与ViewPager关联
        tlFood.setViewPager(viewPager);
    }

    @Override
    public void initData() {

        getStoreInfo(storeID);

    }

    /**
     * 获取单个商店信息
     */
    public void getStoreInfo(final int storeID) {

        showDialog();
        String url = ApiUrl.storeUrl + "?store_id=" + storeID;
        OkHttpUtils
                .get()
                .url(url)
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
                                ShopInfoModel bean = (ShopInfoModel) GsonUtil.JSONToObject(response.toString(), ShopInfoModel.class);
                                if (bean.getShop() != null) {
                                    ShopInfoModel.ShopBean dataBean = bean.getShop();
                                    Glide.with(FoodActivity.this)
                                            .load(dataBean.getHeadImg())
                                            .transform(new CenterCrop(FoodActivity.this), new GlideRoundTransform(FoodActivity.this, 2))
                                            .into(ivFood);
                                    tvName.setText(dataBean.getShopName());
                                    ratingbar.setRating((float) dataBean.getScore());
                                    tvEvaluate.setText(String.valueOf(dataBean.getScore()));
                                    tvMonthlySale.setText(String.format(getResources().getString(R.string.text_monthly_sale), String.valueOf(dataBean.getMarket_month())));
                                    tvAddress.setText(dataBean.getAddress());
                                    shopTel = dataBean.getTel();
                                    businessFragment.setInfo(dataBean);
                                    commodityFragment.setCommodityInfo(String.valueOf(storeID),dataBean.getShopName(),dataBean.getAddress(),dataBean.getDelivery_money());

                                    List<ShopInfoModel.ShopBean.PromoBean> promoBeanList=dataBean.getPromo();
                                    for (int i=0;i<promoBeanList.size();i++){
                                        mVals.add(promoBeanList.get(i).getTitle());
                                    }

                                    mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
                                    {
                                        @Override
                                        public View getView(FlowLayout parent, int position, String s)
                                        {
                                            TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_reduction_tv_flow,
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
    public void initListener() {
        super.initListener();
    }

    @OnClick({R.id.icon_back, R.id.iv_call, R.id.line_busi_environ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.iv_call:
                CommanUtils.callPhone(this, shopTel);
                break;
            case R.id.line_busi_environ:

                break;

        }
    }








    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }

}
