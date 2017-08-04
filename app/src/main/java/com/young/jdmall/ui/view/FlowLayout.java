package com.young.jdmall.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhipe on 2017/7/13.
 */

public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private List<Line> mLines = new ArrayList<>();

    private final float GAP = getResources().getDimensionPixelSize(R.dimen.dp_10);

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLines.clear();
//        Log.d(TAG, "onMeasure: parent:" + getMeasuredWidth() + "|" + getMeasuredHeight());
//        Log.d(TAG, "onMeasure: parent:" + (MeasureSpec.getMode(widthMeasureSpec) >> 30) + "|" + (MeasureSpec.getMode(heightMeasureSpec) >> 30));
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            child.setLayoutParams(new LayoutParams(child.getMeasuredWidth(), child.getMeasuredHeight()));
            if (mLines.size() == 0) {
                Line line = new Line(getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
                mLines.add(line);
            }
            if (!mLines.get(mLines.size() - 1).addView(child)) {
                Line line = new Line(getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
                line.addView(child);
                mLines.add(line);
            }
//            Log.d(TAG, "onMeasure: " + child.getMeasuredWidth() + "|" + child.getMeasuredHeight());
        }

        int height = 0;
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);
            height += line.mHeight;
            if (i >= 1) {
                height += (GAP * 1.5f);
            }
        }
        height = getPaddingBottom() + getPaddingTop() + height;
        int measuredWidth = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
        int measuredHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        float left;
        float right;
        float top = getPaddingTop();
        float bottom;
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);
            left = getPaddingLeft();
            for (int j = 0; j < line.mViews.size(); j++) {
                View view = line.mViews.get(j);


//                right = left + (view.getMeasuredWidth() + line.mOffsetWidth);
                right = left + (view.getMeasuredWidth());
                bottom = top + view.getMeasuredHeight();
//                int widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (view.getMeasuredWidth() + line.mOffsetWidth + 0.5f), MeasureSpec.EXACTLY);
                int widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (view.getMeasuredWidth() + 0.5f), MeasureSpec.EXACTLY);
                int heightMeasureSpec = MeasureSpec.makeMeasureSpec(line.mHeight, MeasureSpec.EXACTLY);
                view.measure(widthMeasureSpec, heightMeasureSpec);
                view.layout((int) (left + 0.5f), (int) top, (int) (right + 0.5f), (int) bottom);
                left = right + GAP;
            }
            top += line.mHeight + (GAP * 1.5f);
        }
    }
//
//    @Override
//    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new LayoutParams(getContext(), attrs);
//    }
//
//    @Override
//    protected LayoutParams generateDefaultLayoutParams() {
//        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//    }
//
//    @Override
//    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
//        if (lp instanceof LayoutParams) {
//            return new LayoutParams((LayoutParams) lp);
//        } else if (lp instanceof MarginLayoutParams) {
//            return new LayoutParams((MarginLayoutParams) lp);
//        }
//        return new LayoutParams(lp);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    public class Line {
        public List<View> mViews = new ArrayList<>();
        public int mMaxWidth;
        public float mUsedWidth;
        public int mHeight;
        public float mOffsetWidth;

        public Line(int maxWidth) {
            mMaxWidth = maxWidth;
        }

        public boolean addView(View view) {
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth + mUsedWidth <= mMaxWidth) {
                mViews.add(view);
                int measuredHeight = view.getMeasuredHeight();
                mHeight = measuredHeight > mHeight ? measuredHeight : mHeight;
                mUsedWidth += measuredWidth;
                if (mViews.size() > 1) {
                    mUsedWidth += GAP;
                }
                mOffsetWidth = (mMaxWidth - mUsedWidth) / mViews.size();
                return true;
            }
            return false;
        }
    }

//
//    public static class LayoutParams extends ViewGroup.MarginLayoutParams{
//
//        public LayoutParams(Context c, AttributeSet attrs) {
//            super(c, attrs);
//        }
//
//        public LayoutParams(@Px int width, @Px int height) {
//            super(width, height);
//        }
//
//        public LayoutParams(MarginLayoutParams source) {
//            super(source);
//        }
//
//        public LayoutParams(ViewGroup.LayoutParams source) {
//            super(source);
//        }
//    }
}
