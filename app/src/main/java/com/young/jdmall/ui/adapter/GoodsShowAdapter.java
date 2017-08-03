package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.CartInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  创建者:   liu89
 *  创建时间:  2017/7/31 16:35
 *  描述：    TODO
 */
public class GoodsShowAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CartInfoBean.CartBean> mList;

    public GoodsShowAdapter(Context context) {
        mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.goods_show_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mList!=null) {
            return mList.size();
        }
        return 0;
    }

    public void setData(List<CartInfoBean.CartBean> list) {
        mList = list;
    }


     class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_checkbox)
        CheckBox mGoodsCheckbox;
        @BindView(R.id.goods_icon)
        ImageView mGoodsIcon;
        @BindView(R.id.goods_desc)
        TextView mGoodsDesc;
        @BindView(R.id.goods_color)
        TextView mGoodsColor;
        @BindView(R.id.goods_price)
        TextView mGoodsPrice;
        @BindView(R.id.delete_unclick)
        ImageButton mDeleteUnclick;
        @BindView(R.id.delete_click)
        ImageButton mDeleteClick;
        @BindView(R.id.goods_num)
        TextView mGoodsNum;
        @BindView(R.id.goods_add)
        ImageButton mGoodsAdd;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(int position) {
            CartInfoBean.CartBean.ProductBean product = mList.get(position).getProduct();
            Glide.with(mContext.getApplicationContext()).load(Constant.BASE_URL+product.getPic()).into(mGoodsIcon);
        }
    }
}
