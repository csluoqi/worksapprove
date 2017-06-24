package com.worksApproval.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksApproval.admin.dao.ApprovalManageDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.ApprovalManage;
import com.worksApproval.admin.service.ApprovalManageService;
import com.worksApproval.admin.service.common.AbstractService;


@Service
public class ApprovalManageServiceImpl extends AbstractService<ApprovalManage> implements ApprovalManageService
{
	@Resource
	private ApprovalManageDao approvalManageDao;
	@Override
	protected BaseDao<ApprovalManage> getDao() {
		// TODO Auto-generated method stub
		return this.approvalManageDao;
	}
	
}
