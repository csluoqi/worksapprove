<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="css/matrix-login.css" />
        <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
    </head>
    <body>
        <div id="loginbox">            
            <form id="registerform" class="form-vertical" >
				 <div class="control-group normal_text"> <h3><img src="img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input id="username" type="text" placeholder="请输入用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input id="password" type="password" placeholder="请输入密码" />
                        </div>
                    </div>
                </div>
                
                <!--确认密码  -->
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-lock"></i></span><input id="surePassword" type="password" placeholder="请输入确认密码" />
                        </div>
                    </div>
                </div>
                <div id="errormsg_div" class="main_input_box">
                <%-- <span class="bg_lo" style="padding: 4px 9px;color: #fff;display: inline-block;" id="errormsg">请输入用户名和密码</span> --%>
                </div>
        		<div class="form-actions">
        		 <span id="loginbtn"  class="pull-left"><a href="/worksApproval/login.html" class="flip-link btn btn-success" id="to-login">&laquo; 前往登录页面</a></span>
        		 <span class="pull-right"><button type="submit"  onclick="return registerSubmit();" class="btn btn-success"> 注册</button></span>
                    
                </div>
            </form>
        </div>
        
        <script src="js/jquery.min.js"></script>  
        <script src="js/portalRegister.js"></script> 
    </body>
</html>