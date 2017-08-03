package com.young.jdmall.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.ui.adapter.SettingAdapter;
import com.young.jdmall.ui.adapter.SettingGridAdapter;
import com.young.jdmall.ui.adapter.SettingNavigatorAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class SettingFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.tab_title)
    TextView mTabTitle;
    @BindView(R.id.select_tab)
    RelativeLayout mSelectTab;
    @BindView(R.id.tab_select_item_gv)
    GridView mTabSelectItemGv;
    @BindView(R.id.tab_select_container)
    LinearLayout mTabSelectContainer;
    @BindView(R.id.tab_select_icon)
    ImageView mTabSelectIcon;

    private String[] mTitles = new String[]{"精选", "直播", "订阅", "视频购", "问答", "清单", "好东西", "社区", "生活", "数码", "亲子", "风尚", "美食"};
    private SettingGridAdapter mGridAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(0x66000000);
        }
        View view = View.inflate(getActivity(), R.layout.fragment_find, null);
        unbinder = ButterKnife.bind(this, view);
        setAdapter();
        setListener();
        return view;
    }

    private void setListener() {
        mTabSelectItemGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGridAdapter.selectItem(position);
                mViewPager.setCurrentItem(position);
                toggleTabSelector();
            }
        });

        mSelectTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTabSelector();
            }
        });
    }

    private void toggleTabSelector() {
        if (mTabSelectContainer.getVisibility() == View.GONE) {
            mTabSelectContainer.setVisibility(View.VISIBLE);
            mTabTitle.setVisibility(View.VISIBLE);
            mMagicIndicator.setVisibility(View.GONE);
            mGridAdapter.selectItem(mViewPager.getCurrentItem());
            TabSelectIconAnimator(0, 180);
        } else {
            mTabSelectContainer.setVisibility(View.GONE);
            mTabTitle.setVisibility(View.GONE);
            mMagicIndicator.setVisibility(View.VISIBLE);
            TabSelectIconAnimator(180, 360);
        }
    }

    private void TabSelectIconAnimator(float start, float end) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTabSelectIcon, "rotation", start, end);
        animator.setDuration(200);
        animator.start();
    }

    private void setAdapter() {
        mViewPager.setAdapter(new SettingAdapter());
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        CommonNavigatorAdapter navigatorAdapter = new SettingNavigatorAdapter(mTitles, mViewPager);
        commonNavigator.setAdapter(navigatorAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        mGridAdapter = new SettingGridAdapter(mTitles);
        mTabSelectItemGv.setAdapter(mGridAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
