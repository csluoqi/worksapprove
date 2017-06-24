package com.worksApproval.admin.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.dao.impl.CommonService;
import com.worksApproval.admin.entity.PageInfo;
import com.worksApproval.admin.service.PageService;

@Controller
public class PageController {
	@Resource
	private PageService pageService;
	@Resource
	private CommonService commonService;
	@RequestMapping(value = "/managementPage.html")
	public String managementPage(Model model) {
		List<PageInfo> pageList = pageService.findAll();
		model.addAttribute("pageList", pageList);
		return "managementPage";
	}
	@RequestMapping(value = "/newPage")
	public String newPage(PageInfo page,
			RedirectAttributes redirectAttributes) {
		page.setCreateTime(new Date());//time都是带时间的
		page.setLastUpdatedTime(new Date());
		System.out.println(page.toString());
		pageService.create(page);

		redirectAttributes.addFlashAttribute("message", "添加成功!");
		return HttpAssist.REDIRECT + "managementPage.html";
	}

	@ResponseBody
	@RequestMapping(value = "/isRepeatPage")
	public Boolean isRepeat(PageInfo page) {
		if (page.getId() == null) {
			// 新增
			return !commonService.isRepeate(
					"from PageInfo c where c.url=?1", page.getUrl());
		} else {
			return !commonService.isRepeate(
					"from PageInfo c where c.url=?1 and c.id != ?2",
					page.getUrl(), page.getId());
		}

	}

	@RequestMapping(value = "/updatePage")
	public String updatePage(PageInfo page,
			RedirectAttributes redirectAttributes) {
		page.setCreateTime(new Date());
		page.setLastUpdatedTime(new Date());
		pageService.create(page);
		redirectAttributes.addFlashAttribute("message", "更新成功!");
		return HttpAssist.REDIRECT + "managementPage.html";
	}
	
	@ResponseBody()
	@RequestMapping(value = "/deletePage",method=RequestMethod.POST)
	public String[] deletePage(String ids,
			RedirectAttributes redirectAttributes) {
		try {
			commonService.simpleDelete("delete from  PageInfo where id in (:ids)", ids, "ids");
		} catch (Exception e) {
			return new String[] {"删除失败! 存在依赖关系"};
		}
		return new String[] {"删除成功! 页面将自动跳转"};
	}
	
}
