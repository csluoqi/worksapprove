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
            <form id="loginform" class="form-vertical">
				 <div class="control-group normal_text"> <h3><img src="img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input id="username" type="text" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input id="password" type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div id="errormsg_div" class="main_input_box">
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
                    <span class="pull-right"><button type="submit" onclick="return loginSubmit()" class="btn btn-success" > 登 录</button></span>
                </div>
            </form>
            <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">请联系管理员初始化密码</p>
				<p class="normal_text">管理员邮箱 : admin@approval.com</p>
                   <!--  <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div> -->
               
                <div class="form-actions">
                    <span class="pull-right"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 重新登录</a></span>
                    <!-- <span class="pull-right"><a class="btn btn-info">Reecover</a></span> -->
                </div>
            </form>
        </div>
        
        <script src="js/jquery.min.js"></script>  
        <script src="js/portalLogin.js"></script> 
    </body>

</html>
