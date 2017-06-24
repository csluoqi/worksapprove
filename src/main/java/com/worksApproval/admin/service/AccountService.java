package com.worksApproval.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;

@Service
@Transactional
public interface AccountService extends BaseDao<UserInfo> {
	
	public UserInfo portalLogin(UserInfo userInfo);
	public UserInfo findUser(UserInfo userInfo);
	public void createUsr(UserInfo userInfo);
	
}