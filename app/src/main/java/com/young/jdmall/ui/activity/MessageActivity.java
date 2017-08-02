package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/31 0031 11:25
 *  描述：    TODO
 */
public class MessageActivity extends AppCompatActivity {
    @BindView(R.id.iv_calendar)
    ImageView mIvCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_calendar)
    public void onClick() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
