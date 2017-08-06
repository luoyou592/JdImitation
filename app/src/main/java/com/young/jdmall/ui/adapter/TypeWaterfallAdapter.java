package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.ProductBean;
import com.young.jdmall.ui.activity.ProductDetaiActivity;
import com.young.jdmall.ui.activity.TypeActivity;
import com.young.jdmall.ui.utils.PriceFormater;
import com.young.jdmall.ui.view.RecyclerLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/4.
 * 包名:com.young.jdmall.ui.adapter
 * 时间:2017/8/4
 */

public class TypeWaterfallAdapter extends RecyclerLoadMoreView.Adapter {

    private static final String TAG = "TypeWaterfallAdapter";
    private Context mContext;
    private List<ProductBean.ProductListBean> mData = new ArrayList<>();

    public TypeWaterfallAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ProductBean.ProductListBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<ProductBean.ProductListBean> productList) {
        mData.addAll(productList);
        notifyDataSetChanged();
    }



    @Override
    protected RecyclerView.ViewHolder onCreateViewHolderToRecyclerLoadMoreView(ViewGroup parent,
                                                                               int viewType) {
        return new TypeWaterfallAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_type_water, parent, false));
    }

    @Override
    protected void
    onBindViewHolderToRecyclerLoadMoreView(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mData.get(position));
    }

    @Override
    protected int getItemCountToRecyclerLoadMoreView() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    protected int getItemViewTypeToRecyclerLoadMoreView(int position) {
        return 0;
    }




    class ViewHolder extends RecyclerLoadMoreView.ViewHolder {
        @BindView(R.id.type_water_icon)
        ImageView mTypeWaterIcon;
        @BindView(R.id.type_water_name)
        TextView mTypeWaterName;
        @BindView(R.id.type_water_money)
        TextView mTypeWaterMoney;
        private ProductBean.ProductListBean mProductListBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductDetaiActivity.class);
                    intent.putExtra("id",mProductListBean.getId());
                    mContext.startActivity(intent);
                }
            });

        }

        public void setData(ProductBean.ProductListBean productListBean) {
            mProductListBean = productListBean;

            Display display= ((TypeActivity)mContext).getWindow().getWindowManager().getDefaultDisplay();
            DisplayMetrics dm=new DisplayMetrics();
            display.getMetrics(dm);
            int mWidth=dm.widthPixels;
            int mHeight=dm.heightPixels;

            ViewGroup.LayoutParams layoutParams = mTypeWaterIcon.getLayoutParams();

            //将宽度设置为屏幕的1/2
            layoutParams.width=mWidth/2;
            layoutParams.height=mHeight/2;
            mTypeWaterIcon.setLayoutParams(layoutParams);

            String imageUrl = Constant.IMAGE_URL + productListBean.getPic();
            Glide.with(mTypeWaterIcon.getContext())
                    .load(imageUrl)
                    .error(R.mipmap.default_pic)
                    .fallback(R.mipmap.default_pic)
                    .dontAnimate()
                    .into(mTypeWaterIcon);

            mTypeWaterName.setText(productListBean.getName());

            SpannableString spannableString = new SpannableString(PriceFormater.format(productListBean.getMarketPrice()));
            spannableString.setSpan(new AbsoluteSizeSpan(40), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTypeWaterMoney.setText(spannableString);
        }
    }
}
