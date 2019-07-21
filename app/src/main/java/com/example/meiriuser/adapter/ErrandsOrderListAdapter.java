package com.example.meiriuser.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.ErrandsOrderListModel;
import com.example.meiriuser.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/6/27.
 */

public class ErrandsOrderListAdapter extends BaseQuickAdapter<ErrandsOrderListModel.DataBeanX.DataBean, BaseViewHolder> {


    public ErrandsOrderListAdapter(List<ErrandsOrderListModel.DataBeanX.DataBean> list) {
        super(R.layout.item_errands_order_detail, list);

    }

    @Override
    protected void convert(BaseViewHolder helper, ErrandsOrderListModel.DataBeanX.DataBean item) {
        helper.setText(R.id.tv_order_type,item.getShipping_name());
        helper.setText(R.id.tv_order_status,item.getStatus_name());
        if(item.getOther_info().getBuy_list()!=null){
            if(item.getOther_info().getBuy_list().size()>0){
                String buyList=item.getOther_info().getBuy_list().toString();
                String buyString =buyList.substring(1,buyList.length()-1).replace(","," ");
                helper.setText(R.id.tv_substitute_shopping,String.format(mContext.getString(R.string.text_substitute_shopping2),buyString));
            }
        }

        int rider_status=item.getRider_status();
        int payStatus=item.getPay_status();
        if(rider_status==0 && payStatus==2){
            helper.setVisible(R.id.btn_cancel_order,true);
        }else {
            helper.setVisible(R.id.btn_cancel_order,false);
        }

        if(rider_status==0){
            helper.setVisible(R.id.btn_contact_riders,false);
        }else {
            helper.setVisible(R.id.btn_contact_riders,true);
        }
        helper.setText(R.id.tv_take_address,item.getOther_info().getBuy_address_name());
        helper.setText(R.id.tv_collect_address,item.getAddress());
        helper.setText(R.id.tv_contact,item.getConsignee()+"  "+item.getTel());
        helper.addOnClickListener(R.id.btn_cancel_order);
        helper.addOnClickListener(R.id.btn_contact_riders);
        helper.addOnClickListener(R.id.line_item);

    }
}
