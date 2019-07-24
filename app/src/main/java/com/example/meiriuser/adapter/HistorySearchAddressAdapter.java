package com.example.meiriuser.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.TakeOutFoodModel;

import java.util.List;

/**
 * Created by admin on 2019/6/17.
 */

public class HistorySearchAddressAdapter  extends BaseQuickAdapter<TakeOutFoodModel.DataBean, BaseViewHolder> {

    public HistorySearchAddressAdapter(List<TakeOutFoodModel.DataBean> list) {
        super(R.layout.item_history_search_address, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, TakeOutFoodModel.DataBean item) {
        helper.setText(R.id.tv_address,item.getStore_name());
        helper.addOnClickListener(R.id.iv_cancel);
        helper.addOnClickListener(R.id.line_item);
    }

}
