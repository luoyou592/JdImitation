package com.young.jdmall.ui.activity;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.young.jdmall.R;
import com.young.jdmall.ui.adapter.SplashAdapter;
import com.young.jdmall.ui.fragment.SplashFirstFragment;
import com.young.jdmall.ui.fragment.SplashThirdFragment;
import com.young.jdmall.ui.fragment.SplashTwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BjyJyk on 2017/7/31.
 */

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.vp_splash)
    ViewPager mVpSplash;
    private FragmentManager mSupportFragmentManager;
    private int mlastPosition = 0;
    private int mlastPosition1 = 0;

    private int mPosition;

    //  private android.app.FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initdata();
        init();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
    }

    private void initdata() {
        Fragment fragment1 = new SplashFirstFragment();
        Fragment fragment2 = new SplashTwoFragment();
        Fragment fragment3 = new SplashThirdFragment();
        mSupportFragmentManager = getSupportFragmentManager();


        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

    }


    private void init() {
        SplashAdapter splashAdapter = new SplashAdapter(mSupportFragmentManager, fragments);
        mVpSplash.setAdapter(splashAdapter);
        mVpSplash.setOnPageChangeListener(mOnPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e(TAG, "onPageScrolled: " + position + positionOffsetPixels);
            if (position == 0) {
                ((SplashFirstFragment) fragments.get(0)).ScrollView(positionOffsetPixels - mlastPosition);
                ((SplashTwoFragment) fragments.get(1)).ScrollView(positionOffsetPixels);
                mlastPosition = positionOffsetPixels;
            } else if (position == 1) {
                ((SplashTwoFragment) fragments.get(1)).ScrollView(positionOffsetPixels);
            }

        }

        @Override
        public void onPageSelected(int position) {
            mPosition = position;
            if (position == 2) {
                ((SplashThirdFragment) fragments.get(2)).startLoop();
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {
            /*if(mPosition==1){
                if(state==2){
                  ((SplashTwoFragment)fragments.get(1)).mRotationAnim.start();
                }else if(state==0){
                    ((SplashTwoFragment)fragments.get(1)).mRotationAnim.clone();
                }
            }*/
            if (mPosition == 2) {
                ((SplashThirdFragment) fragments.get(2)).mIvSplash.setVisibility(View.GONE);
            }


        }
    };


}
