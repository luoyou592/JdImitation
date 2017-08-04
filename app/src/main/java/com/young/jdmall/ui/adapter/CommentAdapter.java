package com.young.jdmall.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.CommentInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/8/3.
 */

public class CommentAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<CommentInfoBean.CommentBean> mComment;

    public CommentAdapter(Activity activity, List<CommentInfoBean.CommentBean> comment) {
        mActivity = activity;
        mComment = comment;
    }

    @Override
    public int getCount() {
        if (mComment.size() != 0) {
            return mComment.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mComment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentViewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.comment_view, null);
            holder = new CommentViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (CommentViewHolder) convertView.getTag();
        }
        holder.mTvCvName.setText(mComment.get(position).getUsername());
        holder.mTvCvComment.setText(mComment.get(position).getContent());
        holder.mTvCvTime.setText(mComment.get(position).getTime()+"年");
        return convertView;
    }

    static class CommentViewHolder {
        @BindView(R.id.tv_cv_name)
        TextView mTvCvName;
        @BindView(R.id.tv_cv_time)
        TextView mTvCvTime;
        @BindView(R.id.tv_cv_comment)
        TextView mTvCvComment;

        CommentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
