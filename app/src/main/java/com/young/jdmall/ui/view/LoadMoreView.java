package com.young.jdmall.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.young.jdmall.R;

/**
 * Created by 钟志鹏 on 2017/8/2.
 */

public class LoadMoreView extends RelativeLayout {
    public LoadMoreView(Context context) {
        this(context, null);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View.inflate(context, R.layout.view_load_more, this);

        init();
    }

    private void init() {

    }
}
