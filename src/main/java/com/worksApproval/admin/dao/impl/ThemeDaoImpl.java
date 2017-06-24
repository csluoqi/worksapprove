package com.worksApproval.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.ThemeDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.WorksTheme;

@Repository
public class ThemeDaoImpl extends AbstractHibernateDao<WorksTheme> implements ThemeDao{

	/**
	 * 必须写
	 */
	public ThemeDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(WorksTheme.class);
	}

	 
}
