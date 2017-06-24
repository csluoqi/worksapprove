package com.worksApproval.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksApproval.admin.dao.AccountDao;
import com.worksApproval.admin.dao.ApprovalInfoDao;
import com.worksApproval.admin.dao.ThemeDao;
import com.worksApproval.admin.dao.WorksDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.ApprovalInfo;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.service.AccountService;
import com.worksApproval.admin.service.ApprovalInfoService;
import com.worksApproval.admin.service.WorksService;
import com.worksApproval.admin.service.common.AbstractService;


@Service
public class ApprovalInfoServiceImpl extends AbstractService<ApprovalInfo> implements ApprovalInfoService
{
	@Resource
	private ApprovalInfoDao approvalInfoDao;
	@Override
	protected BaseDao<ApprovalInfo> getDao() {
		// TODO Auto-generated method stub
		return this.approvalInfoDao;
	}
	
}
