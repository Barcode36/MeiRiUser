package com.example.meiriuser.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.PurchaseModel;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/31.
 */

public class PurchaseAdapter extends BaseQuickAdapter<PurchaseModel, BaseViewHolder> {


    public PurchaseAdapter(List<PurchaseModel> list) {
        super(R.layout.item_purchase, list);
    }


    @Override
    protected void convert(BaseViewHolder helper, PurchaseModel item) {
        ImageView ivLogo=helper.getView(R.id.iv_logo);
        helper.setText(R.id.tv_name,item.getS2());

        Glide.with(mContext)
                .load(item.getS1())
                .centerCrop()
                .bitmapTransform(new GlideRoundTransform(mContext,2))
                .into(ivLogo);
    }
}
