package com.young.jdmall.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;

/**
 * Created by 钟志鹏 on 2017/8/3.
 */

public class NewsDetailContainerTextView extends RelativeLayout {

    private static final String TAG = "NewsDetailContainerText";
    private TextView mTextView;

    public NewsDetailContainerTextView(Context context) {
        this(context, null);
    }

    public NewsDetailContainerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTextView = new TextView(context);
        addView(mTextView);
        int padding = getResources().getDimensionPixelSize(R.dimen.dp_10);
        Log.d(TAG, "NewsDetailContainerTextView: " + padding);
        setPadding(padding, padding, padding, padding);
        mTextView.setTextColor(0xCC000000);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        int lineSpacing = getResources().getDimensionPixelSize(R.dimen.dp_10);
        mTextView.setLineSpacing(lineSpacing, mTextView.getLineSpacingMultiplier());
    }

    public void bindView(String container) {
        mTextView.setText(container);
    }
}
