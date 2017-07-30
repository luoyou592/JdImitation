package com.young.jdmall.ui.adaper;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by 25505 on 2017/7/30.
 */

public class HomePageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImags;
    public HomePageAdapter(Context context, int[] imags){
        mContext = context;
        mImags = imags;
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
        //保证条目索引都在0-2
        position = position%3;

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mImags[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
