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
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/21.
 */

public class OrderFinishDetailsAdapter extends BaseQuickAdapter<OrderDetailsModel.DataBean.GoodsBean, BaseViewHolder> {

    public OrderFinishDetailsAdapter(List<OrderDetailsModel.DataBean.GoodsBean> list) {
        super(R.layout.item_order_details, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailsModel.DataBean.GoodsBean item) {
        ImageView ivFood=helper.getView(R.id.iv_food);
        helper.setText(R.id.tv_food_name,item.getGoods_name());
        helper.setText(R.id.tv_food_content,item.getGoods_attr());
        helper.setText(R.id.tv_num,"x"+item.getGoods_number());
        helper.setText(R.id.tv_price,String.format(mContext.getResources().getString(R.string.text_price),item.getGoods_price()));
    /*    Glide.with(mContext)
                .load(item.get())
                .transform(new CenterCrop(mContext),new GlideRoundTransform(mContext,2))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivFood);*/
    }
}
