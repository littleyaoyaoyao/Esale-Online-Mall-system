package com.esale.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.common.pojo.EasyUIDataGridResult;
import com.esale.common.pojo.EsaleResult;
import com.esale.mapper.TbContentMapper;
import com.esale.pojo.TbContent;
import com.esale.pojo.TbContentExample;
import com.esale.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	public EasyUIDataGridResult getContentList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		
		//查询
		TbContentExample example = new TbContentExample();
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//获取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		
		//返回前端需要的结果集
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	
	public EsaleResult insertContent(TbContent tbContent) {
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		contentMapper.insert(tbContent);
		return EsaleResult.ok();
	}
	
}
