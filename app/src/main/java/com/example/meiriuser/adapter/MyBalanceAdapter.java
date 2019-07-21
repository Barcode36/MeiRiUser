package com.example.meiriuser.adapter;

import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.MyApplicationModel;
import com.example.meiriuser.model.MyBalanceModel;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.GlideCircleTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/30.
 */

public class MyBalanceAdapter  extends BaseQuickAdapter<MyBalanceModel, BaseViewHolder> {

    public MyBalanceAdapter(List<MyBalanceModel> list) {
        super(R.layout.item_my_balance, list);
    }


    @Override
    protected void convert(BaseViewHolder helper, MyBalanceModel item) {
        helper.setText(R.id.tv_content,item.getS1());
        helper.setText(R.id.tv_price,item.getS2());
        helper.setText(R.id.tv_date,item.getS3());

        if(item.getS2().contains("+")){
            helper.setTextColor(R.id.tv_price, Color.parseColor("#F49500"));
        }else  if(item.getS2().contains("-")){
            helper.setTextColor(R.id.tv_price, Color.parseColor("#32BFA3"));
        }


    }
}
