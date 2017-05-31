package com.csair.service;

import java.util.List;

import com.csair.entity.Product;
import com.csair.entity.User;
import com.csair.entity.UserHeart;

public interface UserService {
    public User login(String username,String password);
    
    public User regist(String username,String password);
    
    public String saveProduct(Product p);
    
    public List<Product> queryProductByDate(int page);
    
    public List<Product> queryProductByHeart(int page);
    
    public List<UserHeart> getHeart(String userid, String productid);
    
    public int setHeart(String heartnum,String userid, String productid);
    
    public int saveHeart(UserHeart userheart);
    
    public List<Product> getProduct(String productName);
    
    
}
