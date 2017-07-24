package com.esale.service;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;
import com.esale.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId);
	
	EasyUIDataGridResult getItemList(int page, int rows);
	
	EsaleResult creatItem(TbItem item , String desc);
}
