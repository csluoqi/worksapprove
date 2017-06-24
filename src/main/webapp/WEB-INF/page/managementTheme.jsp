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
			<h1>主题管理</h1>
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

								<a class="btn btn-success"  href="${basePath}managementTheme.html?t=<%=new Date().getTime()%>">刷新</a>
								<a href="#newTheme" data-toggle="modal" class="btn btn-primary">添加</a>
								<div id="newTheme" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form class="form-horizontal" method="post"
												action="${basePath}newTheme" name="theme_add" id="theme_add"
												novalidate="novalidate">

												<div class="control-group">
													<label class="control-label">主题名称 :</label>
													<div class="controls">
														<input id="name" name="name" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">简介 :</label>
													<div class="controls">
														<textarea name="comment" class="span11"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序 :</label>
													<div class="controls">
														<input name="order" type="text" class="span11" />
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
								<!-- <a data-toggle="modal"  id="updateTheme_a" href="#updateTheme" class="btn btn-warning">修改</a> -->
								<button id="updateTheme_a" class="btn btn-warning">修改</button>
								<div id="updateTheme_modal" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form  class="form-horizontal" method="post"
												action="${basePath}updateTheme" name="theme_update" id="theme_update"
												novalidate="novalidate">

												<div class="control-group">
													<label class="control-label">主题名称 :</label>
													<div class="controls">
														<input id="update_id" name="id" type="hidden"/>
														<input id="update_name" name="name" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">简介 :</label>
													<div class="controls">
														<textarea id="update_comment" name="comment" class="span11"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序 :</label>
													<div class="controls">
														<input id="update_order" name="order" type="text" class="span11" />
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
								<button id="deleteTheme" class="btn btn-danger">删除</button>
							</div> 

						</div>
						<!-- test upwindows end  -->
						 


						<div class="widget-content nopadding">
<!-- dynamic table start -->
 <table id="themeTable" class="table table-bordered data-table">
 <!-- 
 编号
主题名字
简介
序号
创建时间
  -->
              <thead>
                <tr>
                  <th width="10%"> <input disabled="disabled" type="checkbox"> </th> 
                  <th width="10%">编号</th>
                  <th width="20%">主题名字</th>
                  <th width="30%">简介</th>
                  <th width="10%">序号</th>
                  <th width="20%">创建时间</th>
                </tr>
              </thead>
              <tbody id="theme_tbody">
              <c:forEach items="${worksThemeList}" var="theme">
              	<tr class="gradeA">
              	  <td style="text-align: center;" width="10%"> <input type="checkbox" value="${theme.id }"> </td>
                  <td>${theme.id }</td>
                  <td>${theme.name }</td>
                  <td>${theme.comment }</td>
                  <td>${theme.order }</td>
                  <td><fmt:formatDate value="${theme.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/matrix.js"></script>
 <%-- 	<script src="js/matrix.tables.js"></script>   --%>
	
    <script src="js/jquery.validate.js"></script> 
    <script src="js/messages_zh.js"></script>
<%--    <script src="js/matrix.form_validation.js"></script> --%>
    
    
	<script src="js/worksApproval/managementTheme.js"></script>

</body>
</html>
