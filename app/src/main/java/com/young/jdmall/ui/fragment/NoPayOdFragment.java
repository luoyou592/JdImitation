package com.young.jdmall.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.young.jdmall.R;
import com.young.jdmall.bean.OrderInfoBean;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.AllOrderAdapter;
import com.young.jdmall.ui.utils.PreferenceUtils;
import com.young.jdmall.ui.view.RecyclerRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 25505 on 2017/8/2.
 */

public class NoPayOdFragment extends Fragment {
    @BindView(R.id.order_recycler_view)
    RecyclerView mOrderRecyclerView;
    @BindView(R.id.ll_nonOrder)
    LinearLayout mLlNonOrder;
    @BindView(R.id.fv_fresh)
    RecyclerRefreshLayout mFvFresh;
    private AllOrderAdapter mAllOrderAdapter;
    private int page = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View receiveView = inflater.inflate(R.layout.fragment_total, null);
        ButterKnife.bind(this, receiveView);
        initView();
        initData();
        return receiveView;
    }

    private void initView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mOrderRecyclerView.setLayoutManager(layoutManager);
        mAllOrderAdapter = new AllOrderAdapter(getActivity(), 1, mLlNonOrder);
        mOrderRecyclerView.setAdapter(mAllOrderAdapter);
        mOrderRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = layoutManager.findLastCompletelyVisibleItemPosition();
                if (position == mAllOrderAdapter.getItemCount() - 2) {
                    page++;
                    initData();
                }
            }
        });
    }

    private static final String TAG = "NoPayOdFragment";

    private void initData() {
        Call<OrderInfoBean> call = (Call<OrderInfoBean>) RetrofitFactory.getInstance().listOrderInfo(PreferenceUtils.getUserId(getActivity()), 1, page, 10);
        call.enqueue(new Callback<OrderInfoBean>() {
            @Override
            public void onResponse(Call<OrderInfoBean> call, Response<OrderInfoBean> response) {
                if (response.body().getOrderList() != null) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse());
//                    Toast.makeText(getActivity(), "获取1订单", Toast.LENGTH_SHORT).show();
                    mAllOrderAdapter.setAddressBeanList(response.body().getOrderList());

                } else {
//                    Toast.makeText(getActivity(), response.body().getResponse(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderInfoBean> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();
        mFvFresh.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        mFvFresh.post(new Runnable() {
                            @Override
                            public void run() {
                                mFvFresh.closeRefresh();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
