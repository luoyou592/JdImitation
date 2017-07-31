package com.young.jdmall.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.iv_left_pic)
    ImageView mIvLeftPic;
    @BindView(R.id.tv_left_title)
    TextView mTvLeftTitle;
    @BindView(R.id.left_goods_item)
    LinearLayout mLeftGoodsItem;
    @BindView(R.id.iv_right_pic)
    ImageView mIvRightPic;
    @BindView(R.id.tv_right_title)
    TextView mTvRightTitle;
    @BindView(R.id.right_goods_item)
    LinearLayout mRightGoodsItem;
    @BindView(R.id.tv_left_price)
    TextView mTvLeftPrice;
    @BindView(R.id.tv_right_price)
    TextView mTvRightPrice;
    @BindView(R.id.iv_left_small_pic)
    ImageView mIvLeftSmallPic;
    @BindView(R.id.iv_right_small_pic)
    ImageView mIvRightSmallPic;

    public GoodsItemView(Context context) {
        this(context, null);
    }

    public GoodsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.goods_item_view, this);
        ButterKnife.bind(this, this);
    }

    public void setLeftTitle(String title) {
        mTvLeftTitle.setText(title);
    }

    public void setRightTitle(String title) {
        mTvRightTitle.setText(title);
    }

    public void setLeftImagId(int resId) {
        mIvLeftPic.setImageResource(resId);
    }

    public void setRightImagId(int resId) {
        mIvRightPic.setImageResource(resId);
    }

    public void setLeftImagUrl(String url) {
        Picasso.with(getContext()).load(url).into(mIvLeftPic);
    }

    public void setRightImagUrl(String url) {
        Picasso.with(getContext()).load(url).into(mIvRightPic);
    }

    public void setLeftPrice(String price) {
        mTvLeftPrice.setText(price);
    }

    public void setRightPrice(String price) {
        mTvRightPrice.setText(price);
    }

    public void setLeftSmallImagId(int resId) {
        mIvLeftSmallPic.setImageResource(resId);
    }

    public void setRightSmallImagId(int resId) {
        mIvRightSmallPic.setImageResource(resId);
    }

    public void setLeftSmallImagUrl(String url) {
        Picasso.with(getContext()).load(url).into(mIvLeftSmallPic);
    }

    public void setRightSmallImagUrl(String url) {
        Picasso.with(getContext()).load(url).into(mIvRightSmallPic);
    }

    @OnClick({R.id.left_goods_item, R.id.right_goods_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_goods_item:
                break;
            case R.id.right_goods_item:
                break;
        }
    }
}
