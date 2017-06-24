<%@page import="org.apache.commons.lang3.time.DateUtils"%>
<%@page import="org.apache.commons.lang3.time.FastDateFormat"%>
<%@page import="org.infinispan.factories.annotations.SurvivesRestarts"%>
<%@page import="com.worksApproval.admin.constant.SessionUtil"%>
<%@page import="com.worksApproval.admin.entity.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
    String nameSpace = request.getContextPath();
%>
<%
    String projectPath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ nameSpace + "/";
%>
<c:set value="<%=projectPath %>" var="basePath"/>

<!DOCTYPE html>
<html lang="en">
<head>
<title>稿件审核系统</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>

<body>

<jsp:include page="common/navbar.jsp"></jsp:include>
<div id="content">
  <div id="content-header">
    <div id="breadcrumb">  
    </div>
    <h1>个人资料</h1>
  </div>
  <div class="container-fluid"><hr>
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
            <h5>编辑个人资料</h5>
  							<c:if test="${not empty message}">
				              <input type="hidden" id="result_message" value="${message}"/>
							</c:if>
            
          </div>
          <%
         UserInfo usr = SessionUtil.getUsrFromSession(request);
          %>
          <div class="widget-content nopadding">
            <form class="form-horizontal" method="post" action="${basePath}updateProfile" name="portalProfileForm" id="portalProfileForm">
              <div class="control-group">
                <label class="control-label">昵称：</label>
                <div class="controls">
                  <input type="hidden" id = "userId" value=""/>
                  <%--从session中取出来回显不可编辑 --%>
                  <!-- <input type="text" name="username" readonly="readonly"/> -->
                  <input type="text" name="username" value="<%=usr.getUsername()==null ? "" : usr.getUsername()%>"/>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">真实姓名：</label>
                <div class="controls">
                  <input type="text" name="realName" value="<%=usr.getRealName() == null ? "":usr.getRealName()%>">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">出生日期：</label>
                <div class="controls">
                <%if(usr.getBirthday()==null) {%>
                  <input  name="birthday_" type="text"  onClick="WdatePicker()" />
                <%
                }else{%>
                  <input  name="birthday_" type="text"  onClick="WdatePicker()" value='<%=FastDateFormat.getInstance("yyyy-MM-dd").format(usr.getBirthday())%>'/>
                <%} %>
                
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">性别：</label>
                <div class="controls">
                  <!-- <input type="text" name="gender" > -->
                  <select name="gender">
                  <%if(usr.getGender()==null || usr.getGender()!=0) 
                  {
                  %>
                  <option value="1">男</option>
                  <option value="0">女</option>
                  <%
                  }
                  else{%>
                  <option value="0">女</option>
                  <option value="1">男</option>
                  <%
                  }%>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">手机号码：</label>
                <div class="controls">
                  <input type="text" name="phoneNumber" value="<%=usr.getPhoneNumber() == null ? "" : usr.getPhoneNumber()%>">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">邮箱：</label>
                <div class="controls">
                  <input type="text" name="email" value="<%=usr.getEmail()==null ? "" : usr.getEmail()%>"/>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">通讯地址：</label>
                <div class="controls">
                  <input type="text" class="span6" name="address" value="<%=usr.getAddress()==null ? "" : usr.getAddress()%>"/>
                </div>
              </div>
              
              <div class="form-actions" style="padding-left:180px;">
                <input type="submit" value="确认" class="btn btn-success">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
 
  </div>
</div>

<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/matrix.js"></script> 
<script src="js/My97DatePicker/WdatePicker.js"></script>

<script src="js/jquery.gritter.min.js"></script>
<script src="js/jquery.validate.js"></script> 
<script src="js/messages_zh.js"></script>

<script src="js/portalProfile.js"></script>

</body>
</html>
