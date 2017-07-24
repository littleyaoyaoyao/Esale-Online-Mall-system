package com.esale.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;
import com.esale.mapper.TbItemParamMapper;
import com.esale.pojo.TbItemParam;
import com.esale.pojo.TbItemParamExample;
import com.esale.pojo.TbItemParamExample.Criteria;
import com.esale.service.ItemParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 规格参数模板service
 * @author YAOSIR
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		
		//执行查询
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		
		//取到分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		
		//返回 ： 结果集
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		return result;
		
		
		
	}

	@Override
	public EsaleResult getItemParamByCid(Long cid) {
		
		//根据cid查询规格参数模板
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		
		//执行查询
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list != null && list.size() > 0){
			TbItemParam tbItemParam = list.get(0);
			return EsaleResult.ok(tbItemParam);
		}
		return EsaleResult.ok();
	}

	@Override
	public EsaleResult insertItemParam(Long cid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setUpdated(new Date());
		itemParam.setCreated(new Date());
		
		itemParamMapper.insert(itemParam);
		return EsaleResult.ok();
	}

}
