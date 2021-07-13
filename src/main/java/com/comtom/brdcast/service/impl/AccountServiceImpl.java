package com.comtom.brdcast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.dao.AccountDao;
import com.comtom.brdcast.model.entity.AccountEntity;
import com.comtom.brdcast.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表业务层具体实现类
 *
 * @author zengwei
 * @date 2020-08-31 14:09:53
 */
@Service("userService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;


	/**
	 * 查询详情(单条)
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult info(String account,String password) throws Exception {
		QueryWrapper<AccountEntity> queryWrapper=new QueryWrapper();
		queryWrapper.lambda()
				.eq(AccountEntity::getAccount,account)
				.eq(AccountEntity::getPassword,password);
		AccountEntity accountEntity= accountDao.selectOne(queryWrapper);
		return ApiResult.success(accountEntity);
	}


	
}
