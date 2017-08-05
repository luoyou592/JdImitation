package com.young.jdmall.ui.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
/*
 *  创建者:   tiao
 *  创建时间:  2017/8/4 0004 17:41
 *  描述：    TODO
 */
public class TimeUtil {

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(String time) {

        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    public static String stampToDate (String s){

        String res;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);

        Date date = new Date(lt);

        res = simpleDateFormat.format(date);

        return res;

    }


}
