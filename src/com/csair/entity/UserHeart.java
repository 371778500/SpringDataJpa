package com.csair.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pic_userheart")
public class UserHeart {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		private String id;
		
		@Column(name="userid")
		private String userid;
		
		@Column(name="productid")
		private String productid;
		
		@Column(name="heartnum")
		private String heartnum;
		
		
		public String toJson(){
			return "{\"id\":\""+id+"\",\"userid\":\"" + userid + "\",\"productid\":\""
					+ productid + "\",\"heartnum\":\"" + heartnum + "\"}";
		}
		public UserHeart(){}
		public UserHeart(String userid,String productid,String heartnum){
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateString = formatter.format(currentTime);
			this.id=dateString;
			this.userid=userid;
			this.productid=productid;
			this.heartnum=heartnum;
		}

		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getUserid() {
			return userid;
		}


		public void setUserid(String userid) {
			this.userid = userid;
		}


		public String getProductid() {
			return productid;
		}


		public void setProductid(String productid) {
			this.productid = productid;
		}


		public String getHeartnum() {
			return heartnum;
		}


		public void setHeartnum(String heartnum) {
			this.heartnum = heartnum;
		}
		
		

}
