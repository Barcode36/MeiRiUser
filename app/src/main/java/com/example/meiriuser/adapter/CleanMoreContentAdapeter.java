package com.example.meiriuser.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.CleanMoreContentModel;

import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */

public class CleanMoreContentAdapeter extends BaseQuickAdapter<CleanMoreContentModel, BaseViewHolder> {


    public CleanMoreContentAdapeter(List<CleanMoreContentModel> list) {
        super(R.layout.item_clean_more_content, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CleanMoreContentModel item) {
        TextView tvType=helper.getView(R.id.tv_type);
        ImageView ivType=helper.getView(R.id.iv_type);
        tvType.setText(item.getTitle());
        helper.addOnClickListener(R.id.line_task_clean);

        if(item.isCheck()){
            helper.setBackgroundRes(R.id.line_task_clean,R.drawable.shape_fillet_green_stroke_3dp);
        }else {
            helper.setBackgroundRes(R.id.line_task_clean,R.drawable.shape_fillet_gray_stroke_3dp);
        }
        Glide.with(mContext)
                .load(item.getRes())
                .centerCrop()
                .into(ivType);
    }
}
