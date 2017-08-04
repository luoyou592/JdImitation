package com.young.jdmall.app;

import android.app.Application;
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
    }

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


}
