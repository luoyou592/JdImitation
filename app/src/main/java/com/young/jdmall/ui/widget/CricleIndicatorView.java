package com.young.jdmall.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.young.jdmall.R;

/**
 * Created by 25505 on 2017/7/11.
 */

public class CricleIndicatorView extends View {
    private int mRadius; //圆心半径
    private int mDistance; //圆与圆的距离
    private int mCenterDistance; //圆心之间距离
    private int mCount;  //viewpage个数
    private ViewPager mViewPager;
    private Paint mPaint;
    private int mPosition;
    private float mpositionOffset;


    public CricleIndicatorView(Context context) {
        this(context,null);
    }

    public CricleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mRadius = getResources().getDimensionPixelSize(R.dimen.radius); //dp—(转换)—px
        mDistance = getResources().getDimensionPixelSize(R.dimen.distance);  //dp—(转换)—px
        mCenterDistance = mDistance+2*mRadius; //圆心间距
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
    }
    public void setViewPage(ViewPager viewPage) {
        //mCount = viewPage.getAdapter().getCount();
        //防止画很多圆，暂时固定写死，有多少个fragment，mCount就是为多少
        mCount = 2;
        mViewPager = viewPage;
        //触动viewpage事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //保证条目索引都在0-2
                position = position%2;
                mPosition = position;
                mpositionOffset = positionOffset;
                invalidate();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (mCount-1)*mDistance+mRadius+mCount*2*mRadius;  //View宽
        int height = mRadius*2;  //View高
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //先画不动点
        float cy = mRadius;
        for(int i=0;i<mCount;i++){
            mPaint.setColor(Color.GRAY);
            float cx = mRadius+i*mCenterDistance;
            canvas.drawCircle(cx,cy,mRadius,mPaint);
        }
        //画动点
        mPaint.setColor(Color.BLACK);
        float DynaCx = mRadius+mPosition*mCenterDistance+mCenterDistance*mpositionOffset;
        float DynaCy = mRadius;
        canvas.drawCircle(DynaCx,DynaCy,mRadius,mPaint);
    }
}
