package com.young.jdmall.ui.fragment;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.ui.view.RotateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SplashTwoFragment extends Fragment {

    @BindView(R.id.rotate_view_splash)
    RotateView mRotateViewSplash;
    @BindView(R.id.running_man)
    Button mRunningMan;
    @BindView(R.id.tv_two1)
    TextView mTvTwo1;
    @BindView(R.id.tv_two2)
    TextView mTvTwo2;
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

        mRunningMan.setBackgroundResource(R.drawable.runningman);
        AnimationDrawable drawable= (AnimationDrawable) mRunningMan.getBackground();
        drawable.start();
  /*      LinearInterpolator linearInterpolator = new LinearInterpolator();
        mRotationAnim = ObjectAnimator.ofFloat(mRotateViewSplash, "rotation", 0f, 360f);
        mRotationAnim.setDuration(2000);
        mRotationAnim.setInterpolator(linearInterpolator);
        mRotationAnim.setRepeatCount(-1);*/


    }

    public void ScrollView(int positionOffsetPixels) {
        mRotateViewSplash.getViewLayout(positionOffsetPixels / 3);

    }

}

