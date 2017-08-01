package com.young.jdmall.bean;

import java.util.List;

/**
 * Created by 25505 on 2017/8/1.
 */

public class LimitbuyBean {

    /**
     * listCount : 10
     * productList : [{"id":4,"leftTime":15000,"limitPrice":98,"name":"帽子","pic":"/images/product/detail/b1.jpg","price":168},{"id":5,"leftTime":14000,"limitPrice":68,"name":"时尚女裙","pic":"/images/product/detail/a1.jpg","price":108},{"id":6,"leftTime":13000,"limitPrice":36,"name":"时尚秋装","pic":"/images/product/detail/w2.jpg","price":52},{"id":7,"leftTime":14000,"limitPrice":120,"name":"韩版外套","pic":"/images/product/detail/qun1.jpg","price":160},{"id":8,"leftTime":14000,"limitPrice":120,"name":"情女装","pic":"/images/product/detail/q6.jpg","price":160},{"id":9,"leftTime":14000,"limitPrice":120,"name":"女鞋","pic":"/images/product/detail/q7.jpg","price":200},{"id":10,"leftTime":14000,"limitPrice":120,"name":"韩版棉袄","pic":"/images/product/detail/q8.jpg","price":160},{"id":11,"leftTime":14000,"limitPrice":120,"name":"韩版秋装","pic":"/images/product/detail/q9.jpg","price":160},{"id":12,"leftTime":14000,"limitPrice":120,"name":"女士外套","pic":"/images/product/detail/q10.jpg","price":160},{"id":13,"leftTime":14000,"limitPrice":120,"name":"时尚女装","pic":"/images/product/detail/q11.jpg","price":200}]
     * response : limitbuy
     */

    private int listCount;
    private String response;
    /**
     * id : 4
     * leftTime : 15000
     * limitPrice : 98
     * name : 帽子
     * pic : /images/product/detail/b1.jpg
     * price : 168
     */

    private List<ProductListBean> productList;

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        private int id;
        private int leftTime;
        private int limitPrice;
        private String name;
        private String pic;
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
