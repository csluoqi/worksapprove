package com.worksApproval.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.worksApproval.admin.dao.PageDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.PageInfo;
import com.worksApproval.admin.service.PageService;
import com.worksApproval.admin.service.common.AbstractService;


@Service
public class PageServiceImpl extends AbstractService<PageInfo> implements PageService
{
	@Resource
	private PageDao pageDao;
	@Override
	protected BaseDao<PageInfo> getDao() {
		// TODO Auto-generated method stub
		return this.pageDao;
	}
	
}
