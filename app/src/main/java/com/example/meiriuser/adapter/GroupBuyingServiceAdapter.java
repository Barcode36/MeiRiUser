package com.example.meiriuser.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.model.GroupBuyingServiceModel;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class GroupBuyingServiceAdapter extends BaseQuickAdapter<FoodDetailsModel.DataBean.GoodsBean, BaseViewHolder> {

    public GroupBuyingServiceAdapter(List<FoodDetailsModel.DataBean.GoodsBean> list) {
        super(R.layout.item_group_buying_service, list);
    }



    @Override
    protected void convert(BaseViewHolder helper, FoodDetailsModel.DataBean.GoodsBean item) {
        ImageView ivFood=helper.getView(R.id.iv_food);
        helper.setText(R.id.tv_name,item.getGoods_name());
        helper.setText(R.id.tv_content,item.getGoods_brief());
        helper.setText(R.id.tv_hour,item.getGoods_brief());
        helper.setText(R.id.tv_food_price, CommanUtils.doubleTrans(Double.valueOf(item.getShop_price()))+"");
        helper.setText(R.id.tv_monthly_sales,String.format(mContext.getResources().getString(R.string.text_monthly_sales),"200"));
        helper.addOnClickListener(R.id.line_item);
        helper.addOnClickListener(R.id.btn_buy);
        Glide.with(mContext)
                .load(Constant.url)
                .transform(new CenterCrop(mContext),new GlideRoundTransform(mContext,2))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivFood);

    }
}
