package com.young.jdmall.ui.utils;

import java.text.NumberFormat;

/**
 * Created by lidongzhi on 16/10/14.
 */
public class PriceFormater {

    public static String format(float countPrice){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        return format.format(countPrice);
    }
}
