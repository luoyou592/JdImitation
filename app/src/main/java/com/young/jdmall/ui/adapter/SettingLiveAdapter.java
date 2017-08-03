package com.young.jdmall.ui.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.bean.FindInfoBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 钟志鹏 on 2017/7/31.
 */

public class SettingLiveAdapter extends BaseSettingContainerAdapter {


    private ArrayList<FindInfoBean> mFinInfoBeanList;

    public SettingLiveAdapter(ArrayList<FindInfoBean> finInfoBeanList) {
        mFinInfoBeanList = finInfoBeanList;
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mFinInfoBeanList.get(position));
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_type_4, parent, false));
    }

    @Override
    protected int itemCount() {
        return mFinInfoBeanList.size();
    }

    @Override
    public int itemViewType(int position) {
        return mFinInfoBeanList.get(position).type;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.live_animation_list)
        ImageView mLiveAnimationList;
        private AnimationDrawable mDrawable;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(FindInfoBean findInfoBean) {
            if (mDrawable == null) {
                mDrawable = (AnimationDrawable) mLiveAnimationList.getBackground();
            }
            mDrawable.start();
        }
    }


}
