package com.csair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.csair.entity.UserHeart;

public interface UserHeartRepository extends JpaRepository<UserHeart, String>{

	@Query(value = "select * from pic_userheart where userid=?1 and productid=?2", nativeQuery = true) 
	List<UserHeart> getHeart(String userid, String productid);
	
}
