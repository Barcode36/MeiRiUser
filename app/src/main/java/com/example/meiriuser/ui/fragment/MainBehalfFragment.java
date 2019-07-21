package com.example.meiriuser.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.PurchaseAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.model.PurchaseModel;
import com.example.meiriuser.ui.activity.ErrandsActivity;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/31.
 */

public class MainBehalfFragment extends BaseFragment {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_click_drinks)
    TextView tvClickDrinks;
    @BindView(R.id.tv_click_snack)
    TextView tvClickSnack;
    @BindView(R.id.tv_click_coffee)
    TextView tvClickCoffee;
    @BindView(R.id.tv_click_drugs)
    TextView tvClickDrugs;
    @BindView(R.id.tv_click_fruits)
    TextView tvClickFruits;
    @BindView(R.id.tv_click_breakfast)
    TextView tvClickBreakfast;
    @BindView(R.id.tv_click_expenses)
    TextView tvClickExpenses;
    @BindView(R.id.rv_purchase)
    RecyclerView rvPurchase;
    PurchaseAdapter purchaseAdapter;
    List<PurchaseModel>purchaseModelList;
    ErrandsActivity mActivity;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main_behalf;
    }


    @Override
    public void initView(View rootView) {
        Glide.with(getContext())
                .load(Constant.url)
                .into(ivBg);
        rvPurchase.setNestedScrollingEnabled(false);
        rvPurchase.setLayoutManager(new GridLayoutManager(getContext(),3));
        purchaseModelList=new ArrayList<>();
        purchaseModelList.add(new PurchaseModel("http://n.sinaimg.cn/sinacn11/640/w333h307/20180516/9eda-harvfht6217957.png","麦当劳"));
        purchaseModelList.add(new PurchaseModel("http://img2.ctoutiao.com/uploads/2018/02/09/09ae67eb428d5ad7b8557c88492bad81.jpg","星巴克"));
        purchaseModelList.add(new PurchaseModel("http://img4.imgtn.bdimg.com/it/u=411098260,3805770804&fm=26&gp=0.jpg","必胜客"));
        mActivity= (ErrandsActivity) getActivity();

    }

    @Override
    public void initData() {
        purchaseAdapter=new PurchaseAdapter(purchaseModelList);
        rvPurchase.setAdapter(purchaseAdapter);
    }

    @OnClick({R.id.et_search, R.id.tv_click_drinks, R.id.tv_click_snack, R.id.tv_click_coffee,
            R.id.tv_click_drugs, R.id.tv_running_errands, R.id.tv_click_breakfast, R.id.tv_click_expenses,R.id.tv_click_fruits})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                jumpActivity("other");
                break;
            case R.id.tv_click_drinks:
                jumpActivity("drinks");
                break;
            case R.id.tv_click_snack:
                jumpActivity("snack");
                break;
            case R.id.tv_click_coffee:
                jumpActivity("coffee");
                break;
            case R.id.tv_click_drugs:
                jumpActivity("drugs");
                break;
            case R.id.tv_running_errands:
                jumpActivity("wine");
                break;
            case R.id.tv_click_breakfast:
                jumpActivity("breakfast");
                break;
            case R.id.tv_click_expenses:
                jumpActivity("expenses");
                break;
            case R.id.tv_click_fruits:
                jumpActivity("fruits");
                break;
        }
    }


    public void jumpActivity(String type){
        Intent intent=new Intent(getActivity(),ErrandsActivity.class);
        intent.putExtra(Constant.TYPE,"other");
        intent.putExtra("jumptype",type);
        jumpToActivity(intent);
    }
}
