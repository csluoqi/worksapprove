package com.worksApproval.admin.dao;

import java.util.List;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.PageInfo;

public interface PageDao extends BaseDao<PageInfo>{
	  
	/**
	 * 通过角色查询页面
	 * @return
	 */
   List<PageInfo> findPageByRoleId();
	
}
