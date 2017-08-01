package com.young.jdmall.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.BrandInfoBean;
import com.young.jdmall.ui.activity.SecKillActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/31.
 */

public class SeckillHeadRvAdapter extends RecyclerView.Adapter {
    private SecKillActivity mActivity;
    private BrandInfoBean mBrandInfoBean;

    public SeckillHeadRvAdapter(SecKillActivity activity, BrandInfoBean brandInfoBean) {
        mActivity = activity;
        mBrandInfoBean = brandInfoBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headImagView = LayoutInflater.from(mActivity).inflate(R.layout.item_seckill_head_imag, parent, false);
        return new SeckillHeadViewHolder(headImagView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SeckillHeadViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        if (mBrandInfoBean==null){
            return 0;
        }
        return mBrandInfoBean.brand.get(4).getValue().size();  //取其中一个组图
    }

    class SeckillHeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_seckill_head_img)
        ImageView mIvSeckillHeadImg;

        SeckillHeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(int position) {
            Log.d("luoyou", "url"+mBrandInfoBean.brand.get(4).getValue().get(position).getPic());
            Glide.with(mActivity).load(Constant.BASE_URL+mBrandInfoBean.brand.get(4).getValue().get(position).getPic()).into(mIvSeckillHeadImg);
        }
    }
}
