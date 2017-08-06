package com.young.jdmall.ui.fragment;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.bean.UsersInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.activity.AccountSettingActivity;
import com.young.jdmall.ui.activity.InfoActivity;
import com.young.jdmall.ui.activity.LoginActivity;
import com.young.jdmall.ui.adapter.MyRvAdapter;
import com.young.jdmall.ui.utils.PreferenceUtils;
import com.young.jdmall.ui.view.RecyclerRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;

import static android.content.ContentValues.TAG;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class MyFragment extends BaseFragment {


    @BindView(R.id.rv_user)
    RecyclerView mRvUser;
    @BindView(R.id.iv_user)
    ImageView mIvUser;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.ll_title_container)
    RelativeLayout mLlTitleContainer;
    Unbinder unbinder;
    @BindView(R.id.tv_myself)
    TextView mTvMyself;
    public MyRvAdapter mMyRvAdapter;
    @BindView(R.id.rv_fresh)
    RecyclerRefreshLayout mRvFresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("我的");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(0xAA000000);
        }

        View view = inflater.inflate(R.layout.activity_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initReco();
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position > 1 ? 1 : 2;
            }
        });
        mRvUser.setLayoutManager(manager);

        mMyRvAdapter = new MyRvAdapter(getActivity());
        mArgbEvaluator = new ArgbEvaluator();
        mRvUser.setAdapter(mMyRvAdapter);
        mRvUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = manager.findLastCompletelyVisibleItemPosition();
                if (position == mMyRvAdapter.getItemCount() - 1) {
                    page++;
                    initReco();
                }
            }
        });
        mRvFresh.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        mRvFresh.post(new Runnable() {
                            @Override
                            public void run() {
                                mRvFresh.closeRefresh();
                            }
                        });
                    }
                }).start();
            }
        });
        return view;
    }

    private void initReco() {
        Observable<NewsProductInfoBean> newsObservable = RetrofitFactory.getInstance().listNewsProduct(page, 10, "saleDown");
        newsObservable.compose(compose(this.<NewsProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<NewsProductInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(NewsProductInfoBean newsProductInfoBean) {
                if(newsProductInfoBean.getProductList()!= null){

                    mMyRvAdapter.setNewsProductData(newsProductInfoBean.getProductList());
                }else {
//                    Toast.makeText(getActivity(), newsProductInfoBean.getResponse(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected void onHandleError(String msg) {
                super.onHandleError(msg);
                Log.d(TAG, "onHandleError: "+msg);
                PreferenceUtils.setUserId(getActivity(), "");
                mMyRvAdapter.setUsers("");
            }
        });
    }

    private int page = 0;
    private void initData() {
        final String userId = PreferenceUtils.getUserId(getActivity());
        Log.d(TAG, "initData: " + userId);
       /* if("".equals(userId)){
            return;
        }*/
        Observable<UsersInfoBean> usersInfoBeanObservable = RetrofitFactory.getInstance().listUserInfo(userId);
        usersInfoBeanObservable.compose(compose(this.<UsersInfoBean>bindToLifecycle())).subscribe(new BaseObserver<UsersInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(UsersInfoBean usersInfoBean) {
                Log.d(TAG, "onHandleSuccess: " + usersInfoBean.getResponse());

                //Log.d("luoyou", "homeimgurl"+homeInfoBean.getResponse());
                if ("userInfo".equals(usersInfoBean.getResponse())) {
//                    Toast.makeText(getActivity(), "成功获取用户信息", Toast.LENGTH_SHORT).show();
                    mMyRvAdapter.setUserInfoBean(usersInfoBean.getUserInfo());

                }else{
//                    Toast.makeText(getActivity(), usersInfoBean.getResponse(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onHandleSuccess: "+usersInfoBean.getResponse());
                    PreferenceUtils.setUserId(getActivity(), "");
                    mMyRvAdapter.setUsers("");
                }
//                String level = usersInfoBean.getUserInfo().getLevel();
//                Log.d(TAG, "onHandleSuccess: "+ level);
            }

            @Override
            protected void onHandleError(String msg) {
                super.onHandleError(msg);
                Log.d(TAG, "onHandleError: "+msg);
                PreferenceUtils.setUserId(getActivity(),"");
                mMyRvAdapter.setUsers("");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.d(TAG, "onHandleError: "+e);
                PreferenceUtils.setUserId(getActivity(), "");
                mMyRvAdapter.setUsers("");
            }
        });

    }


    private void login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    ArgbEvaluator mArgbEvaluator;
    int sumY;
    float distance = 280.0f;
    int start = 0x00000000;
    int end = 0X99FFFFFF;
    int bgColor;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        testData();
        mRvUser.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sumY += dy;
//                float percent = sumY / distance;

                if (sumY <= 0) {
                    bgColor = start;

                } else if (sumY >= distance) {
                    bgColor = end;

                } else {

                    bgColor = (int) mArgbEvaluator.evaluate(sumY / distance, start, end);

                }
                mLlTitleContainer.setBackgroundColor(bgColor);
                if (bgColor == (int) mArgbEvaluator.evaluate(1, start, end)) {

                    mIvSetting.setBackgroundResource(R.mipmap.a93);
                    mIvMessage.setBackgroundResource(R.mipmap.a90);
                    mIvUser.setVisibility(View.VISIBLE);
                    mTvMyself.setVisibility(View.VISIBLE);
                } else if (sumY >= 0 && sumY < distance) {
                    mIvSetting.setBackgroundResource(R.mipmap.a95);
                    mIvMessage.setBackgroundResource(R.mipmap.a92);
                    mIvUser.setVisibility(View.INVISIBLE);
                    mTvMyself.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user, R.id.iv_message, R.id.iv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user:
                Intent intent = new Intent(getActivity(), AccountSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_message:
                Intent intent1=new Intent(getActivity(), InfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_setting:
                Intent intent3 = new Intent(getActivity(), AccountSettingActivity.class);
                startActivity(intent3);
                break;
        }
    }

/*    private List<String> mDatas = new ArrayList<>();
    private void testData() {
        for (int i = 0; i < 100; i++) {
            mDatas.add("我是条目:" + i);
        }
        mHomeRvAdapter.setDatas(mDatas);
//        mHomeFragmentPresenter.loadHomeInfo();
    }*/

    @Override
    public void onStart() {
//        if(!"-1".equals(JDMallApplication.sUser.getUserInfo().getUserid())){
//            mMyRvAdapter.setUserInfoBean(JDMallApplication.sUser.getUserInfo());
//
//        }
        super.onStart();
        initData();
        initReco();
        Log.d(TAG, "onStart: ++++++++++++++++++++++++");
        String userName = PreferenceUtils.getUserName(getActivity());
        if(!"".equals(PreferenceUtils.getUserId(getActivity()))){
            mRvFresh.setToggle(true);
        }else{
            mRvFresh.setToggle(false);
        }
        mMyRvAdapter.setUsers(userName);
//        String userid = JDMallApplication.sLoginInfoBean.getUserInfo().getUserid();
//        mMyRvAdapter.setUsers(userid);
    }

}
