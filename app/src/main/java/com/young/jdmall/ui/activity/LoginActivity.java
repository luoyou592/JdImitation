package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.young.jdmall.R;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.fragment.MyFragment;
import com.young.jdmall.ui.utils.PreferenceUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 19:48
 *  描述：    TODO
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.tv_regist)
    TextView mTvRegist;
    @BindView(R.id.tv_pwd)
    TextView mTvPwd;
    @BindView(R.id.pwd_delete)
    ImageView mPwdDelete;
    private BufferedReader bfr;
    private static final String TAG = "LoginActivity";
    private FileOutputStream nameFos;
    public static int LOGIN_STATE = -1;
    private MyFragment mMyFragment;
    //    private String baseUrl = "http://localhost:8080/market/login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mMyFragment = new MyFragment();
       /* //设置保存的用户名
        try {
            File nameFile = new File(getFilesDir(), "name.txt");
            bfr = new BufferedReader(new FileReader(nameFile));
            String name = bfr.readLine();
            mEtName.setText(name);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mPwdDelete.setVisibility(View.INVISIBLE);

                    mBtLogin.setBackgroundColor(Color.parseColor("#D6D7D7"));
                    mBtLogin.setTextColor(Color.parseColor("#BABABA"));
                } else {
                    mPwdDelete.setVisibility(View.VISIBLE);
                    mBtLogin.setTextColor(Color.WHITE);
                    mBtLogin.setBackgroundColor(Color.RED);
                }
            }
        });
    }


    public void login() {
        //保存账号密码
        final String name = mEtName.getText().toString().trim();
        String pwd = mEtPassword.getText().toString().trim();
        //判断是否为空
        if (TextUtils.isEmpty(name)) {//str == null || str.length() == 0
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {//str == null || str.length() == 0
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
      /*  //都不为空  需要首先保存账号 保存到私有目录下面
        try {
            File nameFile = new File(getFilesDir(), "name.txt");
            nameFos = new FileOutputStream(nameFile);

            //写账号
            nameFos.write(name.getBytes());
            Log.d(TAG, "保存用户名成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (nameFos != null) {
                try {
                    nameFos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/

        Observable<LoginInfoBean> loginInfoBeanObservable = RetrofitFactory.getInstance().listLogin(name, pwd);
        loginInfoBeanObservable.compose(compose(this.<LoginInfoBean>bindToLifecycle())).subscribe(new BaseObserver<LoginInfoBean>(this) {
            @Override
            protected void onHandleSuccess(LoginInfoBean loginInfoBean) {

                Log.d(TAG, "onHandleSuccess: " + loginInfoBean.getResponse());
/*                if("login".equals(loginInfoBean.getResponse())){
                    Toast.makeText(LoginActivity.this, "成功登录", Toast.LENGTH_SHORT).show();
                    PreferenceUtils.setUserName(LoginActivity.this, name);
//                    JDMallApplication.sLoginInfoBean = loginInfoBean;
                    PreferenceUtils.setUserId(LoginActivity.this, loginInfoBean.getUserInfo().getUserid());
                    finish();
                }
                if(loginInfoBean.getUserInfo() == null){
                    String errorCode = loginInfoBean.getErrorCode();
                    Log.d(TAG, "onHandleSuccess: "+errorCode);
                }*/
                if (loginInfoBean.getUserInfo() != null) {
                    Toast.makeText(LoginActivity.this, "成功登录", Toast.LENGTH_SHORT).show();
                    PreferenceUtils.setUserName(LoginActivity.this, name);
                    PreferenceUtils.setUserId(LoginActivity.this, loginInfoBean.getUserInfo().getUserid());

                    finish();
                } else {
                    String errorCode = loginInfoBean.getError();
                    Toast.makeText(LoginActivity.this, errorCode, Toast.LENGTH_SHORT).show();
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
                Log.e(TAG, "onError: " + e);
            }
        });

//        loginBy(name, pwd);
        /*//2.登录请求提交
        final String data = "name="+name+"&pwd="+pwd;
        //3.发起网络请求
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL(baseUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);

                    //4.设置请求方式为post
                    conn.setRequestMethod("POST");
                    //5.设置可以通过输出流将数据传递给服务器
                    conn.setDoOutput(true);
//					conn.setDoInput(true);

                    //6.打开输出流
                    OutputStream os = conn.getOutputStream();
                    //7.通过输出流将数据传递给服务器
                    os.write(data.getBytes());

                    //8.获取服务器返回的输入流
                    InputStream is = conn.getInputStream();
                    String result = StreamUtil.parseStream(is);
                    Log.d(TAG, "result="+result);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();*/

    }



 /*   public void loginBy(final String name, String password) {
        JDMallService jdMallService = NetworkManage.getJDMallService();
       *//* Call<LoginInfoBean> loginCall = jdMallService.listLogin(name, password);
        loginCall.enqueue(new Callback<LoginInfoBean>() {
            @Override
            public void onResponse(Call<LoginInfoBean> call, Response<LoginInfoBean> response) {
//                Log.d(TAG, "onResponse: " + response.body().getUserInfo().getUserid());
                if("login".equals(response.body().getResponse())){
                    Toast.makeText(LoginActivity.this, "成功登录", Toast.LENGTH_SHORT).show();
                    PreferenceUtils.setUserName(LoginActivity.this, name);
                    finish();
                }else if("1530".equals(response.code())){
                    Toast.makeText(LoginActivity.this, "用户名不存在或密码错误", Toast.LENGTH_SHORT).show();
                }else if("1533".equals(response.body().getResponse())){
                    Toast.makeText(LoginActivity.this, "没有登录或则需要重新登录", Toast.LENGTH_SHORT).show();
                }
            }*/


    @OnClick({R.id.iv_back, R.id.bt_login, R.id.tv_regist, R.id.pwd_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.pwd_delete:
                mEtPassword.setText("");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!"".equals(PreferenceUtils.getUserName(this))) {
            mEtName.setText(PreferenceUtils.getUserName(this));
        }
        if (PreferenceUtils.getRegistSuccess(this)) {
            finish();
        }
    }


}
