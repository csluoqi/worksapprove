package com.worksApproval.admin.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.constant.UsrRoleConst;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.service.WorksService;

/**
 * 下载权限拦截认证的拦截器
 */
public class DownloadInterceptor implements HandlerInterceptor {
	private static Logger log = Logger.getLogger(DownloadInterceptor.class);
	private static List<String> portalBasicUrlList = null;
	
	@Resource
	private WorksService worksService;
	static {
		portalBasicUrlList = new ArrayList<String>(5);
		portalBasicUrlList.add("login.html");
		portalBasicUrlList.add("portalLogin");
		portalBasicUrlList.add("registe.htmlr");
		portalBasicUrlList.add("portalRegister");
		portalBasicUrlList.add("logout");
		portalBasicUrlList.add("portalHome.html");
		portalBasicUrlList.add("portalThemeDetail.html");
	}

	/**
	 * Handler执行完成之后调用这个方法
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exc)
			throws Exception {
	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * Handler执行之前调用这个方法
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (uri.contains(HttpAssist.DOWNLOAD_URL)) {
			//检查用户的下载权限
			HttpSession session = request.getSession();
			UserInfo usr = (UserInfo) session
					.getAttribute(SysConstant.SESSION_USR);
			//管理员
			if(UsrRoleConst.hasPermission(usr.getUserRole().getId()))
			{
				return true;
			}
			//如果是自己的
			Long downloadWorksId = HttpAssist.getPramaterAndType(HttpAssist.DOWNLOAD_ID, request, Long.class);
			if(worksService.hasPermissionDownload(downloadWorksId, request))
			{
				return true;
			}
			
			String message = "下载失败,无权限下载,请联系管理员";
			request.getRequestDispatcher(this.createBackUrl(request, message))
					.forward(request, response);
			// accountService.findAll();
			return false;
		}
		return true;
	}

	private String createBackUrl(HttpServletRequest request, String message) {
		if(!StringUtils.isEmpty(message))
		{
			request.setAttribute(HttpAssist.MESSAGE, message);
		}
		return HttpAssist.getBackUrl(request.getParameter(HttpAssist.BACK_URL));
	}

}