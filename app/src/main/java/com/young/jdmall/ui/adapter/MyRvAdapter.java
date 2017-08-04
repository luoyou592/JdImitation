package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.bean.UsersInfoBean;
import com.young.jdmall.ui.activity.CollectionActivity;
import com.young.jdmall.ui.activity.LoginActivity;
import com.young.jdmall.ui.activity.OrderActivity;
import com.young.jdmall.ui.activity.RecepitAddressActivity;
import com.young.jdmall.ui.utils.PreferenceUtils;
import com.young.jdmall.ui.utils.PriceFormater;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/1 0001 13:47
 *  描述：    TODO
 */
public class MyRvAdapter extends RecyclerView.Adapter {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_NORMAL = 0;
    private static final int TYPE_RECO = 2;
    @BindView(R.id.iv_signOrReg)
    ImageView mIvSignOrReg;
    @BindView(R.id.rl_signOrReg)
    RelativeLayout mRlSignOrReg;



    private Context mContext;
    private List<String> mDatas = new ArrayList<>();
    private LoginInfoBean mLoginInfoBean;
    private String mUsers;
    private UsersInfoBean.UserInfoBean mInfoBean;
    private static final String TAG = "MyRvAdapter";
    private NewsProductInfoBean mNewsProductInfoBean;

    public void setUserInfoBean(UsersInfoBean.UserInfoBean infoBean) {
        mInfoBean = infoBean;
        notifyDataSetChanged();
    }

    public MyRvAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else if (position == 1) {
            return TYPE_RECO;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void setDatas(List<String> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                View titleView = View.inflate(mContext, R.layout.item_user_head, null);
                TitleHolder titleHolder = new TitleHolder(titleView);
                return titleHolder;
            case TYPE_RECO:
                ImageView imageView = new ImageView(mContext);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(params);
                imageView.setImageResource(R.mipmap.tuijian);
                return new RecoHolder(imageView);
            case TYPE_NORMAL:
//                View normalView = View.inflate(mContext, R.layout.goods_item_view, null);
//                NormalHolder normalHolder = new NormalHolder(normalView);
//                return normalHolder;
//                GoodsItemView normalView = new GoodsItemView(mContext);
//                return new NormalHolder(normalView);
                View normalView = LayoutInflater.from(mContext).inflate(R.layout.goods_item_view, parent, false);
                return new NormalHolder(normalView);
            default:
                Log.e("home", "怎么出现了第四种holder");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_TITLE:
                ((TitleHolder) holder).bindView();
                break;
            case TYPE_RECO:
                ((RecoHolder) holder).bindView();
                break;
            case TYPE_NORMAL:
                ((NormalHolder) holder).bindView(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if ((!"".equals(mUsers)) && (mNewsProductInfoBean != null)) {

            return mNewsProductInfoBean.getListCount() + 2;
        } else {
            return 1;
        }
    }

    public void setUsers(String users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    public void setNewsProductData(NewsProductInfoBean newsProductInfoBean) {
        mNewsProductInfoBean = newsProductInfoBean;
    }


    class RecoHolder extends RecyclerView.ViewHolder {
        RecoHolder(View view) {
            super(view);
        }

        public void bindView() {

        }
    }


    /*class NormalHolder extends RecyclerView.ViewHolder {
        private View mView;

        NormalHolder(View view) {
            super(view);
            mView = view;
        }

        public void bindView() {
            GoodsItemView goodsItemView = (GoodsItemView) mView;

        }
    }*/

    class NormalHolder extends RecyclerView.ViewHolder {

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
        private View mView;

        NormalHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        public void bindView(int position) {
            final NewsProductInfoBean.ProductListBean productListBean = mNewsProductInfoBean.getProductList().get(position - 2);
            //渲染首页商品列表
            mTvTitle.setText(productListBean.getName());
            mTvPrice.setText(PriceFormater.format(productListBean.getPrice()));
            Glide.with(mContext).load(Constant.BASE_URL + productListBean.getPic()).placeholder(R.mipmap.default_pic).into(mIvPic);
            mGoodsItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点了" + productListBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
           /* if (mNewsProductInfoBean != null) {
                GoodsItemView goodsItemView = (GoodsItemView) mView;
                NewsProductInfoBean.ProductListBean productListBean = mNewsProductInfoBean.getProductList().get(position);
                goodsItemView.setTitle(productListBean.getName());
                goodsItemView.setImagUrl(productListBean.getPic());
                goodsItemView.setPrice(productListBean.getPrice());
            }*/

        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_collection)
        TextView mTvCollection;
        @BindView(R.id.tv_level)
        TextView mTvLevel;
        @BindView(R.id.iv_signOrReg)
        ImageView mIvSignOrReg;
        @BindView(R.id.rl_signOrReg)
        RelativeLayout mRlSignOrReg;
        @BindView(R.id.tv_address_manage)
        TextView mTvAddressManage;
        @BindView(R.id.ll_order)
        LinearLayout mLlOrder;
        @BindView(R.id.tv_login)
        TextView mTvLogin;
        @BindView(R.id.tv_jdou)
        TextView mTvJdou;
        @BindView(R.id.tv_fav)
        TextView mTvFav;
        @BindView(R.id.tv_order_num)
        TextView mTvOrderNum;

        @OnClick({R.id.rl_signOrReg, R.id.tv_address_manage, R.id.ll_order, R.id.tv_collection})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_signOrReg:
                    if ("".equals(PreferenceUtils.getUserId(mContext))) {

                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                    }
                    break;
                case R.id.tv_address_manage:
                    if ("".equals(PreferenceUtils.getUserId(mContext))) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                    } else {

                        Intent intent = new Intent(mContext, RecepitAddressActivity.class);
                        mContext.startActivity(intent);
                    }
                    break;
                case R.id.ll_order:
                    if ("".equals(PreferenceUtils.getUserId(mContext))) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                    } else {

                        Intent intent = new Intent(mContext, OrderActivity.class);
                        mContext.startActivity(intent);
                    }
                    break;
                case R.id.tv_collection:
                    if ("".equals(PreferenceUtils.getUserId(mContext))) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                    } else {

                        Intent intent = new Intent(mContext, CollectionActivity.class);
                        mContext.startActivity(intent);
                    }
                    break;


            }
        }


        TitleHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView() {
/*            if (mLoginInfoBean != null) {
//                mRlSignOrReg.setVisibility(View.GONE);
//                mRlSignOk.setVisibility(View.VISIBLE);
            }*/
            if (!"".equals(mUsers)) {
                mTvLogin.setText("您好，" + mUsers);
                mIvSignOrReg.setBackgroundResource(R.mipmap.fragment_daidai);
                mRlSignOrReg.setBackgroundResource(R.mipmap.b0x);

            } else {
                mTvLogin.setText("登录/注册");
                mTvLevel.setText("会员等级");
                mTvFav.setText("0");
                mTvJdou.setText("0");
                mTvOrderNum.setText("0");
                mIvSignOrReg.setBackgroundResource(R.mipmap.b0p);
                mRlSignOrReg.setBackgroundResource(R.mipmap.b0z);
                return;
            }


            if (mInfoBean != null) {
                Log.d(TAG, "bindView: " + mInfoBean.getLevel());
                mTvLevel.setText(mInfoBean.getLevel());
                mTvFav.setText(mInfoBean.getFavoritesCount() + "");
                mTvJdou.setText(mInfoBean.getBonus() + "");
                mTvOrderNum.setText(mInfoBean.getOrderCount() + "");

            }

        }
    }
}
