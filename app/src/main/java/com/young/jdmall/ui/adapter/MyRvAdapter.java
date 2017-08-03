package com.young.jdmall.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.ui.activity.LoginActivity;
import com.young.jdmall.ui.activity.OrderActivity;
import com.young.jdmall.ui.utils.PreferenceUtils;
import com.young.jdmall.ui.widget.GoodsItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/1 0001 13:47
 *  描述：    TODO
 */
public class MyRvAdapter extends RecyclerView.Adapter {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_NORMAL = 0;
    private static final int TYPE_RECO = 2;
    @BindView(R.id.iv_signOrReg)
    ImageView mIvSignOrReg;
    @BindView(R.id.rl_signOrReg)
    RelativeLayout mRlSignOrReg;



    @BindView(R.id.tv_order)
    TextView mTvOrder;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.textView2)
    TextView mTextView2;


    private Context mContext;
    private List<String> mDatas = new ArrayList<>();
    private LoginInfoBean mLoginInfoBean;
    private String mUsers;

    public void setUserInfoBean(LoginInfoBean loginInfoBean) {
        mLoginInfoBean = loginInfoBean;
        notifyDataSetChanged();
    }

    public MyRvAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else if (position == 1) {
            return TYPE_RECO;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void setDatas(List<String> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                View titleView = View.inflate(mContext, R.layout.item_user_head, null);
                TitleHolder titleHolder = new TitleHolder(titleView);
                return titleHolder;
            case TYPE_RECO:
                ImageView imageView = new ImageView(mContext);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(params);
                imageView.setImageResource(R.mipmap.tuijian);
                return new RecoHolder(imageView);
            case TYPE_NORMAL:
//                View normalView = View.inflate(mContext, R.layout.goods_item_view, null);
//                NormalHolder normalHolder = new NormalHolder(normalView);
//                return normalHolder;
                GoodsItemView normalView = new GoodsItemView(mContext);
                return new NormalHolder(normalView);
            default:
                Log.e("home", "怎么出现了第四种holder");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_TITLE:
                ((TitleHolder) holder).bindView();
                break;
            case TYPE_RECO:
                ((RecoHolder) holder).bindView();
                break;
            case TYPE_NORMAL:
                ((NormalHolder) holder).bindView();
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(!"".equals(mUsers)){

            return 10;
        }else{
            return 1;
        }
    }

    public void setUsers(String users) {
        mUsers = users;
        notifyDataSetChanged();
    }


    class RecoHolder extends RecyclerView.ViewHolder {
        RecoHolder(View view) {
            super(view);
        }

        public void bindView() {

        }
    }


    class NormalHolder extends RecyclerView.ViewHolder {
        private View mView;

        NormalHolder(View view) {
            super(view);
            mView = view;
        }

        public void bindView() {
            GoodsItemView goodsItemView = (GoodsItemView) mView;

        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_signOrReg)
        ImageView mIvSignOrReg;
        @BindView(R.id.rl_signOrReg)
        RelativeLayout mRlSignOrReg;

        @BindView(R.id.tv_order)
        TextView mTvOrder;
        @BindView(R.id.tv_login)
        TextView mTvLogin;
        @OnClick({R.id.rl_signOrReg, R.id.tv_order})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_signOrReg:
                    if("".equals(PreferenceUtils.getUserName(mContext))){

                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                    }
                    break;
                case R.id.tv_order:
                    Intent intent1 = new Intent(mContext, OrderActivity.class);
                    mContext.startActivity(intent1);
                    break;
            }
        }


        TitleHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView() {
/*            if (mLoginInfoBean != null) {
//                mRlSignOrReg.setVisibility(View.GONE);
//                mRlSignOk.setVisibility(View.VISIBLE);
            }*/
            if(!"".equals(mUsers)){
                mTvLogin.setText("您好，"+ mUsers);
            }else {
                mTvLogin.setText("登录/注册");
            }

        }
    }
}
