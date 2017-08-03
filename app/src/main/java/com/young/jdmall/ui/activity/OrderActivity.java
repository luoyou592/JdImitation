package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.young.jdmall.R;
import com.young.jdmall.ui.adapter.OrderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/31 0031 16:51
 *  描述：    TODO
 */
public class OrderActivity extends AppCompatActivity {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private String[] mTitles = {"全部","待付款","待收货","已完成","已取消"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        mViewPager.setAdapter(new OrderAdapter(getSupportFragmentManager(), mTitles));
        mTabLayout.setupWithViewPager(mViewPager);
        initPosition();
    }

    private void initPosition() {

    }
}
