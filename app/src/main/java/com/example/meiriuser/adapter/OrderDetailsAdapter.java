package com.example.meiriuser.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.MainTypeModel;
import com.example.meiriuser.model.OrderDetailsModel;
import com.example.meiriuser.model.OrderQuetyListModel;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/21.
 */

public class OrderDetailsAdapter extends BaseQuickAdapter<OrderQuetyListModel.ListBean, BaseViewHolder> {

    public OrderDetailsAdapter(List<OrderQuetyListModel.ListBean> list) {
        super(R.layout.item_order_details, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderQuetyListModel.ListBean item) {
        ImageView ivFood=helper.getView(R.id.iv_food);
        helper.setText(R.id.tv_food_name,item.getFoodName());
        helper.setText(R.id.tv_food_content,item.getFoodattr());
        helper.setText(R.id.tv_num,"x"+item.getNum());
        helper.setText(R.id.tv_price,String.format(mContext.getResources().getString(R.string.text_price),item.getPrice()));
        Glide.with(mContext)
                .load(item.getFoodUrl())
                .transform(new CenterCrop(mContext),new GlideRoundTransform(mContext,2))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivFood);
    }
}
