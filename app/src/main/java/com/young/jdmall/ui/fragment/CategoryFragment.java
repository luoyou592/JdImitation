package com.young.jdmall.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.young.jdmall.R;
import com.young.jdmall.ui.adapter.CategoryLeftAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by BjyJyk on 2017/7/30.
 */

public class CategoryFragment extends Fragment {

    private static final String TAG = "CategoryFragment";

    @BindView(R.id.catecary_left_list)
    public RecyclerView mCategoryLeftList;
    @BindView(R.id.category_right_contains)
    FrameLayout mCategoryRightContains;
    private Context mContext;
    private CategoryLeftAdapter mCategoryLeftAdapter;

    private List<CategoryBaseRightListFragment> fragments = new ArrayList();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.catecary_fragment, null);
        ButterKnife.bind(this, rootview);
        mContext = getContext();
        initFragment();
        init();
        initRightContains();
        //默认选中第0个
        selectFragment(0);

        return rootview;
    }

    private void initFragment() {
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());
        fragments.add(new WomanDressFragment());

    }

    private void initRightContains() {
        //  selectFragment();
    }

    private int lastcheck = 0;

    public void selectFragment(int position) {
        CategoryBaseRightListFragment baseCategoryRightFragment = fragments.get(position);
        getFragmentManager().beginTransaction().replace(R.id.category_right_contains, baseCategoryRightFragment).commit();
        if (position >4) {
            if (lastcheck < position) {
                mCategoryLeftList.smoothScrollToPosition(position +4);
                lastcheck = position;
            } else {
                lastcheck = position;
                  mCategoryLeftList.smoothScrollToPosition(position -4 );
            }
        }else {
            mCategoryLeftList.smoothScrollToPosition(0);
        }


        }

        /**
         * 初始化
         */

    private void init() {
        //RecyclerView的初始化
        mCategoryLeftList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCategoryLeftAdapter = new CategoryLeftAdapter(mContext, this);

        mCategoryLeftList.setAdapter(mCategoryLeftAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
