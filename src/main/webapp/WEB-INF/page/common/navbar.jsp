<%@page import="com.worksApproval.admin.constant.UsrRoleConst"%>
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

String servletPath = request.getServletPath();
int commonMessageFlag = 0;
//0不查,1,查询审批的消息,2查询作者的消息
%>
<c:set value="<%=projectPath%>" var="basePath" />
<script type="text/javascript">
var commonMessage = "";

</script>
	<!--Header-part-->
	<div id="header">
		<h1>
			<a href="${basePath}portalHome.html">稿件审核系统</a>
		</h1>
	</div>
	<!--close-Header-part-->

<div id="user-nav" class="navbar navbar-inverse">
<div class="span8"></div>
		<ul class="nav">
			<li class="dropdown" id="profile-messages"><a title="" href="#"
				data-toggle="dropdown" data-target="#profile-messages"
				class="dropdown-toggle"><i class="icon icon-user"></i> <span
					class="text">&nbsp;<b><%=usr==null ? "游客" : usr.getUsername() %></b>&nbsp;</span><b class="caret"></b></a>
				<ul class="dropdown-menu">
					
					<li><a href="${basePath}managementProfile.html?id=<%=usr==null ? "": usr.getId()%>"><i class="icon-user"></i>个人资料</a></li>
					<li class="divider"></li>
					<%
					if(usr!=null && UsrRoleConst.hasPermission(usr.getUserRole().getId()))
					{
					%>
					<li><a href="${basePath}managementMyApprovalTodo.html?id=<%=usr.getId()%>"><i class="icon-check"></i>待办审批</a></li>
					<li class="divider"></li>
					<%
					 }
					%>
					
					<li><a href="${basePath}portalPasswordModify.html"><i class="icon-key"></i>密码修改</a></li>
				</ul></li>
			<li class="dropdown" id="menu-messages"><a href="javascript:void(0);"
				data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"><i class="icon icon-envelope"></i> <span
					class="text">消息</span> <span id="sumMsg" class="label label-important"></span>
					<b class="caret"></b></a>
				<ul class="dropdown-menu">
				<%if(usr!=null && usr.getUserRole().getId()==UsrRoleConst.ROLE_AUTHOR) 
					{
						commonMessageFlag = 2;
					%>
					<li><a class="sAdd" title="" href="javascript:viewMsg();"><i class="icon-plus"></i>
							稿件动态 <span class="label label-important" id="msg2"></span></a></li>
					
					<%} %>
					<!-- approvalMsgCount -->
					
				<%if(usr!=null && UsrRoleConst.hasPermissionApprovalTodo(usr.getUserRole().getId())) 
					{
						commonMessageFlag = 1;
					%>	
					<li><a class="sInbox" title="" href="managementMyApprovalTodo.html"><i class="icon-envelope"></i> 待审批 <span id="msg1" class="label label-important"></span></a></li>		
					
					<%} %>
				
				<%if(usr!=null && usr.getUserRole().getId()==UsrRoleConst.ROLE_ADMIN) 
					{
						commonMessageFlag = 3;
					%>	
					<li><a class="sInbox" title="" href="managementApprovalSetting.html"><i class="icon-envelope"></i> 待设置审批流程 <span id="msg3" class="label label-important"></span></a></li>		
					
					<%} %>
						
				</ul></li>
			<!-- <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li> -->
			<li class=""><a title="" href="${basePath}logout.html"><i
					class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
		</ul>
	</div>

	<div id="sidebar">
		<a href="#" class="visible-phone"><i class="icon icon-th"></i></a>
		<ul>
    <jsp:include page="menu.jsp" />

		</ul>
	</div>
	
	
	
		<!-- alert start -->
	<div id="msgModal" class="modal hide">
        <div class="modal-header">
          <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>新消息列表</h3>
        </div>
	<div class="modal-body">
		<div id="msgWrap" class="widget-content nopadding updates">
		
		
			
		</div>  
        </div>
    </div>
	<!-- alert end-->
	
	
	
<script type="text/javascript">
function viewMsg()
{
	$.post("/worksApproval/getAuthorMsgList",{ t:"123456" },
			function(data){
		//console.log(data);
		if(data==null || data.length==0){
			msgHtml= '<div class="new-update clearfix"> <i class="icon-gift"></i> <span class="update-notice"> <span><strong id="b1_approvalor">没有新消息!</strong></span> <span id="b1_comment">'+getdateTime(new Date().getTime())+'</span></span></div>';
			$("#msgWrap").html(msgHtml);
			$('#msgModal').modal('toggle');
			return false;
			}
		var msgHtml = '';
		$.each(data, function(index, element){
			msgHtml += '<div class="new-update clearfix"> <i class="icon-gift"></i> <span class="update-notice"> <span><strong id="b1_approvalor">'+element.content+'</strong></span> <span id="b1_comment">'+getdateTime(element.createDate)+'</span></span></div>';
			});
	    $("#msgWrap").html(msgHtml);
		$('#msgModal').modal('toggle');
		$("#sumMsg").html("0");//清零消息
	});
}
function getdateTime(dateTime)
{
	var d = new Date(dateTime);
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDay()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
}
window.onload = function(){
	//如果是作者的话用另一个ajax查询
	if(<%=commonMessageFlag==1%>){
	$.post("/worksApproval/approvalorMsgCount",{ t:"123456" },
			function(data){
		//alert(data[0]);
		$("#msg1").html(data[0]);
		$("#sumMsg").html(data[0]);
	});
	}else if(<%=commonMessageFlag==2%>){
		$.post("/worksApproval/getAuthorMsgListCount",{ t:"123456" },
				function(data){
			//alert(data[0]);
			$("#msg2").html(data[0]);
			$("#sumMsg").html(data[0]);
		});	
		}else if(<%=commonMessageFlag==3%>){
			$.post("/worksApproval/approvalorMsgCount",{ t:"123456" },
				function(data){
			//alert(data[0]);
			$("#msg3").html(data[0]);
			$("#sumMsg").html(data[0]);
		});	
		}else{
			$("#msg3").html(0);
			$("#sumMsg").html(0);
		}
};

</script>