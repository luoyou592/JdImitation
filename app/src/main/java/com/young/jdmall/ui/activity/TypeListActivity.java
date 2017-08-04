package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.young.jdmall.R;
import com.young.jdmall.bean.ProductBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.TypeListAdapter;
import com.young.jdmall.ui.widget.ViewTypeHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/1.
 * 包名:com.young.jdmall.ui.View.CategoryView
 * 时间:2017/8/1
 */

public class TypeListActivity extends BaseActivity {

    private static final String TAG = "TypeListActivity";

    @BindView(R.id.view_type_list_header)
    ViewTypeHeader mViewTypeListHeader;
    private List<ProductBean.ProductListBean> mData = new ArrayList<>();
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;


    private List<ProductBean.ProductListBean> mProductList;
    private TypeListAdapter mTypeListAdapter;
    private int page = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);
        ButterKnife.bind(this);

        loadData();
        init();
    }

    //初始化
    private void init() {

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //设置适配器
        mTypeListAdapter = new TypeListAdapter(this);
        mRecycleView.setAdapter(mTypeListAdapter);

//        mViewTypeListHeader.setOnClickPrimaryListener(new ViewTypeHeader.onClickPrimaryListener() {
//            @Override
//            public void onPrimaryVolume() {
//                Log.d(TAG, "onPrimaryVolume: 修改请求");
//                loadPrimary("saleDown");
//            }
//
//            @Override
//            public void onPrimaryEvaluate() {
//                loadPrimary("shelvesDown");
//            }
//
//            @Override
//            public void onPrimaryPrice(boolean isMode) {
//                if (isMode){
//                    loadPrimary("priceDown");
//                }else {
//                    loadPrimary("priceUp");
//                }
//            }
//
//            @Override
//            public void onPrimaryScreen() {
//                Log.d(TAG, "onPrimaryScreen: 弹出筛选菜单");
//            }
//
//            @Override
//            public void onBack() {
//                Log.d(TAG, "onBack: 销毁");
//                finish();
//            }
//
//            @Override
//            public void onSelectorLayout() {
//                Log.d(TAG, "onSelectorLayout: 修改布局");
//            }
//        });
    }

    private void loadMoreData(int page) {
        page++;
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductList(page, 10, 125, "saleDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(this) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.addData(mProductList);
//                mRecycleView.setAdapter(mTypeListAdapter);
                mRecycleView.smoothScrollToPosition(mProductList.size());
            }
            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 请求失败");
            }
        });
    }

    private void loadPrimary(String saleDown) {
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductList(1, 10, 125, saleDown);
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(this) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.setData(mProductList);
                mTypeListAdapter.notifyDataSetChanged();
//                mRecycleView.setAdapter(mTypeListAdapter);
            }
            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 请求失败");
            }
        });
    }


    //加载数据
    private void loadData() {

        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductList(1, 10, 125, "shelvesDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(this) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.setData(mProductList);
//                mRecycleView.setAdapter(mTypeListAdapter);
            }
            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 请求失败");
            }
        });

    }
}
