package com.young.jdmall.ui.fragment;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rance.chatui.ui.activity.MainActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.young.jdmall.R;
import com.young.jdmall.bean.HomeInfoBean;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.activity.ProductDetaiActivity;
import com.young.jdmall.ui.activity.SearchActivity;
import com.young.jdmall.ui.adapter.HomeAdapter;
import com.young.jdmall.ui.view.RecyclerLoadMoreView;
import com.young.jdmall.ui.view.RecyclerRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.ib_sweep)
    TextView mIbSweep;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_news)
    TextView mTvMsg;
    @BindView(R.id.top_container)
    LinearLayout mTopContainer;
    @BindView(R.id.rv_home)
    RecyclerLoadMoreView mRvHome;
    @BindView(R.id.refresh)
    RecyclerRefreshLayout mRefresh;

    private View mHomeView;
    private HomeAdapter mHomeRvAdapter;
    //上拉透明效果变量
    private int mSumY = 0;
    private float mDistance = 200;  //控制透明度在距离150px的变化
    private int startValue = 0x50436EEE;
    private int endValue = 0xff436EEE;
    private int bgColor;
    private ArgbEvaluator mEvaluator;
    private HomeAdapter mHomeAdapter;
    private List<NewsProductInfoBean.ProductListBean> mProductList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mHomeView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, mHomeView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(0xAA000000);
        }
        initView();
        initData();
        statusBarAplaChange();
        initListener();
        return mHomeView;
    }

    private void initListener() {
        /*mSrlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });*/

        mRefresh.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRefresh.closeRefresh();
                            }
                        },2000);
                    }
                }).start();
            }
        });
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position > 1 ? 1 : 2;
            }
        });
        mRvHome.setLayoutManager(manager);

        mHomeRvAdapter = new HomeAdapter(getActivity());
//        mHomeRvAdapter = new HomeRvAdapter(getActivity());
        mRvHome.setAdapter(mHomeRvAdapter);
        mEvaluator = new ArgbEvaluator();



        mTvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);

            }
        });

        mEtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    //Rxjava2请求数据
    private void initData() {
        //请求轮播图
        Observable<HomeInfoBean> homeObservable = RetrofitFactory.getInstance().listHome();
        homeObservable.compose(compose(this.<HomeInfoBean>bindToLifecycle())).subscribe(new BaseObserver<HomeInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(HomeInfoBean homeInfoBean) {
                //Log.d("luoyou", "homeimgurl"+homeInfoBean.getResponse());
                mHomeRvAdapter.setHomeData(homeInfoBean.getHomeTopic());

            }
        });
        //请求商品列表
        //Observable<NewsProductInfoBean> newsObservable = RetrofitFactory.getInstance().listNewsProduct(1, 10, "saleDown");
        requestCarouselPic();

    }

    private void requestProductList() {
        Observable<NewsProductInfoBean> newsObservable = RetrofitFactory.getInstance().listNewsProduct(1, 10, "saleDown");
        newsObservable.compose(compose(this.<NewsProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<NewsProductInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(NewsProductInfoBean newsProductInfoBean) {
                mProductList = newsProductInfoBean.getProductList();
                mHomeRvAdapter.setNewsProductData(mProductList);
                mHomeRvAdapter.notifyDataSetChanged();
                //请求商品列表后才设置监听请求加载更多
                mRvHome.setOnRefreshListener(new RecyclerLoadMoreView.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.d(TAG, "onRefresh: 加载更多");
                        requestProductMoreList();
                    }
                });
            }
        });
    }

    private void requestSeckill() {
        Observable<LimitbuyBean> limitObservable = RetrofitFactory.getInstance().listLimitbuy(1, 10);
        limitObservable.compose(compose(this.<LimitbuyBean>bindToLifecycle())).subscribe(new BaseObserver<LimitbuyBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(LimitbuyBean limitbuyBean) {
                mHomeRvAdapter.setLimitProductData(limitbuyBean);
                //请求列表
                requestProductList();

            }
        });
    }

    private void requestCarouselPic() {
        Observable<HomeInfoBean> homeObservable = RetrofitFactory.getInstance().listHome();
        homeObservable.compose(compose(this.<HomeInfoBean>bindToLifecycle())).subscribe(new BaseObserver<HomeInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(HomeInfoBean homeInfoBean) {
                mHomeRvAdapter.setHomeData(homeInfoBean.getHomeTopic());
                //请求秒杀
                requestSeckill();
            }

        });
    }


    //设置标题栏渐变沉浸效果
    private void statusBarAplaChange() {

        //监听recyclerview移动距离来设置透明度
        mRvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mSumY += dy;
                if (mSumY <= 0) {
                    bgColor = startValue;
                } else if (mSumY >= mDistance) {
                    bgColor = endValue;
                } else {
                    bgColor = (int) mEvaluator.evaluate(mSumY / mDistance, startValue, endValue);
                }
                mTopContainer.setBackgroundColor(bgColor);
            }
        });
    }

    private int page = 1;

    private void requestProductMoreList() {
        page++;
        Log.d(TAG, "requestProductMoreList: 加载更多" + page);
        Observable<NewsProductInfoBean> newsObservable = RetrofitFactory.getInstance().listNewsProduct(page, 10, "saleDown");
        newsObservable.compose(compose(this.<NewsProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<NewsProductInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(NewsProductInfoBean newsProductInfoBean) {
                Log.d(TAG, "onHandleSuccess: 加载更多成功");
                mProductList.addAll(newsProductInfoBean.getProductList());
                mHomeRvAdapter.setNewsProductData(mProductList);
                mHomeRvAdapter.notifyDataSetChanged();
                mRvHome.onLoadSuccess();
            }

            @Override
            protected void onHandleError(String msg) {
                super.onHandleError(msg);
                Log.d(TAG, "onHandleError: 加载更多失败");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRvHome.onLoadFailure();
            }
        });
    }

    @OnClick(R.id.ib_sweep)
    public void onViewClicked() {
        startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            String goodsId = data.getStringExtra("GoodsId");
            Log.d(TAG, "onActivityResult: " + requestCode + "|" + resultCode + "|" + goodsId);
            if (resultCode == -1) {
                Intent intent = new Intent(getActivity(), ProductDetaiActivity.class);
                intent.putExtra("id", Integer.valueOf(goodsId));
                startActivity(intent);
            }
        }
    }

}
