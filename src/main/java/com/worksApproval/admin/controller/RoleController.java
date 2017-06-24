package com.worksApproval.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.dao.impl.CommonService;
import com.worksApproval.admin.entity.PageInfo;
import com.worksApproval.admin.entity.UserRole;
import com.worksApproval.admin.service.PageService;
import com.worksApproval.admin.service.RoleService;
import com.worksApproval.admin.service.impl.PageServiceImpl;
import com.worksApproval.admin.support.RolePageDetail;

@Controller
public class RoleController {
	@Resource
	private RoleService roleService;
	@Resource
	private CommonService commonService;
	@Resource
	private PageService pageService;

	@RequestMapping(value = "/managementRole.html")
	public String managementPage(Model model) {

		/**
		 * id shortName comment pageList
		 */
		List<UserRole> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		// pageList
		List<PageInfo> pageList = pageService.findAll();
		model.addAttribute("pageList", pageList);
		return "managementRole";
	}

	@RequestMapping(value = "/newRole")
	public String newRole(UserRole userRole,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String[] pages = request.getParameterValues("pages");// 获取select中的值
		// System.out.println(pages.length);
		// userRole.setCreat;//time都是带时间的
		// userRole.setLastUpdatedTime(new Date());

		// 通过id找到用户选中的页面
		Set<PageInfo> pageSet = new HashSet<PageInfo>(1);
		PageInfo p = null;
		if (pages == null || pages.length == 0) {
			redirectAttributes.addFlashAttribute("message", "添加失败,可访问的页面不能为空!");
			return HttpAssist.REDIRECT + "managementRole.html";
		}
		for (String id : pages) {
			p = pageService.findOne(Long.valueOf(id));
			pageSet.add(p);
		}
		userRole.setPageSet(pageSet);
		roleService.create(userRole);
		redirectAttributes.addFlashAttribute("message", "添加成功!");
		return HttpAssist.REDIRECT + "managementRole.html";
	}

	@ResponseBody
	@RequestMapping(value = "/isRepeatRole")
	public Boolean isRepeat(PageInfo page) {
		if (page.getId() == null) {
			// 新增
			return !commonService.isRepeate("from PageInfo c where c.url=?1",
					page.getUrl());
		} else {
			return !commonService.isRepeate(
					"from PageInfo c where c.url=?1 and c.id != ?2",
					page.getUrl(), page.getId());
		}

	}

	@ResponseBody
	@RequestMapping(value = "/pageDetail")
	public List<RolePageDetail> pageDetail(String roleId, int typeId)// typeId
																		// 1,表示查询时使用,2表示在新增和更新时使用
	{
		List<RolePageDetail> detailList = new ArrayList<RolePageDetail>(1);
		RolePageDetail detail = null;
		int TYPE_SELECT = 1;
		int TYPE_UPDATE = 2;
		UserRole userRole = roleService.findOne(Long.valueOf(roleId));
		if (roleId != null && typeId == TYPE_SELECT) {
			for (PageInfo page : userRole.getPageSet()) {
				detail = new RolePageDetail();
				detail.setPage(page);
				detail.setStatus(1);
				detailList.add(detail);
			}
		} else if (roleId != null && typeId == TYPE_UPDATE) {
			List<PageInfo> pages = pageService.findAll();
			boolean isinclude = false;
			for (PageInfo p : pages) {
				detail = new RolePageDetail();
				detail.setPage(p);
				for (PageInfo page : userRole.getPageSet()) {
					if (p.getId() == page.getId()) {
						isinclude = true;
						break;
					}
				}
				
				if(isinclude)
				{
					detail.setStatus(1);
					isinclude = false;
				}
				else
				{
					detail.setStatus(0);
				}
				detailList.add(detail);
				
			}
		} else {
			System.out.println("出错了,原因可能是当类型为选择时,role为null");
		}

		return detailList;
	}

	@RequestMapping(value = "/updateRole")
	public String updateRole(UserRole userRole,
			RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		String[] pages = request.getParameterValues("pages");// 获取select中的值
		Set<PageInfo> pageSet = new HashSet<PageInfo>(1);
		PageInfo p = null;
		if (pages == null || pages.length == 0) {
			redirectAttributes.addFlashAttribute("message", "修改失败,可访问的页面不能为空!");
			return HttpAssist.REDIRECT + "managementRole.html";
		}
		for (String id : pages) {
			p = pageService.findOne(Long.valueOf(id));
			pageSet.add(p);
		}
		userRole.setPageSet(pageSet);
		roleService.create(userRole);
		redirectAttributes.addFlashAttribute("message", "修改成功!");
		return HttpAssist.REDIRECT + "managementRole.html";
	}

	@ResponseBody()
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	public String[] deleteRole(String ids, RedirectAttributes redirectAttributes) {
		
		Long id = Long.valueOf(ids.split(",")[0]);
		//int result = commonService.checkUseCount(id+"");
	    
		//一种欠妥的方法
		try {
			roleService.deleteById(Long.valueOf(id));
		} catch (Exception e) {
			return new String[] { "删除失败! 角色被使用,不能删除","0" };
		}
		 
		return new String[] { "删除成功! 页面将自动跳转" };
	}


}
