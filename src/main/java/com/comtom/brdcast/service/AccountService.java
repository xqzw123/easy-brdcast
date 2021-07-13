package com.comtom.brdcast.service;

import com.comtom.brdcast.common.api.ApiResult;

/**
 * 用户表业务层接口
 * 
 * @author zengwei
 * @date 2020-08-31 14:09:53
 */
public interface AccountService {


	/**
     * 查询详情(单条)
     *
     * @param
     * @return
     * @throws Exception
     */
	ApiResult info(String account,String password) throws Exception;




}
