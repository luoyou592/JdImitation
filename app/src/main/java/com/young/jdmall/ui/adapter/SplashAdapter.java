package com.young.jdmall.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by BjyJyk on 2017/7/31.
 */

public class SplashAdapter extends FragmentPagerAdapter {


    private  List<Fragment> listfragment;
    private  FragmentManager fragmetnmanager;

    public SplashAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmetnmanager=fm;
        this.listfragment=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    @Override
    public int getCount() {
        if(listfragment.size()!=0){
            return  listfragment.size();
        }
        return 0;
    }
}
