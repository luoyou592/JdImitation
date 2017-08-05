package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/5.
 * 包名:com.young.jdmall.ui.activity
 * 时间:2017/8/5
 */

public class SubmitSuccessActivity extends BaseActivity {

    @BindView(R.id.submit_home)
    Button mSubmitHome;
    @BindView(R.id.submit_order)
    Button mSubmitOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_success);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.submit_home, R.id.submit_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit_home:
                Intent intentHome = new Intent(this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.submit_order:
                Intent intent = new Intent(this, OrderDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
