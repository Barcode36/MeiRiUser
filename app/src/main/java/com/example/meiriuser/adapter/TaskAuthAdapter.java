package com.example.meiriuser.adapter;

import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.FoodDetailsModel;
import com.example.meiriuser.model.TakeOutFoodModel;
import com.example.meiriuser.model.TaskAuthModel;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public class TaskAuthAdapter extends BaseQuickAdapter<TaskAuthModel.DataBean, BaseViewHolder> {

    public TaskAuthAdapter(List<TaskAuthModel.DataBean> list) {
        super(R.layout.item_task_auth, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskAuthModel.DataBean item) {
        ImageView ivHead=helper.getView(R.id.iv_head);
        if(item.getUser()!=null){
            helper.setText(R.id.tv_name,item.getUser().getUsername());
        }
        helper.setText(R.id.tv_apply_time,item.getAddtime());
        helper.setText(R.id.tv_score,item.getComments());
        helper.setText(R.id.tv_evaluation,item.getAdvantage());
        RatingBar ratingBar=helper.getView(R.id.ratingbar);
        ratingBar.setRating(Float.parseFloat(item.getComments()));
        helper.addOnClickListener(R.id.btn_sure);

        Glide.with(mContext)
                .load(item.getUser().getHeadImg())
                .placeholder(R.mipmap.icon_logo_oval)
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(mContext))
                .into(ivHead);

    }
}
