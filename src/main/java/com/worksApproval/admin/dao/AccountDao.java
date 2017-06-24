package com.worksApproval.admin.dao;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.WorksTheme;

public interface AccountDao extends BaseDao<UserInfo>{
	/**
	 * 判断主题名称是否重复
	 * @param worksTheme worksTheme对象
	 * @return true ,是重复主题,false,不是重复主题
	 */
	//public boolean isRepeate(WorksTheme worksTheme);
	
	/**
	 * 验证登录
	 * @param userInfo
	 */
	public UserInfo portalLogin(UserInfo userInfo);
	/**
	 * 通过用户名查找用户
	 * @param userInfo
	 * @return
	 */
	public UserInfo findUser(UserInfo userInfo);
}
