package com.young.jdmall.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.ui.activity.TypeListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.View
 * 时间:2017/7/30
 */

class CategoryBaseRightItemInfoView extends LinearLayout {

    private static final String TAG = "CategoryBaseRightItemIn";
    @BindView(R.id.item_info_image)
    ImageView mItemInfoImage;
    @BindView(R.id.item_info_text)
    TextView mItemInfoText;
    @BindView(R.id.category_right_item_info)
    LinearLayout mItemInfo;

    public CategoryBaseRightItemInfoView(Context context) {
        this(context, null);
    }

    public CategoryBaseRightItemInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_info, this);
        ButterKnife.bind(this, this);

    }

    public void bindView(String dataList) {
        mItemInfoText.setText(dataList);

        mItemInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TypeListActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
