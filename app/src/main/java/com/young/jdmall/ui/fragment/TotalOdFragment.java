package com.young.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.R;
import com.young.jdmall.bean.OrderInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.OrderRvAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 25505 on 2017/8/2.
 */

public class TotalOdFragment extends BaseFragment {
    @BindView(R.id.order_recycler_view)
    RecyclerView mOrderRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View totalView = inflater.inflate(R.layout.fragment_total, null);
        ButterKnife.bind(this, totalView);
        initView();
        initData();
        return totalView;
    }

    private void initData() {
        //获取全部订单列表
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),"plain text request body");
        // TODO: 2017/8/3
        Observable<OrderInfoBean> orderObservable = RetrofitFactory.getInstance().listOrderInfo("20428", body);
        orderObservable.compose(compose(this.<OrderInfoBean>bindToLifecycle())).subscribe(new BaseObserver<OrderInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(OrderInfoBean orderInfoBean) {

            }
        });
    }

    private void initView() {
        mOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrderRecyclerView.setAdapter(new OrderRvAdapter(getActivity()));
    }
}
