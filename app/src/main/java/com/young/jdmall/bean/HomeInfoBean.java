package com.young.jdmall.bean;

import java.util.List;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class HomeInfoBean {

    /**
     * homeTopic : [{"id":123,"pic":"/images/home/image1.jpg","title":"活动1"},{"id":124,"pic":"/images/home/image2.jpg","title":"活动2"},{"id":125,"pic":"/images/home/image3.jpg","title":"活动3"},{"id":126,"pic":"/images/home/image4.jpg","title":"活动1"},{"id":127,"pic":"/images/home/image5.jpg","title":"活动2"},{"id":128,"pic":"/images/home/image6.jpg","title":"活动3"},{"id":129,"pic":"/images/home/wawa.jpg","title":"充气娃娃"}]
     * response : home
     */

    private String response;
    private List<HomeTopicBean> homeTopic;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<HomeTopicBean> getHomeTopic() {
        return homeTopic;
    }

    public void setHomeTopic(List<HomeTopicBean> homeTopic) {
        this.homeTopic = homeTopic;
    }

    public static class HomeTopicBean {
        /**
         * id : 123
         * pic : /images/home/image1.jpg
         * title : 活动1
         */

        private int id;
        private String pic;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
