package com.young.jdmall.ui.widget;

import android.content.Context;
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

    public ViewTypeHeader(Context context) {
        this(context, null);
    }

    public ViewTypeHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_type_header, this);
        ButterKnife.bind(this, this);
        mTypeSortComprehensive.setEnabled(false);
    }

    @OnClick({R.id.type_sort_comprehensive, R.id.type_sort_volume, R.id.type_sort_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.type_sort_comprehensive:
                mTypeSortComprehensive.setEnabled(false);
                mTypeSortVolume.setEnabled(true);
                mTypeSortPrice.setEnabled(true);
                break;
            case R.id.type_sort_volume:
                mTypeSortComprehensive.setEnabled(true);
                mTypeSortVolume.setEnabled(false);
                mTypeSortPrice.setEnabled(true);
                break;
            case R.id.type_sort_price:
                mTypeSortComprehensive.setEnabled(true);
                mTypeSortVolume.setEnabled(true);
                mTypeSortPrice.setEnabled(false);
                break;
        }
    }
}
