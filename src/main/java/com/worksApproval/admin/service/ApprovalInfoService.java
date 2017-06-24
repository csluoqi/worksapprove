package com.worksApproval.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.ApprovalInfo;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;

@Service
@Transactional
public interface ApprovalInfoService extends BaseDao<ApprovalInfo> {

 
}