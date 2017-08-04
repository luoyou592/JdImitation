package com.young.jdmall.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.ui.activity.ProductDetaiActivity;

/**
 * Created by 钟志鹏 on 2017/8/3.
 */

public class NewsDetailContainerGoodsView extends RelativeLayout {
    private ImageView mViewNewsDetailContainerIcon;
    private TextView mViewNewsDetailContainerName;
    private TextView mViewNewsDetailContainerPrice;

    public NewsDetailContainerGoodsView(Context context) {
        this(context, null);
    }

    public NewsDetailContainerGoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_news_detail_container_goods, this);
        mViewNewsDetailContainerIcon = (ImageView) findViewById(R.id.view_news_detail_container_icon);
        mViewNewsDetailContainerName = (TextView) findViewById(R.id.view_news_detail_container_name);
        mViewNewsDetailContainerPrice = (TextView) findViewById(R.id.view_news_detail_container_price);
        mViewNewsDetailContainerName.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void bindView(String name, String icon, String price, final String id) {
        Glide.with(getContext()).load(icon).into(mViewNewsDetailContainerIcon);
        mViewNewsDetailContainerName.setText(name);
        mViewNewsDetailContainerPrice.setText("￥" + String.format("%.2f", Float.valueOf(price)));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductDetaiActivity.class);
                intent.putExtra("id", id);
                getContext().startActivity(intent);
            }
        });
    }
}
