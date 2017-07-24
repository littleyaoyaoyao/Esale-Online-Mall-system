package com.esale.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.common.pojo.EasyUITreeNode;
import com.esale.mapper.TbItemCatMapper;
import com.esale.pojo.TbItemCat;
import com.esale.pojo.TbItemCatExample;
import com.esale.pojo.TbItemCatExample.Criteria;
import com.esale.service.ItemCatService;

/**
 * 商品分类管理Service
 * @author YAOSIR
 *
 */

@Service
//第一次写的时候忘记添加@Service，导致spring不能正常注入
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		//根据parentId查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = 	itemCatMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			//创建一个节点列表
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到列表中
			resultList.add(node);
		}
		return resultList;
	}

}
