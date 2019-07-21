package com.example.meiriuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.ui.activity.TaskTwoActivity;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.example.meiriuser.widget.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2019/5/23.
 */

public class TaskListAdapter extends BaseQuickAdapter<TaskListModel.DataBean, BaseViewHolder> {

    public TaskListAdapter(List<TaskListModel.DataBean> list) {
        super(R.layout.item_task_list, list);
    }


    @Override
    protected void convert(BaseViewHolder helper, TaskListModel.DataBean item) {
        ImageView ivHead=helper.getView(R.id.iv_head);
        helper.setText(R.id.tv_content,item.getTitle());
        helper.setText(R.id.tv_address,item.getAddress());
        helper.setText(R.id.tv_date,item.getComplete_date());
        final TagFlowLayout mFlowLayout=helper.getView(R.id.id_flowlayout);
        if(item.getTags()!=null){
            TagAdapter tagAdapter = new TagAdapter<String>(item.getTags()) {
                @Override
                public View getView(FlowLayout parent, final int position, String s) {

                    TextView tvRemarks = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_remark_flow,
                            mFlowLayout, false);
                    tvRemarks.setText(String.format(mContext.getString(R.string.text_remark_tips),s));
                    return tvRemarks;
                }
            };
            mFlowLayout.setAdapter(tagAdapter);
        }

        helper.setText(R.id.tv_order_time,item.getAddtime());
        helper.setText(R.id.tv_price,String.format(mContext.getResources().getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(item.getMoney()))));
        helper.addOnClickListener(R.id.line_item);

        Glide.with(mContext)
                .load(item.getUser().getHeadImg())
                .placeholder(R.mipmap.icon_logo_oval)
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(mContext))
                .into(ivHead);

    }
}
