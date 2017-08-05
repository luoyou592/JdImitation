package com.young.jdmall.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.jdmall.R;

/**
 * Created by 钟志鹏 on 2017/8/3.
 */

public class RecyclerMoreView extends RecyclerView {
    private int mItemLastPosition = 0;
    private OnRefreshListener mRefreshListener;
    private boolean mIsExceed;
    private boolean mIsLoading;
    private Adapter mAdapter;

    public RecyclerMoreView(Context context) {
        this(context, null);
    }

    public RecyclerMoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LayoutManager layoutManager = getLayoutManager();
                if (layoutManager != null) {
                    if (layoutManager instanceof LinearLayoutManager) {
                        int position = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                        if ((getAdapter().getItemCount() - mItemLastPosition - 1 <= position) && !mIsExceed) {
                            mIsExceed = true;
                            if (mRefreshListener != null) {
                                loadMoreData();
                                setLoadItemStart(mIsLoading);
                            }
                        }
                        if (getAdapter().getItemCount() - mItemLastPosition - 1 > position) {
                            mIsExceed = false;
                        }

                    }
                }
            }
        });
    }

    private void setLoadItemStart(boolean b) {
        mAdapter = (Adapter) getAdapter();
        if (mAdapter != null) {
            mAdapter.setLoadItemStart(b);
            mAdapter.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadMoreData();
                    setLoadItemStart(mIsLoading);
                }
            });
        }
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter instanceof Adapter) {
            super.setAdapter(adapter);
        } else {
            throw new RuntimeException("使用RecyclerLoadMoreView.Adapter");
        }
    }

    public void onLoadFinish() {
        mIsLoading = false;
        setLoadItemStart(mIsLoading);
        setLoadItemStart(mIsLoading);
    }

    private void loadMoreData() {
        if (!mIsLoading) {
            mRefreshListener.onRefresh();
            mIsLoading = true;
        }
    }

    /**
     * 滑动到倒数第几个条目开始刷新
     *
     * @param itemLastPosition
     */
    public void setLoadMoreOpportunity(int itemLastPosition) {
        mItemLastPosition = itemLastPosition;
    }

    public void setOnRefreshListener(OnRefreshListener l) {
        mRefreshListener = l;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public static abstract class Adapter extends RecyclerView.Adapter {

        private static final int KEY_ITEM_TYPE_LOADING = 707001;
        public static View mLoadingView;
        private LinearLayout mLoading;
        private TextView mLoaded;
        private OnRefreshListener mRefreshListener;

        @Override
        public final ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mLoadingView == null) {
                mLoadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_loading, parent, false);
                mLoadingView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mRefreshListener != null) {
                            mRefreshListener.onRefresh();
                        }
                    }
                });
            }
            if (viewType == KEY_ITEM_TYPE_LOADING) {
                return new ViewHolderFromLoading(mLoadingView);
            } else {
                return onCreateViewHolderToRecyclerLoadMoreView(parent, viewType);
            }
        }


        @Override
        public final void onBindViewHolder(ViewHolder holder, int position) {
            if (getItemViewType(position) == KEY_ITEM_TYPE_LOADING) {
                ((ViewHolderFromLoading) holder).bindView();
            } else {
                onBindViewHolderToRecyclerLoadMoreView(holder, position);
            }
        }


        @Override
        public final int getItemCount() {
            return getItemCountToRecyclerLoadMoreView() + 1;
        }


        @Override
        public final int getItemViewType(int position) {
            if (position == getItemCountToRecyclerLoadMoreView()) {
                return KEY_ITEM_TYPE_LOADING;
            }
            return getItemViewTypeToRecyclerLoadMoreView(position);
        }


        protected abstract ViewHolder onCreateViewHolderToRecyclerLoadMoreView(ViewGroup parent, int viewType);

        protected abstract void onBindViewHolderToRecyclerLoadMoreView(ViewHolder holder, int position);

        protected abstract int getItemCountToRecyclerLoadMoreView();

        protected abstract int getItemViewTypeToRecyclerLoadMoreView(int position);

        public void setLoadItemStart(boolean b) {
            if (mLoadingView != null) {
                mLoading = (LinearLayout) mLoadingView.findViewById(R.id.loading);
                mLoaded = (TextView) mLoadingView.findViewById(R.id.loaded);
                if (b) {
                    mLoaded.setVisibility(GONE);
                    mLoading.setVisibility(VISIBLE);
                } else {
                    mLoaded.setVisibility(VISIBLE);
                    mLoading.setVisibility(GONE);
                }
            }
        }

        public void setOnRefreshListener(OnRefreshListener l) {
            mRefreshListener = l;
        }
    }

    static class ViewHolderFromLoading extends ViewHolder {

        public ViewHolderFromLoading(View itemView) {
            super(itemView);
        }

        public void bindView() {

        }
    }

}
