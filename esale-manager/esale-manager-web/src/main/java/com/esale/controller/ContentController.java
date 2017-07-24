package com.esale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;
import com.esale.pojo.TbContent;
import com.esale.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Integer page , Integer rows){
		EasyUIDataGridResult result = contentService.getContentList(page, rows);
		return result;
	}

	@RequestMapping("/save")
	@ResponseBody
	public EsaleResult insertContent(TbContent tbContent){
		EsaleResult result = contentService.insertContent(tbContent);
		return result;
	}
}


