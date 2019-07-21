package com.example.meiriuser.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.IReleasedModel;
import com.example.meiriuser.model.TaskAuthModel;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public class IReleasedAdapter extends BaseQuickAdapter<TaskListModel.DataBean, BaseViewHolder> {

    public IReleasedAdapter(List<TaskListModel.DataBean> list) {
        super(R.layout.item_ireleased, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskListModel.DataBean item) {
        ImageView ivHead=helper.getView(R.id.iv_head);
        helper.setText(R.id.tv_details_date,item.getAddtime());
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
        TextView tvState=helper.getView(R.id.tv_state);
        tvState.setText(item.getStatus_name());
        Glide.with(mContext)
                .load(item.getUser().getHeadImg())
                .placeholder(R.mipmap.icon_logo_oval)
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(mContext))
                .into(ivHead);
        helper.addOnClickListener(R.id.tv_call);
        helper.addOnClickListener(R.id.btn_state);
        helper.addOnClickListener(R.id.btn_cancel_order);

        TextView tvCall=helper.getView(R.id.tv_call);
        Button btnState=helper.getView(R.id.btn_state);
        Button btnCancelOrder=helper.getView(R.id.btn_cancel_order);

       int status=item.getStatus();
       int isComment=item.getIs_comment();

        if(status==2 || status==3){//匹配中（取消任务）
            btnState.setVisibility(View.GONE);
            btnCancelOrder.setVisibility(View.VISIBLE);
            tvCall.setVisibility(View.GONE);

        }else if(status==4){//等待完成（联系）
            btnState.setVisibility(View.GONE);
            btnCancelOrder.setVisibility(View.GONE);
            tvCall.setVisibility(View.VISIBLE);

        }else if(status==5){//已完成待确认（确认支付尾款）
            btnState.setVisibility(View.VISIBLE);
            btnCancelOrder.setVisibility(View.GONE);
            tvCall.setVisibility(View.VISIBLE);
            btnState.setText(R.string.text_pay_tail_money);
            tvState.setTextColor(Color.parseColor("#74D1BF"));
            btnState.setBackgroundResource(R.drawable.shape_fillet_light_green_4dp);

        }else if(status==6 && isComment==0){//待评价（联系、评价）
            btnState.setVisibility(View.VISIBLE);
            btnCancelOrder.setVisibility(View.GONE);
            tvCall.setVisibility(View.VISIBLE);
            btnState.setText(R.string.text_evaluated);
            tvState.setTextColor(Color.parseColor("#74D1BF"));
            btnState.setBackgroundResource(R.drawable.shape_fillet_light_green_4dp);

        }else if(status==6 && isComment==1){//已完成已评价（联系）

            btnState.setVisibility(View.GONE);
            btnCancelOrder.setVisibility(View.GONE);
            tvCall.setVisibility(View.VISIBLE);

        }else if(status==7){//已取消

            btnState.setVisibility(View.GONE);
            btnCancelOrder.setVisibility(View.GONE);
            tvCall.setVisibility(View.GONE);

        }
    }
}
