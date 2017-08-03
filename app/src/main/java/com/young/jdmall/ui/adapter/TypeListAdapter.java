package com.young.jdmall.ui.adapter;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/1.
 * 包名:com.example.administrator.jdx.Adapter
 * 时间:2017/8/1
 */

public class TypeListAdapter extends RecyclerView.Adapter {

    private static final String TAG = "RecycleAdapter";
    private Context mContext;
    private List<ProductBean.ProductListBean> mData = new ArrayList<>();


    public TypeListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ProductBean.ProductListBean> data) {
        mData = data;

        Log.d(TAG, "setData: 设置数据" + mData.size() + this);
        notifyDataSetChanged();
    }

    public void addData(List<ProductBean.ProductListBean> productList) {
        mData.addAll(productList);

        Log.d(TAG, "addData: 添加数据"+mData.size());
    }




    /**------------------------设置适配器----------------------------*/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_type_list, parent,false);
            ViewHolder viewHolder = new ViewHolder(rootView);
        Log.d(TAG, "onCreateViewHolder: 测试1");
            return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mData.get(position));
        Log.d(TAG, "onCreateViewHolder: 测试2");
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+mData.size());
        if (mData != null) {
            Log.d(TAG, "size: ");
            return mData.size();
        }
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
            Glide.with(mContext)
                    .load(imageUrl)
                    .error(R.mipmap.test_image)
                    .fallback(R.mipmap.test_image)
                    .override(200, 200)
                    .dontAnimate()
                    .into(mIcon);
            mItemTypePrimaryMoney.setText("原价" + listTestBean.getMarketPrice() + "元");
            mItemTypePrimaryMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);


            SpannableString spannableString = new SpannableString("现在只要" + listTestBean.getPrice() + "! 元");
            spannableString.setSpan(new AbsoluteSizeSpan(80), 4, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 4, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mItemTypeMoney.setText(spannableString);
            mItemTypeMoney.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

}
