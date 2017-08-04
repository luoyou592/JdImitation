package com.young.jdmall.dao;

import com.young.jdmall.app.JDMallApplication;
import com.young.jdmall.bean.GoodsOrderInfoBean;

import java.util.List;

/**
 * Created by 25505 on 2017/8/4.
 */

public class CartDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param orderInfoBean
     */
    public static void insertCart(GoodsOrderInfoBean orderInfoBean) {
        JDMallApplication.getDaoInstant().getGoodsOrderInfoBeanDao().insertOrReplace(orderInfoBean);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteCart(long id) {
        JDMallApplication.getDaoInstant().getGoodsOrderInfoBeanDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param orderInfoBean
     */
    public static void updateCart(GoodsOrderInfoBean orderInfoBean) {
        JDMallApplication.getDaoInstant().getGoodsOrderInfoBeanDao().update(orderInfoBean);
    }

   /* *//**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     *//*
    public static List<GoodsOrderInfoBean> queryLove() {
        return JDMallApplication.getDaoInstant().getGoodsOrderInfoBeanDao().queryBuilder().where(ShopDao.Properties.Type.eq(GoodsOrderInfoBean.TYPE_LOVE)).list();
    }*/

    /**
     * 查询全部数据
     */
    public static List<GoodsOrderInfoBean> queryAll() {
        return JDMallApplication.getDaoInstant().getGoodsOrderInfoBeanDao().loadAll();
    }
    /**
     * 通过id查询商品
     */
    public static GoodsOrderInfoBean queryGood(long id){
        return JDMallApplication.getDaoInstant().getGoodsOrderInfoBeanDao().loadByRowId(id);
    }
}
