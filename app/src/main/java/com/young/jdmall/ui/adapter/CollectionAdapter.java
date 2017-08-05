package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.ui.activity.ProductDetaiActivity;
import com.young.jdmall.ui.utils.PriceFormater;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/3 0003 19:30
 *  描述：    TODO
 */
public class CollectionAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public CollectionAdapter(Context context) {
        mContext = context;
    }

    private List<NewsProductInfoBean.ProductListBean> mProductListBeen = new ArrayList<>();

    public void setNewsProductInfoBeen(List<NewsProductInfoBean.ProductListBean> newsProductInfoBeen) {
        mProductListBeen = newsProductInfoBeen;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.goods_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mProductListBeen.get(position));
    }

    @Override
    public int getItemCount() {
        if (mProductListBeen != null) {
            return mProductListBeen.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(final NewsProductInfoBean.ProductListBean productListBean) {
            mTvTitle.setText(productListBean.getName());
            mTvPrice.setText(PriceFormater.format(productListBean.getPrice()));
            Glide.with(mContext).load(Constant.BASE_URL+productListBean.getPic()).placeholder(R.mipmap.default_pic).into(mIvPic);
            mGoodsItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext,"点了"+productListBean.getName(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, ProductDetaiActivity.class);
                    intent.putExtra("id", productListBean.getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
