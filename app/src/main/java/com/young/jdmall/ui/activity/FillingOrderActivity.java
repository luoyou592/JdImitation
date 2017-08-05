package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.CartInfoBean;
import com.young.jdmall.bean.OrdersumbitBean;
import com.young.jdmall.bean.RecepitAddressBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.utils.PreferenceUtils;
import com.young.jdmall.ui.widget.PopWinPayWay;
import com.young.jdmall.ui.widget.PopWindowFaPiao;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

import static com.young.jdmall.R.id.pay_right_icon;

/**
 * Created by BjyJyk on 2017/8/2.
 */

public class FillingOrderActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "FillingOrderActivity";
    @BindView(R.id.pay_contains)
    LinearLayout mPayContains;
    @BindView(R.id.order_back_icon)
    ImageView mOrderBackIcon;
    @BindView(R.id.order_menu)
    ImageView mOrderMenu;
    @BindView(R.id.order_user_name)
    TextView mOrderUserName;
    @BindView(R.id.order_phone)
    TextView mOrderPhone;
    @BindView(R.id.order_address)
    TextView mOrderAddress;
    @BindView(R.id.ll_address_model)
    LinearLayout mLlAddressModel;
    @BindView(R.id.goods_preview)
    HorizontalScrollView mGoodsPreview;
    @BindView(R.id.tv_goods_number)
    TextView mTvGoodsNumber;
    @BindView(R.id.goods_details)
    LinearLayout mGoodsDetails;
    @BindView(pay_right_icon)
    ImageView mPayRightIcon;
    @BindView(R.id.order_pay_way)
    TextView mOrderPayWay;
    @BindView(R.id.seller_name)
    TextView mSellerName;
    @BindView(R.id.pay_right2_icon)
    ImageView mPayRight2Icon;
    @BindView(R.id.order_invoice)
    TextView mOrderInvoice;
    @BindView(R.id.pay_right3_icon)
    ImageView mPayRight3Icon;
    @BindView(R.id.order_coupon)
    TextView mOrderCoupon;
    @BindView(R.id.goods_meney)
    TextView mGoodsMeney;
    @BindView(R.id.order_freight)
    TextView mOrderFreight;
    @BindView(R.id.order_all_pay)
    TextView mOrderAllPay;
    @BindView(R.id.order_commit)
    TextView mOrderCommit;
    private static final int TOADDRESS = 1;
    private PopWindowFaPiao popWinShare;
    private PopWinPayWay mPopWinPayWay;
    private RecepitAddressBean.AddressListBean mAddressListBean;
    private int payWayType = 1;
    private int invoiceType = 1;
    private int mId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filling_order);
        ButterKnife.bind(this);
        init();
    }

    //初始化
    private void init() {
        mOrderBackIcon.setOnClickListener(this);
        mOrderCommit.setOnClickListener(this);
        mOrderMenu.setOnClickListener(this);
        mOrderInvoice.setOnClickListener(this);
        mPayContains.setOnClickListener(this);
        mLlAddressModel.setOnClickListener(this);

    }

    /**
     * 设置订单信息
     *
     * @param data
     */
    public void setData(CartInfoBean data) {
        mTvGoodsNumber.setText("共" + data.getTotalCount() + "件");
        mOrderAllPay.setText("合计：￥" + (data.getTotalPrice() - 5));
        mGoodsMeney.setText("￥" + data.getTotalPrice());
        for (int i = 0; i < data.getCart().size(); i++) {
            ImageView imageView = new ImageView(this);
            String pic = Constant.BASE_URL + data.getCart().get(i).getProduct().getPic();
            Glide.with(this).load(pic).into(imageView);
            mGoodsPreview.addView(imageView);
        }

    }

    /**
     * 设置地址
     * @param data
     */
   public void setAddressData(RecepitAddressBean.AddressListBean data) {
       mId = data.getId();
       mOrderUserName.setText(data.getName());
        mOrderPhone.setText(data.getPhoneNumber());
        mOrderAddress.setText(data.getAddressArea()+data.getAddressDetail());
    }


    /**
     * 点击跳转到支付界面
     *
     * @param v 提交按钮
     */
    @Override
    public void onClick(View v) {
        if (v == mOrderCommit) {
            long l = System.currentTimeMillis();
            commit();
        } else if (v == mOrderBackIcon) {
            finish();
        } else if (v == mOrderMenu) {
            Toast.makeText(this, "点击菜单", Toast.LENGTH_SHORT).show();
        } else if (v == mLlAddressModel) {
            Intent intent = new Intent(this, RecepitAddressActivity.class);
            startActivityForResult(intent, TOADDRESS);
        } else if (v == mOrderInvoice) {
            showPopUp(mOrderInvoice);
        } else if (v == mPayContains) {
            showPopUpPay(mPayContains);
        }
    }

    /**
     * useid   用户ID
     * sku:商品ID:数量:属性
     * addressId:地址簿ID
     * paymentType:支付方式  -
     * deliveryType:送货时间
     * invoiceType	发票类型  -
     * invoiceTitle	发票抬头   -没有
     * invoiceContent	发票内容  -  没有
     * <p>
     * <p>
     * orderId	 //订单编号
     * price   //订单金额
     * paymentType  //支付方式
     */
    private void commit() {
// TODO: 2017/8/4 网络请求数据  商品ID
        String userId = PreferenceUtils.getUserId(this);
        Observable<OrdersumbitBean> ordersumbitlist = RetrofitFactory.getInstance().Ordersumbitlist(userId, "1:3:1,2,3,4|2:2:2,3", mId,payWayType, 0,invoiceType, "", 0);
        ordersumbitlist.compose(compose(this.<OrdersumbitBean>bindToLifecycle())).subscribe(new BaseObserver<OrdersumbitBean>(this) {
            @Override
            protected void onHandleSuccess(OrdersumbitBean ordersumbitBean) {
                if (ordersumbitBean != null) {
// TODO: 2017/8/4  
                    Toast.makeText(FillingOrderActivity.this, "提交订单成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(FillingOrderActivity.this, "提交订单失败", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TOADDRESS:
                if (resultCode == RESULT_OK) {
                    mAddressListBean = (RecepitAddressBean.AddressListBean) data.getSerializableExtra("address");
                    setAddressData(mAddressListBean);
                    Toast.makeText(this, "回调成功", Toast.LENGTH_SHORT).show();
                }
        }
    }


    private void showPopUp(View v) {
        if (popWinShare == null) {
            //自定义的单击事件
            OnClickLintener paramOnClickListener = new OnClickLintener();

            popWinShare = new PopWindowFaPiao(this, paramOnClickListener, 1080, 500);
            //监听窗口的焦点事件，点击窗口外面则取消显示
            popWinShare.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        popWinShare.dismiss();
                    }
                }
            });
        }
//设置默认获取焦点
        popWinShare.setFocusable(true);
//以某个控件的x和y的偏移量位置开始显示窗口
        popWinShare.showAsDropDown(v, 0, 0);
//如果窗口存在，则更新
        popWinShare.update();
    }

    class OnClickLintener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.invoice_one:
                    invoiceType = 1;
                    popWinShare.mInvoiceOne.setBackgroundResource(R.mipmap.a3h);
                    popWinShare.mInvoiceTwo.setBackgroundResource(R.mipmap.a3g);
                    popWinShare.mInvoiceThird.setBackgroundResource(R.mipmap.a3g);
                    break;
                case R.id.invoice_two:
                    invoiceType = 2;
                    popWinShare.mInvoiceOne.setBackgroundResource(R.mipmap.a3g);
                    popWinShare.mInvoiceTwo.setBackgroundResource(R.mipmap.a3h);
                    popWinShare.mInvoiceThird.setBackgroundResource(R.mipmap.a3g);
                    break;
                case R.id.invoice_third:
                    invoiceType = 3;
                    popWinShare.mInvoiceOne.setBackgroundResource(R.mipmap.a3g);
                    popWinShare.mInvoiceTwo.setBackgroundResource(R.mipmap.a3g);
                    popWinShare.mInvoiceThird.setBackgroundResource(R.mipmap.a3h);
                    break;
                case R.id.invoice_ok:
                    switch (invoiceType) {
                        case 1:
                            mOrderInvoice.setText("明细（纸质发票）—个人");
                            break;
                        case 2:
                            mOrderInvoice.setText("明细（电子发票）—个人");
                            break;
                        case 3:
                            mOrderInvoice.setText("明细（增值税发票）—个人");
                            break;
                    }
                    popWinShare.dismiss();
                    break;

                default:
                    break;
            }

        }

    }


    private void showPopUpPay(View v) {
        if (mPopWinPayWay == null) {
            //自定义的单击事件
            OnClickPayWayLintener onClickPayWayLintener = new OnClickPayWayLintener();

            mPopWinPayWay = new PopWinPayWay(this, onClickPayWayLintener, 1080, 500);
            //监听窗口的焦点事件，点击窗口外面则取消显示
            mPopWinPayWay.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        mPopWinPayWay.dismiss();
                    }
                }
            });
        }
//设置默认获取焦点
        mPopWinPayWay.setFocusable(true);
//以某个控件的x和y的偏移量位置开始显示窗口
        mPopWinPayWay.showAsDropDown(v, 0, 0);
//如果窗口存在，则更新
        mPopWinPayWay.update();
    }

    class OnClickPayWayLintener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.payway_one:
                    payWayType = 1;
                    mPopWinPayWay.mInvoiceOne.setBackgroundResource(R.mipmap.a3h);
                    mPopWinPayWay.mInvoiceTwo.setBackgroundResource(R.mipmap.a3g);
                    mPopWinPayWay.mInvoiceThird.setBackgroundResource(R.mipmap.a3g);
                    break;
                case R.id.payway_two:
                    payWayType = 2;
                    mPopWinPayWay.mInvoiceOne.setBackgroundResource(R.mipmap.a3g);
                    mPopWinPayWay.mInvoiceTwo.setBackgroundResource(R.mipmap.a3h);
                    mPopWinPayWay.mInvoiceThird.setBackgroundResource(R.mipmap.a3g);
                    break;
                case R.id.payway_third:
                    payWayType = 3;
                    mPopWinPayWay.mInvoiceOne.setBackgroundResource(R.mipmap.a3g);
                    mPopWinPayWay.mInvoiceTwo.setBackgroundResource(R.mipmap.a3g);
                    mPopWinPayWay.mInvoiceThird.setBackgroundResource(R.mipmap.a3h);
                    break;
                case R.id.payway_ok:
                    switch (payWayType) {
                        case 1:
                            mOrderPayWay.setText("在线支付");
                            break;
                        case 2:
                            mOrderPayWay.setText("货到付款");
                            break;
                        case 3:
                            mOrderPayWay.setText("公司转账");
                            break;
                    }
                    mPopWinPayWay.dismiss();
                    break;

                default:
                    break;
            }

        }

    }

}
