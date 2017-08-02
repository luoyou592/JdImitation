package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.TestTypeListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/1.
 * 包名:com.example.administrator.jdx.Adapter
 * 时间:2017/8/1
 */

public class TypeListAdapter extends RecyclerView.Adapter {

    private static final String TAG = "RecycleAdapter";
    private Context mContext;
    private List<TestTypeListBean> mData;

    public TypeListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<TestTypeListBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_type_list, null);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.money)
        TextView mMoney;
        @BindView(R.id.evaluation)
        TextView mEvaluation;
        @BindView(R.id.goodEvaluation)
        TextView mGoodEvaluation;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(TestTypeListBean listTestBean) {
            mName.setText(listTestBean.getName());
            mEvaluation.setText(listTestBean.getEvaluation() + "个评论");
            mGoodEvaluation.setText(listTestBean.getGoodEvaluation() + "%好评率");
        }
    }

}
