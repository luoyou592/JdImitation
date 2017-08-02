package com.young.jdmall.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;

/**
 * Created by 钟志鹏 on 2017/8/2.
 */

public class RecyclerRefreshLayout extends RelativeLayout {

    private static final String TAG = "RecyclerRefreshLayout";
    private RecyclerView mRecyclerView;
    private float mStartX;
    private float mStartY;
    private View mProgressView;
    private ImageView mPeople;
    private ImageView mGoods;
    private TextView mText;
    private LinearLayout mLoading;
    private RelativeLayout mStartLoad;
    private ImageView mLoadIng;
    private AnimationDrawable mDrawable;
    private boolean mRefreshing;
    private OnRefreshListener mListener;

    public RecyclerRefreshLayout(Context context) {
        this(context, null);
    }

    public RecyclerRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createProgressView();
        insureChildViewCount();
    }

    private boolean isChildViewGetToTop() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof RecyclerView) {
                mRecyclerView = (RecyclerView) view;
                break;
            }
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int position = layoutManager.findFirstCompletelyVisibleItemPosition();
//        Log.d(TAG, "insureChildViewGetToTop: " + position);

        if (position <= 0) {
            return true;
        }
        return false;
    }

    private void insureChildViewCount() {
        int childCount = getChildCount();
        if (childCount > 2) {
            throw new RuntimeException("只能有一个Child");
        }
    }

    private void createProgressView() {
        mProgressView = View.inflate(getContext(), R.layout.view_load_more, null);
        mGoods = (ImageView) mProgressView.findViewById(R.id.goods);
        mPeople = (ImageView) mProgressView.findViewById(R.id.people);
        mLoading = (LinearLayout) mProgressView.findViewById(R.id.loading);
        mText = (TextView) mProgressView.findViewById(R.id.text_hint);
        mStartLoad = (RelativeLayout) mProgressView.findViewById(R.id.start_load);
        mLoadIng = (ImageView) mProgressView.findViewById(R.id.load_ing);
        mDrawable = (AnimationDrawable) mLoadIng.getBackground();
        addView(mProgressView);
    }

    public void closeRefresh() {
        mRefreshing = false;
        startAnimation(getScrollY(), 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mRefreshing) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
//                boolean isDownOrUp = Math.abs(mStartX - x) < Math.abs(mStartY - y);
//                if (isDownOrUp) {
                setScrollY(-(int) ((y - mStartY) / 2.5));
                if (getScrollY() >= 0) {
                    setScrollY(0);
                }

                if (Math.abs(getScrollY()) > mLoading.getMeasuredHeight()) {
                    mText.setText("松开刷新...");
                } else {
                    mText.setText("下拉刷新...");
                }
//                Log.d(TAG, "onTouchEvent: " + getScrollY() + "|" + mLoading.getMeasuredHeight());
//                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(getScrollY()) > mLoading.getMeasuredHeight()) {
                    mRefreshing = true;
                    mText.setText("更新中...");
                    startAnimation(getScrollY(), mLoading.getMeasuredHeight());
                } else {
                    mText.setText("下拉刷新...");
                    startAnimation(getScrollY(), 0);
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    private void startAnimation(float start, final float end) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(Math.abs(start), Math.abs(end));
        valueAnimator.start();
        valueAnimator.setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedValue();
                setScrollY((int) -fraction);
                Log.d(TAG, "onAnimationUpdate: " + fraction);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (end == 0) {
                    mLoadIng.setVisibility(View.GONE);
                    mStartLoad.setVisibility(View.VISIBLE);
                    mDrawable.stop();
                } else {
                    mLoadIng.setVisibility(View.VISIBLE);
                    mStartLoad.setVisibility(View.GONE);
                    mDrawable.start();
                    if (mListener != null) {
                        mListener.OnRefresh();
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mRefreshing) {
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                boolean isDownOrUp = Math.abs(mStartX - ev.getX()) < Math.abs(mStartY - ev.getY());
                boolean isDown = ev.getY() > mStartY;
                if (isDownOrUp && isDown) {
                    if (isChildViewGetToTop()) {
                        return true;
                    }
                }
                break;
        }


        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mProgressView.layout(l, t - mProgressView.getMeasuredHeight(), r, t);
        View childView = null;
        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            if (childView != mProgressView) {
                childView.layout(l, t, r, b);
            }
        }
    }


    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }

    public interface OnRefreshListener {
        void OnRefresh();
    }
}
