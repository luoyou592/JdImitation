package com.young.jdmall.app;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.young.jdmall.bean.DaoMaster;
import com.young.jdmall.bean.DaoSession;
import com.young.jdmall.network.NetworkManage;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class JDMallApplication extends Application {

//    public static LoginInfoBean sLoginInfoBean;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManage.init();
        Fresco.initialize(this); //图片加载
        //配置数据库
        setupDatabase();
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
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }



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
