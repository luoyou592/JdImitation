package com.young.jdmall.app;

import android.app.Application;

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
    }


}
