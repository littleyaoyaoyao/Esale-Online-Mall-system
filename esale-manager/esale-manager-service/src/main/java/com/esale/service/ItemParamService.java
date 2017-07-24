package com.esale.service;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;

public interface ItemParamService {
	
	/**
	 * 规格参数列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGridResult getItemParamList(int page, int rows);
	
	
	EsaleResult getItemParamByCid(Long cid);
	
	/**
	 * 添加规格参数模板
	 * @param cid
	 * @param paramData
	 * @return
	 */
	EsaleResult insertItemParam(Long cid , String paramData);
	
}
