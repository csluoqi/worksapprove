package com.worksApproval.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.worksApproval.admin.service.ApprovalManageService;
import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.constant.SessionUtil;
import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.constant.UsrRoleConst;
import com.worksApproval.admin.constant.WorksStatusConst;
import com.worksApproval.admin.entity.ApprovalInfo;
import com.worksApproval.admin.entity.ApprovalManage;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.entity.WorksTheme;
import com.worksApproval.admin.service.AccountService;
import com.worksApproval.admin.service.ApprovalInfoService;
import com.worksApproval.admin.service.SiteMessageService;
import com.worksApproval.admin.service.ThemeService;
import com.worksApproval.admin.service.WorksService;

/**
 * 审批管理
 * 
 * @author rocky
 *
 */
@Controller
public class ApprovalController {
	private Logger log = Logger.getLogger(ApprovalController.class);
	@Resource
	private ThemeService themeService;
	@Resource
	private SiteMessageService siteMessageService;
	@Resource
	private WorksService worksService;
	@Resource
	private AccountService accountService;
	@Resource
	private ApprovalManageService approvalManageService;

	@Resource
	private ApprovalInfoService approvalInfoService;

	@RequestMapping(value = "/managementApprovalSetting.html")
	public String managementApprovalSetting(Model model) {
		String hql = "select c from Works c where c.status = "
				+ WorksStatusConst.WORKS_STATUS_POST_SUCCESS;
		List<Works> worksList = worksService.find(hql);

		System.out.println(worksList);
		model.addAttribute("worksList", worksList);// 需要加条件限制,只查询状态是投递成功的数据
		// 下拉框可以提取到jsp页面,jsp应该自定义这些基础的数据
		// System.out.println(worksThemeList);
		// 上面需要改,要加权限,这里要用

		String juniorApprovalUserHql = "select c.id,c.username from UserInfo c left join c.userRole where c.userRole.id = 16";
		List<UserInfo> juniorApprovalUser = accountService
				.find(juniorApprovalUserHql);

		String middleApprovalUserHql = "select c.id,c.username from UserInfo c left join c.userRole where c.userRole.id = 17";
		List<UserInfo> middleApprovalUser = accountService
				.find(middleApprovalUserHql);

		String seniorApprovalUserHql = "select c from UserInfo c left join c.userRole where c.userRole.id = 18";
		List<UserInfo> seniorApprovalUser = accountService
				.find(seniorApprovalUserHql);
		System.out.println(seniorApprovalUser.get(0));
		model.addAttribute("juniorApprovalUser", juniorApprovalUser);
		model.addAttribute("middleApprovalUser", middleApprovalUser);
		model.addAttribute("seniorApprovalUser", seniorApprovalUser);
		return "managementApprovalSetting";
	}

	/**
	 * 保存审批流程设置
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveApprovalSetting")
	public String saveApprovalSetting(Model model, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {
		String workId = req.getParameter("works.id");
		String juniorUserId = req.getParameter("junior.id");
		String middleUserId = req.getParameter("middle.id");
		String seniorUserId = req.getParameter("senior.id");
		Works work = worksService.findOne(Long.valueOf(workId));
		work.setStatus(WorksStatusConst.WORKS_STATUS_JUNIOR);// 进入初审
		worksService.create(work);
		List<ApprovalInfo> ApprovalUsers = new ArrayList<ApprovalInfo>(3);
		// 初审
		UserInfo juniorUser = accountService
				.findOne(Long.valueOf(juniorUserId));
		ApprovalInfo juniorUserApproval = new ApprovalInfo();
		juniorUserApproval.setApprover(juniorUser);
		// 复审
		UserInfo middleUser = accountService
				.findOne(Long.valueOf(middleUserId));
		ApprovalInfo middleUserApproval = new ApprovalInfo();
		middleUserApproval.setApprover(middleUser);
		// 终审
		UserInfo seniorUser = accountService
				.findOne(Long.valueOf(seniorUserId));
		ApprovalInfo seniorUserApproval = new ApprovalInfo();
		seniorUserApproval.setApprover(seniorUser);
		approvalInfoService.create(juniorUserApproval);
		approvalInfoService.create(middleUserApproval);
		approvalInfoService.create(seniorUserApproval);
		ApprovalManage approvalManage = new ApprovalManage();

		approvalManage.setJunior(juniorUserApproval);
		approvalManage.setMiddle(middleUserApproval);
		approvalManage.setSenior(seniorUserApproval);
		approvalManage.setWorks(work);
		// approvalManage.setStage(WorksStageConst.WORKS_STAGE_JUNIOR);
		siteMessageService.create(SysConstant.createMessage(work.getAuthor(), work, SysConstant.MSG_SET_APPROVAL_FLOW));
		approvalManageService.create(approvalManage);
		// 指向设置页面
		redirectAttributes.addFlashAttribute(HttpAssist.MESSAGE, "设置成功!");
		return HttpAssist.REDIRECT + "managementApprovalSetting.html";
	}

	/**
	 * 我的审批代办
	 * 
	 * @return
	 */
	@RequestMapping(value = "/managementMyApprovalTodo.html")
	public String myApprovalTodo(Model model, HttpServletRequest request) {		
		StringBuffer hql = new StringBuffer(
				"select c from Works c,ApprovalManage d where d.works.id = c.id ");
		Object obj[] = new Object[2];
		UserInfo usr = SessionUtil.getUsrFromSession(request);

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
		}
		List<Works> worksList = worksService.find(hql.toString(),obj);

		System.out.println(worksList);
		model.addAttribute("worksList", worksList);

		return "managementMyApprovalTodo";
	}

	/**
	 * 通过审批
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myApproval")
	public String myApproval(Model model, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {
		String wid = req.getParameter("works.id");
		String comment = req.getParameter("approvalComment");
		boolean througApproval = Boolean.valueOf(req
				.getParameter("approvalResult"));
		// 稿件
		Works works = worksService.findOne(Long.valueOf(wid));
		String hql = "select c from ApprovalManage c where c.works.id = " + wid;
		List<ApprovalManage> approvalManages = approvalManageService.find(hql);
		// 审批
		ApprovalManage approvalManage = new ApprovalManage();
		// 只有一个所以索引是1
		if (approvalManages == null || approvalManages.isEmpty()) {
			// 判断是否存在,不存在则返回
			redirectAttributes.addFlashAttribute(HttpAssist.MESSAGE,
					"稿件已经不存在,请联系管理员!");
			return HttpAssist.REDIRECT + "managementMyApprovalTodo.html";
		}
		approvalManage = approvalManages.get(0);
		// 更新状态
		if (!througApproval) {
			works.setStatus(WorksStatusConst.WORKS_STATUS_SEND_BACK);// 退回
			siteMessageService.create(SysConstant.createMessage(works.getAuthor(), works, SysConstant.MSG_APPROVAL_EDITOR_NO_PASS));
			worksService.create(works);
			redirectAttributes.addFlashAttribute(HttpAssist.MESSAGE, "稿件已退回!");
			return HttpAssist.REDIRECT + "managementMyApprovalTodo.html";
		}
		
		if (works.getStatus() == WorksStatusConst.WORKS_STATUS_JUNIOR) {
			works.setStatus(WorksStatusConst.WORKS_STATUS_MIDDLE);
			ApprovalInfo junior = approvalManage.getJunior();
			junior.setComment(comment);
			siteMessageService.create(SysConstant.createMessage(works.getAuthor(), works, SysConstant.MSG_APPROVAL_JUNIOR_PASS));
			approvalInfoService.create(junior);
		} else if (works.getStatus() == WorksStatusConst.WORKS_STATUS_MIDDLE) {
			works.setStatus(WorksStatusConst.WORKS_STATUS_SENIOR);
			ApprovalInfo middle = approvalManage.getMiddle();
			middle.setComment(comment);
			siteMessageService.create(SysConstant.createMessage(works.getAuthor(), works, SysConstant.MSG_APPROVAL_MIDDLE_PASS));
			approvalInfoService.create(middle);
		} else if (works.getStatus() == WorksStatusConst.WORKS_STATUS_SENIOR) {
			works.setStatus(WorksStatusConst.WORKS_STATUS_ACCESS);
			ApprovalInfo senior = approvalManage.getSenior();
			senior.setComment(comment);
			siteMessageService.create(SysConstant.createMessage(works.getAuthor(), works, SysConstant.MSG_APPROVAL_EDITOR_PASS));
			approvalInfoService.create(senior);
		} else {
			log.info(works);
		}

		
		// 一下审批需要查询用户session判断当前用户是否有权限审批这个稿件
		
		// 这里用户需要根据session中的用户权限去查询,然后做判断

		// 更新稿件状态
		worksService.create(works);
		// 更新审批流程
		approvalManageService.create(approvalManage);// 更新评论
		redirectAttributes.addFlashAttribute(HttpAssist.MESSAGE, "审批成功!");
		return HttpAssist.REDIRECT + "managementMyApprovalTodo.html";
	}
}
