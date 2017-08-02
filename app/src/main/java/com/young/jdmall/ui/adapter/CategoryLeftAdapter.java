package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.young.jdmall.R;
import com.young.jdmall.ui.fragment.CategoryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BjyJyk on 2017/7/30.
 */

public class CategoryLeftAdapter extends RecyclerView.Adapter {

    private Context mContext;
    // TODO: 2017/7/30 这里是假数据待替换；
    private String [] mTitle=new String[]{"妈妈专区","时尚女装","宝宝用品","日常用品","儿童服饰","儿童玩具","妈妈专区","时尚女装","宝宝用品","日常用品","儿童服饰","儿童玩具","妈妈专区","时尚女装","宝宝用品","日常用品","儿童服饰","儿童玩具","妈妈专区","时尚女装","宝宝用品","日常用品","儿童服饰","儿童玩具"};
    private final CategoryFragment mFragment;
    private int mSelectindex;


    public CategoryLeftAdapter(Context context, CategoryFragment catecaryFragment) {
        mContext = context;
        mFragment = catecaryFragment;
    }


    /**
     * 条目初始化
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(mContext).inflate(R.layout.item_catecary_left, parent,false);
        ViewHolder  viewHolder=new ViewHolder(rootview);
        return viewHolder;
    }

    /**
     * 刷新条目
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.setData(mTitle[position],position);
    }

    /**
     * 条目个数
     * @return
     */
    @Override
    public int getItemCount() {
        if(mTitle!=null){
            return mTitle.length;
        }
        return 0;
    }


     class ViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_item_left)
        Button mTvItemLeft;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mTvItemLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectindex=mPosition;
                   mFragment.selectFragment(mPosition);
//                    mFragment.mCategoryLeftList.smoothScrollBy();

                    notifyDataSetChanged();

                }
            });
        }

        public void setData(String title, int position) {
            mPosition = position;
            if (mSelectindex == position) {
                //选中
                mTvItemLeft.setBackgroundResource(R.color.background);
                mTvItemLeft.setTextColor(Color.RED);

            } else {
                mTvItemLeft.setBackgroundResource(R.color.white);
                mTvItemLeft.setTextColor(Color.BLACK);
            }
            mTvItemLeft.setText(title);
        }
    }

}
