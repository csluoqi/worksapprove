package com.worksApproval.admin.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.jdbc.Buffer;
import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.constant.SessionUtil;
import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.constant.UsrRoleConst;
import com.worksApproval.admin.constant.WorksStatusConst;
import com.worksApproval.admin.dao.impl.CommonService;
import com.worksApproval.admin.entity.PageInfo;
import com.worksApproval.admin.entity.SiteMessage;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.UserRole;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.entity.WorksTheme;
import com.worksApproval.admin.service.AccountService;
import com.worksApproval.admin.service.RoleService;
import com.worksApproval.admin.service.SiteMessageService;
import com.worksApproval.admin.service.ThemeService;
import com.worksApproval.admin.service.WorksService;
import com.worksApproval.admin.service.impl.UserService;

/**
 * 用户管理
 * 
 * @author rocky
 */
@Controller
public class SiteMessageController {
	
	@Resource
	private AccountService accountService;
	@Resource	
	private RoleService roleService;
	@Resource
	private CommonService commonService;
	
	@Resource
	private WorksService worksService;
	@Resource
	private SiteMessageService siteMessageService;
	@ResponseBody
	@RequestMapping("/approvalorMsgCount")
	public String[] approvalorMsgCount(HttpServletRequest request)
	{		
			StringBuffer hql = new StringBuffer(
					"select c from Works c,ApprovalManage d where d.works.id = c.id ");
			Object obj[] = new Object[2];
			UserInfo usr = SessionUtil.getUsrFromSession(request);
			List<Works> works = null;
			if(usr==null)
			{
				return new String[]{0+""};
			}
			if (usr.getUserRole().getId() == UsrRoleConst.ROLE_JUNIOR) {
				hql.append("and d.junior.approver.id=?0 and c.status = ?1");
				//+WorksStatusConst.WORKS_STATUS_JUNIOR
				obj[0] = usr.getId();
				obj[1] = WorksStatusConst.WORKS_STATUS_JUNIOR;
			} else if (usr.getUserRole().getId() == UsrRoleConst.ROLE_MIDDLE) {
				hql.append("and d.middle.approver.id=?0 and c.status = ?1");
				obj[0] = usr.getId();
				obj[1] = WorksStatusConst.WORKS_STATUS_MIDDLE;
			} else if (usr.getUserRole().getId() == UsrRoleConst.ROLE_EDITOR) {
				hql.append("and d.senior.approver.id=?0 and c.status = ?1");
				obj[0] = usr.getId();
				obj[1] = WorksStatusConst.WORKS_STATUS_SENIOR;
			}else if (usr.getUserRole().getId() == UsrRoleConst.ROLE_ADMIN) {
				hql = new StringBuffer("select c from Works c where c.status = "+WorksStatusConst.WORKS_STATUS_POST_SUCCESS);
			}else
			{
				obj[0] = 0;
				obj[1] = 0;
			}
		
			if (usr.getUserRole().getId() == UsrRoleConst.ROLE_ADMIN) {
				works = worksService.find(hql.toString());
						
			}
			else
			{
				works = worksService.find(hql.toString(),obj);	
			}
		    
			if(works==null ||works.isEmpty())
			{
				return new String[]{0+""};
			}
			//Long msgCount = worksService.findCountByHql(hql.toString());
			return new String[]{works.size()+""};
	
	}
	

	@ResponseBody
	@RequestMapping("/getAuthorMsgList")
	public List<SiteMessage> getAuthorMsgList(HttpServletRequest request)
	{		
			UserInfo usr = SessionUtil.getUsrFromSession(request);
			if(usr==null)
			{
				return null;  
			}
			return siteMessageService.getAuthorMsgList(usr.getId());
	}

	

	@ResponseBody
	@RequestMapping("/getAuthorMsgListCount")
	public String[] getAuthorMsgListCount(HttpServletRequest request)
	{		
			Object obj[] = new Object[2];
			UserInfo usr = SessionUtil.getUsrFromSession(request);
			if(usr==null)
			{
				return new String[]{"0"};  
			}
			
			StringBuffer hql = new StringBuffer(
					"select c from SiteMessage c where c.status = "+SysConstant.MSG_STATUS_NOT_READ+" and c.recipients.id = "+usr.getId());
			return new String[]{siteMessageService.find(hql.toString()).size()+""};
	}

}
