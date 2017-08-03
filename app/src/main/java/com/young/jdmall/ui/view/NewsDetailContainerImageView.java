package com.young.jdmall.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;

/**
 * Created by 钟志鹏 on 2017/8/3.
 */

public class NewsDetailContainerImageView extends RelativeLayout {

    private ImageView mImageView;

    public NewsDetailContainerImageView(Context context) {
        this(context, null);
    }

    public NewsDetailContainerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mImageView = new ImageView(context);
        int high = getResources().getDimensionPixelSize(R.dimen.dp_220);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, high);
        addView(mImageView, layoutParams);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setBackgroundResource(R.drawable.al1);
        int padding5 = getResources().getDimensionPixelSize(R.dimen.dp_5);
        int padding10 = getResources().getDimensionPixelSize(R.dimen.dp_10);
        setPadding(padding5, padding10, padding5, padding10);
    }

    public void bindView(String url) {
        Glide.with(getContext()).load(url).into(mImageView);
    }
}
