package com.esale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esale.common.pojo.EasyUITreeNode;
import com.esale.common.pojo.EsaleResult;
import com.esale.service.ContentCategoryService;

/**
 * 内容分类管理controller
 * @author YAOSIR
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public EsaleResult createNode(Long parentId , String name){
		EsaleResult result = contentCategoryService.insertCategory(parentId,name);
		return result;
	}

	@RequestMapping("/update")
	@ResponseBody
	public EsaleResult renameNode(Long id , String name){
		EsaleResult result = contentCategoryService.renameCategory(id, name);
		return result;
	}
	
	@RequestMapping("/delete")
	public void deleteNode(Long parentId, Long id){
		contentCategoryService.deleteCategory(parentId, id);
	}
}
