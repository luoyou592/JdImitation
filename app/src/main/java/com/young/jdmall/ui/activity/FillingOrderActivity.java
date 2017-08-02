package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.young.jdmall.R;
import com.young.jdmall.app.Constant;
import com.young.jdmall.bean.AddressBean;
import com.young.jdmall.bean.CartInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BjyJyk on 2017/8/2.
 */

public class FillingOrderActivity extends AppCompatActivity implements View.OnClickListener {

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

    //初始化
    private void init() {
        mOrderBackIcon.setOnClickListener(this);
        mOrderCommit.setOnClickListener(this);
        mOrderMenu.setOnClickListener(this);

    }

    /**
     * 设置订单信息
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
    public void setAddressData(AddressBean data) {
        mOrderUserName.setText(data.getUserName());
        mOrderPhone.setText(data.getPhone());
        mOrderAddress.setText(data.getAddress());
    }


    /**
     * 点击跳转到支付界面
     * @param v 提交按钮
     */
    @Override
    public void onClick(View v) {
        if(v==mOrderCommit){
            Toast.makeText(this, "点击提交", Toast.LENGTH_SHORT).show();
        }else if(v==mOrderBackIcon){
            finish();
        }else if(v==mOrderMenu){
            Toast.makeText(this, "点击菜单", Toast.LENGTH_SHORT).show();
        }
    }
}
