package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.OrderInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/31 0031 17:36
 *  描述：    TODO
 */
public class AllOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public AllOrderAdapter(Context context) {
        mContext = context;
    }

    private List<OrderInfoBean> mAllOrderBeanList = new ArrayList<>();

    public void setAddressBeanList(List<OrderInfoBean> orderInfoBeanList) {
        mAllOrderBeanList = orderInfoBeanList;
        Log.d(TAG, "setAddressBeanList: "+mAllOrderBeanList.size());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_all_order, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mAllOrderBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mAllOrderBeanList != null) {
            return mAllOrderBeanList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_goods)
        LinearLayout mLlGoods;
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_desc)
        TextView mTvDesc;
        @BindView(R.id.tv_pay)
        TextView mTvPay;
        @BindView(R.id.tv_num)
        TextView mTvNum;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(OrderInfoBean orderInfoBean) {
//            String url = orderInfoBean.getIcon();
//            Picasso.with(mContext).load(url).into(mIvIcon);
            mTvDesc.setText(orderInfoBean.getDesc());

            mTvNum.setText("共" + orderInfoBean.getNum() + "件商品");
            mTvPay.setText("实付款：￥"+ orderInfoBean.getPrice());

        }
    }
}
