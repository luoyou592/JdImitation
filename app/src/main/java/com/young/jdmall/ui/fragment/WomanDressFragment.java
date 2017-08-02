package com.young.jdmall.ui.fragment;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.bean.TestBean;
import com.young.jdmall.ui.adapter.WomanDressAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.Fragment
 * 时间:2017/7/30
 */

public class WomanDressFragment extends CategoryBaseRightListFragment {

    private static final String TAG = "WomanDressFragment";
    private List<TestBean> mList;
    private int mId;

    public WomanDressFragment(int id) {
        mId = id;
    }

    @Override
    protected BaseAdapter getBaseCategoryAdapter() {
        //设置适配器
        WomanDressAdapter womanDressAdapter = new WomanDressAdapter(getActivity(), mList);
        return womanDressAdapter;
    }

    @Override
    protected View getHeader() {

        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(R.mipmap.jd);
        return imageView;
    }

    @Override
    public void loadNetwork() {
        onLoadDataSucced();

        mList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < random.nextInt(5) + 2; i++) {
            TestBean testBean = new TestBean();
            testBean.setTitle("标题" + i);
            //每创建一个对象给其中内容遍历添加
            List<TestBean.stringBean> list = new ArrayList<>();
            for (int j = 0; j < random.nextInt(6) + 3; j++) {

                TestBean.stringBean stringBean = new TestBean.stringBean();
                stringBean.setName("name" + j);
                list.add(stringBean);

                testBean.setNameList(list);
            }
            mList.add(testBean);
        }

    }
}
