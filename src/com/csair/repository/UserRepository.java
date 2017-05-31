package com.csair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csair.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
//	@Modifying nativeQuery=true 表示执行sql 不加该属性则表示执行hql，表名应为实体名
	@Query(value = "select id,username,password from pic_users where username=?1 and password=?2", nativeQuery = true) 
	List<User> Login(String username, String password);
	
}
