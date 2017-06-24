package com.worksApproval.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksApproval.admin.dao.IUsersDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.dao.impl.UsersDao;
import com.worksApproval.admin.entity.UsersTest;
import com.worksApproval.admin.service.IUserService;
import com.worksApproval.admin.service.common.AbstractService;
@Service("userService")
public class UserService extends AbstractService<UsersTest> implements IUserService {

	@Resource(name="usersDao")
	private IUsersDao dao;
	
	@Override
	protected BaseDao<UsersTest> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	
}
