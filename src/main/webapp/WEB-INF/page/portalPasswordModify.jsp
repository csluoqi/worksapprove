<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String nameSpace = request.getContextPath();
%>
<%
	String projectPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ nameSpace + "/";
%>
<c:set value="<%=projectPath%>" var="basePath" />

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

<style type="text/css">
</style>
</head>
<body>

 <jsp:include page="common/navbar.jsp"></jsp:include> 
<div id="content">
  <div id="content-header">
    <div id="breadcrumb">  
    </div>
    <h1>密码修改</h1>
  </div>
  <div class="container-fluid"><hr>
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
          <c:if test="${not empty message}">
              <input type="hidden" id="result_message" value="${message}"/>
			</c:if>
            <h5>密码修改</h5>
          </div>
          <div class="widget-content nopadding">
            <form class="form-horizontal" method="post" action="${basePath}portalModifyPassword" name="portalProfileForm" id="passwordModifyForm">
              <div class="control-group">
                <label class="control-label">原密码：</label>
                <div class="controls">
                  <!-- <input type="text" name="username" readonly="readonly"/> -->
                  <input type="password" id="password" name="password"/>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">新密码：</label>
                <div class="controls">
                  <input type="password" name="newPassword" />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">确认新密码：</label>
                <div class="controls">
                  <input type="password" name="sureNewPassword" />
                </div>
              </div>
              <div class="form-actions" style="padding-left:180px;">
                <input type="submit" value="确认修改" class="btn btn-success">
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
<script src="js/jquery.gritter.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/matrix.js"></script> 
<script src="js/jquery.validate.js"></script> 
<script src="js/messages_zh.js"></script>
<script src="js/portalPasswordModify.js"></script>
</body>
</html>
 