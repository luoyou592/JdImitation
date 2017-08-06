package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.CartInfoBean;
import com.young.jdmall.bean.RecommendInfoBean;
import com.young.jdmall.ui.activity.CollectionActivity;
import com.young.jdmall.ui.activity.LoginActivity;
import com.young.jdmall.ui.activity.SecKillActivity;
import com.young.jdmall.ui.utils.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  创建者:   liu89
 *  创建时间:  2017/7/30 16:20
 *  描述：    TODO
 */
public class ShoppingCarFragmentAdapter extends RecyclerView.Adapter {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_GLIDE = 1;


    private Context mContext;
    private List<RecommendInfoBean.ProductListBean> mData;
    public GoodsShowAdapter mGoodsShowAdapter;


    public ShoppingCarFragmentAdapter(Context context) {
        mContext = context;

        notifyDataSetChanged();
    }

    public CartInfoBean mList;

    public void setList(CartInfoBean list) {
        mList = list;

        notifyDataSetChanged();
    }

    public void setData(List<RecommendInfoBean.ProductListBean> data) {
        mData = data;
        Log.d("data", "mdata==============" + mData.toString());

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        Log.d("shopcar", "得到数目");
        if (mData != null) {
            Log.d("data", "mData.getListCount()===============" + mData.size());
            return mData.size();
        }
        Log.d("data", "得到一个0");
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        Log.d("shopcar", "调用getitemviewtype");
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_GLIDE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("shopcar", "创建viewholder");
        switch (viewType) {
            case TYPE_TITLE:
                View titleView = View.inflate(mContext, R.layout.shop_car_title, null);
                ViewHolder viewHolder = new ViewHolder(titleView);
                Log.d("shopcar", "创建头布局");
                return viewHolder;
            case TYPE_GLIDE:
                View grideView = View.inflate(mContext, R.layout.shop_car_item, null);

                ItemViewHolder itemViewHolder = new ItemViewHolder(grideView);
                Log.d("shopcar", "创建其他布局");
                return itemViewHolder;
            default:
                Log.d("shopcar", "出现了第三种情况");
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("shopcar", "调用onbindviewholder");
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TITLE:
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.setData();
                break;
            case TYPE_GLIDE:
                ItemViewHolder grideViewHolder = (ItemViewHolder) holder;
                grideViewHolder.setData(position);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.login)
        Button mLogin;
        @BindView(R.id.sencond_kill)
        Button mSencondKill;
        @BindView(R.id.check)
        Button mCheck;
        @BindView(R.id.goods_show)
        RecyclerView mGoodsShow;
        @BindView(R.id.visible_table)
        LinearLayout mVisibleTable;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了login");
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            });
            mSencondKill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了SencondKill");
                    Intent intent = new Intent(mContext, SecKillActivity.class);
                    mContext.startActivity(intent);
                }
            });
            mCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了mCheck");
                    if (PreferenceUtils.getUserId(mContext).length()!=0){
                        Intent intent = new Intent(mContext,LoginActivity.class);
                        mContext.startActivity(intent);
                    }
                    Intent intent = new Intent(mContext, CollectionActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
        public void showSeckill(){
            mVisibleTable.setVisibility(View.VISIBLE);
        }
        public void hideSeckill(){
            mVisibleTable.setVisibility(View.GONE);
        }

        public void setData() {
            if (mList != null) {
                mGoodsShow.setVisibility(View.VISIBLE);
                mVisibleTable.setVisibility(View.GONE);

            } else {
                mGoodsShow.setVisibility(View.GONE);
                mVisibleTable.setVisibility(View.VISIBLE);
            }
            mGoodsShowAdapter = new GoodsShowAdapter(mContext);
            mGoodsShowAdapter.setData(mList);
            mGoodsShow.setLayoutManager(new LinearLayoutManager(mContext));
            mGoodsShow.setAdapter(mGoodsShowAdapter);
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goaway)
        ImageView mGoaway;
        @BindView(R.id.add)
        ImageView mAdd;
        @BindView(R.id.item_src)
        ImageView mItemSrc;
        @BindView(R.id.item_desc)
        TextView mItemDesc;
        @BindView(R.id.item_price)
        TextView mItemPrice;
        @BindView(R.id.goods_left)
        RelativeLayout mGoodsLeft;

        ItemViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);

            mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了add");
                }
            });

            mGoaway.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了goaway");
                    mData.remove(getLayoutPosition());
                    notifyDataSetChanged();
                }
            });

            mGoodsLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了进入详情");
                }
            });

        }

        public void setData(int position) {

            RecommendInfoBean.ProductListBean productList = mData.get(position);
            mItemDesc.setText(productList.getName());
            mItemPrice.setText(productList.getPrice() + "");
            Glide.with(mContext.getApplicationContext()).load(Constant.BASE_URL + productList.getPic()).into(mItemSrc);
        }
    }


}

