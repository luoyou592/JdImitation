package com.young.jdmall.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.young.jdmall.R;
import com.young.jdmall.bean.ProductBean;
import com.young.jdmall.bean.ProductInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.TypeAdapter;
import com.young.jdmall.ui.adapter.TypeWaterfallAdapter;
import com.young.jdmall.ui.view.RecyclerLoadMoreView;
import com.young.jdmall.ui.widget.ImageViewPagerDialog;
import com.young.jdmall.ui.widget.ViewTypeHeader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.type_btn_1)
    Button mTypeBtn1;
    @BindView(R.id.type_btn_2)
    Button mTypeBtn2;
    @BindView(R.id.type_btn_3)
    Button mTypeBtn3;
    @BindView(R.id.type_btn_4)
    Button mTypeBtn4;

    private List<ProductBean.ProductListBean> mProductList;
    private TypeAdapter mTypeListAdapter;
    private int page = 2;
    private TypeWaterfallAdapter mWaterfallAdapter;
    private Context mContext;

    private int loadData = 0;
    private String mKeyword;
    private CharSequence mText;

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);
        ButterKnife.bind(this);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        mKeyword = intent.getStringExtra("name");
        Log.d(TAG, "onCreate: " + intent.getStringExtra("name"));
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

        if (mKeyword != null) {
            Log.d(TAG, "onResume: 设置" + mKeyword);

            mViewTypeListHeader.mTypeSearch.setText(mKeyword);
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

            //销毁
            @Override
            public void onBack() {
                finish();
            }

            //修改适配器
            @Override
            public void onSelectorLayout(boolean isRecyclerView) {
                if (!isRecyclerView) {
                    //设置为垂直布局
                    mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
                    mRecycleView.setAdapter(mTypeListAdapter);
                } else {
                    if (mWaterfallAdapter == null) {
                        mWaterfallAdapter = new TypeWaterfallAdapter(mContext);
                        if (loadData == 0) {
                            loadCommodityData();
                        } else if (loadData == 1) {
                            loadSearchData(mKeyword);
                        }
                    }
                    mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2,
                            StaggeredGridLayoutManager.VERTICAL));
                    mRecycleView.setAdapter(mWaterfallAdapter);
                }

            }
            //跳转到详情页
            @Override
            public void onSearch() {
                startActivity(new Intent(TypeActivity.this, SearchActivity.class));
            }
        });


        mTypeListAdapter.setListener(new TypeAdapter.onClickItemControlListener() {
            @Override
            public void onImageClick(ProductBean.ProductListBean productBean, int position) {
                requestProductDetail(productBean.getId());
            }
        });


    }


    //图片轮播图
    private void requestProductDetail(final int id) {
        Observable<ProductInfoBean> productObservable = RetrofitFactory.getInstance()
                .listProductInfo(id);
        productObservable.compose(compose(this.<ProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<ProductInfoBean>(this) {
            @Override
            protected void onHandleSuccess(ProductInfoBean productInfoBean) {
                List<String> pics = productInfoBean.getProduct().getPics();
                ImageViewPagerDialog imageViewPagerDialog = new ImageViewPagerDialog(mContext,R.style.Dialog);
                imageViewPagerDialog.setData(pics, id);
                imageViewPagerDialog.show();
            }
        });
    }


    //PopupWindow处理事件
    @OnClick({R.id.type_btn_1, R.id.type_btn_2, R.id.type_btn_3, R.id.type_btn_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.type_btn_1:
                View pop1 = LayoutInflater.from(this).inflate(R.layout.type_popup_window1, null);
                PopupWindow popupWindow1 = getPopupWindow(pop1);
                setPopupWindowOnClick(1, pop1, popupWindow1);
                break;
            case R.id.type_btn_2:
                View pop2 = LayoutInflater.from(this).inflate(R.layout.type_popup_window2, null);
                PopupWindow popupWindow2 = getPopupWindow(pop2);

                setPopupWindowOnClick(2, pop2, popupWindow2);
                break;
            case R.id.type_btn_3:
                View pop3 = LayoutInflater.from(this).inflate(R.layout.type_popup_window3, null);

                PopupWindow popupWindow3 = getPopupWindow(pop3);
                setPopupWindowOnClick(3, pop3, popupWindow3);
                break;
            case R.id.type_btn_4:
                View pop4 = LayoutInflater.from(this).inflate(R.layout.type_popup_window4, null);

                PopupWindow popupWindow4 = getPopupWindow(pop4);
                setPopupWindowOnClick(4, pop4, popupWindow4);

                break;
        }
    }

    //创建PopupWindow
    @NonNull
    private PopupWindow getPopupWindow(View pop) {
        final PopupWindow popupWindow4 = new PopupWindow(pop, ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow4.setOutsideTouchable(true);
        popupWindow4.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow4.setFocusable(true);
        popupWindow4.update();
        popupWindow4.showAsDropDown(mTypeBtn1, 0, 20);
        return popupWindow4;
    }

    //PopupWindowBottom点击事件
    private void setPopupWindowOnClick(final int a, View pop, final PopupWindow popupWindow) {
        if (pop instanceof ViewGroup) {
            int childCount = ((ViewGroup) pop).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ((ViewGroup) pop).getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    int childAtChildCount = ((ViewGroup) childAt).getChildCount();
                    for (int j = 0; j < childAtChildCount; j++) {
                        ((ViewGroup) childAt).getChildAt(j).setOnClickListener(new View
                                .OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CharSequence text = ((Button) v).getText();
                                switch (a) {
                                    case 1:
                                        mTypeBtn1.setText(text);
                                        mTypeBtn2.setText("类型");
                                        mTypeBtn3.setText("品牌");
                                        mTypeBtn4.setText("样式");
                                        break;
                                    case 2:
                                        mTypeBtn1.setText("包邮");
                                        mTypeBtn2.setText(text);
                                        mTypeBtn3.setText("品牌");
                                        mTypeBtn4.setText("样式");
                                        break;
                                    case 3:
                                        mTypeBtn3.setText(text);
                                        mTypeBtn1.setText("包邮");
                                        mTypeBtn2.setText("类型");
                                        mTypeBtn4.setText("样式");
                                        break;
                                    case 4:
                                        mTypeBtn4.setText(text);
                                        mTypeBtn1.setText("包邮");
                                        mTypeBtn2.setText("类型");
                                        mTypeBtn3.setText("品牌");
                                        break;
                                }
                                popupWindow.dismiss();
                            }
                        });
                    }
                }
            }
        }
    }


}
