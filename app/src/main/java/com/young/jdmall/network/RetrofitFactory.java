package com.young.jdmall.network;

import com.young.jdmall.app.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitFactory
 * Created by luoyou on 2017/8/1.
 * 请求网络套路：
     Observable<HomeInfoBean> homeObservable = RetrofitFactory.getInstance().listhome【这里是service类所写的接口】
    homeObservable.compose(compose(this.<HomeInfoBean>bindToLifecycle())).subscribe(new BaseObserver<HomeInfoBean>(getActivity()) {
    @Override
    protected void onHandleSuccess(HomeInfoBean homeInfoBean) {【这个方法表示接收成功回调】
    Log.d("luoyou", "homeimgurl"+homeInfoBean.getResponse());
    }
    });
 *
 */
public class RetrofitFactory {

    private static final long TIMEOUT = 30;

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置【设置请求超时之类】
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)  //30秒
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)  //30秒
            .build();

    private static JDMallService retrofitService = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create())
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(JDMallService.class);

    public static JDMallService getInstance() {
        return retrofitService;
    }
}
