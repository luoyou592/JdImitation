package com.young.jdmall.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.young.jdmall.R;
import com.young.jdmall.bean.ProductBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.TypeAdapter;
import com.young.jdmall.ui.adapter.TypeWaterfallAdapter;
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
    private TypeWaterfallAdapter mWaterfallAdapter;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);
        ButterKnife.bind(this);
        mContext = this;

        loadData();
        init();
    }

    //初始化
    private void init() {


        mRecycleView.setOnRefreshListener(new RecyclerLoadMoreView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMoreData(page);
            }
        });

        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));

        //设置适配器

        mTypeListAdapter = new TypeAdapter();
        mRecycleView.setAdapter(mTypeListAdapter);
        mRecycleView.setLoadMoreOpportunity(1);

        setListener();

    }



    /**----------------------------------请求数据----------------------------------------*/
    private void loadMoreData(int page) {
        page++;
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductlist(page, 10, 125, "saleDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.addData(mProductList);
                if (mWaterfallAdapter != null){
                    Log.d(TAG, "onHandleSuccess: 获取请求数据");
                    mWaterfallAdapter.addData(mProductList);
                }
                mRecycleView.onLoadFinish();
            }
            @Override
            protected void onHandleError(String msg) {
                mRecycleView.onLoadFinish();
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
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.setData(mProductList);
                mRecycleView.onLoadFinish();
                if (mWaterfallAdapter != null){
                    mWaterfallAdapter.setData(mProductList);
                }
            }
            @Override
            protected void onHandleError(String msg) {
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
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductlist(1, 10, 125, "shelvesDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.addData(mProductList);
                if (mWaterfallAdapter != null){
                    Log.d(TAG, "onHandleSuccess: 重新设置数据");
                    mWaterfallAdapter.setData(mProductList);
                }
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
        /**----------------------------------请求数据----------------------------------------*/
    }




    private void setListener() {
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
            @Override
            public void onPrimaryScreen() {
                Log.d(TAG, "onPrimaryScreen: 弹出侧边菜单");
            }

            @Override
            public void onBack() {
                finish();
//                Log.d(TAG, "onBack: 有bug");
            }

            @Override
            public void onSelectorLayout(boolean isRecyclerView) {
                Log.d(TAG, "onSelectorLayout: 修改布局");
                if (!isRecyclerView){
                    //设置为垂直布局
                    mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
                    mRecycleView.setAdapter(mTypeListAdapter);
                }else {
                    if (mWaterfallAdapter == null){
                        mWaterfallAdapter = new TypeWaterfallAdapter(mContext);
                        loadData();
                    }
                    mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    mRecycleView.setAdapter(mWaterfallAdapter);
                }

            }
        });
    }

}
