package com.worksApproval.admin.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.entity.UserInfo;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
	private static Logger log = Logger.getLogger(LoginInterceptor.class);
	private static List<String> portalBasicUrlList = null;
	static{
		portalBasicUrlList = new ArrayList<String>(5);
		portalBasicUrlList.add("login.html");
		portalBasicUrlList.add("portalLogin");
		portalBasicUrlList.add("register.html");
		portalBasicUrlList.add("portalRegister");
		portalBasicUrlList.add("logout.html");
		portalBasicUrlList.add("portalHome.html");
		portalBasicUrlList.add("portalThemeDetail.html");
		portalBasicUrlList.add("approvalCommentDetail");
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
		// 获取请求的URL
		String uri = request.getRequestURI();
		// URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
		// 如果是登录页面的js,css等不用session做判断
		if (isBasicUrl(uri)) {
			return true;
		} else if (isStaticPath(uri)) {
			return true;
		}

		// 获取Session
		HttpSession session = request.getSession();
		UserInfo usr = (UserInfo) session
				.getAttribute(SysConstant.SESSION_USR);
		if (usr == null) {
			// session
			// response.sendRedirect("");
			request.getRequestDispatcher("/login.html").forward(request,
					response);
			return false;
		}
		return true;
	}

	/**
	 * 过滤掉静态的资源的url
	 * @param url
	 * @return
	 */
	private boolean isStaticPath(String url) {
		if (url.contains(".") && !url.contains(".html")) {
			return true;
		}
		return false;
	}
	
	private boolean isBasicUrl(String url)
	{
		for(String keyWord : portalBasicUrlList)
		{
			if (url.indexOf(keyWord) > 0)
			{
				return true;
			}
		}
		return false;
	}


}