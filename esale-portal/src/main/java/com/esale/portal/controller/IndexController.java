package com.esale.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 访问首页Controller
 * @author YAOSIR
 *
 */
@Controller
public class IndexController {
	//因为在index.jsp中有欢迎页：index.html，此时会被拦截，所以直接访问localhost:8082可以访问到主页
	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}
}
