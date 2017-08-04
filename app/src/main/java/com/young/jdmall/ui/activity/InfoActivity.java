package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.ui.adapter.MessageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.calendar)
    ImageView mCalendar;
    @BindView(R.id.message_list)
    RecyclerView mMessageList;
    private MessageAdapter mRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        mMessageList.setLayoutManager(new LinearLayoutManager(this));
        mRvAdapter=new MessageAdapter(this);
        mMessageList.setAdapter(mRvAdapter);
    }

    @OnClick({R.id.iv_back, R.id.calendar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.calendar:
                Intent intent=new Intent(this,CalendarActivity.class);
                startActivity(intent);
                break;
        }
    }
}
