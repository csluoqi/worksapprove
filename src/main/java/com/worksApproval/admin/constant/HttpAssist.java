package com.worksApproval.admin.constant;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * response util
 * @author rocky
 *
 */
public class HttpAssist {
	public static String REDIRECT = "redirect:/";
	public static String MESSAGE = "message";//与前台的所有页面约定的
	public static String BACK_URL = "backurl";
	public static String DOWNLOAD_URL = "downloadWorks2";
	public static String DOWNLOAD_ID = "downloadId";
	/**
	 * 通过jsp的路径解析jsp对应的的访问路径
	 * @param backUrl
	 * @return
	 */
	public static String getBackUrl(String backUrl)
	{
		String reqUrl = backUrl.substring(backUrl.lastIndexOf('/'), backUrl.lastIndexOf("."));
		return reqUrl+".html";
	}
	/*public static void main(String[] args) {
		System.out.println(getBackUrl("WEB-INF/page/portalThemeDetail.jsp"));
	}*/
	
	public static String getPramater(String pramName,HttpServletRequest request)
	{
		return request.getParameter(pramName);
	}
	@SuppressWarnings("unchecked")
	public static <T> T getPramaterAndType(String paramName,HttpServletRequest request, Class<T> clazz) throws ParseException
	{
		String param = request.getParameter(paramName);
		if(StringUtils.isEmpty(param))
		{
			return null;
		}
		if(String.class==clazz)
		{
			return (T) param;
		}else if(Date.class==clazz)
		{
			return (T) FastDateFormat.getInstance("yyyy-MM-dd").parse(param);
		}else if(clazz == Long.class)
		{	
			return (T) Long.valueOf(param);
		}else if(clazz == Integer.class)
		{	
			return (T) Integer.valueOf(param);
		}
		
		return null;
	}
	
}
