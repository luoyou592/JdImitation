package com.young.jdmall.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.CommentInfoBean;
import com.young.jdmall.ui.activity.ProductDetaiActivity;
import com.young.jdmall.ui.adapter.CommentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * item页ViewPager里的评价Fragment
 */
public class GoodsCommentFragment extends Fragment {
    public TextView tv_comment_count, tv_good_comment;
    public ProductDetaiActivity mActivity;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R.id.tv_good_comment)
    TextView mTvGoodComment;
    @BindView(R.id.iv_comment_right)
    ImageView mIvCommentRight;
    @BindView(R.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R.id.ll_empty_comment)
    LinearLayout mLlEmptyComment;
    @BindView(R.id.lv_comment)
    ListView mLvComment;
    private CommentInfoBean mCommentInfoBean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (ProductDetaiActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goods_comment, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setData(CommentInfoBean commentInfoBean) {
        mCommentInfoBean = commentInfoBean;
        if (mCommentInfoBean.getComment().size()>0){
            mLlEmptyComment.setVisibility(View.GONE);
            mLvComment.setAdapter(new CommentAdapter(getActivity(), mCommentInfoBean.getComment()));
        }else{
            mLlEmptyComment.setVisibility(View.VISIBLE);
        }

    }

}
