package com.esale.sso.service;

import com.esale.common.pojo.EsaleResult;
import com.esale.pojo.TbUser;

public interface RegisterService {
	public EsaleResult checkData(String param , int type);
	public EsaleResult register(TbUser user);
}
