package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.GroupBuyingDetailsImaAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.OrderPriceEvent;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideRoundTransform;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情
 */

public class FoodDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_food_content)
    TextView tvFoodContent;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_reduce)
    ImageView ivReduce;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    int count=0;
    double totalPrice=0;
    FoodDetailsModel.DataBean.GoodsBean goodsBean;
    List<String> stringList;
    GroupBuyingDetailsImaAdapter groupBuyingDetailsImaAdapter;
    double goodPrice;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_food_details;
    }


    @Override
    public void initView() {
        stringList=new ArrayList<>();
        toolbarTitle.setText(getString(R.string.title_food_details));
        goodsBean= (FoodDetailsModel.DataBean.GoodsBean) getIntent().getExtras().get(Constant.DATEBEAN);
        rvImg.setNestedScrollingEnabled(false);
        rvImg.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Glide.with(this)
                .load(R.mipmap.icon_reduce_green)
                .centerCrop()
                .into(ivReduce);
        Glide.with(this)
                .load(R.mipmap.icon_add_green)
                .centerCrop()
                .into(ivAdd);
        if(goodsBean!=null){
            tvName.setText(goodsBean.getGoods_name());
            tvFoodContent.setText(goodsBean.getGoods_brief());
            tvPrice.setText(goodsBean.getShop_price());
            if(goodsBean.getGoods_desc()!=null){
                stringList.addAll(goodsBean.getGoods_desc());
            }
            goodPrice=Double.valueOf(goodsBean.getShop_price());
            count=goodsBean.getClick_count();
            tvNum.setText(count+"");
        }

    }

    @Override
    public void initData() {
        super.initData();
        groupBuyingDetailsImaAdapter=new GroupBuyingDetailsImaAdapter(stringList);
        rvImg.setAdapter(groupBuyingDetailsImaAdapter);
    }





    @OnClick({R.id.icon_back, R.id.iv_reduce, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                //数据是使用Intent返回
                Intent intent1 = new Intent();
                //把返回数据存入Intent
                intent1.putExtra("count", count);
                intent1.putExtra("totalPrice", totalPrice);
                //设置返回数据
                setResult(100, intent1);
                //关闭Activity
                finish();
         /*
                if(totalPrice!=0){
                    Message message = new Message();
                    message.what =1;
                    myHandler.sendMessage(message);
                }
                finish();*/
                break;
            case R.id.iv_reduce:
                if (count > 0) {
                    count--;
                    totalPrice=totalPrice-goodPrice;
                }
                tvNum.setText(count+"");
                break;
            case R.id.iv_add:
                count++;
                totalPrice=totalPrice+goodPrice;
                tvNum.setText(count+"");
                break;
        }
    }


    /*Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BusProvider.getBus().post(new OrderPriceEvent(count,totalPrice));
                    break;
            }
            super.handleMessage(msg);
        }
    };*/
}
