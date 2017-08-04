package com.young.jdmall.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    @BindView(R.id.type_sort_screen)
    TextView mTypeSortScreen;
    @BindView(R.id.type_back)
    ImageView mTypeBack;
    @BindView(R.id.type_search)
    EditText mTypeSearch;
    @BindView(R.id.type_selector_layout)
    ImageView mTypeSelectorLayout;

    private boolean isDropOrder = true;//默认则降序
    private boolean isRecyclerView = true;//默认布局

    public ViewTypeHeader(Context context) {
        this(context, null);
    }

    public ViewTypeHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_type_header, this);
        ButterKnife.bind(this, this);
        selectorPrimaryShow(Color.RED, Color.BLACK, Color.BLACK);
    }

    @OnClick({R.id.type_sort_comprehensive, R.id.type_sort_volume, R.id.type_sort_price, R.id
            .type_sort_screen, R.id.type_selector_layout, R.id.type_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.type_sort_comprehensive:
                selectorPrimaryShow(Color.RED, Color.BLACK, Color.BLACK);
                if (mOnClickPrimaryListener != null) {
                    mOnClickPrimaryListener.onPrimaryEvaluate();
                }
                break;
            case R.id.type_sort_volume:
                selectorPrimaryShow(Color.BLACK, Color.RED, Color.BLACK);
                if (mOnClickPrimaryListener != null) {
                    mOnClickPrimaryListener.onPrimaryVolume();
                }
                break;
            case R.id.type_sort_price:
                selectorPrimaryShow(Color.BLACK, Color.BLACK, Color.RED);
                if (mOnClickPrimaryListener != null) {
                    mOnClickPrimaryListener.onPrimaryPrice(isDropOrder);

                    if (isDropOrder) {

                        isDropOrder = false;
                    } else {
                        isDropOrder = true;
                    }
                }
                break;
            case R.id.type_sort_screen:
                selectorPrimaryShow(Color.BLACK, Color.BLACK, Color.BLACK);
                if (mOnClickPrimaryListener != null) {
                    mOnClickPrimaryListener.onPrimaryScreen();
                }
                break;
            case R.id.type_back:
                if (mOnClickPrimaryListener != null) {
                    mOnClickPrimaryListener.onBack();
                }
                break;
            case R.id.type_selector_layout:
                Log.d(TAG, "onViewClicked: 点击");
                if (mOnClickPrimaryListener != null) {
                    mOnClickPrimaryListener.onSelectorLayout();

                    if (isRecyclerView) {
                        mTypeSelectorLayout.setBackgroundResource(R.mipmap.a8r);
                        isRecyclerView = false;
                    } else {
                        mTypeSelectorLayout.setBackgroundResource(R.mipmap.a9e);
                        isRecyclerView = true;

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


    public interface onClickPrimaryListener {
        void onPrimaryVolume();//销量排序回调

        void onPrimaryEvaluate();//评价排序回调

        void onPrimaryPrice(boolean isMode);//价格排序回调

        void onPrimaryScreen();//筛选排序回调

        void onBack();//返回键回调

        void onSelectorLayout();//选择布局回调


    }
}
