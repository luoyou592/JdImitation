package com.young.jdmall.bean;

import java.util.List;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class TopicInfoBean {

    /**
     * response : topic
     * topic : [{"id":2,"name":"儿童玩具聚划算","pic":"/images/topic/2.jpg"},{"id":3,"name":"尿不湿大酬宾","pic":"/images/topic/3.png"},{"id":4,"name":"美女","pic":"/images/topic/4.png"},{"id":5,"name":"蝴蝶","pic":"/images/topic/5.png"},{"id":6,"name":"黄昏下的城市","pic":"/images/topic/6.png"},{"id":7,"name":"激情冲浪","pic":"/images/topic/7.png"},{"id":8,"name":"润微","pic":"/images/topic/8.png"},{"id":9,"name":"美倪","pic":"/images/topic/9.png"}]
     */

    private String response;
    private List<TopicBean> topic;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<TopicBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    public static class TopicBean {
        /**
         * id : 2
         * name : 儿童玩具聚划算
         * pic : /images/topic/2.jpg
         */

        private int id;
        private String name;
        private String pic;

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
    }
}
