package com.example.meiriuser.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.GroupBuyingDetailsModel;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class GroupBuyingDetailsAdapter extends BaseQuickAdapter<GroupBuyingDetailsModel, BaseViewHolder> {

    public GroupBuyingDetailsAdapter(List<GroupBuyingDetailsModel> list) {
        super(R.layout.item_group_buying_details, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupBuyingDetailsModel item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_num,"x"+item.getNum());
    }
}
