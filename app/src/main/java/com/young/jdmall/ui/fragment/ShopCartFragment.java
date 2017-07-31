package com.young.jdmall.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.R;
import com.young.jdmall.ui.adapter.ShoppingCarFragmentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class ShopCartFragment extends Fragment {

    @BindView(R.id.rv_shopcar)
    RecyclerView mRvShopcar;
    private ShoppingCarFragmentAdapter mShoppingCarFragmentAdapter;

    private List<Map<String, Object>> mMapList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View shopCarView = inflater.inflate(R.layout.shop_car, null);
        ButterKnife.bind(this, shopCarView);
        mShoppingCarFragmentAdapter = new ShoppingCarFragmentAdapter(getActivity());
        mRvShopcar.setAdapter(mShoppingCarFragmentAdapter);
        mRvShopcar.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d("shopcar", "fragment创建布局,添加适配器");
        init();

        return shopCarView;

    }


    private void init() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 25; i++) {
            map.put("name", "name" + i);
        }
        mMapList.add(map);
        mShoppingCarFragmentAdapter.setList(mMapList);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("shopcar", "设置数据");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
