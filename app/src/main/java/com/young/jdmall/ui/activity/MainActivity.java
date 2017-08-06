package com.young.jdmall.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.GoodsOrderInfoBean;
import com.young.jdmall.dao.CartDao;
import com.young.jdmall.ui.fragment.CategoryFragment;
import com.young.jdmall.ui.fragment.HomeFragment;
import com.young.jdmall.ui.fragment.MyFragment;
import com.young.jdmall.ui.fragment.SettingFragment;
import com.young.jdmall.ui.fragment.ShopCartFragment;
import com.young.jdmall.ui.widget.RedPacketDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_navigation)
    LinearLayout mMainNavigation;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    public SparseArray<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(0xAA000000);
        }
        ButterKnife.bind(this);
        mFragments = new SparseArray<>();
        setListener();
        selectedTab(0);

        if (getIntent()!=null){
            processIntent();
        }else {
            RedPacketDialog redPacketDialog = new RedPacketDialog(this, R.style.Dialog);
            redPacketDialog.create();
            redPacketDialog.show();
        }

    }

    private void processIntent() {

            String page = getIntent().getStringExtra("page");
            Log.d("luoyou", "intent");
            //判断是否详情页跳转过来的，是则切换到购物车
            if ("detail".equals(page)){
                /*getFragmentManager().beginTransaction().replace(R.id.container_fragment,new ShopCartFragment());*/
                selectedTab(2);
        }
    }

    private void setListener() {
        for (int i = 0; i < mMainNavigation.getChildCount(); i++) {
            final View childAt = mMainNavigation.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = mMainNavigation.indexOfChild(childAt);
                    selectedTab(index);
                }
            });
        }
    }

    private void selectedTab(int index) {
        Log.d("luoyou", "select");
        for (int i = 0; i < mMainNavigation.getChildCount(); i++) {
            View view = mMainNavigation.getChildAt(i);
            if (i == index) {
                if (view instanceof RelativeLayout){
                    ((RelativeLayout) view).getChildAt(0).setEnabled(false);
                }
                view.setEnabled(false);
            } else {
                if (view instanceof RelativeLayout){
                    ((RelativeLayout) view).getChildAt(0).setEnabled(true);
                }
                view.setEnabled(true);
            }
        }
        selectedTabFragment(index);
    }

    private void selectedTabFragment(int index) {
        Fragment fragment = mFragments.get(index);
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new CategoryFragment();
                    break;
                case 2:
                    fragment = new ShopCartFragment();

                    break;
                case 3:
                    fragment = new MyFragment();
                    break;
                case 4:
                    fragment = new SettingFragment();
                    break;
            }
            mFragments.put(index, fragment);
        }


        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_fragment, fragment);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int mCount = 0;
        List<GoodsOrderInfoBean> goodsOrderInfoBeen = CartDao.queryAll();
        if (goodsOrderInfoBeen.size() > 0) {
            for (GoodsOrderInfoBean goodsOrderInfoBean : goodsOrderInfoBeen) {
                mCount += goodsOrderInfoBean.getCount();
            }
            mTvCount.setVisibility(View.VISIBLE);
            mTvCount.setText(mCount + "");
        } else {
            mTvCount.setVisibility(View.INVISIBLE);
        }
    }
    public void updataCount(){
        onStart();
    }
}
