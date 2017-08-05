package com.young.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.R;
import com.young.jdmall.bean.OrderInfoBean;
import com.young.jdmall.ui.adapter.AllOrderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 21:43
 *  描述：    TODO
 */
public class OrderALLFragment extends Fragment {

    @BindView(R.id.rv_all_order)
    RecyclerView mRvAllOrder;
    private AllOrderAdapter mAllOrderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_order, null);
        ButterKnife.bind(this, root);
        init();
        return root;

    }

    public void init() {
        mRvAllOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mAllOrderAdapter = new AllOrderAdapter(getActivity(), 3, mLlNonOrder);
        mRvAllOrder.setAdapter(mAllOrderAdapter);
        List<OrderInfoBean.OrderListBean> data = initTempData();
        mAllOrderAdapter.setAddressBeanList(data);

    }

    private List<OrderInfoBean.OrderListBean> mOrderInfoBeanList = new ArrayList<>();

    public List<OrderInfoBean.OrderListBean> initTempData() {

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            /*OrderInfoBean orderInfoBean = new OrderInfoBean();
            orderInfoBean.desc = "描述"+i;

            orderInfoBean.num = String.valueOf(random.nextInt(10));
            orderInfoBean.price = String.valueOf(random.nextInt(100));
            mOrderInfoBeanList.add(orderInfoBean.getOrderList().get(i));*/
        }
        Log.d(TAG, "initTempData: " + mOrderInfoBeanList.size());

        return mOrderInfoBeanList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        null.unbind();
    }
}
