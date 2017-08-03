package com.young.jdmall.ui.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.young.jdmall.R;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by BjyJyk on 2017/8/2.
 */

public class RotateView extends RelativeLayout {

    public int mRadius;
    private int mMeasuredWidth;
    private int mMeasuredHeight;

    public void setNum(int num) {
        this.num = num;
    }

    private int num =0;

    public RotateView(Context context) {
        this(context, null);
    }

    public RotateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RotateView, defStyleAttr, 0);
        mRadius = (int) a.getDimension(R.styleable.RotateView_radius, TypedValue.applyDimension(COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
 /*       int count = getChildCount();
        //测量Child
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);

        }*/
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int cleft = (int) (getMeasuredWidth() / 2 - child.getMeasuredWidth() / 2 + mRadius * Math.sin(Math.PI * 2 / count * i+(Math.PI * 2 /360)*num));
            int ctop = (int) (getMeasuredHeight() / 2 - child.getMeasuredHeight() / 2 + mRadius * Math.cos(Math.PI * 2 / count * i+(Math.PI * 2 /360)*num));
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            child.layout(cleft, ctop, width + cleft, ctop + height);
        }

    }
    int tatalnumber=0;
    public void getViewLayout(int number) {
        int count = getChildCount();
        tatalnumber+=number;
        Log.e(TAG, "getViewLayout: "+tatalnumber );
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int cleft = (int) (getMeasuredWidth() / 2 - child.getMeasuredWidth() / 2 + mRadius * Math.sin(Math.PI * 2 / count * i+(Math.PI * 2 /360)*number));
            int ctop = (int) (getMeasuredHeight() / 2 - child.getMeasuredHeight() / 2 + mRadius * Math.cos(Math.PI * 2 / count * i+(Math.PI * 2 /360)*number));
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            child.layout(cleft, ctop, width + cleft, ctop + height);
        }
    }





}
