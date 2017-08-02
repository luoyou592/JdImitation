package com.young.jdmall.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.young.jdmall.ui.fragment.CalendarALLFragment;
import com.young.jdmall.ui.fragment.CalendarHouseFragment;
import com.young.jdmall.ui.fragment.CalendarWarnFragment;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 21:39
 *  描述：    TODO
 */
public class CalendarAdapter extends FragmentPagerAdapter {

    private final String[] mTitle;

    public CalendarAdapter(FragmentManager fm, String[] title) {
        super(fm);

        mTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }



    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    private static final int FRAGMENT_ALL = 0;
    private static final int FRAGMENT_WARN = 1;
    private static final int FRAGMENT_HOUSE = 2;


    public Fragment getFragment(int position){
        switch (position){
            case FRAGMENT_ALL:
                return new CalendarALLFragment();
            case FRAGMENT_WARN:
                return new CalendarWarnFragment();
            case FRAGMENT_HOUSE:
                return new CalendarHouseFragment();


        }
        return null;
    }
}