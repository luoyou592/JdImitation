package com.young.jdmall.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品条目展示自定义item
 * Created by 25505 on 2017/7/31.
 */

public class GoodsItemView extends RelativeLayout {


    @BindView(R.id.iv_pic)
    ImageView mIvPic;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_small_pic)
    ImageView mIvSmallPic;
    @BindView(R.id.goods_item)
    LinearLayout mGoodsItem;

    public GoodsItemView(Context context) {
        this(context, null);
    }

    public GoodsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.goods_item_view, this);
        ButterKnife.bind(this, this);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }


    public void setImagId(int resId) {
        mIvPic.setImageResource(resId);
    }


    public void setImagUrl(String url) {
        Picasso.with(getContext()).load(url).into(mIvPic);
    }

    public void setPrice(String price) {
        mTvPrice.setText(price);
    }


    public void setSmallImagId(int resId) {
        mIvSmallPic.setImageResource(resId);
    }


    public void setSmallImagUrl(String url) {
        Picasso.with(getContext()).load(url).into(mIvSmallPic);
    }

    @OnClick(R.id.goods_item)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goods_item:

                Toast.makeText(getContext(), "左边", Toast.LENGTH_SHORT).show();
        }
    }
}
