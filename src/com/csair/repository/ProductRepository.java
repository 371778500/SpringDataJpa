package com.csair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csair.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	@Query(value = "select tp.* from (select rownum as rn,t.* from (select pu.username as username,p.* from pic_product p left join pic_users pu on pu.id = p.user_id order by p.product_date desc) t ) tp where tp.rn>?1 and tp.rn<=?2", nativeQuery = true) 
	List<Product> queryProductByDate(int begin,int end);
	
	@Query(value = "select tp.* from (select rownum as rn,t.* from (select p.* from pic_product p order by p.heart+0 desc) t ) tp where tp.rn>?1 and tp.rn<=?2", nativeQuery = true) 
	List<Product> queryProductByHeart(int begin,int end);
	
	@Modifying
	@Query(value = "update pic_product set heart=?1 where id=?2", nativeQuery = true) 
	int setHeart(String heartnum,String productid);
	
	@Query(value = "select * from pic_product where PRODUCT_NAME like ?1 order by PRODUCT_DATE desc", nativeQuery = true) 
	List<Product> queryProductByProductName(String productName);
}
