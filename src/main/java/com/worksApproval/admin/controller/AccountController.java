package com.worksApproval.admin.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.constant.SessionUtil;
import com.worksApproval.admin.dao.impl.CommonService;
import com.worksApproval.admin.entity.PageInfo;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.UserRole;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.entity.WorksTheme;
import com.worksApproval.admin.service.AccountService;
import com.worksApproval.admin.service.RoleService;
import com.worksApproval.admin.service.ThemeService;
import com.worksApproval.admin.service.WorksService;
import com.worksApproval.admin.service.impl.UserService;

/**
 * 消息管理
 * 
 * @author rocky
 */
@Controller
public class AccountController {
	
	@Resource
	private AccountService accountService;
	@Resource	
	private RoleService roleService;
	@Resource
	private CommonService commonService;
	
	
	
	
	@RequestMapping(value = "/managementAccount.html")
	public String managementAccount(Model model,HttpServletRequest request) {
		List<UserInfo> userList = accountService.findAll();
		//测试菜单
		//pageList
		UserInfo userInfo = SessionUtil.getUsrFromSession(request);
		model.addAttribute("pageSet", userInfo.getUserRole().getPageSet());
		List<UserRole> roleList = roleService.findAll();
		
		model.addAttribute("userList",userList);
		model.addAttribute("roleList",roleList);
		return "managementAccount";
	}

	// @ResponseBody
	@RequestMapping(value = "/newAccount")
	public String newAccount(HttpServletRequest req, UserInfo userInfo,	RedirectAttributes redirectAttributes) {
		userInfo.setRegisterDate(new Date());
		String birthdayStr = req.getParameter("birthday_");
		Date birthday = null;
		try {
			if(!StringUtils.isEmpty(birthdayStr))
			{
				birthday = DateUtils.parseDate(birthdayStr, "yyyy-MM-dd");	
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userInfo.setBirthday(birthday);
		if(userInfo.getPassword()==null)
		{
			userInfo.setPassword("123456");//这个是出事密码,用户在设置时不能设置为初始密码,这个本应用一个字段来维护是否
			//初始密码
		}
		
		if(accountService.findUser(userInfo)!=null)
		{
			redirectAttributes.addFlashAttribute("message", "用户名已经存在请重新输入!");
			return HttpAssist.REDIRECT + "managementAccount.html";
		}	
		accountService.create(userInfo);
		
		redirectAttributes.addFlashAttribute("message", "添加成功!");
		return HttpAssist.REDIRECT + "managementAccount.html";
	}

	

	@ResponseBody
	@RequestMapping(value = "/isRepeatAccount")
	public Boolean isRepeat(UserInfo userInfo) {
		if (userInfo.getId() == null) {
		
			return !commonService.isRepeate(
				"from UserInfo c where c.username=?1 ", userInfo.getUsername());
		} else {
			return !commonService.isRepeate(
					"from UserInfo c where c.username=?1 and c.id != ?2",
					userInfo.getUsername(), userInfo.getId());
		}

	}
	@RequestMapping(value = "/updateAccount")
	public String updateAccount(UserInfo userInfo, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {
		//查询数据库中的这条数据
		UserInfo userInfodb = accountService.findOne(userInfo.getId());
		
		userInfo.setRegisterDate(userInfodb.getRegisterDate());
		String birthdayStr = req.getParameter("birthday_");
		Date birthday = null;
		try {
			if(!StringUtils.isEmpty(birthdayStr))
			{
				birthday = DateUtils.parseDate(birthdayStr, "yyyy-MM-dd");	
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userInfo.setBirthday(birthday);
		userInfo.setPassword(userInfodb.getPassword());
		accountService.create(userInfo);
		redirectAttributes.addFlashAttribute("message", "更新成功!");
		return HttpAssist.REDIRECT + "managementAccount.html";
	}
	
	@ResponseBody()
	@RequestMapping(value = "/findAccountById",method=RequestMethod.POST)
	public UserInfo findAccountById(String id)
	{
		UserInfo userInfo = accountService.findOne(Long.valueOf(id));
		return userInfo;
	}
	
	@ResponseBody()
	@RequestMapping(value = "/deleteAccount",method=RequestMethod.POST)
	public String[] deleteAccount(String ids) {
		//这个也是被关联的所以只能一条一条删除
		
		try {
			commonService.simpleDelete("delete from  UserInfo where id in (:ids)", ids, "ids");
		} catch (Exception e) {
			return new String[] {"删除失败! 该用户被依赖不能删除","1"};
		}
		return new String[] {"删除成功! 页面将自动跳转"};
	}
	}
