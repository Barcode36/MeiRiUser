package com.example.meiriuser.adapter;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meiriuser.R;
import com.example.meiriuser.model.IReleasedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */

public class BedroomNumAdapetr extends BaseQuickAdapter<String, BaseViewHolder> {

    List<String> slist=new ArrayList<>();
    private int pos=-1;

    public void setSelectPos(int pos){
        this.pos=pos;
        notifyDataSetChanged();
    }

    public BedroomNumAdapetr(List<String> list) {
        super(R.layout.item_bedroom_num, list);
        this.slist=list;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tvBedroomNum=helper.getView(R.id.tv_bedroom_num);
        helper.setText(R.id.tv_bedroom_num,item);
        helper.addOnClickListener(R.id.tv_bedroom_num);
        tvBedroomNum.setTextColor(pos==helper.getAdapterPosition()?Color.parseColor("#32BFA3"):Color.parseColor("#888888"));
        if(slist.size()-1!=helper.getAdapterPosition()){
            tvBedroomNum.setPadding(0,0, SizeUtils.dp2px(32),0);
        }
    }
}
