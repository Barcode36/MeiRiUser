package com.example.meiriuser.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.OrderDetailsModel;
import com.example.meiriuser.model.OrderModel;
import com.example.meiriuser.model.OrderQuetyListModel;
import com.example.meiriuser.until.CommanUtils;

import java.util.List;

/**
 * Created by admin on 2019/6/20.
 */

public class OrderGoodsFinishAdapter extends BaseQuickAdapter<OrderModel.DataBeanX.DataBean.GoodsBean, BaseViewHolder> {

    public OrderGoodsFinishAdapter(List<OrderModel.DataBeanX.DataBean.GoodsBean> list) {
        super(R.layout.item_order_details_finish, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderModel.DataBeanX.DataBean.GoodsBean item) {
        String attrs=item.getGoods_attr();
        if(attrs==null){
            helper.setText(R.id.tv_food_content,item.getGoods_name()+"    x"+item.getGoods_number());
        }else {
            helper.setText(R.id.tv_food_content,item.getGoods_name()+"  "+item.getGoods_attr()+"    x"+item.getGoods_number());
        }

        helper.setText(R.id.tv_price,String.format(mContext.getResources().getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(item.getGoods_price()))+""));
        helper.addOnClickListener(R.id.line_item);
    }
}
