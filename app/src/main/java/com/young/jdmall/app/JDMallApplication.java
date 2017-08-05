package com.young.jdmall.app;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.young.jdmall.network.NetworkManage;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class JDMallApplication extends Application {

//    public static LoginInfoBean sLoginInfoBean;

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManage.init();
        Fresco.initialize(this);
/*        sLoginInfoBean = new LoginInfoBean();
        sLoginInfoBean.getUserInfo().setUserid("-1");*/

        mContext = getApplicationContext();
        mInstance = this;
        initScreenSize();
    }

    private static JDMallApplication mInstance;
    public static Context mContext;
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;


    public static Context getInstance() {
        return mInstance;
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }
}
