package com.young.jdmall.bean;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/2 0002 10:38
 *  描述：    TODO
 */
public class UsersInfoBean {

    /**
     * response : userInfo
     * userInfo : {"bonus":295,"favoritesCount":0,"level":"白金会员","orderCount":0,"userid":"153224"}
     */

    private String response;
    /**
     * bonus : 295
     * favoritesCount : 0
     * level : 白金会员
     * orderCount : 0
     * userid : 153224
     */

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
        private int bonus;
        private int favoritesCount;
        private String level;
        private int orderCount;
        private String userid;

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public int getFavoritesCount() {
            return favoritesCount;
        }

        public void setFavoritesCount(int favoritesCount) {
            this.favoritesCount = favoritesCount;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
