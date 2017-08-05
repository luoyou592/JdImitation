package com.young.jdmall.ui.adapter;

import android.app.Application;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.young.jdmall.R;
import com.young.jdmall.app.JDMallApplication;
import com.young.jdmall.bean.FindInfoBean;
import com.young.jdmall.bean.GirlInfoBean;
import com.young.jdmall.network.GsonRequest;
import com.young.jdmall.network.JDMallService;
import com.young.jdmall.ui.view.RecyclerLoadMoreView;
import com.young.jdmall.ui.view.RecyclerRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class SettingAdapter extends PagerAdapter {

    private static final String TAG = "SettingAdapter";

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

        final RecyclerLoadMoreView recyclerView = new RecyclerLoadMoreView(container.getContext());
//        recyclerView.setLoadMoreOpportunity(1);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        final SettingLiveLoadMoreAdapter adapter = new SettingLiveLoadMoreAdapter();
        recyclerView.setAdapter(adapter);
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

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://pic.sogou.com/pics/channel/")////category=%C3%C0%C5%AE&tag=%E5%85%A8%E9%83%A8
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        retrofit.create(JDMallService.class).listGirl("C3%C0%C5%AE", "%E5%85%A8%E9%83%A8", 0, 15).enqueue(new Callback<GirlInfoBean>() {
//            @Override
//            public void onResponse(Call<GirlInfoBean> call, Response<GirlInfoBean> response) {
//                List<GirlInfoBean.AllItemsBean> allItems = response.body().getAll_items();
//                Log.d(TAG, "onResponse: " + allItems.get(0).getOri_pic_url());
//                recyclerView.onLoadSuccess();
//            }
//
//            @Override
//            public void onFailure(Call<GirlInfoBean> call, Throwable t) {
//                recyclerView.onLoadFailure();
//            }
//        });

        getLiveData(recyclerView, adapter);
        recyclerView.setOnRefreshListener(new RecyclerLoadMoreView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLiveData(recyclerView, adapter);
            }
        });
        return relativeLayout;


//        RecyclerView recyclerView = new RecyclerView(container.getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
//        recyclerView.setAdapter(new SettingLiveAdapter(FindInfoBean.getFinInfoBeanList()));
//        final RecyclerRefreshLayout recyclerRefreshLayout = new RecyclerRefreshLayout(container.getContext());
//        recyclerRefreshLayout.addView(recyclerView);
//        recyclerRefreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
//            @Override
//            public void OnRefresh() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        SystemClock.sleep(2000);
//                        recyclerRefreshLayout.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                recyclerRefreshLayout.closeRefresh();
//                            }
//                        });
//                    }
//                }).start();
//            }
//        });
//        RelativeLayout relativeLayout = new RelativeLayout(container.getContext());
//        relativeLayout.addView(recyclerRefreshLayout);
//        relativeLayout.setBackgroundResource(R.drawable.live_item_bg);
//        container.addView(relativeLayout);
//        return relativeLayout;

    }

    private void getLiveData(final RecyclerLoadMoreView recyclerView, final SettingLiveLoadMoreAdapter adapter) {
        String url = "http://pic.sogou.com/pics/channel/getAllRecomPicByTag.jsp?category=%C3%C0%C5%AE&tag=%E5%85%A8%E9%83%A8&start=" + adapter.getItemCountToRecyclerLoadMoreView() + "&len=15";
        GsonRequest<GirlInfoBean> request = new GsonRequest<>(GirlInfoBean.class, url, new GsonRequest.onGsonRequestListener<GirlInfoBean>() {
            @Override
            public void completeRequest(GirlInfoBean response, boolean result) {
                List<GirlInfoBean.AllItemsBean> all_items = response.getAll_items();
                adapter.addData(all_items);
                Log.d(TAG, "completeRequest: " + all_items.get(0).getOri_pic_url());
                recyclerView.onLoadSuccess();
            }

            @Override
            public void completeRequest(VolleyError error) {
                recyclerView.onLoadFailure();
            }
        });
        JDMallApplication.getVolley().add(request);
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
