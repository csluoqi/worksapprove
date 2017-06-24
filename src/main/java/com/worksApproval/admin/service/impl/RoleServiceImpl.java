package com.worksApproval.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksApproval.admin.dao.PageDao;
import com.worksApproval.admin.dao.RoleDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.PageInfo;
import com.worksApproval.admin.entity.UserRole;
import com.worksApproval.admin.service.PageService;
import com.worksApproval.admin.service.RoleService;
import com.worksApproval.admin.service.common.AbstractService;


@Service
public class RoleServiceImpl extends AbstractService<UserRole> implements RoleService
{
	@Resource
	private RoleDao roleDao;
	@Override
	protected BaseDao<UserRole> getDao() {
		// TODO Auto-generated method stub
		return this.roleDao;
	}
	
}
