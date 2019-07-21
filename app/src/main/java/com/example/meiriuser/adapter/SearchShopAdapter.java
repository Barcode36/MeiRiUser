package com.example.meiriuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.model.MapAddressModel;
import com.example.meiriuser.model.TakeOutFoodModel;

import java.util.List;

/**
 * Created by admin on 2019/7/16.
 */

public class SearchShopAdapter extends BaseAdapter {
    private Context mContext;
    private List<TakeOutFoodModel.DataBean> mListTips;

    public SearchShopAdapter(Context context, List<TakeOutFoodModel.DataBean> tipList) {
        mContext = context;
        mListTips = tipList;
    }

    @Override
    public int getCount() {
        if (mListTips != null) {
            return mListTips.size();
        }
        return 0;
    }


    @Override
    public Object getItem(int i) {
        if (mListTips != null) {
            return mListTips.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_address, null);
            holder.mName =  view.findViewById(R.id.name);
            holder.mAddress =  view.findViewById(R.id.address);
            view.setTag(holder);
        } else{
            holder = (Holder)view.getTag();
        }
        if(mListTips == null){
            return view;
        }

        holder.mName.setText(mListTips.get(i).getStore_name());
        holder.mAddress.setVisibility(View.GONE);
        /*String address = mListTips.get(i).getAddress();*/
       /* if(address == null || address.equals("")){
            holder.mAddress.setVisibility(View.GONE);
        }else{
            holder.mAddress.setVisibility(View.VISIBLE);
            holder.mAddress.setText(address);
        }*/

        return view;
    }

    class Holder {
        TextView mName;
        TextView mAddress;
    }
}
