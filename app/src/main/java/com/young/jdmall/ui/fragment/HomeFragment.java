package com.young.jdmall.ui.fragment;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.HomeInfoBean;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.activity.CustomServiceActivity;
import com.young.jdmall.ui.adapter.HomeRvAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.ib_sweep)
    TextView mIbSweep;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_news)
    TextView mTvMsg;
    @BindView(R.id.top_container)
    LinearLayout mTopContainer;
    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.srl_home)
    SwipeRefreshLayout mSrlHome;

    private View mHomeView;
    private HomeRvAdapter mHomeRvAdapter;
    //上拉透明效果变量
    private int mSumY = 0;
    private float mDistance = 200;  //控制透明度在距离150px的变化
    private int startValue = 0x50436EEE;
    private int endValue = 0xff436EEE;
    private int bgColor;
    private ArgbEvaluator mEvaluator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mHomeView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, mHomeView);
        initView();
        initData();
        initListener();
        return mHomeView;

    }

    private void initListener() {
        /*mSrlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });*/
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

        mHomeRvAdapter = new HomeRvAdapter(getActivity());
        mRvHome.setAdapter(mHomeRvAdapter);
        mEvaluator = new ArgbEvaluator();

        statusBarAplaChange();
        mTvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomServiceActivity.class);
                getActivity().startActivity(intent);

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
        Observable<NewsProductInfoBean> newsObservable = RetrofitFactory.getInstance().listNewsProduct(1,10,"saleDown");
        newsObservable.compose(compose(this.<NewsProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<NewsProductInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(NewsProductInfoBean newsProductInfoBean) {
                mHomeRvAdapter.setNewsProductData(newsProductInfoBean);
            }
        });
        //请求秒杀
        Observable<LimitbuyBean> limitObservable = RetrofitFactory.getInstance().listLimitbuy(1, 10);
        limitObservable.compose(compose(this.<LimitbuyBean>bindToLifecycle())).subscribe(new BaseObserver<LimitbuyBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(LimitbuyBean limitbuyBean) {
                mHomeRvAdapter.setLimitProductData(limitbuyBean);
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
}
