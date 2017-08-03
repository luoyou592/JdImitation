package com.young.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.R;
import com.young.jdmall.bean.CartInfoBean;
import com.young.jdmall.bean.RecommendInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.ShoppingCarFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;


public class ShopCartFragment extends BaseFragment {

    @BindView(R.id.rv_shopcar)
    RecyclerView mRvShopcar;
    private ShoppingCarFragmentAdapter mShoppingCarFragmentAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View shopCarView = inflater.inflate(R.layout.shop_car, null);
        ButterKnife.bind(this, shopCarView);
        mShoppingCarFragmentAdapter = new ShoppingCarFragmentAdapter(getActivity());
        initView();
        initData();
        mRvShopcar.setAdapter(mShoppingCarFragmentAdapter);
        return shopCarView;

    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        mRvShopcar.setLayoutManager(manager);
        Log.d("shopcar", "fragment创建布局,添加适配器");

    }

    private void initData() {
        Observable<CartInfoBean> cartInfoBeanObservable = RetrofitFactory.getInstance().listCart("1:3:1,2,3,4|2:2:2,3");
        cartInfoBeanObservable.compose(compose(this.<CartInfoBean>bindToLifecycle())).subscribe(new BaseObserver<CartInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(CartInfoBean cartInfoBean) {
                mShoppingCarFragmentAdapter.setList(cartInfoBean.getCart());
                Log.d("data","cartInfoBean=============="+cartInfoBean.toString());
            }
        });

        Observable<RecommendInfoBean> recommendInfoBeanObservable = RetrofitFactory.getInstance().listRecommend(1,10,"saleDown");
        recommendInfoBeanObservable.compose(compose(this.<RecommendInfoBean>bindToLifecycle())).subscribe(new BaseObserver<RecommendInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(RecommendInfoBean recommendInfoBean) {
                mShoppingCarFragmentAdapter.setData(recommendInfoBean.getProductList());
                Log.d("data","recommendInfoBean============="+recommendInfoBean.getProductList().toString());
            }
        });


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
