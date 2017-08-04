package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.bean.BrandInfoBean;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.SeckillRvAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by 25505 on 2017/7/31.
 */

public class SecKillActivity extends BaseActivity {
    @BindView(R.id.iv_seckill_back)
    ImageView mIvSeckillBack;
    @BindView(R.id.seckill_recycle_view)
    RecyclerView mSeckillRecycleView;
    private SeckillRvAdapter mSeckillRvAdapter;
    private long mTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seckill);
        ButterKnife.bind(this);
        processIntent();
        initView();
        initData();

    }

    private void processIntent() {
        if (getIntent()!=null){
            mTime = getIntent().getLongExtra("time", 500);
        }
    }

    private void initView() {
        mSeckillRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mSeckillRvAdapter = new SeckillRvAdapter(this,mTime);
        mSeckillRecycleView.setAdapter(mSeckillRvAdapter);
    }
    private void initData() {
        //请求品牌
        Observable<BrandInfoBean> brandObservable = RetrofitFactory.getInstance().listBrand();
        brandObservable.compose(compose(this.<BrandInfoBean>bindToLifecycle())).subscribe(new BaseObserver<BrandInfoBean>(this) {
            @Override
            protected void onHandleSuccess(BrandInfoBean brandInfoBean) {
                mSeckillRvAdapter.setBrandData(brandInfoBean);

            }
        });
        //请求秒杀
        Observable<LimitbuyBean> limitObservable = RetrofitFactory.getInstance().listLimitbuy(1, 10);
        limitObservable.compose(compose(this.<LimitbuyBean>bindToLifecycle())).subscribe(new BaseObserver<LimitbuyBean>(this) {
            @Override
            protected void onHandleSuccess(LimitbuyBean limitbuyBean) {
                mSeckillRvAdapter.setLimitProductData(limitbuyBean);
                mSeckillRvAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.iv_seckill_back)
    public void onClick() {
        finish();
    }
}
