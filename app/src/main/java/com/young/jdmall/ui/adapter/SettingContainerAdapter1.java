package com.young.jdmall.ui.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 钟志鹏 on 2017/7/31.
 */

public class SettingContainerAdapter1 extends BaseSettingContainerAdapter {


    @Override
    protected RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_type_1, parent, false);
        return new ViewHolderFormType1(view);
    }

    @Override
    protected int itemCount() {
        return 10;
    }

    class ViewHolderFormType1 extends RecyclerView.ViewHolder {

        public ViewHolderFormType1(View itemView) {
            super(itemView);
        }
    }

    class ViewHolderFormType2 extends RecyclerView.ViewHolder {
        @BindView(R.id.live_animation_list)
        ImageView mLiveAnimationList;
        private AnimationDrawable mDrawable;

        public ViewHolderFormType2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView() {
            if (mDrawable == null) {
                mDrawable = (AnimationDrawable) mLiveAnimationList.getBackground();
            }
            mDrawable.start();
        }
    }
}
