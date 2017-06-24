package com.worksApproval.admin.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.PageDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.PageInfo;

@Repository
public class PageDaoImpl extends AbstractHibernateDao<PageInfo> implements PageDao{

	/**
	 * 必须写
	 */
	public PageDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(PageInfo.class);
	}

	@Override
	public List<PageInfo> findPageByRoleId() {
		String hql = "select * from PageInfo c ,UserRole d where c.id = d.";
		return null;
	}

	 
}
