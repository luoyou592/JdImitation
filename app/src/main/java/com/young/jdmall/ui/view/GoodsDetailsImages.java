package com.young.jdmall.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BjyJyk on 2017/8/6.
 */

public class GoodsDetailsImages extends LinearLayout {
    @BindView(R.id.goods_images_pic)
    ImageView mGoodsImagesPic;
    @BindView(R.id.goods_images_title)
    TextView mGoodsImagesTitle;
    @BindView(R.id.goods_images_price)
    TextView mGoodsImagesPrice;

    public GoodsDetailsImages(Context context) {
        this(context, null,null,null,null);
    }



    public GoodsDetailsImages(Context context, AttributeSet attrs, String url, String Title, String price) {
        super(context, attrs);
        View rootview = LayoutInflater.from(context).inflate(R.layout.goods_detail_view, this);
        ButterKnife.bind(this, rootview);
        setdata(context,url,Title,price);
    }


    private void setdata(Context context, String url, String Title, String price) {
        Glide.with(context).load(url).override(400,800).into(mGoodsImagesPic);
        mGoodsImagesTitle.setText(Title);
        mGoodsImagesPrice.setText(price);


    }

    private void init() {


    }


}
