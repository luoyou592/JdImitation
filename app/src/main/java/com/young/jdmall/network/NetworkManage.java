package com.young.jdmall.network;

import com.young.jdmall.app.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class NetworkManage {

    private static JDMallService sJdMallService;

    public static void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sJdMallService = retrofit.create(JDMallService.class);
    }

    public static JDMallService getJDMallService() {
        return sJdMallService;
    }
}
