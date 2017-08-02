package com.young.jdmall.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/1 0001 20:14
 *  描述：    TODO
 */
public class PreferenceUtils {
    private static final String NAME = "jdmall";
    private static final String KEY_USER_NAME= "user_name";

    public static void setUserName(Context context, String value){
        SharedPreferences sp = getPreference(context);
        sp.edit().putString(KEY_USER_NAME, value).commit();
    }

    /**
     * 获取程序锁密码
     */
    public static String getUserName(Context context){
        SharedPreferences sp = getPreference(context);
        return sp.getString(KEY_USER_NAME, "");
    }

    private static SharedPreferences getPreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp;
    }
}
