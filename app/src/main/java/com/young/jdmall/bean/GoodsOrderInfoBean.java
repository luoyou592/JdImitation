package com.young.jdmall.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 25505 on 2017/8/4.
 */
@Entity
public class GoodsOrderInfoBean {
    @Id
    private long goodsId; //商品id
    private int count;  //商品数量
    private String property; //商品属性

    @Generated(hash = 485177141)
    public GoodsOrderInfoBean(long goodsId, int count, String property) {
        this.goodsId = goodsId;
        this.count = count;
        this.property = property;
    }

    @Generated(hash = 1052982321)
    public GoodsOrderInfoBean() {
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return goodsId +":"+count+":"+property;
    }
}
