package com.young.jdmall.network;

import com.young.jdmall.bean.BrandInfoBean;
import com.young.jdmall.bean.HomeInfoBean;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.bean.RecommendInfoBean;
import com.young.jdmall.bean.TopicInfoBean;
import com.young.jdmall.bean.UsersInfoBean;

import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public interface JDMallService {

    //主页
    @GET("home")
    Observable<HomeInfoBean> listHome();

    //搜索推荐
    @GET("search/recommend")
    Observable<RecommendInfoBean> listRecommend();

    //促销快报
    @GET("topic")
    Observable<TopicInfoBean> listTopic(@Query("page") int page, @Query("pageNum") int pageNum);

    //登陆
    @FormUrlEncoded
    @POST("login")
    Observable<LoginInfoBean> listLogin(@Field("username") String username, @Query("password") String password);
    //限时抢购limitbuy
    @GET("limitbuy")
    Observable<LimitbuyBean> listLimitbuy(@Query("page") int page, @Query("pageNum") int pageNum);
    @GET("newproduct")
    Observable<NewsProductInfoBean> listNewsProduct(@Query("page") int page,@Query("pageNum") int pageNum,@Query("orderby") String orderby);
    //品牌
    @GET("brand")
    Observable<BrandInfoBean> listBrand();
    @FormUrlEncoded
    @POST("register")
    Observable<LoginInfoBean> listRegister(@Field("username") String username, @Query("password") String password);

    @GET("userinfo")
    Observable<UsersInfoBean> listUserInfo(@Header("userid") String userid);

}
