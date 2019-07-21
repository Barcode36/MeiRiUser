package com.example.meiriuser.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.NearbySearchAddressModel;

import java.util.List;

/**
 * Created by admin on 2019/6/25.
 */

public class SelectDelegationAddressAdapter extends BaseQuickAdapter<NearbySearchAddressModel.DataBean.ResultsBean, BaseViewHolder> {

    public SelectDelegationAddressAdapter(List<NearbySearchAddressModel.DataBean.ResultsBean> list) {
        super(R.layout.item_select_delegation_address, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearbySearchAddressModel.DataBean.ResultsBean item) {
        helper.setText(R.id.tv_name,item.getName());
      /*  helper.setText(R.id.tv_address,item.getS2());
        helper.setText(R.id.tv_distance,item.getS3());*/
        helper.addOnClickListener(R.id.line_item);
    }
}
