package com.young.jdmall.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.young.jdmall.R;
import com.young.jdmall.ui.view.RotateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.young.jdmall.network.NetworkManage.init;

public class SplashTwoFragment extends Fragment {

    @BindView(R.id.rotate_view_splash)
    RotateView mRotateViewSplash;
    private Unbinder mBind;
    public ObjectAnimator mRotationAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.splash_two, container, false);
        mBind = ButterKnife.bind(this, rootview);
        init();
        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    public void init() {

        LinearInterpolator linearInterpolator = new LinearInterpolator();
        mRotationAnim = ObjectAnimator.ofFloat(mRotateViewSplash, "rotation", 0f, 360f);
        mRotationAnim.setDuration(2000);
        mRotationAnim.setInterpolator(linearInterpolator);
        mRotationAnim.setRepeatCount(-1);


    }
}
