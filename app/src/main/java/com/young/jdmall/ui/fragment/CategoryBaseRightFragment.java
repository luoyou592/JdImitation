package com.young.jdmall.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.Fragment
 * 时间:2017/7/30
 */

public abstract class CategoryBaseRightFragment extends Fragment {

    private static final String TAG = "CategoryBaseRightFragment";
    @BindView(R.id.fragment_base_pb_load)
    ProgressBar mFragmentBasePbLoad;
    @BindView(R.id.fragment_base_bt_load)
    Button mFragmentBaseBtLoad;
    @BindView(R.id.fragment_base_ll_load)
    LinearLayout mFragmentBaseLlLoad;
    @BindView(R.id.fragment_frame)
    FrameLayout mFragmentFrame;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        //加载网络的布局
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_catecary, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        //将子类添加进布局
        mFragmentFrame.addView(getLayoutView());

        return root;
    }

    protected void init() {
        loadNetwork();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_base_bt_load)
    public void onViewClicked() {
        //重新加载,隐藏出错布局,调用加载数据方法
        mFragmentBaseLlLoad.setVisibility(View.GONE);
        mFragmentBasePbLoad.setVisibility(View.VISIBLE);
        loadNetwork();
    }

    //加载成功,隐藏进度条,隐藏重新加载
    public void onLoadDataSucced() {
        mFragmentBaseLlLoad.setVisibility(View.GONE);
        mFragmentBasePbLoad.setVisibility(View.GONE);
    }

    //加载失败,显示重新加载,隐藏进度条
    public void onLoadDataFailed(){
        mFragmentBaseLlLoad.setVisibility(View.VISIBLE);
        mFragmentBasePbLoad.setVisibility(View.GONE);
    }


    //子类实现,加载网络数据
    public abstract void loadNetwork();

    //子类实现,返回布局
    protected abstract View getLayoutView();
}
