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

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.young.jdmall.R;
import com.young.jdmall.ui.activity.SecKillActivity;
import com.young.jdmall.ui.widget.CountDownView;
import com.young.jdmall.ui.widget.CricleIndicatorView;
import com.young.jdmall.ui.widget.GoodsItemView;
import com.young.jdmall.ui.widget.NoticeView;

import java.util.HashMap;

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


    public HomeRvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else if (position==1){
         return TYPE_NORMAL_HEAD;
        }else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            View titleView = LayoutInflater.from(mActivity).inflate(R.layout.item_home_title, parent, false);
            return new TitleViewHolder(titleView);
        } else if (viewType== TYPE_NORMAL_HEAD){
            ImageView imageView = new ImageView(mActivity);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.mipmap.tuijian);
            return new HeadViewHolder(imageView);
        }else {
            //View normalView = LayoutInflater.from(mActivity).inflate(R.layout.item_normal, parent, false);
            GoodsItemView normalView = new GoodsItemView(mActivity);
            return new NormalViewHolder(normalView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == TYPE_TITLE) {
            ((TitleViewHolder) holder).bindView(position);
        } else if (position == TYPE_NORMAL_HEAD){
            ((HeadViewHolder)holder).bindView();
        }else {
            ((NormalViewHolder) holder).bindView();
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_slider)
        SliderLayout mHomeSlider;
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

        TitleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(int position) {
            mNoticeView.addNotice(notices);
            mNoticeView.startFlipping();
            HashMap<String, Integer> url_maps = new HashMap<>();
            url_maps.put("Hannibal", R.mipmap.vp1);
            url_maps.put("Big Bang Theory", R.mipmap.vp2);
            url_maps.put("House of Cards", R.mipmap.vp3);
            for (String desc : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(mActivity);
                textSliderView
                        .description(desc)
                        .image(url_maps.get(desc));
                mHomeSlider.addSlider(textSliderView);
            }
            //内嵌适配器
            HomePageAdapter homePageAdapter = new HomePageAdapter(mActivity);
            mHomeVp.setAdapter(homePageAdapter);
            mIndicatorView.setViewPage(mHomeVp);
            //内嵌recycleview适配器
            mFlashRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayout.HORIZONTAL,false));
            FlashRvAdapter flashRvAdapter = new FlashRvAdapter(mActivity,mCountDownView);
            mFlashRecyclerView.setAdapter(flashRvAdapter);
            //倒计时开启
            mCountDownView.setTime(1500);
            mCountDownView.startCountDown();
            mSeckillContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, SecKillActivity.class);
                    long time = mCountDownView.getTime();
                    Log.d("luoyou", "time"+time);
                    intent.putExtra("time", time);
                    mActivity.startActivity(intent);
                }
            });

        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        private View mView;

        NormalViewHolder(View view) {
            super(view);
            mView = view;
        }

        public void bindView() {
            GoodsItemView goodsItemView = (GoodsItemView) mView;

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


