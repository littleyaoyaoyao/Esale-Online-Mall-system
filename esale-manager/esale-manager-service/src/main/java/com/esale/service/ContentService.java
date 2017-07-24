package com.esale.service;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;
import com.esale.pojo.TbContent;

public interface ContentService {
	
	/**
	 * 利用pageHelper 显示内容列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGridResult getContentList(int page , int rows);
	
	/**
	 * 添加内容
	 * @param tbContent
	 * @return
	 */
	EsaleResult insertContent(TbContent tbContent);
}
