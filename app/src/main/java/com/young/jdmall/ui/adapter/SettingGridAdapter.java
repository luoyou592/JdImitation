package com.young.jdmall.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 钟志鹏 on 2017/7/31.
 */

public class SettingGridAdapter extends BaseAdapter {
    private String[] mTitles;
    private int mSelectIndex;

    public SettingGridAdapter(String[] titles) {
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Object getItem(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_grid, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bindView(position);
        return convertView;
    }

    public void selectItem(int selectIndex) {
        mSelectIndex = selectIndex;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.item_tab_name)
        TextView mItemTabName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(int position) {
            mItemTabName.setText(mTitles[position]);
            mItemTabName.setEnabled(position == mSelectIndex ? false : true);
        }
    }
}
