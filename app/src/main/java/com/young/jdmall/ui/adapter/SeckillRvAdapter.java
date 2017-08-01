package com.young.jdmall.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.ui.activity.SecKillActivity;
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
        if (position==0){
            ((HeadViewHolder)holder).bindView();
        }else{
            ((NormalViewHolder)holder).bindView();
        }
    }

    @Override
    public int getItemCount() {
        return 15;
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
            if (isFirst){
                //开启倒计时  mtime从首页传过来的
                mSeckillCountDownView.setTime(mTime);
                mSeckillCountDownView.startCountDown();
                isFirst = false;
            }
            mSeckillHeadRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL,false));
            SeckillHeadRvAdapter headRvAdapter = new SeckillHeadRvAdapter(mActivity);
            mSeckillHeadRecyclerView.setAdapter(headRvAdapter);

        }
    }

    static class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_seckill_img)
        ImageView mIvSeckillImg;
        @BindView(R.id.tv_seckill_price)
        TextView mTvSeckillPrice;

        NormalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bindView() {

        }
    }
}
