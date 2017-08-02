package com.young.jdmall.ui.adapter;

import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.BrandInfoBean;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.ui.activity.SecKillActivity;
import com.young.jdmall.ui.utils.PriceFormater;
import com.young.jdmall.ui.widget.CountDownView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/31.
 */

public class SeckillRvAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_NORMAL = 1;

    private boolean isFirst = true;  //控制重复调用倒计时
    private SecKillActivity mActivity;
    private long mTime;
    private BrandInfoBean mBrandInfoBean;
    private LimitbuyBean mLimitbuyBean;

    public SeckillRvAdapter(SecKillActivity activity, long time) {
        mActivity = activity;
        mTime = time;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View headView = LayoutInflater.from(mActivity).inflate(R.layout.item_seckill_head, parent, false);
            return new HeadViewHolder(headView);
        } else {
            View normalView = LayoutInflater.from(mActivity).inflate(R.layout.item_seckill_normal, parent, false);
            return new NormalViewHolder(normalView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((HeadViewHolder) holder).bindView();
        } else {
            ((NormalViewHolder) holder).bindView(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mLimitbuyBean!=null){
            return mLimitbuyBean.getListCount()+1;
        }
        return 0;
    }

    public void setBrandData(BrandInfoBean brandInfoBean) {
        mBrandInfoBean = brandInfoBean;
    }

    public void setLimitProductData(LimitbuyBean limitbuyBean) {
        mLimitbuyBean = limitbuyBean;
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.seckill_head_recycler_view)
        RecyclerView mSeckillHeadRecyclerView;
        @BindView(R.id.seckill_count_down_view)
        CountDownView mSeckillCountDownView;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView() {
            if (isFirst) {
                //开启倒计时  mtime从首页传过来的
                mSeckillCountDownView.setTime(mTime);
                mSeckillCountDownView.startCountDown();
                isFirst = false;
            }
            mSeckillHeadRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
            SeckillHeadRvAdapter headRvAdapter = new SeckillHeadRvAdapter(mActivity, mBrandInfoBean);
            mSeckillHeadRecyclerView.setAdapter(headRvAdapter);

        }
    }

     class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_seckill_img)
        ImageView mIvSeckillImg;
        @BindView(R.id.tv_seckill_title)
        TextView mTvSeckillTitle;
        @BindView(R.id.tv_seckill_price)
        TextView mTvSeckillPrice;
        @BindView(R.id.tv_normal_price)
        TextView mTvNormalPrice;

        NormalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(int position) {
            LimitbuyBean.ProductListBean productListBean = mLimitbuyBean.getProductList().get(position-1);
            mTvSeckillTitle.setText(productListBean.getName());
            mTvNormalPrice.setText(PriceFormater.format(productListBean.getPrice()));
            mTvNormalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//价格下划线
            mTvSeckillPrice.setText(PriceFormater.format(productListBean.getLimitPrice()));
            Glide.with(mActivity).load(Constant.BASE_URL+productListBean.getPic()).into(mIvSeckillImg);
        }
    }
}
