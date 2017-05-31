package com.csair.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csair.entity.Product;
import com.csair.entity.User;
import com.csair.entity.UserHeart;
import com.csair.repository.ProductRepository;
import com.csair.repository.UserHeartRepository;
import com.csair.repository.UserRepository;
import com.csair.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
	private UserRepository userRepository;
    
    @Autowired
	private ProductRepository productRepository;
    
    @Autowired
	private UserHeartRepository userheartRepository;
    
	
	@Override
	public User login(String username,String password) {
		List<User> userlist=userRepository.Login(username, password);
		if(userlist.size()==1){
			return userlist.get(0);
		}
		 return new User();
	}
	
	@Override
	public User regist(String username, String password) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String id = formatter.format(currentTime);
		User user=new User(id,username,password);
		userRepository.save(user);
		return user;
	}


	@Override
	public String saveProduct(Product p) {
		Product result=productRepository.save(p);
		return "true";
	}


	@Override
	public List<Product> queryProductByDate(int page) {
		int begin=(page-1)*8;
		int end=(page)*8;
		return productRepository.queryProductByDate(begin, end);
	}


	@Override
	public List<Product> queryProductByHeart(int page) {
		int begin=0;
		int end=5;
		return productRepository.queryProductByHeart(begin, end);
	}

	@Override
	public List<UserHeart> getHeart(String userid, String productid) {
		
		return userheartRepository.getHeart(userid, productid);
	}

	@Override
	public int setHeart(String heartnum, String userid, String productid) {
//		return productRepository.setHeart(heartnum,productid);
		
		Product p=productRepository.findOne(productid);
		productRepository.delete(p);
		p.setHeart(heartnum);
		productRepository.save(p);
		return 1;
	}

	@Override
	public int saveHeart(UserHeart userheart) {
		// TODO Auto-generated method stub
		userheartRepository.save(userheart);
		return 0;
	}

	@Override
	public List<Product> getProduct(String productName) {
		productName=productName==""?null:"%"+productName+"%";
		return productRepository.queryProductByProductName(productName);
	}



}
