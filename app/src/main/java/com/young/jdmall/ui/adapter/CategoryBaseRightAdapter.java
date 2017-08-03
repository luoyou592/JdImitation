package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.Fragment
 * 时间:2017/7/30
 */


/**
 * 基类Adapter
 */
abstract class CategoryBaseRightAdapter<T> extends BaseAdapter {

    private static final String TAG = "BaseCatecaryRightAdapte";

    private Context mContext;
    private List<T> mDataList;

    public CategoryBaseRightAdapter(Context context, List<T> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        if (mDataList != null){
            return mDataList.size();
        }
        return 50;

    }

    @Override
    public Object getItem(int position) {
        if (mDataList != null){
            return mDataList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = getItemVIew(position);
        }
        onBindItemView(convertView,position);

        return convertView;
    }

    //子类实现,绑定数据
    protected abstract void onBindItemView(View convertView, int position);

    //子类实现,返回条目布局
    protected abstract View getItemVIew(int position);

    public Context getContext() {
        return mContext;
    }

    public List<T> getDataList() {
        return mDataList;
    }
}
