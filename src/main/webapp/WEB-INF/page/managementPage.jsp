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
			<h1>页面管理</h1>
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
							<h5>页面列表</h5>
							
						 <div style="float: right; padding-top: 3px;">

								<a class="btn btn-success"  href="${basePath}managementPage.html?t=<%=new Date().getTime()%>">刷新</a>
								<a href="#newPage" data-toggle="modal" class="btn btn-primary">添加</a>
								<div id="newPage" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form class="form-horizontal" method="post"
												action="${basePath}newPage" name="page_add" id="page_add"
												novalidate="novalidate">

												<div class="control-group">
													<label class="control-label">页面文件名称 :</label>
													<div class="controls">
														<input id="new_url" name="url" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">文件中文名字 :</label>
													<div class="controls">
														<input name="pageName" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">说明 :</label>
													<div class="controls">
														<textarea name="notes" class="span11"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">使用状态 :</label>
													<div class="controls">
														<select name="useState">
														<option value="true">启用</option>
														<option value="false">关闭</option>
														</select>
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
								<!-- <a data-toggle="modal"  id="updatePage_a" href="#updatePage" class="btn btn-warning">修改</a> -->
								<button id="updatePage_a" class="btn btn-warning">修改</button>
								<div id="updatePage_modal" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form  class="form-horizontal" method="post"
												action="${basePath}updatePage" name="page_update" id="page_update"
												novalidate="novalidate">

													<input type="hidden"  name="id" id="update_page_id"/>
													<div class="control-group">
													<label class="control-label">页面文件名称 :</label>
													<div class="controls">
														<input id="update_url" name="url" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">文件中文名字 :</label>
													<div class="controls">
														<input id="update_pageName" name="pageName" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">说明 :</label>
													<div class="controls">
														<textarea id="update_notes" name="notes" class="span11"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">使用状态 :</label>
													<div class="controls">
														<select id="update_useState" name="useState">
														<option value="true">启用</option>
														<option value="false">关闭</option>
														</select>
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
								<button id="deletePage" class="btn btn-danger">删除</button>
							</div> 

						</div>
						<!-- test upwindows end  -->
						 


						<div class="widget-content nopadding">
<!-- dynamic table start -->
 <table id="pageTable" class="table table-bordered data-table">

              <thead>
                <tr>
                  <th width="5%"> <input disabled="disabled" type="checkbox"> </th> 
                  <th width="5%">编号</th>
                  <th width="20%">页面文件名称</th>
                  <th width="10%">中文名字</th>
                  <th width="20%">注释</th>
                  <th hidden="hidden">使用状态编号</th>
                  <th width="10%">使用状态</th>
                  <th width="10%">排序</th>
                  <th width="20%">创建时间</th>
                </tr>
              </thead>
              <tbody id="page_tbody">
              <c:forEach items="${pageList}" var="page">
              	<tr class="gradeA">
              	  <td style="text-align: center;" width="10%"> <input type="checkbox" value="${page.id }"> </td>
                  <td>${page.id }</td>
                  <td>${page.url }</td>
                  <td>${page.pageName }</td>
                  <td>${page.notes }</td>
                  <td hidden="hidden">${page.useState }</td>
                  <c:if test="${page.useState }">
                  <td>启用</td>
                  </c:if>
                  <c:if test="${!page.useState }">
                  <td>关闭</td>
                  </c:if>
                  <td>${page.order }</td>
                  <td><fmt:formatDate value="${page.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
    
    
	<!-- -->
	 <script src="js/worksApproval/managementPage.js"></script>

</body>
</html>
