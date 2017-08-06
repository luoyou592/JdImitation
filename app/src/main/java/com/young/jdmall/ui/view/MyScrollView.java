package com.young.jdmall.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by BjyJyk on 2017/8/6.
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //请求父容器不要拦截
        requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
