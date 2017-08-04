package com.young.jdmall.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/1 0001 20:14
 *  描述：    TODO
 */
public class PreferenceUtils {

    private static final String TAG = "PreferenceUtils";

    private static final String NAME = "jdmall";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_REGIST_SUCCESS = "regist_success";
    private static final String KEY_SEARCH_LIST = "search_list";

    public static void setUserName(Context context, String value) {
        SharedPreferences sp = getPreference(context);
        sp.edit().putString(KEY_USER_NAME, value).commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences sp = getPreference(context);
        return sp.getString(KEY_USER_NAME, "");
    }

    private static SharedPreferences getPreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp;
    }

    public static void setUserId(Context context, String value) {
        SharedPreferences sp = getPreference(context);
        sp.edit().putString(KEY_USER_ID, value).commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences sp = getPreference(context);
        return sp.getString(KEY_USER_ID, "");
    }

    public static void setRegistSuccess(Context context, boolean value) {
        SharedPreferences sp = getPreference(context);
        sp.edit().putBoolean(KEY_REGIST_SUCCESS, value).commit();
    }


    public static boolean getRegistSuccess(Context context) {
        SharedPreferences sp = getPreference(context);
        return sp.getBoolean(KEY_REGIST_SUCCESS, false);
    }

    public static List<String> getSearchList(Context context) {
        SharedPreferences preference = getPreference(context);
        String string = preference.getString(KEY_SEARCH_LIST, "");
        if (!TextUtils.isEmpty(string)) {
            Gson gson = new Gson();
            List<String> strings = gson.fromJson(string, new TypeToken<List<String>>() {
            }.getType());
            return strings;
        }
        return null;
    }

    public static void setSearchList(Context context, List<String> list) {
        SharedPreferences pf = getPreference(context);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        Log.d(TAG, "setSearchList: " + json);
        pf.edit().putString(KEY_SEARCH_LIST, json).commit();
    }

    public static void addSearchList(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            SharedPreferences preference = getPreference(context);
            String string = preference.getString(KEY_SEARCH_LIST, "");
            List<String> strings;
            if (!TextUtils.isEmpty(string)) {
                Gson gson = new Gson();
                strings = gson.fromJson(string, new TypeToken<List<String>>() {
                }.getType());
            } else {
                strings = new ArrayList<>();
            }
            for (String s : strings) {
                if (str.equals(s)) {
                    return;
                }
            }
            strings.add(str);
            SharedPreferences pf = getPreference(context);
            Gson gson = new Gson();
            String json = gson.toJson(strings);
            Log.d(TAG, "setSearchList: " + json);
            pf.edit().putString(KEY_SEARCH_LIST, json).commit();
        }
    }

    public static void deleteSearchList(Context context) {
        SharedPreferences pf = getPreference(context);
        pf.edit().putString(KEY_SEARCH_LIST, "").commit();
    }

}
