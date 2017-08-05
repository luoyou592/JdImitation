package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.young.jdmall.R;

import butterknife.ButterKnife;

import static com.young.jdmall.network.NetworkManage.init;

/**
 * Created by BjyJyk on 2017/8/4.
 */

public class OrderDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        ButterKnife.bind(this);
        init();
    }
}
