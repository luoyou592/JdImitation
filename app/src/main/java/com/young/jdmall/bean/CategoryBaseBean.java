package com.young.jdmall.bean;

import java.util.List;

public class CategoryBaseBean {
	
	private String response;
	private List<CategoryBean> category;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<CategoryBean> getCategory() {
		return category;
	}

	public void setCategory(List<CategoryBean> category) {
		this.category = category;
	}

	public static class CategoryBean {
		/**
		 * id : 1
		 * isLeafNode : false
		 * name : 妈妈专区
		 * parentId : 0
		 * pic : /images/category/ym.png
		 * tag : 妈妈内衣  祛纹纤体
		 */
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
//	private int id;//id
//	private Boolean isLeafNode;
//	private String name;//分类名
//	private int parentId;
//	private String pic;//图片
//	private String tag;
//
//	private List<Product> products;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//
//	public Boolean getIsLeafNode() {
//		return isLeafNode;
//	}
//
//	public void setIsLeafNode(Boolean isLeafNode) {
//		this.isLeafNode = isLeafNode;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(int parentId) {
//		this.parentId = parentId;
//	}
//
//	public String getPic() {
//		return pic;
//	}
//
//	public void setPic(String pic) {
//		this.pic = pic;
//	}
//
//	public String getTag() {
//		return tag;
//	}
//
//	public void setTag(String tag) {
//		this.tag = tag;
//	}
//
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//

	
	

}
