package com.young.jdmall.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.young.jdmall.R;
import com.young.jdmall.ui.activity.MainActivity;
import com.young.jdmall.ui.view.PreviewVideoView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by BjyJyk on 2017/7/31.
 */

public class SplashThirdFragment extends Fragment {
    @BindView(R.id.vv_splash)
    PreviewVideoView mVvSplash;
    @BindView(R.id.btn_splash_start)
    Button mBtnSplashStart;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.splash_third, container, false);
        unbinder = ButterKnife.bind(this, rootview);
        init();
        return rootview;
    }

    private void init() {
        mVvSplash.setVideoPath(getVideoPath());
    }

    private String getVideoPath() {
        return "android.resource://" + getActivity().getPackageName() + "/" + R.raw.intro_video;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    //private Subscription mLoop;
    private Disposable mLoop;

    /**
     * 开启轮询
     */
    public void startLoop() {
        if (null != mLoop) {
            /*mLoop.unsubscribe();*/
            mLoop.dispose();

        }
       /* mLoop = Observable.interval(0, 6 * 1000, TimeUnit.MILLISECONDS)
              .subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {

            }
        });*/
        Disposable subscribe = Observable.interval(0, 6 * 1000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (mVvSplash != null) {
                            mVvSplash.seekTo(0 * 6 * 1000);
                            if (!mVvSplash.isPlaying()) {
                                mVvSplash.start();
                            }
                        }
                    }
                });
    }


    @OnClick(R.id.btn_splash_start)
    public void onViewClicked() {
        Intent intent=new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
