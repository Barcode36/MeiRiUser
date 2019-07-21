package com.example.meiriuser.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.FoodDetailsModel;

import java.util.List;

/**
 * Created by admin on 2019/5/20.
 */

public class FoodClassTitleAdapter extends BaseQuickAdapter<FoodDetailsModel.DataBean, BaseViewHolder> {
    int mSelect = 0;   //选中项

    public void changeSelected(int positon){ //刷新方法
        mSelect = positon;
        notifyDataSetChanged();
    }

    public FoodClassTitleAdapter(List<FoodDetailsModel.DataBean> list) {
        super(R.layout.item_food_class_title, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodDetailsModel.DataBean item) {
        TextView tvClassTitle=helper.getView(R.id.tv_class_title);
        tvClassTitle.setText(item.getName());
        helper.addOnClickListener(R.id.tv_class_title);

        if(mSelect==helper.getAdapterPosition()){
            tvClassTitle.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));  //选中项背景
        }else{
            tvClassTitle.setBackgroundColor(mContext.getResources().getColor(R.color.colorGrayBg));
        }
    }
}
