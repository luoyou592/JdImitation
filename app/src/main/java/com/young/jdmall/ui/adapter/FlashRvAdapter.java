package com.young.jdmall.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.ui.activity.SecKillActivity;
import com.young.jdmall.ui.utils.PriceFormater;
import com.young.jdmall.ui.widget.CountDownView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/31.
 */

public class FlashRvAdapter extends RecyclerView.Adapter {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_LAST = 1;

    private Activity mActivity;
    private CountDownView mCountDownView;
    private LimitbuyBean mLimitbuyBean;

    //countDownview，為了传当前倒计时
    public FlashRvAdapter(Activity activity, CountDownView countDownView, LimitbuyBean limitbuyBean) {
        mActivity = activity;
        mCountDownView = countDownView;
        mLimitbuyBean = limitbuyBean;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO: 2017/7/31
        //最后一个的布局
        if (position == mLimitbuyBean.getListCount()) {
            return TYPE_LAST;
        } else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View goodsView = LayoutInflater.from(mActivity).inflate(R.layout.item_goods, parent, false);
            return new GoodsViewHolder(goodsView);
        } else {
            View lastView = LayoutInflater.from(mActivity).inflate(R.layout.item_goods_last, parent, false);
            return new LastViewHolder(lastView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != mLimitbuyBean.getListCount()) {  //判断为最后一个条目
            ((GoodsViewHolder) holder).bindView(position);
        } else {
            ((LastViewHolder) holder).bindView();
        }
    }

    @Override
    public int getItemCount() {
        if (mLimitbuyBean != null) {
            return mLimitbuyBean.getListCount() + 1; //加上最后一个底部
        }
        return 0;
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_limit)
        ImageView mIvLimit;
        @BindView(R.id.tv_new_price)
        TextView mTvNewPrice;
        @BindView(R.id.tv_old_price)
        TextView mTvOldPrice;
        @BindView(R.id.flash_container)
        LinearLayout mFlashContainer;

        GoodsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, SecKillActivity.class);
                    intent.putExtra("time", mCountDownView.getTime());
                    mActivity.startActivity(intent);
                }
            });
        }

        public void bindView(int position) {
            LimitbuyBean.ProductListBean limitProductBean = mLimitbuyBean.getProductList().get(position);
            Glide.with(mActivity).load(Constant.BASE_URL+limitProductBean.getPic()).placeholder(R.mipmap.default_small_pic).into(mIvLimit);
            mTvNewPrice.setText(PriceFormater.format(limitProductBean.getLimitPrice()));
            mTvOldPrice.setText(PriceFormater.format(limitProductBean.getPrice()));
            mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//价格下划线
        }
    }

    class LastViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_last)
        TextView mTvLast;

        LastViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, SecKillActivity.class);
                    intent.putExtra("time", mCountDownView.getTime());
                    mActivity.startActivity(intent);
                }
            });
        }

        public void bindView() {

        }
    }
}
