package com.young.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.R;

import butterknife.ButterKnife;

import static com.young.jdmall.network.NetworkManage.init;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 21:43
 *  描述：    TODO
 */
public class OrderWaitSFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_wait_s_order, null);
        ButterKnife.bind(this, root);
        init();
        return root;

    }
}
