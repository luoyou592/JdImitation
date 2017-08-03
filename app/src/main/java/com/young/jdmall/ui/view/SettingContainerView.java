package com.young.jdmall.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.young.jdmall.ui.adapter.SettingContainerAdapter;
import com.young.jdmall.ui.adapter.SettingContainerAdapter1;

/**
 * Created by 钟志鹏 on 2017/7/31.
 */

public class SettingContainerView extends RecyclerView {

    private LinearLayoutManager mLayoutManager;

    public SettingContainerView(Context context) {
        this(context, null);
    }

    public SettingContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);


//        View.inflate(context, R.layout.view_setting_container, this);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(mLayoutManager);
        setAdapter(new SettingContainerAdapter1());
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

//        mLayoutManager.findLastCompletelyVisibleItemPosition()
    }
}
