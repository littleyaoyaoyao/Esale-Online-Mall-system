package com.esale.service;

import java.util.List;

import com.esale.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getItemCatList(Long parentId);
}
