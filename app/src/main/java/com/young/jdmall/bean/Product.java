package com.young.jdmall.bean;

import java.util.List;

public class Product {
	private int id;
	private String name;
	private float marketPrice;
	private float price;
	private float limitPrice;
	private int leftTime;
	private float score;
	private boolean available;
	private int buyLimit;
	private String productProm;
	private int commentCount;
	private String inventoryArea;
	private String number;
	private String upLimit;
	private int isHot;
	private int isNew;
	private int saleNum;
	private long shelves;
	private String productDesc;
	private String pic;
	private List<String> pics;
	private List<String> bigPic;
	private String product_property_id;
	private List<ProductPropertyBean> productProperty;
	private String productPropertyId;

	public String getProduct_property_id() {
		return product_property_id;
	}

	public void setProduct_property_id(String product_property_id) {
		this.product_property_id = product_property_id;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public List<String> getBigPic() {
		return bigPic;
	}

	public void setBigPic(List<String> bigPic) {
		this.bigPic = bigPic;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}


	public long getShelves() {
		return shelves;
	}

	public void setShelves(long shelves) {
		this.shelves = shelves;
	}

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

	public float getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}

	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(int buyLimit) {
		this.buyLimit = buyLimit;
	}

	public String getProductProm() {
		return productProm;
	}

	public void setProductProm(String productProm) {
		this.productProm = productProm;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getInventoryArea() {
		return inventoryArea;
	}

	public void setInventoryArea(String inventoryArea) {
		this.inventoryArea = inventoryArea;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUpLimit() {
		return upLimit;
	}

	public void setUpLimit(String upLimit) {
		this.upLimit = upLimit;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public List<ProductPropertyBean> getProductProperty() {
		return productProperty;
	}

	public void setProductProperty(List<ProductPropertyBean> productProperty) {
		this.productProperty = productProperty;
	}

	public String getProductPropertyId() {
		return productPropertyId;
	}

	public void setProductPropertyId(String productPropertyId) {
		this.productPropertyId = productPropertyId;
	}

}
