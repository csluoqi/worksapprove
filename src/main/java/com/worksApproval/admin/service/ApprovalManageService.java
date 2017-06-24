package com.worksApproval.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.ApprovalManage;

@Service
@Transactional
public interface ApprovalManageService extends BaseDao<ApprovalManage> {

 
}