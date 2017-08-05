package com.young.jdmall.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.utils.IconUtil;
import com.young.jdmall.ui.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 20:49
 *  描述：    TODO
 */
public class AccountSettingActivity extends BaseActivity {


    @BindView(R.id.iv_signOrReg)
    ImageView mIvSignOrReg;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_user)
    TextView mTvUser;
    @BindView(R.id.iv_plus)
    ImageView mIvPlus;
    @BindView(R.id.iv_xb)
    ImageView mIvXb;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout mRlAddress;
    @BindView(R.id.iv_zp)
    ImageView mIvZp;
    @BindView(R.id.iv_sm)
    ImageView mIvSm;
    @BindView(R.id.iv_accountSecu)
    ImageView mIvAccountSecu;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_myself)
    ImageView mIvMyself;
    @BindView(R.id.bt_unregist)
    Button mBtUnregist;
    @BindView(R.id.rl_login)
    RelativeLayout mRlLogin;
    @BindView(R.id.rl_help)
    RelativeLayout mRlHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    public void init() {
        if (!"".equals(PreferenceUtils.getUserId(this))) {
            mTvUser.setText(PreferenceUtils.getUserName(this));
            mBtUnregist.setVisibility(View.VISIBLE);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.huaji);
            Bitmap roundedCornerBitmap = IconUtil.getRoundedCornerBitmap(bitmap);
            mIvSignOrReg.setImageBitmap(roundedCornerBitmap);
        }
    }

    @OnClick({R.id.iv_back, R.id.rl_address, R.id.bt_unregist, R.id.rl_login, R.id.rl_help})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_address:
                if(!"".equals(PreferenceUtils.getUserId(this))){

                    Intent intent = new Intent(this, RecepitAddressActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.bt_unregist:
                isUnregist();
                break;
            case R.id.rl_login:
                if("".equals(PreferenceUtils.getUserId(this))){
                    Intent intent2 = new Intent(this, LoginActivity.class);
                    startActivity(intent2);

                }
                break;
            case R.id.rl_help:
                if("".equals(PreferenceUtils.getUserId(this))){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(this, HelpCenterActivity.class);
                    startActivity(intent);
                }
                break;

        }
    }

    public void isUnregist() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认退出吗？");
        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Observable<LoginInfoBean> newsObservable = RetrofitFactory.getInstance().unRegist(PreferenceUtils.getUserId(AccountSettingActivity.this));
                newsObservable.compose(compose(AccountSettingActivity.this.<LoginInfoBean>bindToLifecycle())).subscribe(new BaseObserver<LoginInfoBean>(AccountSettingActivity.this) {
                    @Override
                    protected void onHandleSuccess(LoginInfoBean loginInfoBean) {
                        if("logout".equals(loginInfoBean.getResponse())){
//                            PreferenceUtils.setUserName(AccountSettingActivity.this, "");
                            PreferenceUtils.setUserId(AccountSettingActivity.this, "");
                            PreferenceUtils.setRegistSuccess(AccountSettingActivity.this, false);
                            finish();
                        }


                    }

                });

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
