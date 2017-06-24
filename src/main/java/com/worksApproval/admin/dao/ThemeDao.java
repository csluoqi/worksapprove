package com.worksApproval.admin.dao;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.WorksTheme;

public interface ThemeDao extends BaseDao<WorksTheme>{
	/**
	 * 判断主题名称是否重复
	 * @param worksTheme worksTheme对象
	 * @return true ,是重复主题,false,不是重复主题
	 */
	//public boolean isRepeate(WorksTheme worksTheme);  
}
