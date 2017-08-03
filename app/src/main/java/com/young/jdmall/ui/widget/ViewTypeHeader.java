package com.young.jdmall.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/1.
 * 包名:com.example.administrator.jdx.View
 * 时间:2017/8/1
 */

public class ViewTypeHeader extends RelativeLayout {


    @BindView(R.id.hide)
    LinearLayout mHide;
    @BindView(R.id.ll)
    LinearLayout mLl;

    public ViewTypeHeader(Context context) {
        this(context, null);
    }

    public ViewTypeHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_type_header, this);
        ButterKnife.bind(this,this);
    }

}
