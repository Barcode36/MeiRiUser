package com.example.meiriuser.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.OrderDetailsModel;
import com.example.meiriuser.model.PayMethodModel;
import com.example.meiriuser.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class PayMethodAdapter extends BaseQuickAdapter<PayMethodModel, BaseViewHolder> {

    int selectPosition=-1;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public PayMethodAdapter(List<PayMethodModel> list) {
        super(R.layout.item_pay_method, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayMethodModel item) {
        ImageView ivUrl=helper.getView(R.id.iv_url);
        ImageView ivSelect=helper.getView(R.id.iv_select);
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content,item.getContent());
        ivUrl.setImageDrawable(mContext.getResources().getDrawable(item.getUrl()));
        /*Glide.with(mContext)
                .load(item.getUrl())
                .into(ivUrl);*/

        Glide.with(mContext)
                .load(selectPosition== helper.getAdapterPosition()?R.mipmap.icon_pay_select:R.drawable.shape_pay_unselect)
                .centerCrop()
                .into(ivSelect);
        helper.addOnClickListener(R.id.line_item);
    }
}
