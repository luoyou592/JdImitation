package com.young.jdmall.bean;

public class ProductPropertyBean {
	private int id;
	private String k;
	private String v;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductPropertyBean() {
		super();
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	@Override
	public String toString() {
		return "ProductPropertyBean [k=" + k + ", v=" + v + "]";
	}
	
}
