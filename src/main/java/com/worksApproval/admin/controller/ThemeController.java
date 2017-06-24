package com.worksApproval.admin.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.transaction.xa.processor.XARequest.RequestType;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.dao.impl.CommonService;
import com.worksApproval.admin.entity.WorksTheme;
import com.worksApproval.admin.service.ThemeService;

/**
 * 主题管理
 * 
 * @author rocky
 *
 */
@Controller
public class ThemeController {
private Logger log = Logger.getLogger(ThemeController.class);
	@Resource
	private ThemeService themeService;
	@Resource
	private CommonService commonService;

	@RequestMapping(value = "/managementTheme.html")
	public String managementTheme(Model model) {
		log.info("/managementTheme.html");
		List<WorksTheme> worksThemeList = themeService.findAll();
		model.addAttribute("worksThemeList", worksThemeList);
		// System.out.println(worksThemeList);
		return "managementTheme";
	}

	// @ResponseBody
	@RequestMapping(value = "/newTheme")
	public String newTheme(WorksTheme worksTheme,
			RedirectAttributes redirectAttributes) {
		worksTheme.setCreateDate(new Date());
		themeService.create(worksTheme);
		// themeService.
		// message是和前台约定的
		// if(themeService.)

		redirectAttributes.addFlashAttribute("message", "添加成功!");
		return HttpAssist.REDIRECT + "managementTheme.html";
	}

	@ResponseBody
	@RequestMapping(value = "/isRepeat")
	public Boolean isRepeat(WorksTheme worksTheme) {
		// themeService.isRepeate(worksTheme);
		System.out.println(worksTheme);

		if (worksTheme.getId() == null) {
			// 新增
			return !commonService.isRepeate(
					"from WorksTheme c where c.name=?1", worksTheme.getName());
		} else {
			return !commonService.isRepeate(
					"from WorksTheme c where c.name=?1 and c.id != ?2",
					worksTheme.getName(), worksTheme.getId());
		}

	}

	@RequestMapping(value = "/updateTheme")
	public String updateTheme(WorksTheme worksTheme,
			RedirectAttributes redirectAttributes) {
		worksTheme.setCreateDate(new Date());
		themeService.create(worksTheme);
		redirectAttributes.addFlashAttribute("message", "更新成功!");
		return HttpAssist.REDIRECT + "managementTheme.html";
	}
	
	@ResponseBody()
	@RequestMapping(value = "/deleteTheme",method=RequestMethod.POST)
	public String[] deleteTheme(String ids,
			RedirectAttributes redirectAttributes) {
		try {
			commonService.simpleDelete("delete from  WorksTheme where id in (:ids)", ids, "ids");
		} catch (Exception e) {
			return new String[] {"删除失败! 该主题被依赖不能删除"};
		}
		return new String[] {"删除成功! 页面将自动跳转"};
		
	}
	
	
	
}
