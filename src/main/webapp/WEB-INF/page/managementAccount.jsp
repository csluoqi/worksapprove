<%@page import="java.util.Date"%>
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
<link rel="stylesheet" href="css/datepicker.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<!-- <link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'> -->
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
	<div id="content">
		<div id="content-header">
			<div id="breadcrumb">
			</div>
			<h1>用户管理</h1>
		</div>
		<div class="container-fluid">
			<hr>
			
			<c:if test="${not empty message}">
              <input type="hidden" id="result_message" value="${message}"/>
			</c:if>
			
			<div class="row-fluid">
				<div class="span12">

					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-th"></i>
							</span>
							<h5>用户列表</h5>
							
						 <div style="float: right; padding-top: 3px;">

								<a class="btn btn-success"  href="${basePath}managementAccount.html?t=<%=new Date().getTime()%>">刷新</a>
								<a href="#newAccount" data-toggle="modal" class="btn btn-primary">添加</a>
								<div id="newAccount" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form class="form-horizontal" method="post"
												action="${basePath}newAccount" name="account_add" id="account_add"
												novalidate="novalidate">

												<div class="control-group">
													<label class="control-label">昵称 :</label>
													<div class="controls">
														<input id="new_username" name="username" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">真实姓名 :</label>
													<div class="controls">
														<input name="realName" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
									              <label class="control-label">出生日期 : </label>
									              <div class="controls">
									               <input id="new_birthday" name="birthday_" class="span11" type="text"  onClick="WdatePicker()"/>
									               </div>
									            </div>
												<div class="control-group">
										            <label class="control-label">性别 :</label>
										            <div class="controls">
										              <label><input name="gender" value="1" checked="checked"  type="radio"> 男</label>
										              <label><input name="gender" value="0"  type="radio"> 女</label>
										            </div>
										         </div>
												<div class="control-group">
													<label class="control-label">手机号码 :</label>
													<div class="controls">
														<input name="phoneNumber" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">邮箱 :</label>
													<div class="controls">
														<input name="email" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地址 :</label>
													<div class="controls">
														<input name="address" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">个性签名 :</label>
													<div class="controls">
														<input name="profile" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色 :</label>
													<div class="controls">
														<select name="userRole.id" class=" span11">
															<c:forEach items="${roleList}" var="role">
																<option value="${role.id}">${role.shortName}</option>														
															</c:forEach>															
														</select>
														 
													</div>
												</div>

												<div class="modal-footer"
													style="background-color: #fff; border-top: 0px #ddd;">
													<!-- <input value="Validate" class="btn btn-success" > -->
													<input value="提交" class="btn btn-primary" type="submit" />
													<a data-dismiss="modal" class="btn">取消</a>
												</div>
											</form>

										</div>


									</div>

								</div>
								<!-- 添加upwindows end -->
								<!-- <a data-toggle="modal"  id="updateAccount_a" href="#updateAccount" class="btn btn-warning">修改</a> -->
								<button id="updateAccount_a" class="btn btn-warning">修改</button>
								<div id="updateAccount_modal" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form  class="form-horizontal" method="post"
												action="${basePath}updateAccount" name="account_update" id="account_update"
												novalidate="novalidate">
												<div class="control-group">
													<label class="control-label">昵称 :</label>
													<div class="controls">
													    <input  id="update_id" name="id" value="" type="hidden" />
														<input  id="update_username" name="username" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">真实姓名 :</label>
													<div class="controls">
														<input id="update_realName" name="realName" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
									              <label class="control-label">出生日期 : </label>
									              <div class="controls">
									               <input id="update_birthday" name="birthday_" class="span11" type="text"  onClick="WdatePicker()"/>
									               </div>
									            </div>
												<div class="control-group">
										            <label class="control-label">性别 :</label>
										            <div class="controls">
										              <label><input id="gender_m" name="gender" value="1" checked="checked"  type="radio"> 男</label>
										              <label><input id="gender_f" name="gender" value="0"  type="radio"> 女</label>
										            </div>
										         </div>
												<div class="control-group">
													<label class="control-label">手机号码 :</label>
													<div class="controls">
														<input id="update_phoneNumber" name="phoneNumber" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">邮箱 :</label>
													<div class="controls">
														<input id="update_email" name="email" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地址 :</label>
													<div class="controls">
														<input id="update_address" name="address" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">个性签名 :</label>
													<div class="controls">
														<input name="profile" id="update_profile" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色 :</label>
													<div class="controls">
														<select id="update_userRoleId" name="userRole.id" class=" span11">
															<c:forEach items="${roleList}" var="role">
																<option value="${role.id}">${role.shortName}</option>														
															</c:forEach>															
														</select>
														 
													</div>
												</div>
												<div class="modal-footer"
													style="background-color: #fff; border-top: 0px #ddd;">
													<!-- <input value="Validate" class="btn btn-success" > -->
													<input value="更新" class="btn btn-primary" type="submit" />
													<a data-dismiss="modal" class="btn">取消</a>
												</div>
											</form>
										</div>
									</div>
								</div>
								<button id="deleteAccount" class="btn btn-danger">删除</button>
							</div> 

						</div>
						<!-- test upwindows end  -->
						
						<div class="widget-content nopadding">
<!-- dynamic table start -->
 <table id="accountTable" class="table table-bordered data-table">
              <thead>
                <tr>
                  <th > <input width="10px" disabled="disabled" type="checkbox"> </th> 
                  <th width="5%">编号</th>
                  <th width="10%">昵称</th>
                  <th width="10%">真实姓名</th>
                  <th width="10%">出生日期</th>
                  <th width="5%">性别</th>
                  <th width="10%">手机号码</th>
                  <th width="10%">邮箱</th>
                  <th width="10%">地址</th>
                  <th width="10%">个性签名</th>
                  <th width="10%">角色</th>
                  <th width="10%">注册时间</th>
                  
                </tr>
              </thead>
              <tbody id="account_tbody">
              <c:forEach items="${userList}" var="user">
              	<tr class="gradeA">
              	  <td style="text-align: center;" > <input width="10px" type="checkbox" value="${user.id }"> </td>
                  <td>${user.id }</td>
                  <td>${user.username }</td>
                  <td>${user.realName }</td>
                  <td><fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/></td>
                  <c:if test="${user.gender==1 }">
                  <td>男</td>
                  </c:if>
                  <c:if test="${user.gender==0 }">
                  <td>女</td>
                  </c:if>
                  
                  <td>${user.phoneNumber }</td>
                  <td>${user.email }</td>
                  <td>${user.address }</td>
                  <td>${user.profile }</td>
                  <td>${user.userRole.shortName }</td>
                  <td><fmt:formatDate value="${user.registerDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
<!-- dynamic table end -->
						 


						</div>
					</div>
 
				</div>
			</div>
		</div>
	</div>
	<!-- alert start -->
	<div id="alertModal" class="modal hide">
        <div class="modal-header">
          <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>提示</h3>
        </div>
        <div class="modal-body">
                <p id="alertMessage" style="font-size:2em">确认删除?</p>
        </div>
         <div class="modal-footer"> 
         	<button id="sureDelete" class="btn btn-primary">确定</button>
         	<a data-dismiss="modal" class="btn" href="#">取消</a> </div>
    </div>
	<!-- alert end-->
		<script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/jquery.gritter.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.uniform.js"></script> 
	<script src="js/select2.min.js"></script> 
 <script src="js/My97DatePicker/WdatePicker.js"></script> 
    <script src="js/jquery.validate.js"></script> 
    <script src="js/messages_zh.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/matrix.js"></script>
<!--   	<script src="js/matrix.form_common.js"></script> -->

<%--    <script src="js/matrix.form_validation.js"></script> --%>    
	<script src="js/worksApproval/managementAccount.js"></script>


</body>
</html>
