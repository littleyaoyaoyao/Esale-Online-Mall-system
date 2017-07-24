package com.esale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;
import com.esale.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows){
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}

	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public EsaleResult getItemParamByCid(@PathVariable Long cid){
		EsaleResult result = itemParamService.getItemParamByCid(cid);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public EsaleResult insertItemParam(@PathVariable Long cid , String paramData){
		EsaleResult result = itemParamService.insertItemParam(cid, paramData);
		return result;
	}

}
