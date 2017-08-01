package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.ui.adapter.SeckillRvAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 25505 on 2017/7/31.
 */

public class SecKillActivity extends AppCompatActivity {
    @BindView(R.id.iv_seckill_back)
    ImageView mIvSeckillBack;
    @BindView(R.id.seckill_recycle_view)
    RecyclerView mSeckillRecycleView;
    private SeckillRvAdapter mSeckillRvAdapter;
    private long mTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seckill);
        ButterKnife.bind(this);
        processIntent();
        initView();

    }

    private void processIntent() {
        if (getIntent()!=null){
            mTime = getIntent().getLongExtra("time", 0);
        }
    }

    private void initView() {
        mSeckillRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mSeckillRvAdapter = new SeckillRvAdapter(this,mTime);
        mSeckillRecycleView.setAdapter(mSeckillRvAdapter);

    }

    @OnClick(R.id.iv_seckill_back)
    public void onClick() {
        finish();
    }
}
