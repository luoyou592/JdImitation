package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.young.jdmall.network.NetworkManage.init;

/**
 * Created by BjyJyk on 2017/8/2.
 */

public class FillingOrderActivity extends AppCompatActivity {

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
    @BindView(R.id.pay_right_icon)
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filling_order);
        ButterKnife.bind(this);
        init();
    }

    private void init() {


    }


}
