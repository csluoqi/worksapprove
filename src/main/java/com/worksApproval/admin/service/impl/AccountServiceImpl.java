package com.worksApproval.admin.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.worksApproval.admin.dao.AccountDao;
import com.worksApproval.admin.dao.ThemeDao;
import com.worksApproval.admin.dao.WorksDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.UserRole;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.service.AccountService;
import com.worksApproval.admin.service.WorksService;
import com.worksApproval.admin.service.common.AbstractService;

@Service
public class AccountServiceImpl extends AbstractService<UserInfo> implements
		AccountService {
	@Resource
	private AccountDao accountDao;
	@Override
	protected BaseDao<UserInfo> getDao() {
		// TODO Auto-generated method stub
		return this.accountDao;
	}

	@Override
	public UserInfo portalLogin(UserInfo userInfo) {
		return accountDao.portalLogin(userInfo);
	}

	@Override
	public UserInfo findUser(UserInfo userInfo) {
		return accountDao.findUser(userInfo);
	}

	@Override
	public void createUsr(UserInfo userInfo) {
		UserRole userRole = new UserRole();
		userRole.setId(20L);//准注册
		userInfo.setUserRole(userRole);
		userInfo.setRegisterDate(new Date());
		super.create(userInfo);
	}

}
