package com.young.jdmall.ui.adapter;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.young.jdmall.R;
import com.young.jdmall.bean.FindInfoBean;
import com.young.jdmall.ui.view.RecyclerRefreshLayout;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class SettingAdapter extends PagerAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        switch (position) {
            case 1:
                RelativeLayout liveView = getLiveView(container);
                return liveView;
            default:
                RelativeLayout relativeLayout = getContainerView(container);
                return relativeLayout;
        }
    }

    @NonNull
    private RelativeLayout getContainerView(ViewGroup container) {
        RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(new SettingContainerAdapter1(FindInfoBean.getFinInfoBeanList()));
        final RecyclerRefreshLayout recyclerRefreshLayout = new RecyclerRefreshLayout(container.getContext());
        recyclerRefreshLayout.addView(recyclerView);
        recyclerRefreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        recyclerRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                recyclerRefreshLayout.closeRefresh();
                            }
                        });
                    }
                }).start();
            }
        });
        RelativeLayout relativeLayout = new RelativeLayout(container.getContext());
        relativeLayout.addView(recyclerRefreshLayout);
        container.addView(relativeLayout);
        return relativeLayout;
    }

    private RelativeLayout getLiveView(ViewGroup container) {
        RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(new SettingLiveAdapter(FindInfoBean.getFinInfoBeanList()));
        final RecyclerRefreshLayout recyclerRefreshLayout = new RecyclerRefreshLayout(container.getContext());
        recyclerRefreshLayout.addView(recyclerView);
        recyclerRefreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        recyclerRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                recyclerRefreshLayout.closeRefresh();
                            }
                        });
                    }
                }).start();
            }
        });
        RelativeLayout relativeLayout = new RelativeLayout(container.getContext());
        relativeLayout.addView(recyclerRefreshLayout);
        relativeLayout.setBackgroundResource(R.drawable.live_item_bg);
        container.addView(relativeLayout);
        return relativeLayout;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 13;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
