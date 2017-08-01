package com.young.jdmall.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/31.
 */

public class CountDownView extends RelativeLayout {

    @BindView(R.id.tvtime1)
    TextView mTvtime1;
    @BindView(R.id.tvtime2)
    TextView mTvtime2;
    @BindView(R.id.tvtime3)
    TextView mTvtime3;
    private Handler mHandler;
    private long mTime;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View coundownView = LayoutInflater.from(context).inflate(R.layout.layout_count_down, this);
        ButterKnife.bind(this,coundownView);
        mHandler = new Handler();
    }
    //开启
    public void startCountDown() {
        //防止bindView不断刷新重复启动runnable
        if (runnable!=null){
            mHandler.removeCallbacks(runnable);
        }
        mHandler.postDelayed(runnable, 1000);
    }
    public void stopCountDown(){
        mHandler.removeCallbacks(runnable);
    }
    //设置时间
    public void setTime(long time){
        mTime = time;
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mTime--;
            String formatLongToTimeStr = formatLongToTimeStr(mTime);
            String[] split = formatLongToTimeStr.split("：");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    if (split[0].length()==1){
                        mTvtime1.setText("0"+split[0]);
                    }else{
                        mTvtime1.setText(split[0]);
                    }
                }
                if (i == 1) {
                    if (split[1].length()==1){
                        mTvtime2.setText("0"+split[1]);
                    }else {
                        mTvtime2.setText(split[1]);
                    }
                }
                if (i == 2) {
                    if (split[2].length()==1){
                        mTvtime3.setText("0"+split[2]);
                    }else {
                        mTvtime3.setText(split[2]);
                    }

                }
            }
            if (mTime > 0) {
                mHandler.postDelayed(this, 1000);
            }
        }
    };
    public long getTime(){
        return mTime;
    }
    public  String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue() ;
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour+"："+minute+"："+second;
        return strtime;
    }
}
