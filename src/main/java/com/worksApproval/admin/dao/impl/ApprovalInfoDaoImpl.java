package com.worksApproval.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.AccountDao;
import com.worksApproval.admin.dao.ApprovalInfoDao;
import com.worksApproval.admin.dao.IUsersDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.ApprovalInfo;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.UsersTest;
@Repository()
public class ApprovalInfoDaoImpl extends AbstractHibernateDao<ApprovalInfo> implements ApprovalInfoDao

{

	public ApprovalInfoDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(ApprovalInfo.class);
	}
	
}
