package com.young.jdmall.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.BrandInfoBean;
import com.young.jdmall.bean.HomeInfoBean;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.ui.activity.ProductDetaiActivity;
import com.young.jdmall.ui.activity.SecKillActivity;
import com.young.jdmall.ui.utils.PriceFormater;
import com.young.jdmall.ui.view.ZoomOutPageTransformer;
import com.young.jdmall.ui.widget.CountDownView;
import com.young.jdmall.ui.widget.CricleIndicatorView;
import com.young.jdmall.ui.widget.NoticeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/30.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_NORMAL_HEAD = 1;  //商品列表标题
    private static final int TYPE_NORMAL = 2;  //商品条目

    private Activity mActivity;
    private String[] notices = new String[]{"大促销下单拆福袋，亿万新年红包随便拿", "家电五折团，抢十亿无门槛现金红包", "星球大战剃须刀首发送200元代金券"};
    private int[] vpImgIds = new int[]{R.mipmap.vp1, R.mipmap.vp2, R.mipmap.vp3};
    private int[] menuImgIds = new int[]{R.mipmap.menu, R.mipmap.menu1};
    private List<HomeInfoBean.HomeTopicBean> mHomeTopic;
    private NewsProductInfoBean mNewsProductInfoBean;
    private LimitbuyBean mLimitbuyBean;
    private BrandInfoBean mBrandInfoBean;


    public HomeRvAdapter(Activity activity) {
        mActivity = activity;
    }

    public void setHomeData(List<HomeInfoBean.HomeTopicBean> homeTopic) {
        mHomeTopic = homeTopic;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else if (position == 1) {
            return TYPE_NORMAL_HEAD;
        } else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            View titleView = LayoutInflater.from(mActivity).inflate(R.layout.item_home_title, parent, false);
            return new TitleViewHolder(titleView);
        } else if (viewType == TYPE_NORMAL_HEAD) {
            ImageView imageView = new ImageView(mActivity);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.mipmap.tuijian);
            return new HeadViewHolder(imageView);
        } else {
            View normalView = LayoutInflater.from(mActivity).inflate(R.layout.goods_item_view, parent, false);
            //GoodsItemView normalView = new GoodsItemView(mActivity);
            return new NormalViewHolder(normalView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == TYPE_TITLE) {
            ((TitleViewHolder) holder).bindView(position);
        } else if (position == TYPE_NORMAL_HEAD) {
            ((HeadViewHolder) holder).bindView();
        } else {
            ((NormalViewHolder) holder).bindView(position);
        }
    }

    //商品条目上面有两个头
    @Override
    public int getItemCount() {
        if (mNewsProductInfoBean != null) {
            return mNewsProductInfoBean.getListCount() + 2;
        }
        return 0;
    }

    public void setLimitProductData(LimitbuyBean limitbuyBean) {
        mLimitbuyBean = limitbuyBean;
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_banner)
        ConvenientBanner mHomeBanner;
        @BindView(R.id.home_vp)
        ViewPager mHomeVp;
        @BindView(R.id.notice_view)
        NoticeView mNoticeView;
        @BindView(R.id.cricle_view)
        CricleIndicatorView mIndicatorView;
        @BindView(R.id.flash_recycler_view)
        RecyclerView mFlashRecyclerView;
        @BindView(R.id.count_down_view)
        CountDownView mCountDownView;
        @BindView(R.id.seckill_container)
        LinearLayout mSeckillContainer;
        private List<String> imgUrls = new ArrayList<>();

        TitleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mHomeBanner.setPageIndicator(new int[]{R.mipmap.ari, R.mipmap.arh});
            mHomeBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            mHomeBanner.setPageTransformer(new ZoomOutPageTransformer());
            mHomeBanner.isTurning();
            mHomeBanner.startTurning(2500);

        }

        public void bindView(int position) {
            mNoticeView.addNotice(notices);
            mNoticeView.startFlipping();
            //头部轮播图设置
            for (int i = 0; i < mHomeTopic.size(); i++) {
                HomeInfoBean.HomeTopicBean homeTopicBean = mHomeTopic.get(i);
                imgUrls.add(Constant.BASE_URL+homeTopicBean.getPic());
            }
            mHomeBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new NetworkImageHolderView();
                }
            },imgUrls);
            //内嵌适配器
            HomePageAdapter homePageAdapter = new HomePageAdapter(mActivity);
            mHomeVp.setAdapter(homePageAdapter);
            mIndicatorView.setViewPage(mHomeVp);
           /* mHomeVp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
            //内嵌recycleview适配器,
            mFlashRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayout.HORIZONTAL, false));
            FlashRvAdapter flashRvAdapter = new FlashRvAdapter(mActivity, mCountDownView,mLimitbuyBean);
            mFlashRecyclerView.setAdapter(flashRvAdapter);
            //倒计时开启  时间从限时商品获取
            mCountDownView.setTime(mLimitbuyBean.getProductList().get(1).getLeftTime());
            mCountDownView.startCountDown();
            mSeckillContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, SecKillActivity.class);
                    long time = mCountDownView.getTime();
                    Log.d("luoyou", "time" + time);
                    intent.putExtra("time", time);
                    mActivity.startActivity(intent);
                }
            });

        }
    }

    //商品列表展示
    public void setNewsProductData(NewsProductInfoBean newsProductInfoBean) {
        mNewsProductInfoBean = newsProductInfoBean;
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
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

        NormalViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this,view);
        }

        public void bindView(int position) {
            final NewsProductInfoBean.ProductListBean productListBean = mNewsProductInfoBean.getProductList().get(position-2);
            //渲染首页商品列表
            mTvTitle.setText(productListBean.getName());
            mTvPrice.setText(PriceFormater.format(productListBean.getPrice()));
            Glide.with(mActivity).load(Constant.BASE_URL+productListBean.getPic()).placeholder(R.mipmap.default_pic).into(mIvPic);
            mGoodsItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ProductDetaiActivity.class);
                    //传id到详情
                    intent.putExtra("id",productListBean.getId());
                    mActivity.startActivity(intent);
                }
            });
           /* if (mNewsProductInfoBean != null) {
                GoodsItemView goodsItemView = (GoodsItemView) mView;
                NewsProductInfoBean.ProductBean productListBean = mNewsProductInfoBean.getProductList().get(position);
                goodsItemView.setTitle(productListBean.getName());
                goodsItemView.setImagUrl(productListBean.getPic());
                goodsItemView.setPrice(productListBean.getPrice());
            }*/

        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        HeadViewHolder(View view) {
            super(view);
        }

        public void bindView() {

        }
    }
}


