package com.young.jdmall.ui.adapter;

import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.ui.view.RecyclerRefreshLayout;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class SettingAdapter extends PagerAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(new SettingContainerAdapter1());
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
        container.addView(recyclerRefreshLayout);
        return recyclerRefreshLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem();
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
