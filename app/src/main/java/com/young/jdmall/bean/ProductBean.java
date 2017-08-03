package com.young.jdmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 * 包名:com.young.jdmall.bean
 * 时间:2017/8/3
 */

public class ProductBean {

    private int listCount;
    private String response;
    private List<ListFilterBean> listFilter;
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

    public List<ListFilterBean> getListFilter() {
        return listFilter;
    }

    public void setListFilter(List<ListFilterBean> listFilter) {
        this.listFilter = listFilter;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ListFilterBean {

        private String key;
        private List<ValueListBean> valueList;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<ValueListBean> getValueList() {
            return valueList;
        }

        public void setValueList(List<ValueListBean> valueList) {
            this.valueList = valueList;
        }

        public static class ValueListBean {

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class ProductListBean {

        private int evaluation;
        private float goodEvaluation;
        private int commentCount;
        private int id;
        private int marketPrice;//现价
        private String name;
        private String pic;
        private int price;//原价


        public int getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(int evaluation) {
            this.evaluation = evaluation;
        }

        public float getGoodEvaluation() {
            return goodEvaluation;
        }

        public void setGoodEvaluation(float goodEvaluation) {
            this.goodEvaluation = goodEvaluation;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
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
