package com.example.meiriuser.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.AddressListModel;

import java.util.List;

/**
 * Created by admin on 2019/5/31.
 */

public class AddressListAdapter extends BaseQuickAdapter<AddressListModel.DataBean, BaseViewHolder> {


    public AddressListAdapter(List<AddressListModel.DataBean> list) {
        super(R.layout.item_address, list);
    }


    @Override
    protected void convert(BaseViewHolder helper, AddressListModel.DataBean item) {
        helper.setText(R.id.tv_contact_details,item.getConsignee()+"  "+item.getTel());
        helper.setText(R.id.tv_address,item.getAddress());
        helper.addOnClickListener(R.id.line_item);
        helper.addOnClickListener(R.id.iv_editor_address);
    }
}
