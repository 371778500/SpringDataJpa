package com.csair.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.csair.entity.Product;
import com.csair.entity.UserHeart;
import com.csair.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
   @Autowired
   private UserService userSerivce;
   
   @RequestMapping(value="/getheart",method=RequestMethod.GET)
   @ResponseBody
   public String getheart(HttpServletRequest request)
   {
	   String userid=request.getParameter("userid");
	   String productid=request.getParameter("productid");
	   List<UserHeart> list=userSerivce.getHeart(userid, productid);
	   if(list.size()==0){
		   return new UserHeart().toJson();
	   }
	   return list.get(0).toJson();
   }
   
   @RequestMapping(value="/saveheart",method=RequestMethod.GET)
   @ResponseBody
   public String saveHeart(HttpServletRequest request)
   {
	   String userid=request.getParameter("userid");
	   String productid=request.getParameter("productid");
	   String heartnum=request.getParameter("heartnum");
	   UserHeart u=new UserHeart(userid,productid,heartnum);
	   userSerivce.saveHeart(u);
	   userSerivce.setHeart(heartnum, userid, productid);
	   return "{\"flag\":\"true\"}";
   }
   
   @RequestMapping(value="/setHeart",method=RequestMethod.GET)
   @ResponseBody
   public String setHeart(HttpServletRequest request)
   {
	   String userid=request.getParameter("userid");
	   String productid=request.getParameter("productid");
	   String heartnum=request.getParameter("heartnum");
	   int result=userSerivce.setHeart(heartnum, userid, productid);
	   return "{\"flag\":\"true\"}";
   }
   
   @RequestMapping(value="/login",method=RequestMethod.POST)
   @ResponseBody
   public String login(HttpServletRequest request)
   {
	   String username=request.getParameter("username");
	   String password=request.getParameter("password");
	   
	   return userSerivce.login(username, password).toJson();
   }
   
   @RequestMapping(value="/regist",method=RequestMethod.POST)
   @ResponseBody
   public String regist(HttpServletRequest request)
   {
	   String username=request.getParameter("username");
	   String password=request.getParameter("password");
	   
	   return userSerivce.regist(username, password).toJson();
   }
   
   @RequestMapping(value="/upload",method=RequestMethod.POST)
   @ResponseBody
   public String upload(@RequestParam MultipartFile file, HttpServletRequest request)
   {
		//原始文件名
		String originalFilename=file.getOriginalFilename();
		try {
			InputStream in =file.getInputStream();
//			File img=new File("D:/JavaFrame/workspace/TestSpringJpa/WebContent/img/"+originalFilename);//可以是任何图片格式.jpg,.png等
			File img=new File("E:/BPM/apache-tomcat-8.0.9/webapps/TestSpringJpa/img/"+originalFilename);//可以是任何图片格式.jpg,.png等 
			FileOutputStream fos=new FileOutputStream(img);  
            byte[] b = new byte[1024];  
            int nRead = 0;  
            while ((nRead = in.read(b)) != -1) {  
                fos.write(b, 0, nRead);  
            } 
            fos.flush(); 
            fos.close(); 
            in.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}

	   return "{\"filename\":\""+originalFilename+"\"}";
   }
   
   @RequestMapping(value="/saveProduct",method=RequestMethod.GET)
   @ResponseBody
   public String saveProduct(HttpServletRequest request)
   {
	   String productName=request.getParameter("productName");
	   String productDet=request.getParameter("productDet");
	   String productPic=request.getParameter("productPic");
	   String userid=request.getParameter("userid");
	   Product p=new Product(userid,productName,productPic,productDet);
	   userSerivce.saveProduct(p);
	   return "{\"flag\":\"true\"}";
   }
   
   @RequestMapping(value="/queryproductbydate",method=RequestMethod.GET,produces={"text/json;charset=UTF-8"})  
   @ResponseBody
   public String queryProductByDate(HttpServletRequest request,HttpServletResponse response)
   {
	   response.setContentType("application/json;charset=UTF-8");
	   String pagestr=request.getParameter("page");
	   int page=Integer.parseInt(pagestr);
	   List<Product> listp= userSerivce.queryProductByDate(page);
	   return listToJson(listp);
   }
   
   @RequestMapping(value="/queryproductbyheart",method=RequestMethod.GET,produces={"text/json;charset=UTF-8"})
   @ResponseBody
   public String queryProductByHeart(HttpServletRequest request)
   {
	   List<Product> listp= userSerivce.queryProductByHeart(5);
	   return listToJson(listp);
   }
   
   public String listToJson(List<Product> listp){
	   StringBuffer sb=new StringBuffer("[");
	   for(Product p:listp){
		   sb.append(p.toJson());
		   sb.append(",");
	   }
	   if(listp.size()>0){
		   sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",")+1, "");
	   }
	   sb.append("]");
	return sb.toString();
   }
   
   @RequestMapping(value="/queryproductbyProductName",method=RequestMethod.GET,produces={"text/json;charset=UTF-8"})
   @ResponseBody
   public String queryProductByProductName(HttpServletRequest request)
   {
	   String pagestr=request.getParameter("SelectPbyName");
	   List<Product> listp= userSerivce.getProduct(pagestr);
	   return listToJson(listp);
   }
   
}
