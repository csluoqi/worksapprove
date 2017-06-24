package com.worksApproval.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.ApprovalManage;
import com.worksApproval.admin.entity.WorksTheme;

@Service
@Transactional
public interface ThemeService extends BaseDao<WorksTheme> {
	/**
	 * 获得所有的主题,在门户页面用到
	 * @return
	 */
public List<Object[]> getThemeForPortal();
public ApprovalManage approvalCommentDetail(Long id);
}