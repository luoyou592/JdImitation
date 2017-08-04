package com.young.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.young.jdmall.R;

/**
 * Created by 25505 on 2017/8/2.
 */

public class CancelOdFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.mipmap.no_order);
        return imageView;
    }
}
