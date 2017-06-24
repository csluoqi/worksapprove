package com.worksApproval.admin.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.SiteMessage;

@Service
@Transactional
public interface SiteMessageService extends BaseDao<SiteMessage> {
	/**
	 * 查询消息并更新
	 * @param usrId
	 * @return
	 */
	public List<SiteMessage> getAuthorMsgList(Long usrId);
	
}