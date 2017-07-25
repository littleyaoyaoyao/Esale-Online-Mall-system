package com.esale.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
	
	/**
	 * 验证数据
	 */
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

	/**
	 * 注册接口
	 */
	public EsaleResult register(TbUser user) {
		//校验数据：先判断是否为空，再判断数据是否重复
		//判断用户名或者密码是否为空
		if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
			return EsaleResult.build(400, "用户名或者密码为空");
		}
		
		//若用户名和密码均不为空，那么判断数据是否重复
		if(!(boolean)checkData(user.getUsername(), 1).getData()){
			return EsaleResult.build(400, "用户名重复!");
		}
		
		//如果手机号不为空的话，判断手机号码是否重复
		if(user.getPhone() != null){
			if(!(boolean)checkData(user.getPhone(),2).getData()){
				return EsaleResult.build(400, "手机号重复!");
			}
		}
		
		//如果邮箱不为空的话，判断邮箱是否重复
		if(user.getEmail() != null){
			if(!(boolean)checkData(user.getEmail(),3).getData()){
				return EsaleResult.build(400, "邮箱重复!");
			}
		}
		
		//若信息都没有问题，则插入数据
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		
		return EsaleResult.ok();
	}

}
