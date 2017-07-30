package com.young.jdmall.bean;

import java.util.List;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class RecommendInfoBean {

    /**
     * response : searchrecommend
     * searchKeywords : ["女裙","帽子","时尚女裙","时尚秋装","韩版外套","情女装","女鞋","韩版棉袄"]
     */

    private String response;
    private List<String> searchKeywords;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }
}
