package com.young.jdmall.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.bean.CategoryBaseBean;
import com.young.jdmall.ui.adapter.CategoryRightListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.Fragment
 * 时间:2017/7/30
 */

public class DailyArticlesFragment extends CategoryBaseRightListFragment {

    private static final String TAG = "MomAreaFragment";
    private List<CategoryBaseBean> mList = new ArrayList<>();
    private CategoryBaseBean mData;
    private int[] imgages = {R.mipmap.cx,R.mipmap.jd618,R.mipmap.s11};

    public DailyArticlesFragment(CategoryBaseBean data) {
        mData = data;
    }

    @Override
    protected BaseAdapter getBaseCategoryAdapter() {
        //设置适配器
        if (mData == null) {
            return null;
        } else {
            CategoryRightListAdapter categoryRightListAdapter = new CategoryRightListAdapter(getActivity(), mList);
            return categoryRightListAdapter;
        }
    }

    @Override
    protected View getHeader() {

        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.category_header, null);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.category_header_view_pager);
        viewPager.setAdapter(mPagerAdapter);
        return rootView;
    }

    @Override
    public void loadNetwork() {
        mList.clear();
        if (mData != null) {
            onLoadDataSucced();
            for (int i = 0; i < mData.getCategory().size(); i++) {
                //循环遍历找到标题的ID
                CategoryBaseBean.CategoryBean categoryBean = mData.getCategory().get(i);
                //找到目标ID,只有第一个时执行
                if (categoryBean.getId() == 33) {
                    //设置标题
                    CategoryBaseBean categoryInfoBean = new CategoryBaseBean();
                    categoryInfoBean.setTitle("饮食餐具");
                    //添加内容
                    List<CategoryBaseBean.CategoryBean> categoryBeenList = new CopyOnWriteArrayList<>();
                    //在一的里面再次循环,判断是否是需要的ID,如果是,那么添加进去
                    for (int j = 0; j < mData.getCategory().size(); j++) {
                        switch (mData.getCategory().get(j).getId()) {
                            case 149:
                                categoryBeenList.add(mData.getCategory().get(j));
                                break;
                            case 146:
                                categoryBeenList.add(mData.getCategory().get(j));
                                break;
                        }
                        categoryInfoBean.setCategory(categoryBeenList);
                    }
                    mList.add(categoryInfoBean);
                } else if (categoryBean.getId() == 119) {
                    //设置标题
                    CategoryBaseBean categoryInfoBean = new CategoryBaseBean();
                    categoryInfoBean.setTitle("洗漱用品");
                    //添加内容
                    List<CategoryBaseBean.CategoryBean> categoryBeen = new CopyOnWriteArrayList<>();
                    //在一的里面再次循环,判断是否是需要的ID,如果是,那么添加进去
                    for (int j = 0; j < mData.getCategory().size(); j++) {
                        //创建新的对象?
                        switch (mData.getCategory().get(j).getId()) {
                            case 119:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                            case 129:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                        }
                        categoryInfoBean.setCategory(categoryBeen);
                    }
                    mList.add(categoryInfoBean);

                } else if (categoryBean.getId() == 148) {
                    //设置标题
                    CategoryBaseBean categoryInfoBean = new CategoryBaseBean();
                    categoryInfoBean.setTitle("日常工具");
                    //添加内容
                    List<CategoryBaseBean.CategoryBean> categoryBeen = new CopyOnWriteArrayList<>();
                    //在一的里面再次循环,判断是否是需要的ID,如果是,那么添加进去
                    for (int j = 0; j < mData.getCategory().size(); j++) {
                        switch (mData.getCategory().get(j).getId()) {
                            case 148:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                            case 146:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                            case 4:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                        }
                        categoryInfoBean.setCategory(categoryBeen);
                    }
                    mList.add(categoryInfoBean);
                }
            }
        } else {
            onLoadDataFailed();
            Log.d(TAG, "loadNetwork: 没有数据");
        }

    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            if (imgages != null){
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition = position % imgages.length;
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imgages[newPosition]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

}
