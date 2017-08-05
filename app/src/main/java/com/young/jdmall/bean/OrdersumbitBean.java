package com.young.jdmall.bean;

/**
 * Created by BjyJyk on 2017/8/4.
 */
public class OrdersumbitBean {

    /**
     * orderId : 076249
     * paymentType : 1
     * price : 450
     */

    private OrderInfoBean orderInfo;
    /**
     * orderInfo : {"orderId":"076249","paymentType":1,"price":450}
     * response : orderSubmit
     */

    private String response;
    /**
     * error : 娌℃湁鐧诲綍鎴栧垯闇�瑕侀噸鏂扮櫥褰�
     * error_code : 1533
     */
    private String error;
    private String error_code;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public static class OrderInfoBean {
        private String orderId;
        private int paymentType;
        private int price;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
