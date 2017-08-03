package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.jdmall.R;

/**
 * Created by 25505 on 2017/7/30.
 */

public class HomePageAdapter extends PagerAdapter {
    private Context mContext;
    public HomePageAdapter(Context context){
        mContext = context;
    }
    @Override
    public int getCount() {

        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position==0){
            View menu1 = LayoutInflater.from(mContext).inflate(R.layout.item_title_menu1, null);
            container.addView(menu1);
            return menu1;
        }else{
            View menu2 = LayoutInflater.from(mContext).inflate(R.layout.item_title_menu2, null);
            container.addView(menu2);
            return menu2;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
