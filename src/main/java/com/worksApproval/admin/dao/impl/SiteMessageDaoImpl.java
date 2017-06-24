package com.worksApproval.admin.dao.impl;


import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.SiteMessageDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.SiteMessage;

@Repository()
public class SiteMessageDaoImpl extends AbstractHibernateDao<SiteMessage> implements SiteMessageDao
{
	public SiteMessageDaoImpl() {
		super();
		setClazz(SiteMessage.class);
	}

/*	@Override
	public UserInfo portalLogin(UserInfo userInfo) {
		// username
		// password
		UserInfo usr = null;
		String hql = "select c from UserInfo c where c.username = ?0 and "
				+ "c.password = ?1";
		List<UserInfo>  userInfos= this.find(hql, userInfo.getUsername(),userInfo.getPassword());
		if(userInfos!=null && !userInfos.isEmpty())
		{
			usr = userInfos.get(0);
		}
		return usr;
	}
	@Override
	public UserInfo findUser(UserInfo userInfo) {
		// username
		// password
		UserInfo usr = null;
		String hql = "select c from UserInfo c where c.username = ?0 ";
		List<UserInfo>  userInfos= this.find(hql, userInfo.getUsername());
		if(userInfos!=null && !userInfos.isEmpty())
		{
			usr = userInfos.get(0);
		}
		return usr;
	}*/
	
}
