package com.csair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/index")
public class IndexController {

	@RequestMapping(value="/index")
	public String indexLogin(){
		return "index";
	}
	
	@RequestMapping(value="/upload")
	public String indexLogin2(){
		return "upload";
	}
	
	@RequestMapping(value="/contact")
	public String indexLogin3(){
		return "contact";
	}
	@RequestMapping(value="/rules")
	public String indexLogin4(){
		return "rules";
	}
}
