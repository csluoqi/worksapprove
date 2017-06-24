package com.worksApproval.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.dao.SiteMessageDao;
import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.SiteMessage;
import com.worksApproval.admin.service.SiteMessageService;
import com.worksApproval.admin.service.common.AbstractService;

@Service
public class SiteMessageServiceImpl extends AbstractService<SiteMessage> implements
 SiteMessageService {
	
	@Resource
	private SiteMessageDao siteMessageDao;
	@Override
	protected BaseDao<SiteMessage> getDao() {
		// TODO Auto-generated method stub
		return this.siteMessageDao;
	}
	@Override
	public List<SiteMessage> getAuthorMsgList(Long usrId) {
		StringBuffer hql = new StringBuffer(
				"select c from SiteMessage c where c.status = "+SysConstant.MSG_STATUS_NOT_READ+" and  c.recipients.id = "+usrId);
		List<SiteMessage>  msdList = siteMessageDao.find(hql.toString());
		for(SiteMessage m : msdList)
		{
			m.setStatus(SysConstant.MSG_STATUS_READ);
			siteMessageDao.update(m);
		}
		
		
		return msdList;
		

	}
}
