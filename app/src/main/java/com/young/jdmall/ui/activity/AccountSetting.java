package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.young.jdmall.R;

import butterknife.ButterKnife;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 20:49
 *  描述：    TODO
 */
public class AccountSetting extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);

    }

}
