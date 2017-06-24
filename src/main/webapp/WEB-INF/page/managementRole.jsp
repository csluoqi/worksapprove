<%@ page import="java.util.Date"%>
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
			<h1>角色管理</h1>
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

								<a class="btn btn-success"  href="${basePath}managementRole.html?t=<%=new Date().getTime()%>">刷新</a>
								<a href="#newRole" data-toggle="modal" class="btn btn-primary">添加</a>
								<div id="newRole" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form class="form-horizontal" method="post"
												action="${basePath}newRole" name="role_add" id="role_add"
												novalidate="novalidate">
												<!--  
												id
												shortName
												comment
												pageSet
												
												-->
												<div class="control-group">
													<label class="control-label">名称 :</label>
													<div class="controls">
														<input id="new_shortName" name="shortName" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">简介 :</label>
													<div class="controls">
														<input name="comment" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">可访问页面列表 :</label>
													<div class="controls">
														<select name="pages" id="newPageDetail_select" multiple="multiple" size="10">
													  		<c:forEach items="${pageList }" var="page" >
													  		<option value="${page.id }">${page.pageName }</option>
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
								<!-- <a data-toggle="modal"  id="updateRole_a" href="#updateRole" class="btn btn-warning">修改</a> -->
								<button id="updateRole_a" class="btn btn-warning">修改</button>
								<div id="updateRole_modal" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form  class="form-horizontal" method="post"
												action="${basePath}updateRole" name="role_update" id="role_update"
												novalidate="novalidate">

													<input type="hidden"  name="id" id="update_role_id"/>
													<div class="control-group">
													<label class="control-label">名称 :</label>
													<div class="controls">
														<input id="update_shortName" name="shortName" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">简介 :</label>
													<div class="controls">
														<input id="update_comment" name="comment" type="text" class="span11" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">可访问页面列表 :</label>
													<div class="controls">
														<select name="pages" id="updatePageDetail_select" multiple="multiple" size="10">
												  
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
								<button id="deleteRole" class="btn btn-danger">删除</button>
							</div> 

						</div>
						<!-- test upwindows end  -->

						<div class="widget-content nopadding">
<!-- dynamic table start -->
 <table id="roleTable" class="table table-bordered data-table">

              <thead>
                <tr>
                  <th width="5%"> <input disabled="disabled" type="checkbox"> </th> 
                  <th width="5%">编号</th>
                  <th width="35%">角色名称</th>
                  <th width="35%">注释</th>
                  <th width="20%">可访问页面详情</th>
                </tr>
              </thead>
              <tbody id="role_tbody">
              <c:forEach items="${roleList}" var="role">
              	<tr class="gradeA">
              	  <td style="text-align: center;" width="10%"> <input type="checkbox" value="${role.id }"> </td>
                  <td>${role.id }</td>
                  <td>${role.shortName }</td>
                  <td>${role.comment }</td>
                  <td><button class="pageDetail" class="btn btn-mini"><i class="icon-tasks"></i> 详情</button></td>
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
	
	<!-- pageDetail start -->
	<div id="showPageDetail" class="modal hide" style="display: none;"
	aria-hidden="true">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>可访问页面列表</h3>
	</div>
	<div class="modal-body">
		<div class="widget-content nopadding"
			style="border-bottom: 0px #cdcdcd;">
			<form  class="form-horizontal" method="post">
					<!-- <select id="showPageDetail_select" multiple="multiple" size="10">
					  
					</select>	 -->				 
					<div class="control-group">
						<label class="control-label">可访问页面列表 :</label>
						<div class="controls">
							<select id="showPageDetail_select" multiple="multiple" size="10">
					  
							</select>
						</div>
					</div>
				


			<!-- 	<div class="modal-footer"
					style="background-color: #fff; border-top: 0px #ddd;">
					<input value="Validate" class="btn btn-success" >
					<input value="更新" class="btn btn-primary" type="submit" />
					<a data-dismiss="modal" class="btn">取消</a>
				</div> -->
			</form>
		</div>
	</div>
</div>
	<!-- pageDetail end -->

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
	 <script src="js/worksApproval/managementRole.js"></script>

</body>
</html>
