package com.young.jdmall.network;

import com.young.jdmall.bean.BrandInfoBean;
import com.young.jdmall.bean.CartInfoBean;
import com.young.jdmall.bean.CategoryBaseBean;
import com.young.jdmall.bean.CollectInfoBean;
import com.young.jdmall.bean.CommentInfoBean;
import com.young.jdmall.bean.GirlInfoBean;
import com.young.jdmall.bean.HelpInfoBean;
import com.young.jdmall.bean.HelpInfoDetailBean;
import com.young.jdmall.bean.HomeInfoBean;
import com.young.jdmall.bean.HotSearchInfoBean;
import com.young.jdmall.bean.LimitbuyBean;
import com.young.jdmall.bean.LoginInfoBean;
import com.young.jdmall.bean.MessageInfoBean;
import com.young.jdmall.bean.NewsProductInfoBean;
import com.young.jdmall.bean.OrderDetailBean;
import com.young.jdmall.bean.OrderInfoBean;
import com.young.jdmall.bean.OrdersumbitBean;
import com.young.jdmall.bean.ProductBean;
import com.young.jdmall.bean.ProductDesInfoBean;
import com.young.jdmall.bean.ProductInfoBean;
import com.young.jdmall.bean.RecepitAddressBean;
import com.young.jdmall.bean.RecommendInfoBean;
import com.young.jdmall.bean.TopicInfoBean;
import com.young.jdmall.bean.UsersInfoBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<HotSearchInfoBean> listHotSearch();


    @GET("hotproduct")
    Observable<RecommendInfoBean> listRecommend(@Query("page") int page, @Query("pageNum") int pageNum, @Query("orderby") String orderby);

    //促销快报
    @GET("topic")
    Observable<TopicInfoBean> listTopic(@Query("page") int page, @Query("pageNum") int pageNum);

    //购物车
    @FormUrlEncoded
    @POST("cart")
    Observable<CartInfoBean> listCart(@Field("sku") String test);



    //登陆
    @FormUrlEncoded
    @POST("login")
    Observable<LoginInfoBean> listLogin(@Field("username") String username, @Query("password") String password);

    //限时抢购limitbuy
    @GET("limitbuy")
    Observable<LimitbuyBean> listLimitbuy(@Query("page") int page, @Query("pageNum") int pageNum);

    @GET("newproduct")
    Observable<NewsProductInfoBean> listNewsProduct(@Query("page") int page, @Query("pageNum") int pageNum, @Query("orderby") String orderby);

    //品牌
    @GET("brand")
    Observable<BrandInfoBean> listBrand();

    @FormUrlEncoded
    @POST("register")
    Observable<LoginInfoBean> listRegister(@Field("username") String username, @Field("password") String password);

    @GET("userinfo")
    Observable<UsersInfoBean> listUserInfo(@Header("userid") String userid);

    @GET("logout")
    Observable<LoginInfoBean> unRegist(@Header("userid") String userid);

    @GET("addresslist")
    Observable<RecepitAddressBean> listAddressList(@Header("userid") String userid);
    @GET("addresslist")
    Call<RecepitAddressBean> listAddressListCall(@Header("userid") String userid);
    @GET("addressdefault")
    Call<RecepitAddressBean> listAddressDefault(@Header("userid") String userid, @Query("id") String id);
    @POST("addressdelete")
    Call<RecepitAddressBean> listAddressDelete(@Header("userid") String userid, @Body RequestBody addressDelete);
    @POST("addresssave")
    Call<RecepitAddressBean> listAddressSave(@Header("userid") String userid, @Body RequestBody addressSave);

    @GET("favorites")
    Observable<NewsProductInfoBean> listCollectProduct(@Header("userid") String userid, @Query("page") int page, @Query("pageNum") int pageNum);

    @GET("help")
    Observable<HelpInfoBean> listHelpList();

    @GET("helpDetail")
    Observable<HelpInfoDetailBean> listHelpDetail(@Query("id") int id);


    //分类页面
    @GET("category")
    Observable<CategoryBaseBean> listCategory();

    //商品列表
//    http://localhost:8080/market/productlist?page=1&pageNum=10&cId=125&orderby=saleDown
    @GET("productlist")
    Observable<ProductBean> listProductList(@Query("page") int page, @Query("pageNum") int pageNum,
                                            @Query("cId") int cId, @Query("orderby") String orderby);

    //    搜索商品列表
    @GET("search")
    Observable<ProductBean> listSearch(@Query("page") int page, @Query("pageNum") int pageNum,
                                       @Query("orderby") String orderby, @Query("keyword") String keyword);

    @POST("orderlist")
    Observable<OrderInfoBean> listOrderInfo(@Header("userid") String userid, @Body RequestBody body);

    @POST("ordercancel")
    Observable<OrderInfoBean> listOrderCancel(@Header("userid") String userid, @Body RequestBody body);

    //商品详情
    @GET("product")
    Observable<ProductInfoBean> listProductInfo(@Query("pId") int id);

    //商品评论
    @GET("product/comment")
    Observable<CommentInfoBean> listComment(@Query("pId") int id, @Query("page") int page, @Query("pageNum") int pageNum);

    @GET("product/description")
    Observable<ProductDesInfoBean> listProductDes(@Query("pId") int id);

    @POST("/openapi/api/v2")
    Call<MessageInfoBean> listMessage(@Body RequestBody body);

    @GET("product/favorites")
    Observable<CollectInfoBean> listCollect(@Header("userid") String userid,@Query("pId") int id);

    @GET("orderlist")
    Call<OrderInfoBean> listOrderInfo(@Header("userid") String userid, @Query("type") int type, @Query("page") int page, @Query("pageNum") int pageNum);

    @GET("ordercancel")
    Call<OrderInfoBean> listCancelOrder(@Header("userid") String userid, @Query("orderId") String orderId);
    @GET("orderdetail")
    Call<OrderDetailBean> listOrdDetail(@Header("userid") String userid, @Query("orderId") String orderId);


    @FormUrlEncoded
    @POST("ordersumbit")
    Observable<OrdersumbitBean> Ordersumbitlist(@Header("userid") String userid, @Field("sku") String sku,
                                                @Field("addressId") int addressId, @Field("paymentType") int paymentType,
                                                @Field("deliveryType") int deliveryType, @Field("invoiceType") int invoiceType,
                                                @Field("invoiceTitle") String invoiceTitle, @Field("invoiceContent") int invoiceContent);

    @GET("getAllRecomPicByTag.jsp")
    Call<GirlInfoBean> listGirl(@Query("category") String category, @Query("tag") String tag, @Query("start") int start, @Query("len") int len);

    @GET("orderdetail")
    Observable<OrderDetailBean>  listOrderDetail(@Header("userid") String userid,@Query("orderId") String orderId);

}
