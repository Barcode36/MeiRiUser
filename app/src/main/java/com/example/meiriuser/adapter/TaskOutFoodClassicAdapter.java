package com.example.meiriuser.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.TaskListModel;
import com.example.meiriuser.model.TaskOutFoodClassicModel;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;

import java.util.List;

/**
 * Created by admin on 2019/6/17.
 */

public class TaskOutFoodClassicAdapter extends BaseQuickAdapter<TaskOutFoodClassicModel.DataBean, BaseViewHolder> {
    public int postion;

    public void setSelectPos(int pos){
        this.postion=pos;
    }

    public TaskOutFoodClassicAdapter(List<TaskOutFoodClassicModel.DataBean> list) {
        super(R.layout.item_gridview, list);
    }


    @Override
    protected void convert(BaseViewHolder helper, TaskOutFoodClassicModel.DataBean item) {
        TextView tvName=helper.getView(R.id.textView);
        tvName.setText(item.getCat_name());
        ImageView imageView=helper.getView(R.id.imageView);
        Glide.with(mContext)
                .load(item.getIcon())
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(mContext))
                .into(imageView);
        helper.addOnClickListener(R.id.line_item);

        if(postion==helper.getAdapterPosition()){
            tvName.setTextColor(Color.parseColor("#ff6263"));
            helper.setVisible(R.id.view,true);
            helper.setVisible(R.id.iv_bold,true);
        }else {
            tvName.setTextColor(Color.parseColor("#353535"));
            helper.setVisible(R.id.view,false);
           /* imageView.setImageResource(null);*/
            helper.setVisible(R.id.iv_bold,false);
        }

    }
}
