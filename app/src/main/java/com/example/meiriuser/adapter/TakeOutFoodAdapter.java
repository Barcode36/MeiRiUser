package com.example.meiriuser.adapter;

import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.TakeOutFoodModel;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/13.
 */

public class TakeOutFoodAdapter extends BaseQuickAdapter<TakeOutFoodModel.DataBean, BaseViewHolder> {

    public TakeOutFoodAdapter(List<TakeOutFoodModel.DataBean> list) {
        super(R.layout.item_food, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, TakeOutFoodModel.DataBean item) {
        ImageView ivFood=helper.getView(R.id.iv_food);
        helper.setText(R.id.tv_name,item.getStore_name());
        helper.setText(R.id.tv_evaluate,String.valueOf(item.getScore()));
        helper.setText(R.id.tv_monthly_sale,String.format(mContext.getResources().getString(R.string.text_monthly_sale),String.valueOf(item.getMarket_month())));
        helper.setText(R.id.tv_delivery_price,String.format(mContext.getResources().getString(R.string.text_delivery_price),item.getStart_price()));
        helper.setText(R.id.tv_delivery_time,String.format(mContext.getResources().getString(R.string.text_delivery_time),String.valueOf(item.getSend_avg())));
        helper.setText(R.id.tv_delivery_distance,item.getDistance());
        helper.setText(R.id.tv_distribu_fee,String.format(mContext.getResources().getString(R.string.text_distribu_fee),item.getDelivery_money()+""));
        helper.addOnClickListener(R.id.line_item);
        RatingBar ratingbar=helper.getView(R.id.ratingbar);
        ratingbar.setRating((float) item.getScore());
        Glide.with(mContext)
                .load(item.getStore_img())
                .transform(new CenterCrop(mContext),new GlideRoundTransform(mContext,2))
                .into(ivFood);

    }
}
