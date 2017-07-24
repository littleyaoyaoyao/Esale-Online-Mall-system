package com.esale.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Service;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;
import com.esale.common.utils.IDUtils;
import com.esale.mapper.TbItemDescMapper;
import com.esale.mapper.TbItemMapper;
import com.esale.pojo.TbItem;
import com.esale.pojo.TbItemDesc;
import com.esale.pojo.TbItemExample;
import com.esale.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 商品查询Service
 * @author YAOSIR
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	//要注入mapper
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	
	public TbItem getItemById(Long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}
	
	
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page,rows);
		
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		//返回处理结果集
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		return result;
	}


	@Override
	public EsaleResult creatItem(TbItem item, String desc) {
		//生成商品id
		long itemId = IDUtils.genItemId();
		//补全TbItem属性
		item.setId(itemId);
		//'商品状态，1-正常，2-下架，3-删除'
		item.setStatus((byte) 1);
		//创建时间和更新时间
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//插入商品表
		itemMapper.insert(item);
		//商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//插入商品描述数据
		itemDescMapper.insert(itemDesc);
		
		
		return EsaleResult.ok();
	}

}
