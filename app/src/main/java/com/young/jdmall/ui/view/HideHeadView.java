package com.young.jdmall.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.young.jdmall.R;

/**
 * Created by 钟志鹏 on 2017/7/31.
 */

public class HideHeadView extends RelativeLayout {
    private static final String TAG = "HideHeadView";
    private int mHeadViewheight;
    private float mStartX;
    private float mStartY;
    private float mEventY;
    private boolean mIsDispatch;
    private boolean mIsUndispatch;

    public HideHeadView(Context context) {
        this(context, null);
    }

    public HideHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mEventY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                setScrollY(getScrollY() - (int) (event.getY() - mEventY));
//                int scrollY = getScrollY();
//                if (scrollY <= 0) {
//                    setScrollY(0);
//                    mEventY = event.getY();
//                }
//                int measuredHeight = getChildAt(0).getMeasuredHeight();
//                if (scrollY >= measuredHeight) {
//                    setScrollY(measuredHeight);
//                }
//                mEventY = event.getY();
//                break;
//        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mEventY = event.getY();
                mStartX = event.getX();
                mStartY = event.getY();
                return super.dispatchTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                if (((Math.abs(mStartX - event.getX()) > Math.abs(mStartY - event.getY())) && !mIsUndispatch) || mIsDispatch) {
                    mIsDispatch = true;
                    mStartX = event.getX();
                    mStartY = event.getY();
                    return super.dispatchTouchEvent(event);
                }
                if ((Math.abs(mStartX - event.getX()) < Math.abs(mStartY - event.getY())) && !mIsDispatch) {
                    mIsUndispatch = true;
                }

                setScrollY(getScrollY() - (int) (event.getY() - mEventY));
                int scrollY = getScrollY();
                if (scrollY < 0) {
                    setScrollY(0);
                    mEventY = event.getY();
                    mStartX = event.getX();
                    mStartY = event.getY();
                    return super.dispatchTouchEvent(event);
                }
                int measuredHeight = getChildAt(0).getMeasuredHeight();
                if (scrollY >= measuredHeight) {
                    setScrollY(measuredHeight);
                    mEventY = event.getY();
                    mStartX = event.getX();
                    mStartY = event.getY();
                    return super.dispatchTouchEvent(event);
                }
                mEventY = event.getY();
                mStartX = event.getX();
                mStartY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                mIsDispatch = false;
                mIsUndispatch = false;
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mEventY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int scrollY = getScrollY();
//                setScrollY(scrollY - (int) (event.getY() - mEventY));
//                if (scrollY <= 0) {
//                    setScrollY(0);
//                    mEventY = event.getY();
//                }
//                int measuredHeight = getChildAt(0).getMeasuredHeight();
//                if (scrollY >= measuredHeight) {
//                    setScrollY(measuredHeight);
//                }
//                mEventY = event.getY();
//                break;
//        }

//        return false;

//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mStartX = event.getX();
//                mStartY = event.getY();
//                mEventY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(mStartX - event.getX()) < Math.abs(mStartY - event.getY())) {
//                    if ((getScrollY() <= 0 && mStartY < event.getY()) || (getScrollY() >= getChildAt(0).getMeasuredHeight() && mStartY > event.getY())) {
//                        mStartX = event.getX();
//                        mStartY = event.getY();
//                        return false;
//                    }
//                    mStartX = event.getX();
//                    mStartY = event.getY();
//                    return true;
//                }
//        }
//        mStartX = event.getX();
//        mStartY = event.getY();
//        return false;


        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        View headView = getChildAt(0);
        mHeadViewheight = headView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        getChildAt(0).layout(l, t, r, t + mHeadViewheight);
        getChildAt(1).layout(l, t + mHeadViewheight, r, t + mHeadViewheight + getMeasuredHeight());
    }
}
