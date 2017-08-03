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
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_REGIST_SUCCESS = "regist_success";

    public static void setUserName(Context context, String value){
        SharedPreferences sp = getPreference(context);
        sp.edit().putString(KEY_USER_NAME, value).commit();
    }

    public static String getUserName(Context context){
        SharedPreferences sp = getPreference(context);
        return sp.getString(KEY_USER_NAME, "");
    }

    private static SharedPreferences getPreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp;
    }

    public static void setUserId(Context context, String value){
        SharedPreferences sp = getPreference(context);
        sp.edit().putString(KEY_USER_ID, value).commit();
    }

    public static String getUserId(Context context){
        SharedPreferences sp = getPreference(context);
        return sp.getString(KEY_USER_ID, "");
    }

    public static void setRegistSuccess(Context context, boolean value){
        SharedPreferences sp = getPreference(context);
        sp.edit().putBoolean(KEY_REGIST_SUCCESS, value).commit();
    }


    public static boolean getRegistSuccess(Context context){
        SharedPreferences sp = getPreference(context);
        return sp.getBoolean(KEY_REGIST_SUCCESS, false);
    }

}
