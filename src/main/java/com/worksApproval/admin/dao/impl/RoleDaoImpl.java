package com.worksApproval.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.RoleDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.UserRole;

@Repository
public class RoleDaoImpl extends AbstractHibernateDao<UserRole> implements RoleDao{

	/**
	 * 必须写
	 */
	public RoleDaoImpl() {
		super();
		setClazz(UserRole.class);
	}

	 
}
