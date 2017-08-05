package com.young.jdmall.ui.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.bean.GirlInfoBean;
import com.young.jdmall.ui.view.RecyclerLoadMoreView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 钟志鹏 on 2017/8/5.
 */

class SettingLiveLoadMoreAdapter extends RecyclerLoadMoreView.Adapter {
    private ArrayList<GirlInfoBean.AllItemsBean> mFinInfoBeanList = new ArrayList<>();

    public void addData(List<GirlInfoBean.AllItemsBean> finInfoBeanList) {
        mFinInfoBeanList.addAll(finInfoBeanList);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolderToRecyclerLoadMoreView(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_type_4, parent, false));
    }

    @Override
    protected void onBindViewHolderToRecyclerLoadMoreView(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mFinInfoBeanList.get(position));
    }

    @Override
    protected int getItemCountToRecyclerLoadMoreView() {
        return mFinInfoBeanList.size();
    }

    @Override
    protected int getItemViewTypeToRecyclerLoadMoreView(int position) {
        return 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_setting_container_image)
        ImageView mItemSettingContainerImage;
        @BindView(R.id.live_animation_list)
        ImageView mLiveAnimationList;
        @BindView(R.id.item_setting_container_icon)
        ImageView mItemSettingContainerIcon;
        @BindView(R.id.item_setting_container_name)
        TextView mItemSettingContainerName;
        @BindView(R.id.item_setting_container_title)
        TextView mItemSettingContainerTitle;
        @BindView(R.id.item_setting_container_count)
        TextView mItemSettingContainerCount;
        private AnimationDrawable mDrawable;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(GirlInfoBean.AllItemsBean findInfoBean) {
            if (mDrawable == null) {
                mDrawable = (AnimationDrawable) mLiveAnimationList.getBackground();
            }
            mDrawable.start();
            Glide.with(mItemSettingContainerImage.getContext()).load(findInfoBean.getOri_pic_url()).into(mItemSettingContainerImage);
            Glide.with(mItemSettingContainerIcon.getContext()).load(findInfoBean.getOri_pic_url()).bitmapTransform(new CropCircleTransformation(mItemSettingContainerIcon.getContext())).into(mItemSettingContainerIcon);
            mItemSettingContainerTitle.setText(findInfoBean.getTitle());
            List<String> tags = findInfoBean.getTags();
            if (tags != null) {
                mItemSettingContainerName.setText(tags.get(0));
            }
            mItemSettingContainerCount.setText(new Random().nextInt(3000) + 3000 + "人");
        }
    }
}

