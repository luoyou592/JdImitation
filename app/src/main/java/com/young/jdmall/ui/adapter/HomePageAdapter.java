package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.young.jdmall.R;
import com.young.jdmall.ui.activity.TypeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/30.
 */

public class HomePageAdapter extends PagerAdapter {
    private Context mContext;

    public HomePageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            View menu1 = LayoutInflater.from(mContext).inflate(R.layout.item_title_menu1, null);
            menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    mContext.startActivity(intent);
                }
            });
            container.addView(menu1);
            return menu1;
        } else {
            View menu2 = LayoutInflater.from(mContext).inflate(R.layout.item_title_menu2, null);
            menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"等待开放",Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(menu2);
            return menu2;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    static class ViewHolder {
        @BindView(R.id.jd_shop)
        TextView mJdShop;
        @BindView(R.id.global)
        TextView mGlobal;
        @BindView(R.id.clothing)
        TextView mClothing;
        @BindView(R.id.seafood)
        TextView mSeafood;
        @BindView(R.id.jddj)
        TextView mJddj;
        @BindView(R.id.recharge)
        TextView mRecharge;
        @BindView(R.id.jingdou)
        TextView mJingdou;
        @BindView(R.id.zhuanqian)
        TextView mZhuanqian;
        @BindView(R.id.plus)
        TextView mPlus;
        @BindView(R.id.ticket)
        TextView mTicket;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
