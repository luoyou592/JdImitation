package com.young.jdmall.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.CollectionAdapter;
import com.young.jdmall.ui.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/3 0003 18:57
 *  描述：    TODO
 */
public class CollectionActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rl_container)
    RecyclerView mRlContainer;
    private CollectionAdapter mCollectionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.GRAY);
        }
        initData();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mRlContainer.setLayoutManager(manager);
        mCollectionAdapter = new CollectionAdapter(this);

        mRlContainer.setAdapter(mCollectionAdapter);
    }

    private void initData() {
        final Observable<NewsProductInfoBean> newsObservable = RetrofitFactory.getInstance().listCollectProduct(PreferenceUtils.getUserId(this), 1, 10);
        newsObservable.compose(compose(this.<NewsProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<NewsProductInfoBean>(this) {
            @Override
            protected void onHandleSuccess(NewsProductInfoBean newsProductInfoBean) {
                if (newsProductInfoBean.getProductList() != null) {
                    mCollectionAdapter.setNewsProductInfoBeen(newsProductInfoBean.getProductList());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
