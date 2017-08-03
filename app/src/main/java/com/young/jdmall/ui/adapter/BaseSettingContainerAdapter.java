package com.young.jdmall.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.jdmall.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 钟志鹏 on 2017/7/31.
 */

public abstract class BaseSettingContainerAdapter extends RecyclerView.Adapter {

    private static final int KEY_ITEM_TYPE_LOADING = 707001;
    private View mLoadingView;

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == KEY_ITEM_TYPE_LOADING) {
            mLoadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_loading, parent, false);
            return new ViewHolderFromLoading(mLoadingView);
        } else {
            return getViewHolder(parent, viewType);
        }
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == KEY_ITEM_TYPE_LOADING) {
            ((ViewHolderFromLoading) holder).bindView();
        } else {
            bindView(holder, position);
        }
    }

    protected abstract void bindView(RecyclerView.ViewHolder holder, int position);

    @Override
    public final int getItemCount() {
        return itemCount() + 1;
    }

    @Override
    public final int getItemViewType(int position) {
        if (position == itemCount()) {
            return KEY_ITEM_TYPE_LOADING;
        }
        return itemViewType(position);
    }

    public int itemViewType(int position) {
        return 0;
    }

    protected abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType);

    protected abstract int itemCount();


    class ViewHolderFromLoading extends RecyclerView.ViewHolder {
        @BindView(R.id.loading)
        LinearLayout mLoading;
        @BindView(R.id.loaded)
        TextView mLoaded;

        public ViewHolderFromLoading(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            View view = mLoading.getChildAt(0);
        }

        public void bindView() {

        }
    }
}
