package com.example.meiriuser.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.MainRecommendModel;
import com.example.meiriuser.model.MainTypeModel;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/9.
 */

public class MainRecommendAdapter extends BaseQuickAdapter<MainRecommendModel.DataBean.ListBean, BaseViewHolder> {

    public MainRecommendAdapter(List<MainRecommendModel.DataBean.ListBean> list) {
        super(R.layout.item_main_recommend, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainRecommendModel.DataBean.ListBean item) {
        ImageView ivFood=helper.getView(R.id.iv_food);
        helper.setText(R.id.tv_name,item.getName());
        helper.addOnClickListener(R.id.cv_cardview);
        Glide.with(mContext)
                .load(item.getImages())
                .bitmapTransform(new GlideRoundTransform(mContext))
                .into(ivFood);
    }
}
