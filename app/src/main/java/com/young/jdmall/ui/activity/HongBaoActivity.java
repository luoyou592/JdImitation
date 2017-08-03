package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.young.jdmall.R;

import butterknife.ButterKnife;

/**
 * Created by BjyJyk on 2017/8/2.
 */

public class HongBaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hongbao);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

    }
}
