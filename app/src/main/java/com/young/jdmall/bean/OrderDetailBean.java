package com.young.jdmall.bean;

import java.util.List;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/5 0005 15:19
 *  描述：    TODO
 */
public class OrderDetailBean {

    /**
     * addressArea : 洪山区
     * addressDetail : 街道口地铁c口
     * id : 139
     * name : itcast
     */

    private AddressInfoBean addressInfo;
    /**
     * freight : 10
     * totalCount : 5
     * totalPoint : 30
     * totalPrice : 1116
     */

    private CheckoutAddupBean checkoutAddup;
    /**
     * type : 2
     */

    private DeliveryInfoBean deliveryInfo;
    /**
     * invoiceContent : content
     * invoiceTitle : hhz
     */

    private InvoiceInfoBean invoiceInfo;
    /**
     * flag : 1
     * orderId : 260092
     * status : 未处理
     * time : 1439528260115
     */

    private OrderInfoBean orderInfo;
    /**
     * type : 2
     */

    private PaymentInfoBean paymentInfo;
    /**
     * addressInfo : {"addressArea":"洪山区","addressDetail":"街道口地铁c口","id":139,"name":"itcast"}
     * checkoutAddup : {"freight":10,"totalCount":5,"totalPoint":30,"totalPrice":1116}
     * deliveryInfo : {"type":"2"}
     * invoiceInfo : {"invoiceContent":"content","invoiceTitle":"hhz"}
     * orderInfo : {"flag":1,"orderId":"260092","status":"未处理","time":"1439528260115"}
     * paymentInfo : {"type":2}
     * productList : [{"prodNum":3,"product":{"id":3,"name":"女裙","pic":"/images/product/detail/c1.jpg","price":300,"productProperty":[{"id":0,"k":"颜色","v":"红色"},{"id":1,"k":"尺码","v":"M"}]}},{"prodNum":2,"product":{"id":5,"name":"时尚女裙","pic":"/images/product/detail/a1.jpg","price":108,"productProperty":[{"id":0,"k":"颜色","v":"绿色"}]}}]
     * prom : ["促销信息一","促销信息二"]
     * response : orderDetail
     */

    private String response;
    /**
     * prodNum : 3
     * product : {"id":3,"name":"女裙","pic":"/images/product/detail/c1.jpg","price":300,"productProperty":[{"id":0,"k":"颜色","v":"红色"},{"id":1,"k":"尺码","v":"M"}]}
     */

    private List<ProductListBean> productList;
    private List<String> prom;

    public AddressInfoBean getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfoBean addressInfo) {
        this.addressInfo = addressInfo;
    }

    public CheckoutAddupBean getCheckoutAddup() {
        return checkoutAddup;
    }

    public void setCheckoutAddup(CheckoutAddupBean checkoutAddup) {
        this.checkoutAddup = checkoutAddup;
    }

    public DeliveryInfoBean getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfoBean deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public InvoiceInfoBean getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfoBean invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public PaymentInfoBean getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoBean paymentInfo) {
        this.paymentInfo = paymentInfo;
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

    public List<String> getProm() {
        return prom;
    }

    public void setProm(List<String> prom) {
        this.prom = prom;
    }

    public static class AddressInfoBean {
        private String addressArea;
        private String addressDetail;
        private int id;
        private String name;

        public String getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CheckoutAddupBean {
        private int freight;
        private int totalCount;
        private int totalPoint;
        private int totalPrice;

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPoint() {
            return totalPoint;
        }

        public void setTotalPoint(int totalPoint) {
            this.totalPoint = totalPoint;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class DeliveryInfoBean {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class InvoiceInfoBean {
        private String invoiceContent;
        private String invoiceTitle;

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }
    }

    public static class OrderInfoBean {
        private int flag;
        private String orderId;
        private String status;
        private String time;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class PaymentInfoBean {
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class ProductListBean {
        private int prodNum;
        /**
         * id : 3
         * name : 女裙
         * pic : /images/product/detail/c1.jpg
         * price : 300
         * productProperty : [{"id":0,"k":"颜色","v":"红色"},{"id":1,"k":"尺码","v":"M"}]
         */

        private ProductBean product;

        public int getProdNum() {
            return prodNum;
        }

        public void setProdNum(int prodNum) {
            this.prodNum = prodNum;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            private int id;
            private String name;
            private String pic;
            private int price;
            /**
             * id : 0
             * k : 颜色
             * v : 红色
             */

            private List<ProductPropertyBean> productProperty;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public List<ProductPropertyBean> getProductProperty() {
                return productProperty;
            }

            public void setProductProperty(List<ProductPropertyBean> productProperty) {
                this.productProperty = productProperty;
            }

            public static class ProductPropertyBean {
                private int id;
                private String k;
                private String v;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getK() {
                    return k;
                }

                public void setK(String k) {
                    this.k = k;
                }

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }
            }
        }
    }
}
