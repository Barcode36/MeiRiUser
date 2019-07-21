package com.example.meiriuser.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.AddressListModel;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.MainRecommendModel;

import java.util.List;

/**
 * Created by admin on 2019/5/13.
 */

public class HistorySearchAdapter extends BaseQuickAdapter<AddressListModel.DataBean, BaseViewHolder> {

    public HistorySearchAdapter(List<AddressListModel.DataBean> list) {
        super(R.layout.item_history_search, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListModel.DataBean item) {
        helper.setText(R.id.tv_address,item.getAddress());
        helper.setText(R.id.tv_phone,item.getMobile());
        helper.setText(R.id.tv_label,item.getSign_building());
        helper.setText(R.id.tv_name,item.getConsignee());
     /*   helper.addOnClickListener(R.id.iv_cancel);*/
        helper.addOnClickListener(R.id.line_item);
    }
}
