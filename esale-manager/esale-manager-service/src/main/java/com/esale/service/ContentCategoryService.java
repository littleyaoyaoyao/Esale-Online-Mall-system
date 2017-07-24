package com.esale.service;

import java.util.List;

import com.esale.common.pojo.EasyUITreeNode;
import com.esale.common.pojo.EsaleResult;

public interface ContentCategoryService {
	/**
	 * 得到类目列表
	 */
	List<EasyUITreeNode> getContentCatList(Long parentId);
	
	
	EsaleResult insertCategory(Long parentId , String name);
	
	EsaleResult renameCategory(Long id , String name);
	
	EsaleResult deleteCategory(Long parentId , Long id);
}
