package com.young.jdmall.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.ui.activity.SecKillActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/31.
 */

public class SeckillHeadRvAdapter extends RecyclerView.Adapter {
    private SecKillActivity mActivity;

    public SeckillHeadRvAdapter(SecKillActivity activity) {
        mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headImagView = LayoutInflater.from(mActivity).inflate(R.layout.item_seckill_head_imag, parent, false);
        return new SeckillHeadViewHolder(headImagView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SeckillHeadViewHolder)holder).bindView();
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class SeckillHeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_seckill_head_img)
        ImageView mIvSeckillHeadImg;

        SeckillHeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView() {
            
        }
    }
}
