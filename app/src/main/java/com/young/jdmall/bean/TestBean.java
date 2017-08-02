package com.young.jdmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.Modle
 * 时间:2017/7/30
 */

public class TestBean {

    String title;

    List<stringBean> nameList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<stringBean> getNameList() {
        return nameList;
    }

    public void setNameList(List<stringBean> nameList) {
        this.nameList = nameList;
    }


    public static class stringBean{

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }





}
