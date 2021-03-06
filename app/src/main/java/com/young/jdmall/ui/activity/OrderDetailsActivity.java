package com.young.jdmall.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.OrderDetailBean;
import com.young.jdmall.bean.OrderInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.utils.PreferenceUtils;
import com.young.jdmall.ui.utils.TimeUtil;
import com.young.jdmall.ui.view.GoodsDetailsImages;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BjyJyk on 2017/8/4.
 */
/* Observable<OrderDetailBean> listOrderDetail = RetrofitFactory.getInstance().listOrderDetail(userid, orderid);
        listOrderDetail.compose(compose(this.<OrderDetailBean>bindToLifecycle())).subscribe(new BaseObserver<OrderDetailBean>(this) {
            @Override
            protected void onHandleSuccess(OrderDetailBean orderDetailBean) {
                mOrderDetailBean = orderDetailBean;
                setData(mOrderDetailBean);
            }
        });*/
/*String orderid = "098593";
        String userid = "20428";*/

public class OrderDetailsActivity extends BaseActivity {
    private static final String TAG = "OrderDetailsActivity";
    @BindView(R.id.order_back_icon1)
    ImageView mOrderBackIcon1;
    @BindView(R.id.order_user_name1)
    TextView mOrderUserName1;
    @BindView(R.id.order_phone1)
    TextView mOrderPhone1;
    @BindView(R.id.order_address1)
    TextView mOrderAddress1;
    @BindView(R.id.ll_address_mode2)
    LinearLayout mLlAddressMode2;
    @BindView(R.id.textView1)
    TextView mTextView1;
    @BindView(R.id.goods_details1)
    ScrollView mGoodsDetails1;
    @BindView(R.id.order_number1)
    TextView mOrderNumber1;
    @BindView(R.id.order_time1)
    TextView mOrderTime1;
    @BindView(R.id.payway)
    TextView mPayway;
    @BindView(R.id.fapiao_style)
    TextView mFapiaoStyle;
    @BindView(R.id.delete_button)
    TextView mDeleteButton;
    @BindView(R.id.order_all_pay1)
    TextView mOrderAllPay1;


    @BindView(R.id.order_type)
    TextView mOrderType;
    @BindView(R.id.ll_goods_details)
    LinearLayout mLlGoodsDetails;
    @BindView(R.id.textView)
    TextView mTextView;

    private OrderDetailBean mOrderDetailBean;
    private int mType = 0;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        mContext = this;
        ButterKnife.bind(this);
        init1();
        initData();
    }

    private void init1() {

        mType = getIntent().getIntExtra("type", 0);

    }

    /**
     * 订单类型
     *
     * @return
     */
    private String getOrderType() {
        switch (mType) {
            case 1:
                return "近一个月";

            case 2:
                return "一个月前";

            case 3:
                return "已取消";

        }
        return "";
    }

    private void initData() {
        String orderId = getIntent().getStringExtra("orderId");

//OrderDetailBean
        Observable<OrderDetailBean> listOrderDetail = RetrofitFactory.getInstance().listOrderDetail(PreferenceUtils.getUserId(this), orderId);
        listOrderDetail.compose(compose(this.<OrderDetailBean>bindToLifecycle())).subscribe(new BaseObserver<OrderDetailBean>(this) {
            @Override
            protected void onHandleSuccess(OrderDetailBean orderDetailBean) {
                if (orderDetailBean.getProductList() != null) {
                    setData(orderDetailBean);
                    setPic(orderDetailBean);
                }

            }
        });
    }

    private void setPic(OrderDetailBean orderDetailBean) {
        if (orderDetailBean != null) {
            for (int i = 0; i < orderDetailBean.getProductList().size(); i++) {
            /*    String pic = Constant.IMAGE_URL + orderDetailBean.getProductList().get(i).getProduct().getPic();
                Log.e(TAG, "setPic: " + pic);
                ImageView imageView = new ImageView(mContext);
                Glide.with(mContext).load(pic).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.dp_180), getResources().getDimensionPixelSize(R.dimen.dp_180));*/
               // mLlGoodsDetails.addView(imageView, layoutParams);
                String pic = Constant.IMAGE_URL + orderDetailBean.getProductList().get(i).getProduct().getPic();
                String name = orderDetailBean.getProductList().get(i).getProduct().getName();
                int price = orderDetailBean.getProductList().get(i).getProduct().getPrice();
                GoodsDetailsImages goodsDetailsImages = new GoodsDetailsImages(mContext,null,pic,name,"￥"+price);
                mLlGoodsDetails.addView(goodsDetailsImages );

            }
        }

    }

    private String getOrderFaPiaoType(OrderDetailBean orderDetailBean) {
        String type = orderDetailBean.getDeliveryInfo().getType();
        switch (type) {
            case "1":
                return "纸质发票";
            case "2":
                return "电子发票";
            case "3":
                return "增值税发票";
        }
        return "";
    }


    private String getOrderPayWayType(OrderDetailBean orderDetailBean) {
        int type = orderDetailBean.getPaymentInfo().getType();
        switch (type) {
            case 1:
                return "在线支付";
            case 2:
                return "货到付款";
            case 3:
                return "公司转账";
        }
        return "";
    }

    private void setData(OrderDetailBean orderDetailBean) {
        mOrderDetailBean = orderDetailBean;
        mOrderUserName1.setText(orderDetailBean.getAddressInfo().getName());
        mOrderAddress1.setText("地址"+ orderDetailBean.getAddressInfo().getAddressArea() + orderDetailBean.getAddressInfo().getAddressDetail());
        String time = orderDetailBean.getOrderInfo().getTime();
        String result = TimeUtil.stampToDate(time);
        mOrderTime1.setText(result);
        mOrderAllPay1.setText("￥" + orderDetailBean.getCheckoutAddup().getTotalPrice());
        mOrderNumber1.setText("" + orderDetailBean.getOrderInfo().getOrderId());
        mOrderPhone1.setText("" + orderDetailBean.getAddressInfo().getId());

        //需要判断
        mFapiaoStyle.setText(getOrderFaPiaoType(orderDetailBean));
        mPayway.setText(getOrderPayWayType(orderDetailBean));
        if (mType != 0) {
            mOrderType.setText(getOrderType());
        }


    }

    @OnClick({R.id.order_back_icon1, R.id.delete_button, R.id.again_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_back_icon1:
                finish();
                break;
            case R.id.delete_button:
//                Toast.makeText(mContext, "该商品已过期，无法删除", Toast.LENGTH_SHORT).show();
                deleteOrder();
                break;
            case R.id.again_buy:
                Toast.makeText(mContext, "该商品已下架，无法购买", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void deleteOrder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("确认删除吗？");
        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String orderId = mOrderDetailBean.getOrderInfo().getOrderId();
                Log.d(TAG, "deleteOrder: " + orderId);
                Call<OrderInfoBean> call = (Call<OrderInfoBean>) RetrofitFactory.getInstance().listCancelOrder(PreferenceUtils.getUserId(mContext), orderId);
                call.enqueue(new Callback<OrderInfoBean>() {
                    @Override
                    public void onResponse(Call<OrderInfoBean> call, Response<OrderInfoBean> response) {
                        if ("orderCancel".equals(response.body().getResponse())) {
                            Log.d(TAG, "onResponse: " + response.body().getResponse());
//                                Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
                            finish();
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
}
