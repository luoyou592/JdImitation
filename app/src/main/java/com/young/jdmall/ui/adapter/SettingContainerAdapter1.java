package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.bean.FindInfoBean;
import com.young.jdmall.ui.activity.NewsDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 钟志鹏 on 2017/7/31.
 */

public class SettingContainerAdapter1 extends BaseSettingContainerAdapter {


    private ArrayList<FindInfoBean> mFinInfoBeanList;

    public SettingContainerAdapter1(ArrayList<FindInfoBean> finInfoBeanList) {
        mFinInfoBeanList = finInfoBeanList;
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 1:
                ((ViewHolderFormType1) holder).bindView(mFinInfoBeanList.get(position));
                break;
            case 2:
                ((ViewHolderFormType2) holder).bindView(mFinInfoBeanList.get(position));
                break;
            case 3:
                ((ViewHolderFormType3) holder).bindView(mFinInfoBeanList.get(position));
                break;
        }
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ViewHolderFormType1(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_type_1, parent, false));
            case 2:
                return new ViewHolderFormType2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_type_2, parent, false));
            case 3:
                return new ViewHolderFormType3(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_type_3, parent, false));
        }
        return null;
    }

    @Override
    protected int itemCount() {
        return mFinInfoBeanList.size();
    }

    @Override
    public int itemViewType(int position) {
        return mFinInfoBeanList.get(position).type;
    }

    class ViewHolderFormType1 extends RecyclerView.ViewHolder {
        @BindView(R.id.item_setting_container_title)
        TextView mItemSettingContainerTitle;
        @BindView(R.id.item_setting_container_icon)
        ImageView mItemSettingContainerIcon;
        @BindView(R.id.item_setting_container_name)
        TextView mItemSettingContainerName;
        @BindView(R.id.item_setting_container_visits)
        TextView mItemSettingContainerVisits;
        @BindView(R.id.item_setting_container_image)
        ImageView mItemSettingContainerImage;

        public ViewHolderFormType1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(final FindInfoBean findInfoBean) {
            mItemSettingContainerTitle.setText(findInfoBean.title);
            Glide.with(mItemSettingContainerIcon.getContext()).load(findInfoBean.Icon).bitmapTransform(new CropCircleTransformation(mItemSettingContainerIcon.getContext())).into(mItemSettingContainerIcon);
            mItemSettingContainerName.setText(findInfoBean.name);
            mItemSettingContainerVisits.setText(findInfoBean.visitsSum + "人浏览");
            Glide.with(mItemSettingContainerImage.getContext()).load(findInfoBean.image1).into(mItemSettingContainerImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    intent.putExtra("icon", findInfoBean.Icon);
                    intent.putExtra("name", findInfoBean.name);
                    intent.putExtra("title", findInfoBean.title);
                    intent.putExtra("detail", findInfoBean.detail);

                    context.startActivity(intent);
                }
            });

        }
    }

//    class ViewHolderFormType3 extends RecyclerView.ViewHolder {
//        @BindView(R.id.live_animation_list)
//        ImageView mLiveAnimationList;
//        private AnimationDrawable mDrawable;
//
//        public ViewHolderFormType3(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//        public void bindView() {
//            if (mDrawable == null) {
//                mDrawable = (AnimationDrawable) mLiveAnimationList.getBackground();
//            }
//            mDrawable.start();
//        }
//    }

    static class ViewHolderFormType2 extends RecyclerView.ViewHolder {
        @BindView(R.id.item_setting_container_title)
        TextView mItemSettingContainerTitle;
        @BindView(R.id.item_setting_container_image)
        ImageView mItemSettingContainerImage;
        @BindView(R.id.item_setting_container_icon)
        ImageView mItemSettingContainerIcon;
        @BindView(R.id.item_setting_container_name)
        TextView mItemSettingContainerName;
        @BindView(R.id.item_setting_container_visits)
        TextView mItemSettingContainerVisits;

        public ViewHolderFormType2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(FindInfoBean findInfoBean) {
            mItemSettingContainerTitle.setText(findInfoBean.title);
            Glide.with(mItemSettingContainerIcon.getContext()).load(findInfoBean.Icon).bitmapTransform(new CropCircleTransformation(mItemSettingContainerIcon.getContext())).into(mItemSettingContainerIcon);
            mItemSettingContainerName.setText(findInfoBean.name);
            mItemSettingContainerVisits.setText(findInfoBean.visitsSum + "人浏览");
            Glide.with(mItemSettingContainerImage.getContext()).load(findInfoBean.image1).into(mItemSettingContainerImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }

    static class ViewHolderFormType3 extends RecyclerView.ViewHolder {
        @BindView(R.id.item_setting_container_title)
        TextView mItemSettingContainerTitle;
        @BindView(R.id.item_setting_container_image1)
        ImageView mItemSettingContainerImage1;
        @BindView(R.id.item_setting_container_image2)
        ImageView mItemSettingContainerImage2;
        @BindView(R.id.item_setting_container_image3)
        ImageView mItemSettingContainerImage3;
        @BindView(R.id.item_setting_container_icon)
        ImageView mItemSettingContainerIcon;
        @BindView(R.id.item_setting_container_name)
        TextView mItemSettingContainerName;
        @BindView(R.id.item_setting_container_visits)
        TextView mItemSettingContainerVisits;

        public ViewHolderFormType3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(FindInfoBean findInfoBean) {
            mItemSettingContainerTitle.setText(findInfoBean.title);
            Glide.with(mItemSettingContainerIcon.getContext()).load(findInfoBean.Icon).bitmapTransform(new CropCircleTransformation(mItemSettingContainerIcon.getContext())).into(mItemSettingContainerIcon);
            mItemSettingContainerName.setText(findInfoBean.name);
            mItemSettingContainerVisits.setText(findInfoBean.visitsSum + "人浏览");
            Glide.with(mItemSettingContainerImage1.getContext()).load(findInfoBean.image1).into(mItemSettingContainerImage1);
            Glide.with(mItemSettingContainerImage2.getContext()).load(findInfoBean.image2).into(mItemSettingContainerImage2);
            Glide.with(mItemSettingContainerImage3.getContext()).load(findInfoBean.image3).into(mItemSettingContainerImage3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
