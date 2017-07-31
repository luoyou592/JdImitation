package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.jdmall.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    Context mContext;

    public ShoppingCarFragmentAdapter(Context context) {
        mContext = context;
    }

    private List<Map<String, Object>> mList = new ArrayList<>();

    public void setList(List<Map<String, Object>> list) {
        mList = list;
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
                grideViewHolder.setData(mList);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("shopcar", "得到数目");
        return 30;
    }


    public void setData(List<Map<String, Object>> list) {

    }



     class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.login)
        Button mLogin;
        @BindView(R.id.sencond_kill)
        Button mSencondKill;
        @BindView(R.id.check)
        Button mCheck;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了login");
                }
            });
            mSencondKill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了SencondKill");
                }
            });
            mCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了mCheck");
                }
            });

        }

        public void setData() {

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
        @BindView(R.id.goaway_right)
        ImageView mGoawayRight;
        @BindView(R.id.add_right)
        ImageView mAddRight;
        @BindView(R.id.item_src_right)
        ImageView mItemSrcRight;
        @BindView(R.id.item_desc_right)
        TextView mItemDescRight;
        @BindView(R.id.item_price_right)
        TextView mItemPriceRight;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar","点击了左边add");
                }
            });
            mAddRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar","点击了右边add");
                }
            });
            mGoaway.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar","点击了左边goaway");
                }
            });
            mGoawayRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar","点击了右边goaway");
                }
            });


        }

        public void setData(List<Map<String, Object>> list) {

        }
    }
}

