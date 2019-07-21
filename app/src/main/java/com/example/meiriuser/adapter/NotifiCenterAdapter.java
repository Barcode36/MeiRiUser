package com.example.meiriuser.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.NotifiCenterModel;

import java.util.List;

/**
 * Created by admin on 2019/5/30.
 */

public class NotifiCenterAdapter extends BaseQuickAdapter<NotifiCenterModel.DataBeanX.DataBean, BaseViewHolder> {

    public NotifiCenterAdapter(List<NotifiCenterModel.DataBeanX.DataBean> list) {
        super(R.layout.item_notifi_center, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, NotifiCenterModel.DataBeanX.DataBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_date, item.getAddtime());
        helper.setText(R.id.tv_content, item.getContent());
    }
}
