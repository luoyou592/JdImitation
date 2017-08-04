package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.jdmall.R;
import com.young.jdmall.bean.HelpInfoBean;
import com.young.jdmall.bean.HelpInfoDetailBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/3 0003 21:24
 *  描述：    TODO
 */
public class HelpCenterActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;

    @BindView(R.id.rl_buy)
    RelativeLayout mRlBuy;
    @BindView(R.id.rl_way)
    RelativeLayout mRlWay;
    @BindView(R.id.rl_server)
    RelativeLayout mRlServer;
    @BindView(R.id.rl_update)
    RelativeLayout mRlUpdate;
    @BindView(R.id.tv_help)
    TextView mTvHelp;
    @BindView(R.id.tv_way)
    TextView mTvWay;
    @BindView(R.id.tv_server)
    TextView mTvServer;
    @BindView(R.id.tv_update)
    TextView mTvUpdate;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.rl_result)
    RelativeLayout mRlResult;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    private List<HelpInfoBean.HelpListBean> mHelpList;
    private List<HelpInfoBean.HelpListBean> mHelpTitle;
    private Object mHelpContent;
    private static int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
        mHelpList = new ArrayList<>();
        initTitle();
    }

    private void initTitle() {
        Observable<HelpInfoBean> newsObservable = RetrofitFactory.getInstance().listHelpList();
        newsObservable.compose(compose(this.<HelpInfoBean>bindToLifecycle())).subscribe(new BaseObserver<HelpInfoBean>(this) {
            @Override
            protected void onHandleSuccess(HelpInfoBean helpInfoBean) {
                if (helpInfoBean.getHelpList() != null) {
                    Toast.makeText(HelpCenterActivity.this, "获取titile", Toast.LENGTH_SHORT).show();
                    mHelpList = helpInfoBean.getHelpList();
                    setHelpTitle(mHelpList);
                }else {
                    Toast.makeText(HelpCenterActivity.this, helpInfoBean.getResponse(), Toast.LENGTH_SHORT).show();
                }


            }

        });
    }

    @OnClick({R.id.iv_back, R.id.rl_buy, R.id.rl_way, R.id.rl_server, R.id.rl_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if(flag == 1){
                    initTitle();
                    flag = 0;
                }else {
                    finish();
                }
                break;
            case R.id.rl_buy:
                getHelpContent(1);
                break;
            case R.id.rl_way:
                getHelpContent(2);

                break;
            case R.id.rl_server:
                getHelpContent(3);

                break;
            case R.id.rl_update:
                getHelpContent(4);

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initTitle();
    }

    public void setHelpTitle(List<HelpInfoBean.HelpListBean> helpTitle) {
        if (helpTitle == null) {
            mRlBuy.setVisibility(View.GONE);
            mRlServer.setVisibility(View.GONE);
            mRlUpdate.setVisibility(View.GONE);
            mRlWay.setVisibility(View.GONE);
        } else {
            mRlResult.setVisibility(View.GONE);
            mLlContainer.setVisibility(View.VISIBLE);
            mTvBuy.setText(helpTitle.get(0).getTitle());
            mTvWay.setText(helpTitle.get(1).getTitle());
            mTvServer.setText(helpTitle.get(2).getTitle());
            mTvUpdate.setText(helpTitle.get(3).getTitle());
        }
    }

    public void getHelpContent(int id) {

        Observable<HelpInfoDetailBean> newsObservable = RetrofitFactory.getInstance().listHelpDetail(id);
        newsObservable.compose(compose(this.<HelpInfoDetailBean>bindToLifecycle())).subscribe(new BaseObserver<HelpInfoDetailBean>(this) {
            @Override
            protected void onHandleSuccess(HelpInfoDetailBean helpInfoDetailBean) {
                if (helpInfoDetailBean.getHelpDetailList() != null) {
                    mTvHelp.setText(helpInfoDetailBean.getHelpDetailList().get(0).getTitle());
                    mTvResult.setText(helpInfoDetailBean.getHelpDetailList().get(0).getContent());
                    mRlResult.setVisibility(View.VISIBLE);
                    mLlContainer.setVisibility(View.GONE);
                    flag = 1;
                }


            }

        });
    }
}
