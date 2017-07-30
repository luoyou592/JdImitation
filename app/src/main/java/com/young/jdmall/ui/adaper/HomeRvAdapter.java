package com.young.jdmall.ui.adaper;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.young.jdmall.R;
import com.young.jdmall.ui.widget.NoticeView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/30.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_NORMAL = 1;
    private Activity mActivity;
    private String[] notices = new String[]{"大促销下单拆福袋，亿万新年红包随便拿", "家电五折团，抢十亿无门槛现金红包", "星球大战剃须刀首发送200元代金券"};
    private int[] vpImgIds = new int[]{R.mipmap.vp1, R.mipmap.vp2, R.mipmap.vp3};
    private int[] menuImgIds = new int[]{R.mipmap.menu, R.mipmap.menu1};


    public HomeRvAdapter(Activity activity) {
        mActivity = activity;
    }

    /*NoticeView noticeView = (NoticeView) mHomeView.findViewById(R.id.notice_view);
            List<String> notices = new ArrayList<>();
            notices.add("大促销下单拆福袋，亿万新年红包随便拿");
            notices.add("家电五折团，抢十亿无门槛现金红包");
            notices.add("星球大战剃须刀首发送200元代金券");
            noticeView.addNotice(notices);
            noticeView.startFlipping();*/

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            View titleView = LayoutInflater.from(mActivity).inflate(R.layout.item_home_title, parent, false);
            return new TitleViewHolder(titleView);
        } else {
            View normalView = LayoutInflater.from(mActivity).inflate(R.layout.item_normal, parent, false);
            return new NormalViewHolder(normalView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((TitleViewHolder) holder).bindView(position);
        } else {
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


        }
    }
    class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;

        NormalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView() {

        }
    }
}


