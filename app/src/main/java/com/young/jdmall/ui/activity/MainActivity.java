package com.young.jdmall.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.young.jdmall.R;
import com.young.jdmall.ui.fragment.CategoryFragment;
import com.young.jdmall.ui.fragment.HomeFragment;
import com.young.jdmall.ui.fragment.MyFragment;
import com.young.jdmall.ui.fragment.SettingFragment;
import com.young.jdmall.ui.fragment.ShopCartFragment;
import com.young.jdmall.ui.widget.RedPacketDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_navigation)
    LinearLayout mMainNavigation;
    private SparseArray<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        processIntent();
        mFragments = new SparseArray<>();
        setListener();
        selectedTab(0);

        RedPacketDialog redPacketDialog = new RedPacketDialog(this, R.style.Dialog);
        redPacketDialog.create();
        redPacketDialog.show();
    }

    private void processIntent() {
        if (getIntent()!=null){
            String page = getIntent().getStringExtra("page");
            //判断是否详情页跳转过来的，是则切换到购物车
            if ("detail".equals(page)){
                getFragmentManager().beginTransaction().replace(R.id.container_fragment,new ShopCartFragment());
            }
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
        for (int i = 0; i < mMainNavigation.getChildCount(); i++) {
            View view = mMainNavigation.getChildAt(i);
            if (i == index) {
                view.setEnabled(false);
            } else {
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

}
