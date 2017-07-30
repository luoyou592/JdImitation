package com.young.jdmall.app;

import android.app.Application;

import com.young.jdmall.network.NetworkManage;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class JDMallApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManage.init();
    }
}
