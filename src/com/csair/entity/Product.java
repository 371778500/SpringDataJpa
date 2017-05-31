package com.csair.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pic_product")
public class Product {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="productPic")
	private String productPic;
	
	@Column(name="productDet")
	private String productDet;
	
	@Column(name="productDate")
	private String productDate;
	
	@Column(name="heart")
	private String heart;

	public Product(){};
	
	public Product(String userId,String productName,String productPic,String productDet){
		this.userId=userId;
		this.heart="0";
		this.productName=productName;
		this.productPic=productPic;
		this.productDet=productDet;
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		this.productDate=dateString;
		this.id=dateString;
		
	};
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDet() {
		return productDet;
	}

	public void setProductDet(String productDet) {
		this.productDet = productDet;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}
	
	public String toJson(){
		return "{\"id\":\""+id+"\",\"userId\":\"" + userId + "\",\"productName\":\""
				+ productName + "\",\"productPic\":\""+productPic+"\",\"productDet\":\""+productDet+"\","
				+ "\"productDate\":\""+productDate+"\",\"heart\":\""+heart+"\"}";
	}
	
	

}
