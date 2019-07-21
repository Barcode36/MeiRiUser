package com.example.meiriuser.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.MainTypeModel;
import com.example.meiriuser.model.TaskOutFoodClassicModel;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/9.
 */

public class MainTypeAdapter extends BaseQuickAdapter<MainTypeModel, BaseViewHolder> {


    public MainTypeAdapter(List<MainTypeModel> list) {
        super(R.layout.item_main_type, list);
    }
    @Override
    protected void convert(BaseViewHolder helper, MainTypeModel item) {
      /*  TextView tvType=helper.getView(R.id.tv_type)
        ImageView ivType=helper.getView(R.id.iv_type);
        tvType.setText(item.getTitle());
        ivType.setImageResource(item.getUrl());
        helper.addOnClickListener(R.id.line_type);
*/

        TextView tvType=helper.getView(R.id.tv_type);
        ImageView ivType=helper.getView(R.id.iv_type);
        tvType.setText(item.getTitle());
//        ivType.setImageResource(item.getUrl());
        helper.addOnClickListener(R.id.line_type);

        Glide.with(mContext)
                .load(item.getUrl())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivType);

    }
}
