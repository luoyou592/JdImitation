package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.young.jdmall.R;
import com.young.jdmall.bean.TestTypeListBean;
import com.young.jdmall.ui.TypeListListener;
import com.young.jdmall.ui.adapter.TypeListAdapter;
import com.young.jdmall.ui.widget.ViewTypeHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/1.
 * 包名:com.young.jdmall.ui.View.CategoryView
 * 时间:2017/8/1
 */

public class TypeListActivity extends AppCompatActivity {

    @BindView(R.id.view_type_list_header)
    ViewTypeHeader mViewTypeListHeader;
    private List<TestTypeListBean> mData;


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);
        ButterKnife.bind(this);

        loadData();
        init();
    }

    //初始化
    private void init() {
        //添加布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //添加滑动监听
        mRecycleView.addOnScrollListener(new TypeListListener(findViewById(R.id
                .view_type_list_header)));
        //设置适配器
        TypeListAdapter typeListAdapter = new TypeListAdapter(this);
        typeListAdapter.setData(mData);
        mRecycleView.setAdapter(typeListAdapter);
    }


    //加载假数据
    private void loadData() {
        mData = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            TestTypeListBean testTypeListBean = new TestTypeListBean();

            testTypeListBean.setName("名字一定要长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长"
                    + i);
            testTypeListBean.setEvaluation(random.nextInt(5) * 13591);//评论
            testTypeListBean.setGoodEvaluation(random.nextInt(20) * 17 * 0.1f);

            mData.add(testTypeListBean);
        }
    }
}
