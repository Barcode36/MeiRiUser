package com.example.meiriuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.model.OrderModel;
import com.example.meiriuser.ui.activity.FoodActivity;
import com.example.meiriuser.ui.activity.TakeOutFoodActivity;
import com.example.meiriuser.until.CommanUtils;
import com.example.meiriuser.until.Constant;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<OrderModel.DataBeanX.DataBean> orderModels;
    private onItemOrderClick mOnItemClickListener;
    private String orderState;

    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }
    public OrderAdapter(Context context,List<OrderModel.DataBeanX.DataBean> orderModels,String orderState,onItemOrderClick mOnItemClickListener){
        this.orderModels = orderModels;
        this.context = context;
        this.mOnItemClickListener=mOnItemClickListener;
        mLayoutInflater = LayoutInflater.from(context);
        this.orderState=orderState;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         //加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.item_order_food, parent, false));
        } else {
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item_order_group, parent, false));
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Item1ViewHolder) {
            final Item1ViewHolder viewHolder1=(Item1ViewHolder) holder;
            viewHolder1.tvTakeawayOrder.setText(String.format(context.getString(R.string.text_takeaway_order_num), orderModels.get(position).getOrder_sn()));//订单号
            viewHolder1.tvHour.setText(orderModels.get(position).getAdd_time());//时间
            viewHolder1.tvShopName.setText(orderModels.get(position).getStore()!=null?orderModels.get(position).getStore().getName():"");
            viewHolder1.tvDistribuFee.setText(String.format(context.getResources().getString(R.string.text_price), CommanUtils.doubleTrans(Double.valueOf(orderModels.get(position).getShipping_fee()))+""));//配送费
            viewHolder1.tvReductionPrice.setText(String.format(context.getResources().getString(R.string.text_reduct_price),String.valueOf(orderModels.get(position).getDiscount())));//满减优惠

            List<OrderModel.DataBeanX.DataBean.GoodsBean> goodsBean=orderModels.get(position).getGoods();
            int goodNum=0;
            for(int i=0;i<goodsBean.size();i++){
                goodNum+=goodsBean.get(i).getGoods_number();
            }
            viewHolder1.tvTotalCommodity.setText(String.format(context.getResources().getString(R.string.text_total_comodity),goodNum+""));//商品件数
            viewHolder1.tvTotalPrice.setText(String.format(context.getResources().getString(R.string.text_price),CommanUtils.doubleTrans(Double.valueOf(orderModels.get(position).getOrder_amount()))+""));//实付

            viewHolder1.tvDeliveryState.setText(orderModels.get(position).getStatus_name());//订单状态
            viewHolder1.rvOrderDetails.setLayoutManager(new LinearLayoutManager(context));
            OrderGoodsFinishAdapter goodsFinishAdapter=new OrderGoodsFinishAdapter(orderModels.get(position).getGoods());
            viewHolder1.rvOrderDetails.setAdapter(goodsFinishAdapter);
            goodsFinishAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int pos) {
                    int itemViewId = view.getId();
                    switch (itemViewId) {
                        case R.id.line_item:
                            if(mOnItemClickListener!=null){
                                mOnItemClickListener.onSeeOrderClick(orderModels.get(position).getOrder_id()+"",orderModels.get(position).getPay_status()+""
                                ,orderModels.get(position).getOrder_status()+"",orderModels.get(position).getShipping_status()+"");
                            }
                            break;

                    }
                }
            });

            viewHolder1.lineSeeFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onSeeBusClick(orderModels.get(position).getStore().getStore_id());
                    }
                }
            });
            viewHolder1.lineSeeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onSeeOrderClick(orderModels.get(position).getOrder_id()+"",orderModels.get(position).getPay_status()+""
                                ,orderModels.get(position).getOrder_status()+"",orderModels.get(position).getShipping_status()+"");
                    }
                }
            });


            if(orderState.equals("已完成")) {
                viewHolder1.ivOpen.setVisibility(View.GONE);
                viewHolder1.tvOrderState.setVisibility(View.VISIBLE);
                viewHolder1.tvOrderState.setText("已完成");

                int isComment=orderModels.get(position).getIs_comment();
                if(isComment==0){
                    viewHolder1.tvDeliveryState.setVisibility(View.INVISIBLE);
                    viewHolder1.btnCancelOrder.setText("评价");
                    viewHolder1.btnCancelOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(mOnItemClickListener!=null){
                                mOnItemClickListener.onEvaluateOrderClick("food",orderModels.get(position).getOrder_id()+"");
                            }
                        }
                    });
                }else {
                    viewHolder1.tvDeliveryState.setVisibility(View.VISIBLE);
                    viewHolder1.btnCancelOrder.setVisibility(View.GONE);
                    viewHolder1.tvDeliveryState.setText("评价成功");
                }

            }else {
                viewHolder1.ivOpen.setVisibility(View.VISIBLE);
                viewHolder1.tvOrderState.setVisibility(View.GONE);
                viewHolder1.tvDeliveryState.setVisibility(View.VISIBLE);
                viewHolder1.btnCancelOrder.setText("取消订单");
                viewHolder1.btnCancelOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mOnItemClickListener!=null){
                            mOnItemClickListener.onCancelOrderClick(orderModels.get(position).getOrder_id()+"",position);
                        }
                    }
                });
            }

        } else if (holder instanceof Item2ViewHolder) {
            Item2ViewHolder viewHolder2=(Item2ViewHolder) holder;
            viewHolder2.tvTakeawayOrder.setText(String.format(context.getString(R.string.text_takeaway_order_num), orderModels.get(position).getOrder_sn()));//订单号
            viewHolder2.tvHour.setText(orderModels.get(position).getAdd_time());//时间
            viewHolder2.tvShopName.setText(orderModels.get(position).getStore()!=null?orderModels.get(position).getStore().getName():"");
            viewHolder2.tvTotalPrice.setText(String.format(context.getResources().getString(R.string.text_price),CommanUtils.doubleTrans(Double.valueOf(orderModels.get(position).getOrder_amount()))+""));//实付
            viewHolder2.rvOrderDetails.setLayoutManager(new LinearLayoutManager(context));
            viewHolder2.rvOrderDetails.setAdapter(new OrderGoodsFinishAdapter(orderModels.get(position).getGoods()));
            List<OrderModel.DataBeanX.DataBean.GoodsBean> goodsBean=orderModels.get(position).getGoods();
            int goodNum=0;
            for(int i=0;i<goodsBean.size();i++){
                goodNum+=goodsBean.get(i).getGoods_number();
            }
            viewHolder2.tvTotalCommodity.setText(String.format(context.getResources().getString(R.string.text_total_comodity),goodNum+""));//商品件数
            if(orderState.equals("已完成")) {
                viewHolder2.ivOpen.setVisibility(View.GONE);
                viewHolder2.tvOrderState.setVisibility(View.VISIBLE);
                viewHolder2.tvOrderState.setText("已完成");
                viewHolder2.btnEvaluate.setVisibility(View.VISIBLE);

            }else {
                viewHolder2.ivOpen.setVisibility(View.VISIBLE);
                viewHolder2.tvOrderState.setVisibility(View.GONE);
                viewHolder2.btnEvaluate.setVisibility(View.GONE);
            }

            viewHolder2.btnCouponCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onCouponCodeClick(position);
                    }
                }
            });

            viewHolder2.btnEvaluate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onEvaluateOrderClick("group",orderModels.get(position).getOrder_id()+"");
                    }
                }
            });




        }
    }

    @Override
    public int getItemViewType(int position) {
         //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        return orderModels.get(position).getType()==1 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }
    @Override
    public int getItemCount() {
        return orderModels == null ? 0 : orderModels.size();
    }

    //item1 的ViewHolder
    public static class Item1ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTakeawayOrder;
        TextView tvHour;
        TextView tvShopName;
        TextView tvDistribuFee;
        TextView tvReductionPrice;
        TextView tvTotalCommodity;
        TextView tvTotalPrice;
        TextView tvDeliveryState;
        RecyclerView rvOrderDetails;
        Button btnCancelOrder;
        ImageView ivOpen;
        TextView tvOrderState;
        LinearLayout lineSeeFood;
        LinearLayout lineSeeOrder;
        public Item1ViewHolder(View itemView) {
            super(itemView);
            tvTakeawayOrder=(TextView)itemView.findViewById(R.id.tv_takeaway_order);
            tvHour=(TextView)itemView.findViewById(R.id.tv_hour);
            tvShopName=(TextView)itemView.findViewById(R.id.tv_shop_name);
            tvDistribuFee=(TextView)itemView.findViewById(R.id.tv_distribu_fee);
            tvReductionPrice=(TextView)itemView.findViewById(R.id.tv_full_reduction_price);
            tvTotalCommodity=(TextView)itemView.findViewById(R.id.tv_total_commodity);
            tvTotalPrice=(TextView)itemView.findViewById(R.id.tv_total_price);
            tvDeliveryState=(TextView)itemView.findViewById(R.id.tv_delivery_state);
            rvOrderDetails=(RecyclerView)itemView.findViewById(R.id.rv_order_details);
            btnCancelOrder=(Button)itemView.findViewById(R.id.btn_cancel_order);
            ivOpen=(ImageView)itemView.findViewById(R.id.iv_open);
            tvOrderState=(TextView)itemView.findViewById(R.id.tv_order_state);
            lineSeeFood=(LinearLayout)itemView.findViewById(R.id.line_see_food);
            lineSeeOrder=(LinearLayout)itemView.findViewById(R.id.line_see_order);
        }
    }
    //item2 的ViewHolder
    public static class Item2ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTakeawayOrder;
        TextView tvHour;
        TextView tvShopName;
        TextView tvTotalCommodity;
        TextView tvTotalPrice;
        RecyclerView rvOrderDetails;
        Button btnEvaluate;
        Button btnCouponCode;
        ImageView ivOpen;
        TextView tvOrderState;
        public Item2ViewHolder(View itemView) {
            super(itemView);
            tvTakeawayOrder=(TextView)itemView.findViewById(R.id.tv_takeaway_order);
            tvHour=(TextView)itemView.findViewById(R.id.tv_hour);
            tvShopName=(TextView)itemView.findViewById(R.id.tv_shop_name);
            tvTotalCommodity=(TextView)itemView.findViewById(R.id.tv_total_commodity);
            tvTotalPrice=(TextView)itemView.findViewById(R.id.tv_total_price);
            rvOrderDetails=(RecyclerView)itemView.findViewById(R.id.rv_order_details);
            btnCouponCode=(Button)itemView.findViewById(R.id.btn_coupon_code);
            btnEvaluate=(Button)itemView.findViewById(R.id.btn_evaluate);
            ivOpen=(ImageView)itemView.findViewById(R.id.iv_open);
            tvOrderState=(TextView)itemView.findViewById(R.id.tv_order_state);
        }
    }

    public interface onItemOrderClick{
        void onSeeBusClick(int storeID);
        void onSeeOrderClick(String order_id,String payStatus,String orderStatus,String shipping);
        void onEvaluateOrderClick(String type,String order_id);
        void onCancelOrderClick(String order_id,int postion);
        void onCouponCodeClick(int postion);
    }

}
