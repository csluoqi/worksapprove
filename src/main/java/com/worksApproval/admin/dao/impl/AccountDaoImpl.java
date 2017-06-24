package com.worksApproval.admin.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.worksApproval.admin.dao.AccountDao;
import com.worksApproval.admin.dao.IUsersDao;
import com.worksApproval.admin.dao.common.AbstractHibernateDao;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.UsersTest;
@Repository()
public class AccountDaoImpl extends AbstractHibernateDao<UserInfo> implements AccountDao
{

	public AccountDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(UserInfo.class);
	}

	@Override
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
	}
	
}
