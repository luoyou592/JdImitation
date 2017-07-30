package com.young.jdmall.bean;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class LoginInfoBean {

    /**
     * response : login
     * userInfo : {"userid":"153224"}
     */

    private String response;
    private UserInfoBean userInfo;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * userid : 153224
         */

        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
