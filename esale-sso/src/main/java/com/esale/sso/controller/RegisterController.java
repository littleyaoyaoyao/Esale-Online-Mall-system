package com.esale.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esale.common.pojo.EsaleResult;
import com.esale.common.utils.ExceptionUtil;
import com.esale.pojo.TbUser;
import com.esale.sso.service.RegisterService;


@Controller
@RequestMapping("/user")
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param , @PathVariable Integer type , String callback){
		try {
			EsaleResult result = registerService.checkData(param, type);
			if (StringUtils.isNotBlank(callback)) {
				//请求为jsonp调用，需要支持
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return EsaleResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping(value="/register" ,method=RequestMethod.POST)
	@ResponseBody
	public EsaleResult register(TbUser user){
		try {
			EsaleResult result = registerService.register(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return EsaleResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
