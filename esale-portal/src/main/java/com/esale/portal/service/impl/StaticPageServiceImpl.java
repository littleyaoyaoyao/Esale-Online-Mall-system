package com.esale.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.esale.common.pojo.EsaleResult;
import com.esale.portal.service.StaticPageService;

/**
 * 生成商品详情页面静态网页
 * @author YAOSIR
 *
 */
public class StaticPageServiceImpl implements StaticPageService{

	@Autowired
	
	@Override
	public EsaleResult genItemHtml(Long ItemId) {
		return null;
	}

}
