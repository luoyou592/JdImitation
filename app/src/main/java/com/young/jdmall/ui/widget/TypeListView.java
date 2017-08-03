package com.young.jdmall.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/8/2.
 * 包名:com.young.jdmall.ui.widget
 * 时间:2017/8/2
 */

public class TypeListView extends RelativeLayout {

    private int mHeadViewheight;
    private float mStartX;
    private float mStartY;
    private float mEventY;
    private boolean mIsDispatch;
    private boolean mIsUndispatch;

    public TypeListView(Context context) {
        this(context,null);
    }

    public TypeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mEventY = ev.getY();
                mStartX = ev.getX();
                mStartY = ev.getY();
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                if (((Math.abs(mStartX - ev.getX()) > Math.abs(mStartY - ev.getY())) && !mIsUndispatch) || mIsDispatch) {
                    mIsDispatch = true;
                    mStartX = ev.getX();
                    mStartY = ev.getY();
                    return super.dispatchTouchEvent(ev);
                }
                if ((Math.abs(mStartX - ev.getX()) < Math.abs(mStartY - ev.getY())) && !mIsDispatch) {
                    mIsUndispatch = true;
                }

                setScrollY(getScrollY() - (int) (ev.getY() - mEventY));
                int scrollY = getScrollY();
                if (scrollY < 0) {
                    setScrollY(0);
                    mEventY = ev.getY();
                    mStartX = ev.getX();
                    mStartY = ev.getY();
                    return super.dispatchTouchEvent(ev);
                }
                int measuredHeight = getChildAt(0).getMeasuredHeight();
                if (scrollY >= measuredHeight) {
                    setScrollY(measuredHeight);
                    mEventY = ev.getY();
                    mStartX = ev.getX();
                    mStartY = ev.getY();
                    return super.dispatchTouchEvent(ev);
                }
                mEventY = ev.getY();
                mStartX = ev.getX();
                mStartY = ev.getY();
                return true;
            case MotionEvent.ACTION_UP:
                mIsDispatch = false;
                mIsUndispatch = false;
                break;

        }
        return super.dispatchTouchEvent(ev);
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
