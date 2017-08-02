package com.young.jdmall.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.ui.view.SettingContainerView;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class SettingAdapter extends PagerAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        TextView textView = new TextView(container.getContext());
//        textView.setText(position + "");
//        container.addView(textView);

        SettingContainerView containerView = new SettingContainerView(container.getContext());
        container.addView(containerView);
        return containerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
