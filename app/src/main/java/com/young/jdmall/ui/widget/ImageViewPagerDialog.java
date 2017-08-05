package com.young.jdmall.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.ui.activity.ProductDetaiActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BjyJyk on 2017/8/2.
 */

public class ImageViewPagerDialog extends Dialog {


    private static final String TAG = "ImageViewPagerDialog";
    @BindView(R.id.view_image_view_pager)
    ViewPager mViewImageViewPager;
    @BindView(R.id.btn_image_view_pager)
    Button mBtnImageViewPager;
    private Context mContext;
    private List<String> mImages;
    private int mId;

    public ImageViewPagerDialog(Context context) {
        this(context, 0);
    }

    public ImageViewPagerDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }



    private void init() {
        //以view的方式引入，然后回调activity方法，setContentView，实现自定义布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_image_dialog, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        //radiobutton的初始化


        // groupBroadcast.setOnCheckedChangeListener(listener);
        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) mContext).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER);//设置对话框位置
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        params.height = (int) (d.getHeight() * 0.8);
        dialogWindow.setAttributes(params);


        mViewImageViewPager.setAdapter(mPagerAdapter);
    }

    @OnClick(R.id.btn_image_view_pager)
    public void onViewClicked() {
        Intent intent = new Intent(mContext, ProductDetaiActivity.class);
        intent.putExtra("id",mId);
        mContext.startActivity(intent);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            if (mImages != null){
                return mImages.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            String imageUrl = Constant.IMAGE_URL + mImages.get(position);
            Glide.with(mContext)
                    .load(imageUrl)
                    .error(R.mipmap.test_image)
                    .fallback(R.mipmap.test_image)
                    .into(imageView);
            container.addView(imageView);
            return imageView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    public void setData(List<String> images, int id) {
        mId = id;
        Log.d(TAG, "setData: 获取图片" + images.size());
        mImages = images;
    }
}
