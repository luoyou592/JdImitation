package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.young.jdmall.R;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.network.JDMallService;
import com.young.jdmall.network.NetworkManage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 19:48
 *  描述：    TODO
 */
public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    private BufferedReader bfr;
    private static final String TAG = "LoginActivity";
    private FileOutputStream nameFos;

//    private String baseUrl = "http://localhost:8080/market/login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //设置保存的用户名
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
        }
    }


    public void login() {
        //保存账号密码
        String name = mEtName.getText().toString().trim();
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
        //都不为空  需要首先保存账号 保存到私有目录下面
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
        }
        loginBy(name, pwd);
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

    public void loginBy(final String name, String password){
        JDMallService jdMallService = NetworkManage.getJDMallService();
        Call<LoginInfoBean> loginCall = jdMallService.listLogin(name, password);
        loginCall.enqueue(new Callback<LoginInfoBean>() {
            @Override
            public void onResponse(Call<LoginInfoBean> call, Response<LoginInfoBean> response) {
                Toast.makeText(LoginActivity.this, "成功登录", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("name", name);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onFailure(Call<LoginInfoBean> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_login:
                login();
                break;
        }
    }
}
