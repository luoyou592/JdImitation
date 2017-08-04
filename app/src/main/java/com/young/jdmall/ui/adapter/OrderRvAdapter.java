package com.young.jdmall.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/8/2.
 */

public class OrderRvAdapter extends RecyclerView.Adapter {

    private Activity mActivity;

    public OrderRvAdapter(Activity activity) {

        mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View orderView = LayoutInflater.from(mActivity).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(orderView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OrderViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_order_delete)
        ImageView mIvOrderDelete;
        @BindView(R.id.iv_order_pic)
        ImageView mIvOrderPic;
        @BindView(R.id.tv_order_desc)
        TextView mTvOrderDesc;
        @BindView(R.id.tv_order_price)
        TextView mTvOrderPrice;

        OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(int position) {

        }
    }
}
