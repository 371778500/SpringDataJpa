package com.csair.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="pic_users")
public class User {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	public User(){};
	
	public User(String id,String username,String password){
		this.id=id;
		this.username=username;
		this.password=password;
	};
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toJson(){
		return "{\"id\":\""+id+"\",\"username\":\"" + username + "\",\"password\":\""
				+ password + "\"}";
	}
	
}
