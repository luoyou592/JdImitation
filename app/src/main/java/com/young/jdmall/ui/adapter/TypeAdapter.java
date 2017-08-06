package com.young.jdmall.ui.adapter;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeAdapter extends RecyclerLoadMoreView.Adapter {

    private static final String TAG = "TypeAdapter";

    private List<ProductBean.ProductListBean> mData = new ArrayList<>();
    private TypeActivity mContext;

    public TypeAdapter(TypeActivity typeActivity) {
        mContext = typeActivity;
    }

    public void addData(List<ProductBean.ProductListBean> productList) {
        mData.addAll(productList);
        Log.d(TAG, "addData: 添加数据" + mData.size());
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
        viewHolder.setData(mData.get(position),position);
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




    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        public ImageView mIcon;
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
        private ProductBean.ProductListBean mListTestBean;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductDetaiActivity.class);
                    intent.putExtra("id",mListTestBean.getId());
                    mContext.startActivity(intent);
                }
            });

            mIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        mListener.onImageClick(mListTestBean,mPosition);
                    }
                }
            });

        }

        public void setData(ProductBean.ProductListBean listTestBean, int position) {
            mListTestBean = listTestBean;
            mPosition = position;
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
                    .error(R.mipmap.default_pic)
                    .fallback(R.mipmap.default_pic)
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

    private onClickItemControlListener mListener;

    public void setListener(onClickItemControlListener listener) {
        mListener = listener;
    }

    public interface onClickItemControlListener{
        void onImageClick(ProductBean.ProductListBean productBean, int position);
    }
}
