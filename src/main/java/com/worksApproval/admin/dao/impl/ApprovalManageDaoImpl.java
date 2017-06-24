package com.worksApproval.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.ApprovalManageDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.ApprovalManage;
@Repository()
public class ApprovalManageDaoImpl extends AbstractHibernateDao<ApprovalManage> implements ApprovalManageDao
{

	public ApprovalManageDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(ApprovalManage.class);
	}
	
}
