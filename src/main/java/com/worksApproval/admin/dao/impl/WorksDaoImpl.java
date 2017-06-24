package com.worksApproval.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.ThemeDao;
import com.worksApproval.admin.dao.WorksDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.entity.WorksTheme;

@Repository
public class WorksDaoImpl extends AbstractHibernateDao<Works> implements WorksDao{

	/**
	 * 必须写
	 */
	public WorksDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(Works.class);
	}

	 
}
