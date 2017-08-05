package com.young.jdmall.ui.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.young.jdmall.R;

import butterknife.ButterKnife;

/**
 * Created by BjyJyk on 2017/8/4.
 */

public abstract class BasePopWin extends PopupWindow {
    public BasePopWin(Activity paramActivity, View.OnClickListener paramOnClickListener, int paramInt1, int paramInt2) {
        super(paramActivity);
        //窗口布局
       View mainView = LayoutInflater.from(paramActivity).inflate(getlayout(), null);
        ButterKnife.bind(this, mainView);
        //设置每个子布局的事件监听器
        if (paramOnClickListener != null) {
            initSetOnClickListener(paramOnClickListener);
        }
        setContentView(mainView);
        //设置宽度
        setWidth(paramInt1);
        //设置高度
        setHeight(paramInt2);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }

    public abstract void initSetOnClickListener(View.OnClickListener paramOnClickListener) ;



    public abstract int  getlayout();
}
