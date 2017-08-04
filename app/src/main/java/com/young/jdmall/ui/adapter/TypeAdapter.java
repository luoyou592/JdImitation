package com.young.jdmall.ui.adapter;


import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.ProductBean;
import com.young.jdmall.ui.utils.PriceFormater;
import com.young.jdmall.ui.view.RecyclerLoadMoreView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeAdapter extends RecyclerLoadMoreView.Adapter {

    private List<ProductBean.ProductListBean> mData = new ArrayList();

    public void addData(List<ProductBean.ProductListBean> productList) {
        mData.addAll(productList);
        notifyDataSetChanged();
    }

    public void setData(List<ProductBean.ProductListBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolderToRecyclerLoadMoreView(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_list, parent, false));
    }

    @Override
    protected void onBindViewHolderToRecyclerLoadMoreView(RecyclerView.ViewHolder holder, int position) {
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



    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.item_type_name)
        TextView mItemTypeName;
        @BindView(R.id.item_type_primary_money)
        TextView mItemTypePrimaryMoney;
        @BindView(R.id.item_type_money)
        TextView mItemTypeMoney;
        @BindView(R.id.item_type_evaluation)
        TextView mItemTypeEvaluation;
        @BindView(R.id.item_type_goodEvaluation)
        TextView mItemTypeGoodEvaluation;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(ProductBean.ProductListBean listTestBean) {
            Random randomEvaluation = new Random();
            for (int i = 0; i < mData.size(); i++) {
                listTestBean.setEvaluation(randomEvaluation.nextInt(9) * 13591);
                listTestBean.setGoodEvaluation(randomEvaluation.nextInt(20) * 49 * 0.1f);
            }
            mItemTypeEvaluation.setText("评论数  " + listTestBean.getEvaluation());
            mItemTypeGoodEvaluation.setText("好评率  " + listTestBean.getGoodEvaluation() + "%");

            mItemTypeName.setText(listTestBean.getName());

            String imageUrl = Constant.IMAGE_URL + listTestBean.getPic();
            Glide.with(mIcon.getContext())
                    .load(imageUrl)
                    .error(R.mipmap.test_image)
                    .fallback(R.mipmap.test_image)
                    .override(200, 200)
                    .dontAnimate()
                    .into(mIcon);
            mItemTypePrimaryMoney.setText("原价" + listTestBean.getMarketPrice() + "元");
            mItemTypePrimaryMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);


            SpannableString spannableString = new SpannableString("现在只要" + PriceFormater.format(listTestBean.getMarketPrice()) + "!");
            spannableString.setSpan(new AbsoluteSizeSpan(80), 4, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 4, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mItemTypeMoney.setText(spannableString);

            mItemTypeMoney.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
