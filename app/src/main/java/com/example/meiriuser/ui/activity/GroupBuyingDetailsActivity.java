package com.example.meiriuser.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.adapter.GroupBuyingDetailsAdapter;
import com.example.meiriuser.adapter.GroupBuyingDetailsImaAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.model.GroupBuyingDetailsModel;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2019/5/22.
 */

public class GroupBuyingDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_details)
    TextView tvDetails;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_food_price)
    TextView tvFoodPrice;
    GroupBuyingDetailsAdapter groupBuyingDetailsAdapter;
    GroupBuyingDetailsImaAdapter groupBuyingDetailsImaAdapter;
    List<FoodDetailsModel.DataBean.GoodsBean> detailsModels;
    List<String> stringList;
    FoodDetailsModel.DataBean.GoodsBean goodsBean;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_group_buying_details;
    }


    @Override
    public void initView() {
        toolbarTitle.setText("套餐详情");
        detailsModels=new ArrayList<>();
        stringList=new ArrayList<>();
      /*  detailsModels.add(new GroupBuyingDetailsModel("红色指甲油","1"));
        detailsModels.add(new GroupBuyingDetailsModel("红色指甲油","1"));
        detailsModels.add(new GroupBuyingDetailsModel("红色指甲油","1"));
        detailsModels.add(new GroupBuyingDetailsModel("红色指甲油","1"));
        detailsModels.add(new GroupBuyingDetailsModel("红色指甲油","1"));*/
      goodsBean= (FoodDetailsModel.DataBean.GoodsBean) getIntent().getExtras().get(Constant.DATEBEAN);
      if(goodsBean!=null){
          tvDetails.setText(goodsBean.getGoods_desc().toString());
          stringList.addAll(goodsBean.getGoods_images());
          tvFoodPrice.setText(CommanUtils.doubleTrans(Double.valueOf(goodsBean.getShop_price()))+"");
      }

        rvImg.setNestedScrollingEnabled(false);
        rvImg.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void initData() {
        groupBuyingDetailsImaAdapter=new GroupBuyingDetailsImaAdapter(stringList);
        rvImg.setAdapter(groupBuyingDetailsImaAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @OnClick({R.id.icon_back, R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_buy:
                break;
        }
    }
}
