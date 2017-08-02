package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/1 0001 21:35
 *  描述：    TODO
 */
public class RecepitAddressActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rv_receipt_address)
    RecyclerView mRvReceiptAddress;
    @BindView(R.id.bt_add_address)
    Button mBtAddAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepit_address);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.bt_add_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_add_address:
                Intent intent = new Intent(this, AddRecepitAddressActivity.class);
                break;
        }
    }
}
