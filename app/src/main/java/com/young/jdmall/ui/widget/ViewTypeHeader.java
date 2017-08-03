package com.young.jdmall.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/1.
 * 包名:com.example.administrator.jdx.View
 * 时间:2017/8/1
 */

public class ViewTypeHeader extends RelativeLayout {

    private static final String TAG = "ViewTypeHeader";

    @BindView(R.id.type_sort_comprehensive)
    TextView mTypeSortComprehensive;
    @BindView(R.id.type_sort_volume)
    TextView mTypeSortVolume;
    @BindView(R.id.type_sort_price)
    TextView mTypeSortPrice;

    private boolean isDropOrder = true;//默认则降序

    public ViewTypeHeader(Context context) {
        this(context, null);
    }

    public ViewTypeHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_type_header, this);
        ButterKnife.bind(this, this);
//        mTypeSortComprehensive.setEnabled(false);
        selectorPrimaryShow(Color.RED,Color.BLACK,Color.BLACK);
    }

    @OnClick({R.id.type_sort_comprehensive, R.id.type_sort_volume, R.id.type_sort_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.type_sort_comprehensive:
                selectorPrimaryShow(Color.RED,Color.BLACK,Color.BLACK);
                if (mOnClickPrimaryListener != null){
                    mOnClickPrimaryListener.onPrimaryEvaluate();
                }
                break;
            case R.id.type_sort_volume:
                selectorPrimaryShow(Color.BLACK,Color.RED,Color.BLACK);
                if (mOnClickPrimaryListener != null){
                    mOnClickPrimaryListener.onPrimaryVolume();
                }
                break;
            case R.id.type_sort_price:
                selectorPrimaryShow(Color.BLACK,Color.BLACK,Color.RED);
                if (mOnClickPrimaryListener != null){
                    mOnClickPrimaryListener.onPrimaryPrice(isDropOrder);
                    if (isDropOrder){
                        isDropOrder = false;
                    }else{
                        isDropOrder = true;
                    }
                }
                break;
        }
    }

    private void selectorPrimaryShow(int a, int b, int c) {
        mTypeSortComprehensive.setTextColor(a);
        mTypeSortVolume.setTextColor(b);
        mTypeSortPrice.setTextColor(c);
    }


    private onClickPrimaryListener mOnClickPrimaryListener;

    public void setOnClickPrimaryListener(onClickPrimaryListener onClickPrimaryListener) {
        mOnClickPrimaryListener = onClickPrimaryListener;
    }

    public interface onClickPrimaryListener{
       public void onPrimaryVolume();//销量排序回调
       public void onPrimaryEvaluate();//评价排序回调
       public void onPrimaryPrice(boolean isMode);//价格排序回调

    }
}
