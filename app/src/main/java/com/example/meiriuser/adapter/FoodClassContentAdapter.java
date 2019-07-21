package com.example.meiriuser.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/20.
 */

public class FoodClassContentAdapter extends BaseQuickAdapter<FoodDetailsModel.DataBean.GoodsBean, BaseViewHolder> {


    public FoodClassContentAdapter(List<FoodDetailsModel.DataBean.GoodsBean> list) {
        super(R.layout.item_food_class_content, list);
    }



    @Override
    protected void convert(BaseViewHolder helper, FoodDetailsModel.DataBean.GoodsBean item) {
        ImageView ivFood=helper.getView(R.id.iv_food);
        ImageView ivReduce=helper.getView(R.id.iv_reduce);
        helper.setText(R.id.tv_food_name,item.getGoods_name());
        helper.setText(R.id.tv_food_content,item.getGoods_brief());
        helper.setText(R.id.tv_food_price, CommanUtils.doubleTrans(Double.valueOf(item.getShop_price()))+"");
        helper.setText(R.id.tv_num,String.valueOf(item.getClick_count()));
        int co=item.getClick_count();
        if(co==0){
            Glide.with(mContext)
                    .load(R.mipmap.icon_reduce_white)
                    .into(ivReduce);
        }else {
            helper.addOnClickListener(R.id.line_reduce);

            Glide.with(mContext)
                    .load(R.mipmap.icon_reduce_green)
                    .into(ivReduce);
        }
        helper.addOnClickListener(R.id.line_item);
        helper.addOnClickListener(R.id.line_add);
        Glide.with(mContext)
                .load(item.getGoods_img())
                .bitmapTransform(new GlideRoundTransform(mContext))
                .into(ivFood);
    }
}
