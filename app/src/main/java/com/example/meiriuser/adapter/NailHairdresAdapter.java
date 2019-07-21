package com.example.meiriuser.adapter;

import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.NailHairdresModel;
import com.example.meiriuser.model.TakeOutFoodModel;
import com.example.meiriuser.model.TaskOutFoodClassicModel;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/16.
 */

public class NailHairdresAdapter extends BaseQuickAdapter<TakeOutFoodModel.DataBean, BaseViewHolder> {
    String type;

    public NailHairdresAdapter(List<TakeOutFoodModel.DataBean> list,String type) {
        super(R.layout.item_nailhairdres, list);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, TakeOutFoodModel.DataBean item) {
        ImageView ivFood=helper.getView(R.id.iv_food);
        helper.setText(R.id.tv_name,item.getStore_name());
        helper.setText(R.id.tv_evaluate,item.getScore()+"");
        RatingBar ratingbar=helper.getView(R.id.ratingbar);
        ratingbar.setRating((float) item.getScore());
        helper.setText(R.id.tv_monthly_sale,String.format(mContext.getResources().getString(R.string.text_monthly_sale),String.valueOf(item.getMarket_month())));
        helper.setText(R.id.tv_type,type);
        helper.setText(R.id.tv_delivery_distance,String.format(mContext.getResources().getString(R.string.text_delivery_distance),"100"));
        helper.addOnClickListener(R.id.line_item);
        Glide.with(mContext)
                .load(item.getStore_img())
                .transform(new CenterCrop(mContext),new GlideRoundTransform(mContext,2))
                .into(ivFood);
    }
}
