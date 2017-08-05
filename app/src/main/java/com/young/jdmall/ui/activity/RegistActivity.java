package com.young.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.young.jdmall.R;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/2 0002 9:10
 *  描述：    TODO
 */
public class RegistActivity extends BaseActivity {
    @BindView(R.id.et_user)
    EditText mEtUser;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.et_rpwd)
    EditText mEtRpwd;
    @BindView(R.id.bt_regist)
    Button mBtRegist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    private static final String TAG = "RegistActivity";
    private void regist() {
        //请求轮播图
        final String userName = mEtUser.getText().toString().trim();
        String passWord = mEtPwd.getText().toString().trim();
        String rePassword = mEtRpwd.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {//str == null || str.length() == 0
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passWord)) {//str == null || str.length() == 0
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(rePassword) || (!rePassword.equals(passWord))) {//str == null || str.length() == 0
            Toast.makeText(this, "请重新确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        Observable<LoginInfoBean> loginInfoBeanObservable = RetrofitFactory.getInstance().listRegister(userName, passWord);
        loginInfoBeanObservable.compose(compose(this.<LoginInfoBean>bindToLifecycle())).subscribe(new BaseObserver<LoginInfoBean>(this) {
            @Override
            protected void onHandleSuccess(LoginInfoBean loginInfoBean) {

                Log.d(TAG, "onHandleSuccess: "+ loginInfoBean.getResponse());
                if("register".equals(loginInfoBean.getResponse())){
                    Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    PreferenceUtils.setUserName(RegistActivity.this, userName);
                    PreferenceUtils.setUserId(RegistActivity.this, loginInfoBean.getUserInfo().getUserid());
                    PreferenceUtils.setRegistSuccess(RegistActivity.this, true);
                    finish();
                }else {
                    Toast.makeText(RegistActivity.this, loginInfoBean.getError(), Toast.LENGTH_SHORT).show();
                }
            }
           /* @Override
            protected void onHandleSuccess(HomeInfoBean homeInfoBean) {
                //Log.d("luoyou", "homeimgurl"+homeInfoBean.getResponse());
//                mHomeRvAdapter.setHomeData(homeInfoBean.getHomeTopic());
            }*/

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(TAG, "onError: "+ e );
            }
        });


    }



    @OnClick({R.id.bt_regist,R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_regist:
                regist();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
