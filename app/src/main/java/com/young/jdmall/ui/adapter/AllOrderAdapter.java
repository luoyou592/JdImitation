package com.young.jdmall.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.OrderDetailBean;
import com.young.jdmall.bean.OrderInfoBean;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.utils.PreferenceUtils;
import com.young.jdmall.ui.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/31 0031 17:36
 *  描述：    TODO
 */
public class AllOrderAdapter extends RecyclerView.Adapter {


    private static final int KEY_ITEM_TYPE_LOADING = 1;

    private Context mContext;
    private int mType;
    private final LinearLayout mLlNonOrder;

    public AllOrderAdapter(Context context, int i, LinearLayout llNonOrder) {
        mContext = context;
        mType = i;
        mLlNonOrder = llNonOrder;
    }

    private List<OrderInfoBean.OrderListBean> mAllOrderBeanList = new ArrayList<>();

    public void setAddressBeanList(List<OrderInfoBean.OrderListBean> orderInfoBeanList) {
        mAllOrderBeanList.addAll(orderInfoBeanList);
        if (mAllOrderBeanList.size() == 0) {
            mLlNonOrder.setVisibility(View.VISIBLE);

        } else {
            mLlNonOrder.setVisibility(View.GONE);

        }
        Log.d(TAG, "setAddressBeanList: " + mAllOrderBeanList.size());
        notifyDataSetChanged();
    }

    public static View mLoadingView;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (mLoadingView == null) {
        mLoadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_container_loading, parent, false);
//        }
        if (viewType == KEY_ITEM_TYPE_LOADING) {
            return new ViewHolderFromLoading(mLoadingView);
        } else {

            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_all_order, parent, false);
            ViewHolder viewHolder = new ViewHolder(itemView);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == KEY_ITEM_TYPE_LOADING) {

            ((ViewHolderFromLoading) holder).bindView();

        } else {

            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(mAllOrderBeanList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mAllOrderBeanList != null) {
            return mAllOrderBeanList.size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return KEY_ITEM_TYPE_LOADING;
        }
        return 0;
    }



    class ViewHolderFromLoading extends RecyclerView.ViewHolder {
        @BindView(R.id.loading)
        LinearLayout mLoading;
        @BindView(R.id.loaded)
        TextView mLoaded;

        ViewHolderFromLoading(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView() {
            if(mAllOrderBeanList.size() == 0){
                mLoading.setVisibility(View.GONE);
            }else {
                mLoading.setVisibility(View.VISIBLE);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_order_delete)
        ImageView mIvOrderDelete;
        @BindView(R.id.iv_order_pic)
        ImageView mIvOrderPic;
        @BindView(R.id.tv_product)
        TextView mTvProduct;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.again_buy)
        TextView mAgainBuy;
        @BindView(R.id.tv_desc)
        TextView mTvDesc;
        @BindView(R.id.tv_pay)
        TextView mTvPay;
        private OrderInfoBean.OrderListBean mOrderListBean;
        private String mPic;

        @OnClick({R.id.iv_order_delete, R.id.again_buy})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_order_delete:
                    deleteOrder();
                    break;
                case R.id.again_buy:
                    Toast.makeText(mContext, "商品已过期", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        private void deleteOrder() {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("确认删除吗？");
            builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String orderId = mOrderListBean.getOrderId();
                    Log.d(TAG, "deleteOrder: " + orderId);
                    Call<OrderInfoBean> call = (Call<OrderInfoBean>) RetrofitFactory.getInstance().listCancelOrder(PreferenceUtils.getUserId(mContext), mOrderListBean.getOrderId());
                    call.enqueue(new Callback<OrderInfoBean>() {
                        @Override
                        public void onResponse(Call<OrderInfoBean> call, Response<OrderInfoBean> response) {
                            if ("orderCancel".equals(response.body().getResponse())) {
                                Log.d(TAG, "onResponse: " + response.body().getResponse());
//                                Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
                                mAllOrderBeanList.remove(mOrderListBean);
                                notifyDataSetChanged();
                            } else {
//                                Toast.makeText(mContext, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OrderInfoBean> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                        }
                    });
                }
            });
            builder.show();

        }

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(OrderInfoBean.OrderListBean orderInfoBean) {
//            String url = orderInfoBean.getIcon();
//            Picasso.with(mContext).load(url).into(mIvIcon);
            if (mType == 3) {
                mIvOrderDelete.setVisibility(View.GONE);
            }
            mOrderListBean = orderInfoBean;
            mTvDesc.setText(orderInfoBean.getStatus());

            mTvProduct.setText("订单编号:" + orderInfoBean.getOrderId());

            mTvPay.setText("￥" + orderInfoBean.getPrice());
            mTvTime.setText(TimeUtil.stampToDate(orderInfoBean.getTime()));
            Glide.with(mContext).load(Constant.BASE_URL + getImage()).placeholder(R.mipmap.default_pic).into(mIvOrderPic);
        }
        public String getImage() {

            Call<OrderDetailBean> call = (Call<OrderDetailBean>) RetrofitFactory.getInstance().listOrderDetail(PreferenceUtils.getUserId(mContext), mOrderListBean.getOrderId());
            call.enqueue(new Callback<OrderDetailBean>() {
                @Override
                public void onResponse(Call<OrderDetailBean> call, Response<OrderDetailBean> response) {
                    if (response.body().getProductList() != null) {
//                        Random random = new Random();
//                        int size = response.body().getProductList().size();
                        mPic = response.body().getProductList().get(0).getProduct().getPic();

                    } else {
//                        Toast.makeText(mContext, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<OrderDetailBean> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
            return mPic;
        }
    }
}
