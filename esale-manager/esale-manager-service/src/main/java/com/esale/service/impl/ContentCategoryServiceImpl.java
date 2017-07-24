package com.esale.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.common.pojo.EasyUITreeNode;
import com.esale.common.pojo.EsaleResult;
import com.esale.mapper.TbContentCategoryMapper;
import com.esale.pojo.TbContentCategory;
import com.esale.pojo.TbContentCategoryExample;
import com.esale.pojo.TbContentCategoryExample.Criteria;
import com.esale.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	/**
	 * 得到类目列表
	 */
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		
		return resultList;
	}

	/**
	 * 
	 */
	public EsaleResult insertCategory(Long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		//设置TbContentCategory
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		//插入数据的时候，因为在数据库中id是自增的，下面需要拿到id值 。 所以要将insert进行修改
		contentCategoryMapper.insert(contentCategory);
		
		//得到插入的数据的主键id
		Long id = contentCategory.getId();
		
		//将父节点查询出来,因为树形结构需要更新
		//此时的parentId就是父节点的主键id
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()){
			parentNode.setIsParent(true);
			//更新到数据库中
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		
		return EsaleResult.ok(id);
	}

	
	
	public EsaleResult renameCategory(Long id, String name) {
		TbContentCategory node = contentCategoryMapper.selectByPrimaryKey(id);
		if(node.getName() != name){
			node.setName(name);
			contentCategoryMapper.updateByPrimaryKey(node);
		}
		return EsaleResult.ok();
	}


	
	public EsaleResult deleteCategory(Long parentId, Long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//查询parentId为此id的类目，查到了则表示该类目为id类目的自类目
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		contentCategoryMapper.deleteByPrimaryKey(id);
		if(list != null){
			for (TbContentCategory tbContentCategory : list) {
				deleteCategory(tbContentCategory.getParentId(), tbContentCategory.getId());
			}
		}
		return EsaleResult.ok();
	}
	
	

}
