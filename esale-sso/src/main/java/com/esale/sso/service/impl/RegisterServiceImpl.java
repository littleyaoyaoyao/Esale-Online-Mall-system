package com.esale.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.Else;
import com.esale.common.pojo.EsaleResult;
import com.esale.mapper.TbUserMapper;
import com.esale.pojo.TbUser;
import com.esale.pojo.TbUserExample;
import com.esale.pojo.TbUserExample.Criteria;
import com.esale.sso.service.RegisterService;
/**
 * 用户数据验证接口，
 * 到tb_user中进行查询如果查询到结果返回false，查询结果为空返回true
 * @author YAOSIR
 *
 */

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private TbUserMapper userMapper;
	
	@Override
	public EsaleResult checkData(String param, int type) {
		//根据数据类型检查数据
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//1、2、3分别代表username、phone、email
		if(type == 1){
			criteria.andUsernameEqualTo(param);
		}else if(type == 2){
			criteria.andPhoneEqualTo(param);
		}else if(type == 3){
			criteria.andEmailEqualTo(param);
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if(list == null || list.isEmpty()){
			return EsaleResult.ok(true);
		}
		return EsaleResult.ok(false);
	}

}
