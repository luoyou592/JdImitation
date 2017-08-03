package com.young.jdmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 * 包名:com.example.administrator.jdx.Modle
 * 时间:2017/7/30
 */

public class CategoryInfoBean {

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

        private int id;
        private boolean isLeafNode;
        private String name;
        private int parentId;
        private String pic;
        private String tag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsLeafNode() {
            return isLeafNode;
        }

        public void setIsLeafNode(boolean isLeafNode) {
            this.isLeafNode = isLeafNode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }


    }





}
