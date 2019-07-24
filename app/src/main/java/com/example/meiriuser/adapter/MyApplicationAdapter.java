package com.example.meiriuser.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.MyApplicationModel;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class MyApplicationAdapter  extends BaseQuickAdapter<TaskListModel.DataBean, BaseViewHolder> {

    public MyApplicationAdapter(List<TaskListModel.DataBean> list) {
        super(R.layout.item_my_application, list);
    }


    @Override
    protected void convert(BaseViewHolder helper, TaskListModel.DataBean item) {
        ImageView ivHead=helper.getView(R.id.iv_head);
        helper.setText(R.id.tv_content,item.getTitle());
        helper.setText(R.id.tv_address,item.getAddress());
        helper.setText(R.id.tv_date,item.getComplete_date());
        helper.setText(R.id.tv_task_state,item.getReceipt_status_name());
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

        Glide.with(mContext)
                .load(item.getUser().getHeadImg())
                .placeholder(R.mipmap.icon_logo_oval)
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(mContext))
                .into(ivHead);

        int status=item.getStatus();
        int receipt_status=item.getReceipt_status();
        if(status==2 || status==3){//匹配中（取消任务）
            helper.setBackgroundRes(R.id.btn_finish,R.drawable.shape_fillet_green_stroke_6dp);
            helper.setText(R.id.btn_finish,"取消申请");
            helper.setTextColor(R.id.btn_finish, Color.parseColor("#32BFA3"));

        }else if(status==4){//等待完成（联系）
            helper.setBackgroundRes(R.id.btn_finish,R.drawable.shape_fillet_green_6dp);
            helper.setTextColor(R.id.btn_finish, Color.parseColor("#FFFFFF"));
            helper.setText(R.id.btn_finish,"已完成");

        }else if(status==7|| status==6){//已取消
            helper.setVisible(R.id.btn_finish,false);

        }else if(receipt_status==4|| receipt_status==3|| receipt_status==5){
            helper.setVisible(R.id.btn_finish,false);
        }

        helper.addOnClickListener(R.id.btn_finish);
        helper.addOnClickListener(R.id.line_item);


    }
}
