<%@page import="com.worksApproval.admin.entity.UserInfo"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.worksApproval.admin.entity.PageInfo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.worksApproval.admin.constant.SysConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String nameSpace = request.getContextPath();
%>
<%
	String projectPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ nameSpace + "/";

UserInfo usr = (UserInfo)session.getAttribute(SysConstant.SESSION_USR);
Set<PageInfo> pageSet = null;

String servletPath = request.getServletPath();
%>
<c:set value="<%=projectPath%>" var="basePath" />

 <div id="sidebar">
		<a href="#" class="visible-phone"><i class="icon icon-th"></i>Tables</a>
		<ul>
		<!--class="active" 选中样式  -->
		<%
		if(usr!=null)
		{
			pageSet = usr.getUserRole().getPageSet();	
		
		
		for(PageInfo p : pageSet)
		{
			if(servletPath.contains(p.getUrl()))
			{
		%>
			<li class="active"><a href="<%=p.getUrl() %>.html"><i class="icon <%=SysConstant.getMenuIconMapByKey(p.getId())%>"></i>
					<span><%=p.getPageName() %></span></a></li>
		<%
			}
			else{
		%>
			<li><a href="<%=p.getUrl() %>.html"><i class="icon <%=SysConstant.getMenuIconMapByKey(p.getId())%>"></i>
					<span><%=p.getPageName() %></span></a></li>
		<%		
			}
		}
		}
		%>			
		</ul>
	</div>