package com.young.jdmall.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.young.jdmall.ui.fragment.OrderALLFragment;
import com.young.jdmall.ui.fragment.OrderCancelFragment;
import com.young.jdmall.ui.fragment.OrderCompleFragment;
import com.young.jdmall.ui.fragment.OrderWaitFFragment;
import com.young.jdmall.ui.fragment.OrderWaitSFragment;


/*
 *  创建者:   tiao
 *  创建时间:  2017/7/31 0031 16:59
 *  描述：    TODO
 */
public class OrderAdapter extends FragmentPagerAdapter {
    private final String[] mTitle;

    public OrderAdapter(FragmentManager fm, String[] title) {
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
    private static final int FRAGMENT_WAIT_F = 1;
    private static final int FRAGMENT_WAIT_S = 2;
    private static final int FRAGMENT_COMP = 3;
    private static final int FRAGMENT_CANCEL = 4;


    public Fragment getFragment(int position){
        switch (position){
            case FRAGMENT_ALL:
                return new OrderALLFragment();
            case FRAGMENT_WAIT_F:
                return new OrderWaitFFragment();
            case FRAGMENT_WAIT_S:
                return new OrderWaitSFragment();
            case FRAGMENT_COMP:
                return new OrderCompleFragment();
            case FRAGMENT_CANCEL:
                return new OrderCancelFragment();

        }
        return null;
    }
}
