package com.young.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BjyJyk on 2017/7/31.
 */

public class SplashFirstFragment extends Fragment {
    @BindView(R.id.infographic)
    ImageView mInfographic;
    @BindView(R.id.map)
    ImageView mMap;
    @BindView(R.id.mainbox)
    ImageView mMainbox;
    @BindView(R.id.quote)
    ImageView mQuote;
    @BindView(R.id.stockchart)
    ImageView mStockchart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.splash_first, container, false);
        ButterKnife.bind(this, rootview);
        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public void ScrollView(int positionOffsetPixels) {
        getViewLayout(mMap,positionOffsetPixels/3);
        getViewLayout(mInfographic,positionOffsetPixels/2);
        getViewLayout(mQuote,positionOffsetPixels/5);
        getViewLayout(mStockchart,positionOffsetPixels/5);

    }

    private void getViewLayout(View view,int slidingPosition) {
        int top;
        int left;
        int right;
        int bottom;
        top = view.getTop();
        left = view.getLeft();
        right = view.getRight();
        bottom = view.getBottom();
        view.layout(left+slidingPosition,top,right+slidingPosition,bottom);
    }
}
