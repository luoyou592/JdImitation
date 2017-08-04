package com.young.jdmall.bean;

/**
 * Created by 25505 on 2017/8/4.
 */

public class CollectInfoBean {

    /**
     * error : 当前商品已经添加过收藏
     * error_code : 1535
     * response : error
     */

    private String error;
    private String error_code;
    private String response;

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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
