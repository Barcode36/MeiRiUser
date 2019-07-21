package com.example.meiriuser.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.model.GroupBuyingServiceModel;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class GroupBuyingDetailsImaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public GroupBuyingDetailsImaAdapter(List<String> list) {
        super(R.layout.item_big_img, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv=helper.getView(R.id.iv);
        Glide.with(mContext)
                .load(item)
                .transform(new CenterCrop(mContext),new GlideRoundTransform(mContext,2))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(iv);
    }
}
