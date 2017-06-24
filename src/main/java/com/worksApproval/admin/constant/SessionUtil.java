package com.worksApproval.admin.constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.worksApproval.admin.entity.UserInfo;

public class SessionUtil {

	public static  String SESSION_USR = "usr";
	
	public static UserInfo getUsrFromSession(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		return (UserInfo) session.getAttribute(SESSION_USR);
	}
	
	public static void updateUsrInSession(UserInfo usr,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_USR);
		session.setAttribute(SESSION_USR, usr);
	}
}
