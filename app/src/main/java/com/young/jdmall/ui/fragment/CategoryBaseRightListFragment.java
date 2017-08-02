package com.young.jdmall.ui.fragment;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.Fragment
 * 时间:2017/7/30
 */

public abstract class CategoryBaseRightListFragment extends CategoryBaseRightFragment {

    private static final String TAG = "BaseCatecaryRightFragme";
    private ListView mListView;
    private BaseAdapter mBaseAdapter;


    //返回布局
    @Override
    protected View getLayoutView() {
        //创建ListView,子类布局
        mListView = new ListView(getActivity());
        mBaseAdapter = getBaseCategoryAdapter();
        View header = getHeader();
        if (header != null) {
            mListView.addHeaderView(header);
        }

        mListView.setAdapter(mBaseAdapter);

        return mListView;
    }

    @Override
    protected void init() {
        super.init();
    }


    //子类继承实现,返回头部
    protected View getHeader(){
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    //子类实现,返回Adapter
    protected abstract BaseAdapter getBaseCategoryAdapter();



}
