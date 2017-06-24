package com.worksApproval.admin.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.hibernate.engine.transaction.jta.platform.internal.JOnASJtaPlatform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.ob.impl.JSONAsObjectCodec;
import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.constant.SessionUtil;
import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.constant.UsrRoleConst;
import com.worksApproval.admin.constant.WorksStatusConst;
import com.worksApproval.admin.entity.ApprovalManage;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.entity.WorksTheme;
import com.worksApproval.admin.service.AccountService;
import com.worksApproval.admin.service.SiteMessageService;
import com.worksApproval.admin.service.ThemeService;
import com.worksApproval.admin.service.WorksService;
import com.worksApproval.admin.service.impl.UserService;

/**
 * 门户页面
 * 
 * @author rocky
 *
 */
@Controller
public class PortalController {
	private static Logger log = Logger.getLogger(PortalController.class);
	@Resource
	private ThemeService themeService;
	@Resource
	private WorksService worksService;
	@Resource
	private AccountService accountService;
	@Resource
	private SiteMessageService siteMessageService;
	@RequestMapping(value = "/portalHome.html")
	public String portalHome(Model model) {
		List<Object[]> themes = themeService.getThemeForPortal();

		model.addAttribute("themes", themes);
		// 审批详情
		return "portalHome";
	}

	@RequestMapping(value = "/index.jspx")
	public String testjspx(Model model) {
	log.info("index.jspx");
		return "index";
	}
	
	
	@ResponseBody()
	@RequestMapping(value = "/approvalCommentDetail", method = RequestMethod.POST)
	public String[] approvalCommentDetail(String id)
	{
		String[] res = new String[8];
		ApprovalManage approvalManage = themeService.approvalCommentDetail(Long.valueOf(id));
		log.info(approvalManage);
		res[0] = approvalManage.getWorks().getName();
		res[1] = approvalManage.getWorks().getAuthor().getUsername();
		res[2] = approvalManage.getJunior().getApprover().getUsername();
		res[3] = approvalManage.getJunior().getComment();
		res[4] = approvalManage.getMiddle().getApprover().getUsername();
		res[5] = approvalManage.getMiddle().getComment();
		res[6] = approvalManage.getSenior().getApprover().getUsername();
		res[7] = approvalManage.getSenior().getComment();
		
		//res[1] = approvalManage.getJunior();
		//res[2] = approvalManage.getMiddle();
		//res[3] = approvalManage.getSenior();
		
		return res;
	}

	// portalThemeDetail.jsp
	@RequestMapping(value = "/portalThemeDetail.html")
	public String portalThemeDetail(Model model, String id,
			HttpServletRequest request) {
		List<WorksTheme> worksThemeList = new ArrayList<WorksTheme>(22);
		worksThemeList = themeService.find(
				"from WorksTheme c left join fetch c.works where c.id=?0 ",
				Long.valueOf(id));

		List<Object[]> themes = themeService.getThemeForPortal();
		// 这个地方后期要加过滤,只能加载一经录用的稿件的数据
		if (worksThemeList != null && worksThemeList.size() >= 1) {
			model.addAttribute("themes", themes);
			model.addAttribute("theme", worksThemeList.get(0));
			return "portalThemeDetail";
		}

		/*
		 * model.addAttribute("themes", themes); model.addAttribute("theme", new
		 * WorksTheme());
		 */
		return "portalThemeDetail";
	}

	@RequestMapping(value = "/portalUploadWork.html")
	public String portalUploadWork(Model model, String id) {
		List<WorksTheme> worksThemeList = themeService.findAll();
		model.addAttribute("worksThemeList", worksThemeList);

		return "portalUploadWork";
	}

	// @ResponseBody
	@RequestMapping(value = "/uploadWork")
	public String uploadWork(HttpServletRequest req, Works works,
			RedirectAttributes redirectAttributes) {
		works.setCreateDate(new Date());
		MultipartFile worksFile = ((MultipartRequest) req).getFile("worksFile");
		String fileName = worksFile.getOriginalFilename();
		// fileNames.add(fileName);
		// 文件路径写死,后面构建框架时建立起来,在文件中配置
		File f = new File(SysConstant.SERVER_PATH, fileName);
		UserInfo author = SessionUtil.getUsrFromSession(req);
		works.setAuthor(author);
		works.setCreateDate(new Date());
		works.setName(fileName);
		works.setFilepath(f.getPath());
		try {
			worksFile.transferTo(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		works.setFilesize(new File(SysConstant.SERVER_PATH, fileName).length() / 1024L);
		// 20170225添加修改状态代码
		works.setStatus(WorksStatusConst.WORKS_STATUS_POST_SUCCESS);
		worksService.create(works);
		siteMessageService.create(SysConstant.createMessage(works.getAuthor(), works, SysConstant.MSG_POST_SUCCESS));
		redirectAttributes.addFlashAttribute("message", "添加成功!");
		return HttpAssist.REDIRECT + "portalUserCenter.html";
	}

	@RequestMapping(value = "/portalUserCenter.html")
	public String portalUserCenter(Model model, String id,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		List<WorksTheme> worksThemeList = themeService.findAll();
		model.addAttribute("worksThemeList", worksThemeList);
		String hql = "from Works c where c.author.id = ?0 and c.status >= 0";
		List<Works> worksList = worksService.find(hql, SessionUtil
				.getUsrFromSession(request).getId());
		model.addAttribute("worksList", worksList);
		return "portalUserCenter";
	}

	@RequestMapping(value = "/portalUpdateWorks")
	public String updateWorks(Works works, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {

		// 查询数据库中的这条数据
		Works worksdb = worksService.findOne(works.getId());
		works.setCreateDate(new Date());
		MultipartFile worksFile = ((MultipartRequest) req).getFile("worksFile");

		String fileName = worksFile.getOriginalFilename();
		// fileNames.add(fileName);
		// 文件路径写死,后面构建框架时建立起来

		File f = new File(SysConstant.SERVER_PATH, fileName);
		// author.setPassword("asdf");
		// author.setUsername("asdf");
		works.setAuthor(worksdb.getAuthor());
		works.setCreateDate(new Date());
		works.setName(fileName);
		works.setFilepath(f.getPath());
		works.setStatus(worksdb.getStatus());
		try {
			if (!f.isDirectory()) {
				// 上传
				works.setName(fileName);
				works.setFilepath(f.getPath());
				worksFile.transferTo(f);
				works.setFilesize(new File(SysConstant.SERVER_PATH, fileName)
						.length() / 1024L);
			} else {
				works.setName(worksdb.getName());
				works.setFilepath(worksdb.getFilepath());
				works.setFilesize(worksdb.getFilesize());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		worksService.create(works);

		redirectAttributes.addFlashAttribute("message", "更新成功!");
		return HttpAssist.REDIRECT + "portalUserCenter.html";
	}

	@ResponseBody()
	@RequestMapping(value = "/portalDeleteWorks", method = RequestMethod.POST)
	public String[] deleteWorks(String ids,
			RedirectAttributes redirectAttributes) {
		// commonService.simpleDelete("delete from  Works where id in (:ids)",
		// ids, "ids");
		// 循环删除,并删除文件
		Works w = null;
		File f = null;
		for (String id : ids.split(",")) {
			w = worksService.findOne(Long.valueOf(id));
			try {
				siteMessageService.create(SysConstant.createMessage(w.getAuthor(), w, SysConstant.MSG_DELETE_WORKS));
				worksService.delete(w);
			} catch (Exception e) {
				return new String[] { "删除失败! 请联系管理员" };
			}
			// 删除文件
			f = new File(w.getFilepath());
			f.deleteOnExit();
		}
		return new String[] { "删除成功! 页面将自动跳转" };
	}

	@RequestMapping(value = "/login.html")
	public String login() {
		// 登录
		return "login";
	}

	@RequestMapping(value = "/logout.html")
	public String logout(HttpServletRequest req) {
		// 登录
		HttpSession session = req.getSession();
		session.removeAttribute(SysConstant.SESSION_USR);

		return HttpAssist.REDIRECT + "portalHome.html";
	}

	@ResponseBody()
	@RequestMapping(value = "/portalLogin")
	private String[] portalLogin(UserInfo userInfo, HttpServletRequest req) {
		// 判断用户是否存在
		String[] msg = new String[2];
		UserInfo usr = accountService.findUser(userInfo);
		if (usr == null) {
			msg[0] = "false";
			msg[1] = "用户名不存在,请重新输入";
			return msg;
		}

		usr = accountService.portalLogin(userInfo);
		if (usr == null) {
			msg[0] = "false";
			msg[1] = "密码不正确,请重新输入";
			return msg;
		}

		msg[0] = "true";
		// 根据用户角色判断是到主页还是后台
		// 还是直接跳转到后台,直接跳转到后台吧

		if (usr.getUserRole().getId() == UsrRoleConst.ROLE_REGISTER_HALF) {
			msg[1] = "managementProfile.html";
		} else if (UsrRoleConst.hasPermissionApprovalTodo(usr.getUserRole()
				.getId())) {
			msg[1] = "managementMyApprovalTodo.html";
		} else if (usr.getUserRole().getId() == UsrRoleConst.ROLE_ADMIN){
			msg[1] = "managementApprovalSetting.html";
		}else {
			// 管理员,作者
			msg[1] = "portalHome.html";
		}

		// 处理session
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute(SysConstant.SESSION_USR, usr);
		System.out.println(usr);
		return msg;
	}

	@RequestMapping(value = "/register.html")
	public String register() {
		// 登录
		return "register";
	}

	@ResponseBody()
	@RequestMapping(value = "/portalRegister")
	private String[] portalRegister(UserInfo userInfo, HttpServletRequest req) {
		// 判断用户是否存在
		String[] msg = new String[2];
		UserInfo usr = accountService.findUser(userInfo);
		if (usr != null) {
			msg[0] = "false";
			msg[1] = "用户名已经存在,请重新输入";
			return msg;
		}

		accountService.createUsr(userInfo);/*
											 * //usr =
											 * accountService.portalLogin
											 * (userInfo); if(usr == null) {
											 * msg[0] = "false"; msg[1] =
											 * "密码不正确,请重新输入"; return msg; }
											 */
		msg[0] = "true";
		// 根据用户角色判断是到主页还是后台
		// 还是直接跳转到后台,直接跳转到后台吧
		msg[1] = "login.html";
		// 处理session
		/*
		 * HttpSession httpSession = req.getSession();
		 * httpSession.setAttribute(SysConstant.SESSION_USR, usr);
		 * System.out.println(usr);
		 */
		return msg;
	}

	@RequestMapping(value = "/managementProfile.html")
	public String managementProfile() {
		return "managementProfile";
	}

	/**
	 * 更新用户资料,既更新用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateProfile")
	public String updateProfile(UserInfo userInfo, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {
		String birthdayStr = req.getParameter("birthday_");
		UserInfo usr = accountService.findOne(SessionUtil
				.getUsrFromSession(req).getId());

		Date birthday = null;
		try {
			birthday = FastDateFormat.getInstance("yyyy-mm-dd").parse(
					birthdayStr);
		} catch (ParseException e) {
			System.err.println(e);
		}
		userInfo.setBirthday(birthday);
		usr.setBirthday(userInfo.getBirthday());
		usr.setUsername(userInfo.getUsername());
		usr.setRealName(userInfo.getRealName());
		usr.setGender(userInfo.getGender());
		usr.setPhoneNumber(userInfo.getPhoneNumber());
		usr.setAddress(userInfo.getAddress());
		usr.setEmail(userInfo.getEmail());
		// 如果是准注册用户
		if (usr.getUserRole().getId() == UsrRoleConst.ROLE_REGISTER_HALF) {
			usr.getUserRole().setId(UsrRoleConst.ROLE_AUTHOR);
		}
		accountService.create(usr);// 更新用户角色
		// 更新session
		usr = accountService.findOne(usr.getId());
		SessionUtil.updateUsrInSession(usr, req);
		redirectAttributes.addFlashAttribute(HttpAssist.MESSAGE, "更新成功");
		return HttpAssist.REDIRECT + "managementProfile.html";
	}

	@RequestMapping(value = "/portalPasswordModify.html")
	public String portalPasswordModify() {
		return "portalPasswordModify";
	}

	// @ResponseBody
	@RequestMapping(value = "/portalModifyPassword")
	public String portalModifyPassword(String password, String newPassword,
			String sureNewPassword, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {

		HttpSession session = req.getSession();
		UserInfo usr = (UserInfo) session.getAttribute(SysConstant.SESSION_USR);
		if (StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)
				|| StringUtils.isBlank(sureNewPassword)) {
			redirectAttributes.addFlashAttribute("message", "修改密码更新失败,请重新输入");
			return HttpAssist.REDIRECT + "portalPasswordModify.html";
		} else if (!usr.getPassword().equals(password)) {
			redirectAttributes.addFlashAttribute("message", "原密码不正确,请重新输入");
			return HttpAssist.REDIRECT + "portalPasswordModify.html";

		} else if (!newPassword.equals(sureNewPassword)) {
			redirectAttributes
					.addFlashAttribute("message", "新密码和确认密码不一致,请重新输入");
			return HttpAssist.REDIRECT + "portalPasswordModify.html";

		} else {

			usr.setPassword(newPassword);
			accountService.create(usr);
			session.removeAttribute(SysConstant.SESSION_USR);
			session.setAttribute(SysConstant.SESSION_USR, usr);
			redirectAttributes.addFlashAttribute("message", "修改成功");
			return HttpAssist.REDIRECT + "portalUserCenter.html";
		}
	}

	/*
	 * @RequestMapping(value = "/portalModifyPassword") public String
	 * portalModifyPassword() { return null; }
	 */
}
