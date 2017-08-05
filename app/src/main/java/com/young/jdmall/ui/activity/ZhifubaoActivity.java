package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.young.jdmall.R;
import com.young.jdmall.ui.utils.PayResult;
import com.young.jdmall.ui.utils.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：Administrator
 * 时间：2017/8/5
 * 包名：com.young.jdmall.ui.activity
 * 公司：黑马程序员
 */

public class ZhifubaoActivity extends FragmentActivity {
    //商户PID
    public static final String PARTNER = "2088011085074233";
    //商户收款账号
    public static final String SELLER = "917356107@qq.com";
    //商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL51jaxQhxW9PnWpW+nz6yJ76tp9eGFXmfGnuxMK+Pmx/qavdsewXOLBfI2OSCR39TzxwMYvCmUrnrt0fVSa7mblbNos2FnMM9ijnx8bsAAhm+i7BKhuaHMunJKH69L+D753zH3P1YIh0ly5DnAr3WPqHydp384qBvb8NS9Tay0HAgMBAAECgYB82PIVknP6fCMFXg8yPQJViIVa1ASlSpdPIXQv93FdvKABA+QI4kMBIXRUFoCT506KtK55OzzFNOLIXoQJgcXj69z0l6pmjJJgXMaBW/9rOzelot13CiGatrIrGngEZO+bCBTud/jQA598zjZ1g182tT+FLDL7GIftW2hC8GqtAQJBAN+XrYsyfL+uSmLdAVEz1vzziU1naGr10Msm1jMnnO/JYdB+84j7FSHxsQ4YOgsmeN5YVsJcVfc/CReOxknns38CQQDaEHnVPDt+Z7sqT7bN0UKh0/CrqkDTiIjhz1lJyIIoqVRoeJjJn1wlEKBV5R9gkTJutQTVU19XFtblMEnOy6p5AkEAw170rEmMSa0QoHw+d2bVtydR1QnDapqqO6kOx5oYfkm4J4eWYx4J5CQdMpSmuzF9scL85E3sa+NvnV8LEm7cHwJALtXzFPWG4bNt47yTSslzQka/Hl/G5Kginj1mtA44xnr4AihEyKlNpThY95nqj1cgOd7vVtI9W/sv1LH2aFAeIQJBAIqXbMc6xGVfuiFAJKtg+AFNMBP0UOEgMEoKo4RPFp21nBhFgL9/WYM4ZjyHUdr45rCySAqQovw4DCHLfQZC23I=";
    //支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+dY2sUIcVvT51qVvp8+sie+rafXhhV5nxp7sTCvj5sf6mr3bHsFziwXyNjkgkd/U88cDGLwplK567dH1Umu5m5WzaLNhZzDPYo58fG7AAIZvouwSobmhzLpySh+vS/g++d8x9z9WCIdJcuQ5wK91j6h8nad/OKgb2/DUvU2stBwIDAQAB";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.sum)
    TextView mSum;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PayResult payResult = new PayResult((String) msg.obj);
            String result = payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            if (TextUtils.equals(resultStatus, "9000")) {
                Toast.makeText(ZhifubaoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                finish();
                FillingOrderActivity.instance.finish();
            } else {
                if (TextUtils.equals(resultStatus, "8000")) {
                    Toast.makeText(ZhifubaoActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ZhifubaoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhifubao_activity);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String sum=intent.getStringExtra("sum");
        mSum.setText("¥"+sum);
    }

    public void pay(View view) {
        String orderInfo = getOrderInfo("来自罗友便的订单", "2件衣服", "0.01");
        String sign = SignUtils.sign(orderInfo, RSA_PRIVATE);
        try {
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alignPay = new PayTask(ZhifubaoActivity.this);
                String result = alignPay.pay(payInfo);
                Message message = new Message();
                message.obj = result;
                mHandler.sendMessage(message);
            }
        };
        Thread thread = new Thread(payRunnable);
        thread.start();

    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
