package com.worksApproval.admin.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.log.Log;
import com.worksApproval.admin.constant.SessionUtil;
import com.worksApproval.admin.dao.ThemeDao;
import com.worksApproval.admin.dao.WorksDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.service.WorksService;
import com.worksApproval.admin.service.common.AbstractService;


@Service
//public class ThemeServiceImpl extends AbstractService<WorksTheme> implements ThemeService 
public class WorksServiceImpl extends AbstractService<Works> implements WorksService
{
	private static Logger log = Logger.getLogger(WorksServiceImpl.class);
	@Resource
	private WorksDao worksDao;
	@Override
	protected BaseDao<Works> getDao() {
		// TODO Auto-generated method stub
		return this.worksDao;
	}
	@Override
	public boolean hasPermissionDownload(Long id, HttpServletRequest request) {
		UserInfo usr = SessionUtil.getUsrFromSession(request);
		if(usr==null)
		{
			return false;		
		}
		String hql = "select c from Works c where c.id = ?0 and c.author.id = ?1";
		List<Works> workslist = worksDao.find(hql, id,usr.getId());
		if(workslist==null||workslist.isEmpty())
		{
			return false;
		}
		log.info(workslist);
		return true;
	}

	@Override
	public List<Works> findAllFilterDeleted() {
		List<Works> worksList =   super.findAll();
		Works w = null;
		Iterator<Works> iterator = worksList.iterator();
		while(iterator.hasNext())
		{
			w = iterator.next();
			if(w.getStatus() < 0)
			{
				iterator.remove();
			}
		}
		return worksList;
	}
}
