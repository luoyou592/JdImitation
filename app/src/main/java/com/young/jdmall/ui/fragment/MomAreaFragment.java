package com.young.jdmall.ui.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;
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

public class MomAreaFragment extends CategoryBaseRightListFragment {

    private static final String TAG = "MomAreaFragment";
    private List<CategoryBaseBean> mList = new ArrayList<>();
    private CategoryBaseBean mData;
    //    private int[] images = {R.mipmap.cx,R.mipmap.jd618,R.mipmap.s11};
    private List<Integer> mImageList = new ArrayList<>();

    private ConvenientBanner mViewPager;

    public MomAreaFragment(CategoryBaseBean data) {
        mData = data;
    }

    @Override
    protected BaseAdapter getBaseCategoryAdapter() {
        //设置适配器
        if (mData == null) {
            return null;
        } else {
            CategoryRightListAdapter categoryRightListAdapter = new CategoryRightListAdapter
                    (getActivity(), mList);
            return categoryRightListAdapter;
        }

    }

    @Override
    protected View getHeader() {
//        mImageList.add(images);

        mImageList.clear();
        mImageList.add(R.mipmap.cx);
        mImageList.add(R.mipmap.jd618);
        mImageList.add(R.mipmap.s11);
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.category_header, null);
        mViewPager = (ConvenientBanner) rootView.findViewById(R.id.category_header_view_pager);

        mViewPager.setPageIndicator(new int[]{R.mipmap.index_white, R.mipmap.index_red});
        mViewPager.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        mViewPager.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderView();
            }
        }, mImageList);
//        mViewPager.setAdapter(mPagerAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.startTurning(1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stopTurning();
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
                if (categoryBean.getId() == 1) {
                    //设置标题
                    CategoryBaseBean categoryInfoBean = new CategoryBaseBean();
                    categoryInfoBean.setTitle("孕妇专区");
                    //添加内容
                    List<CategoryBaseBean.CategoryBean> categoryBeenList = new
                            CopyOnWriteArrayList<>();
                    //在一的里面再次循环,判断是否是需要的ID,如果是,那么添加进去
                    for (int j = 0; j < mData.getCategory().size(); j++) {
                        switch (mData.getCategory().get(j).getId()) {
                            case 12:
                                categoryBeenList.add(mData.getCategory().get(j));
                                break;
                            case 13:
                                categoryBeenList.add(mData.getCategory().get(j));
                                break;
                            case 117:
                                categoryBeenList.add(mData.getCategory().get(j));
                                break;
                            case 113:
                                categoryBeenList.add(mData.getCategory().get(j));
                                break;
                        }
                        categoryInfoBean.setCategory(categoryBeenList);
                    }
                    mList.add(categoryInfoBean);
                } else if (categoryBean.getId() == 115) {
                    //设置标题
                    CategoryBaseBean categoryInfoBean = new CategoryBaseBean();
                    categoryInfoBean.setTitle("孕妈用品");
                    //添加内容
                    List<CategoryBaseBean.CategoryBean> categoryBeen = new CopyOnWriteArrayList<>();
                    //在一的里面再次循环,判断是否是需要的ID,如果是,那么添加进去
                    for (int j = 0; j < mData.getCategory().size(); j++) {
                        //创建新的对象?
                        switch (mData.getCategory().get(j).getId()) {
                            case 112:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                            case 128:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                            case 130:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                            case 131:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                        }
                        categoryInfoBean.setCategory(categoryBeen);
                    }
                    mList.add(categoryInfoBean);

                } else if (categoryBean.getId() == 11) {
                    //设置标题
                    CategoryBaseBean categoryInfoBean = new CategoryBaseBean();
                    categoryInfoBean.setTitle("妈妈个人用品");
                    //添加内容
                    List<CategoryBaseBean.CategoryBean> categoryBeen = new CopyOnWriteArrayList<>();
                    //在一的里面再次循环,判断是否是需要的ID,如果是,那么添加进去
                    for (int j = 0; j < mData.getCategory().size(); j++) {
                        switch (mData.getCategory().get(j).getId()) {
                            case 114:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                            case 118:
                                categoryBeen.add(mData.getCategory().get(j));
                                break;
                        }
                        categoryInfoBean.setCategory(categoryBeen);
                    }
                    mList.add(categoryInfoBean);
                } else if (categoryBean.getId() == 111) {
                    //设置标题
                    CategoryBaseBean categoryInfoBean = new CategoryBaseBean();
                    categoryInfoBean.setTitle("妈妈养生");
                    //添加内容
                    List<CategoryBaseBean.CategoryBean> categoryBeen = new CopyOnWriteArrayList<>();
                    //在一的里面再次循环,判断是否是需要的ID,如果是,那么添加进去
                    for (int j = 0; j < mData.getCategory().size(); j++) {
                        switch (mData.getCategory().get(j).getId()) {
                            case 116:
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


    public class ImageHolderView implements Holder<Integer> {
        private View rootview;
        private SimpleDraweeView imageView;

        @Override
        public View createView(Context context) {
            rootview = ((LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE)).inflate(R.layout.goods_item_head_img, null);
            imageView = (SimpleDraweeView) rootview.findViewById(R.id.sdv_item_head_img);
            return rootview;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setBackgroundResource(data);
        }
    }

}
