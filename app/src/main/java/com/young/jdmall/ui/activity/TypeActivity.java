package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.young.jdmall.R;
import com.young.jdmall.bean.ProductBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.TypeAdapter;
import com.young.jdmall.ui.view.RecyclerLoadMoreView;
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

public class TypeActivity extends BaseActivity {

    private static final String TAG = "TypeListActivity";

    @BindView(R.id.view_type_list_header)
    ViewTypeHeader mViewTypeListHeader;
    private List<ProductBean.ProductListBean> mData = new ArrayList<>();
    @BindView(R.id.recycle_view)
    RecyclerLoadMoreView mRecycleView;


    private List<ProductBean.ProductListBean> mProductList;
    private TypeAdapter mTypeListAdapter;
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
        //添加布局管理器
        //添加滑动监听
//        mRecycleView.addOnScrollListener(new TypeListListener(findViewById(R.id
//                .view_type_list_header)));


//        mRecycleView.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadMoreData(page);
//            }
//
//            @Override
//            public void onReload() {
//                Log.d(TAG, "onReload: 网络异常");
//            }
//        });

        mRecycleView.setOnRefreshListener(new RecyclerLoadMoreView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMoreData(page);
            }
        });

//        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //设置适配器
        mTypeListAdapter = new TypeAdapter();
        mRecycleView.setAdapter(mTypeListAdapter);
        mRecycleView.setLoadMoreOpportunity(1);

        mViewTypeListHeader.setOnClickPrimaryListener(new ViewTypeHeader.onClickPrimaryListener() {
            @Override
            public void onPrimaryVolume() {
                loadPrimary("saleDown");
            }

            @Override
            public void onPrimaryEvaluate() {
                loadPrimary("shelvesDown");
            }

            @Override
            public void onPrimaryPrice(boolean isMode) {
                if (isMode) {
                    loadPrimary("priceDown");
                } else {
                    loadPrimary("priceUp");
                }
            }
        });
    }

    private void loadMoreData(int page) {
        page++;
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductlist(page, 10, 125, "saleDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(this) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.addData(mProductList);
                mRecycleView.onLoadFinish();
            }

            @Override
            protected void onHandleError(String msg) {
                mRecycleView.onLoadFinish();
                Log.d(TAG, "onHandleError: 请求失败");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRecycleView.onLoadFinish();
            }
        });
    }

    private void loadPrimary(String saleDown) {
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductlist(1, 10, 125, saleDown);
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(this) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.addData(mProductList);
                mTypeListAdapter.notifyDataSetChanged();
                mRecycleView.onLoadFinish();
            }

            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 请求失败");
                mRecycleView.onLoadFinish();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRecycleView.onLoadFinish();
            }
        });
    }


    //加载数据
    private void loadData() {

        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductlist(1, 10, 125, "saleDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(this) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                Log.d(TAG, "onHandleSuccess: 得到数据");
                mTypeListAdapter.addData(mProductList);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRecycleView.onLoadFinish();
            }

            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 请求失败");
            }
        });

    }
}
