package com.young.jdmall.bean;

import java.util.List;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/3 0003 22:13
 *  描述：    TODO
 */
public class HelpInfoDetailBean {

    /**
     * helpDetailList : [{"content":"看说明书","title":"如何使用"}]
     * response : helpDetail
     */

    private String response;
    /**
     * content : 看说明书
     * title : 如何使用
     */

    private List<HelpDetailListBean> helpDetailList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<HelpDetailListBean> getHelpDetailList() {
        return helpDetailList;
    }

    public void setHelpDetailList(List<HelpDetailListBean> helpDetailList) {
        this.helpDetailList = helpDetailList;
    }

    public static class HelpDetailListBean {
        private String content;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
