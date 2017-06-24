package com.worksApproval.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.IUsersDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.UsersTest;
@Repository("usersDao")
public class UsersDao extends AbstractHibernateDao<UsersTest> implements IUsersDao
{

	public UsersDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(UsersTest.class);
	}
	
}
