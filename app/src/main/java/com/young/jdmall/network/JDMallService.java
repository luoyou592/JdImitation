package com.young.jdmall.network;

import com.young.jdmall.bean.HomeInfoBean;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.bean.RecommendInfoBean;
import com.young.jdmall.bean.TopicInfoBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public interface JDMallService {

    //主页
    @GET("home")
    Call<HomeInfoBean> listHome();

    //搜索推荐
    @GET("search/recommend")
    Call<RecommendInfoBean> listRecommend();

    //促销快报
    @GET("topic")
    Call<TopicInfoBean> listTopic(@Query("page") int page, @Query("pageNum") int pageNum);

    //登陆
    @FormUrlEncoded
    @POST("login")
    Call<LoginInfoBean> listLogin(@Field("username") String username, @Query("password") String password);

}
