package com.young.jdmall.ui.activity;

import android.content.Context;
import android.content.Intent;
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
    @BindView(R.id.recycle_view)
    RecyclerLoadMoreView mRecycleView;


    private List<ProductBean.ProductListBean> mProductList;
    private TypeAdapter mTypeListAdapter;
    private int page = 2;
    private TypeWaterfallAdapter mWaterfallAdapter;
    private Context mContext;

    private int loadData = 0;
    private String mKeyword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);
        ButterKnife.bind(this);
        mContext = this;

        Intent intent = getIntent();
        mKeyword = intent.getStringExtra("keyword");
        Log.d(TAG, "onCreate: " + mKeyword);
        if (mKeyword == null) {
            //分类页面进来的
            loadData = 0;
            initCommodity();
            loadCommodityData();
        } else {
            //搜索页面进来的
            loadData = 1;
            initSearch();
            loadSearchData(mKeyword);
        }
    }

    /**
     * 分类页面初始化
     */
    private void initCommodity() {
        if (loadData == 1) {
            return;
        }
        mRecycleView.setOnRefreshListener(new RecyclerLoadMoreView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMoreData(page);
            }
        });
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        //默认的适配器
        mTypeListAdapter = new TypeAdapter(this);
        mRecycleView.setAdapter(mTypeListAdapter);
        mRecycleView.setLoadMoreOpportunity(1);
        setListener();
    }

    /**
     * 搜索页面初始化
     */
    private void initSearch() {
        if (loadData == 0) {
            return;
        }
        mRecycleView.setOnRefreshListener(new RecyclerLoadMoreView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadSearchMoreData(page, mKeyword);
            }
        });
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        //默认的适配器
        mTypeListAdapter = new TypeAdapter(this);
        mRecycleView.setAdapter(mTypeListAdapter);
        mRecycleView.setLoadMoreOpportunity(1);
        setListener();
    }


    /**
     * ----------------------------------请求搜索列表数据----------------------------------------
     */
    private void loadSearchData(String keyword) {
        //加载数据
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listSearch(1,
                10, "saleDown", keyword);
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver
                <ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean searchListBean) {
                mProductList = searchListBean.getProductList();
                mTypeListAdapter.setData(mProductList);
                if (mWaterfallAdapter != null) {
                    mWaterfallAdapter.setData(mProductList);
                }
                mRecycleView.onLoadFinish();
            }

            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 加载失败");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRecycleView.onLoadFinish();
            }
        });
    }

    private void loadSearchMoreData(int page, String mKeyword) {
        page++;
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listSearch
                (page, 10, "saleDown", mKeyword);
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver
                <ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean searchListBean) {
                mProductList = searchListBean.getProductList();
                Log.d(TAG, "onHandleSuccess: 添加数据");
                mTypeListAdapter.addData(mProductList);
                if (mWaterfallAdapter != null) {
                    mWaterfallAdapter.addData(mProductList);
                }
                mRecycleView.onLoadFinish();
            }

            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 加载失败");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRecycleView.onLoadFinish();
            }
        });
    }

    private void loadSearchPrimary(String saleDown, String mKeyword) {
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listSearch(1,
                10, saleDown, mKeyword);
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver
                <ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean searchListBean) {
                mProductList = searchListBean.getProductList();
                mTypeListAdapter.setData(mProductList);

                if (mWaterfallAdapter != null) {
                    mWaterfallAdapter.setData(mProductList);
                }
                mRecycleView.onLoadFinish();
            }

            @Override
            protected void onHandleError(String msg) {
                Log.d(TAG, "onHandleError: 加载失败");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRecycleView.onLoadFinish();
            }
        });
    }

    /**----------------------------------请求搜索列表数据----------------------------------------*/


    /**
     * ----------------------------------请求商品列表数据----------------------------------------
     */
    private void loadMoreData(int page) {
        page++;
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductList
                (page, 10, 125, "saleDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.addData(mProductList);
                if (mWaterfallAdapter != null) {
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
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductList
                (1, 10, 125, saleDown);
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.setData(mProductList);
                mRecycleView.onLoadFinish();
                if (mWaterfallAdapter != null) {
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
    private void loadCommodityData() {
        Observable<ProductBean> productObservable = RetrofitFactory.getInstance().listProductList
                (1, 10, 125, "shelvesDown");
        productObservable.compose(compose(this.<ProductBean>bindToLifecycle())).subscribe(new BaseObserver<ProductBean>(mContext) {
            @Override
            protected void onHandleSuccess(ProductBean productBean) {
                mProductList = productBean.getProductList();
                mTypeListAdapter.addData(mProductList);
                if (mWaterfallAdapter != null) {
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
        /**----------------------------------请求商品列表数据----------------------------------------*/
    }


    private void setListener() {
        mViewTypeListHeader.setOnClickPrimaryListener(new ViewTypeHeader.onClickPrimaryListener() {
            @Override
            public void onPrimaryVolume() {
                if (loadData == 0) {
                    loadPrimary("saleDown");
                } else if (loadData == 1) {
                    loadSearchPrimary("saleDown", mKeyword);
                }
            }

            @Override
            public void onPrimaryEvaluate() {
                if (loadData == 0) {
                    loadPrimary("shelvesDown");
                } else if (loadData == 1) {
                    loadSearchPrimary("shelvesDown", mKeyword);
                }

            }

            @Override
            public void onPrimaryPrice(boolean isMode) {
                if (isMode) {
                    if (loadData == 0) {
                        loadPrimary("priceDown");
                    } else if (loadData == 1) {
                        loadSearchPrimary("priceDown", mKeyword);
                    }
                } else {
                    if (loadData == 0) {
                        loadPrimary("priceUp");
                    } else if (loadData == 1) {
                        loadSearchPrimary("priceUp", mKeyword);
                    }
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
                if (!isRecyclerView) {
                    //设置为垂直布局
                    mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
                    mRecycleView.setAdapter(mTypeListAdapter);
                } else {
                    if (mWaterfallAdapter == null) {
                        mWaterfallAdapter = new TypeWaterfallAdapter(mContext);
                        if (loadData == 0){
                            loadCommodityData();
                        }else if (loadData == 1){
                            loadSearchData(mKeyword);
                        }
                    }
                    mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2,
                            StaggeredGridLayoutManager.VERTICAL));
                    mRecycleView.setAdapter(mWaterfallAdapter);
                }

            }
        });
    }

}
