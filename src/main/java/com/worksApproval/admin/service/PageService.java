package com.worksApproval.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.PageInfo;
import com.worksApproval.admin.entity.Works;

@Service
@Transactional
public interface PageService extends BaseDao<PageInfo> {

 
}