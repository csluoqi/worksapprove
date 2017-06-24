package com.worksApproval.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.constant.WorksStatusConst;
import com.worksApproval.admin.dao.ApprovalManageDao;
import com.worksApproval.admin.dao.ThemeDao;
import com.worksApproval.admin.dao.WorksDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.ApprovalInfo;
import com.worksApproval.admin.entity.ApprovalManage;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.entity.WorksTheme;
import com.worksApproval.admin.service.ThemeService;
import com.worksApproval.admin.service.common.AbstractService;


@Service
//public class ThemeServiceImpl extends AbstractService<WorksTheme> implements ThemeService 
public class ThemeServiceImpl extends AbstractService<WorksTheme> implements ThemeService
{

	@Resource
	private ThemeDao  themeDao;
	@Resource
	private WorksDao worksDao;
	@Resource
	ApprovalManageDao approvalManageDao; 
	
	@Override
	protected BaseDao<WorksTheme> getDao() {
		// TODO Auto-generated method stub
		return this.themeDao;
	}
	
	public List<Object[]> getThemeForPortal()
	{
		List<Object[]> themes = new ArrayList<Object[]>(2);
		Object[] t = null; 
		List<WorksTheme> worksThemeList =  this.findAll();
		//List<WorksTheme> worksThemeList =  this.find("from WorksTheme c left join fetch c.works");
		//查询已经录用的稿件,然后通过循环放在themeList里面
		/*String hql = "select c from Works c where c.status = "+WorksStatusConst.WORKS_STATUS_ACCESS;
		List<Works> workslist =  worksDao.find(hql);*/
		int index = 0;
		for(WorksTheme wt : worksThemeList)
		{
			t = new Object[2];
			t[0] = wt;
			t[1] = SysConstant.getBgColor()[index++];
			themes.add(t);
		}	
		return themes;
	}
	
	/**
	 * 查询审批详情
	 * @param id 稿件id
	 */
	public ApprovalManage approvalCommentDetail(Long id)
	{
		//通过
		String hql = "Select c from ApprovalManage c where c.works.id = "+id;
		List<ApprovalManage>  approvalManages = approvalManageDao.find(hql);
		if(approvalManages!=null)
		{
			ApprovalManage a = approvalManages.get(0);
			return a;
		}
		return new ApprovalManage(); 
	}
}
