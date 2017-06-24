<%@page import="java.util.List"%>
<%@page import="com.worksApproval.admin.entity.Works"%>
<%@page import="com.worksApproval.admin.constant.WorksStatusConst"%>
<%@page import="com.worksApproval.admin.constant.HttpAssist"%>
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
			<h1>个人中心</h1>
		</div>
		<div class="container-fluid">
			<hr>
			<div class="row-fluid">
				<div class="span12">

					<div class="widget-box">
						<!-- 上部的操作按钮 -->
						<div class="widget-title">
							<span class="icon"> <i class="icon-th"></i>
							</span>
							<h5>稿件列表</h5>
							<c:if test="${not empty message}">
				              <input type="hidden" id="result_message" value="${message}"/>
							</c:if>
							<div style="float: right; padding-top: 3px;">
								<a class="btn btn-success"
									href="${basePath}portalUserCenter.html?t=<%=new Date().getTime()%>">刷新</a>
								<!-- <a href="#newWorks" data-toggle="modal" class="btn btn-primary">添加</a> -->
								<div id="newWorks" class="modal hide" style="display: none;"
									aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>添加</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form class="form-horizontal" method="post"
												action="${basePath}portalNewWorks" name="works_add" id="works_add"
												novalidate="novalidate" enctype="multipart/form-data">

												<div class="control-group">
													<label class="control-label">主题 :</label>
													<div class="controls">
														<!-- <input name="order" type="text" class="span11" /> -->
														<select name="theme.id" class="form-control">
															<c:forEach items="${worksThemeList}" var="theme">
																<option value="${theme.id}">${theme.name}</option>														
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">文件上传 :</label>
													<div class="controls">
														<input name="uploadFile" id="uploadFile" placeholder="Choose File"
															readonly="readonly" />
														<div class="btn btn-primary">
															<span >Upload</span> 
															<input name="worksFile"  id="uploadBtn" type="file"
																style="position: absolute; top: 78px; right: 52px; margin: 0; padding: 0; font-size: 20px; cursor: pointer; opacity: 0; width: 80px; filter: alpha(opacity = 0);" />
														</div>
													</div>
												</div>


												<div class="control-group">
													<label class="control-label">概要 :</label>
													<div class="controls">
														<textarea name="summary" class="span11"></textarea>
													</div>
												</div>
												<!--提交表单的状态 start-->
												<div class="control-group">
													<!-- 													<label class="control-label">主题 :</label>
													<div class="controls">
														<input name="order" type="text" class="span11" />
													</div> -->

													<div class="alert alert-info alert-block">
														<a class="close" data-dismiss="alert" href="#">×</a>
														<h4 class="alert-heading">提示!</h4>
														<!--
														文件是必须选择的,且大小建议不要超过10M
														 
														 -->
														<div id="errorList">
														<span class="help-inline">
															文件是必须选择的,且大小建议不要超过10M
														</span>
														</div>
													</div>
												</div>
												<!--提交表单的状态 end  -->

												<div class="modal-footer"
													style="background-color: #fff; border-top: 0px #ddd;">
													<!-- <input value="Validate" class="btn btn-success" > -->
													<button id="" class="btn btn-primary" >提交</button>
													<a data-dismiss="modal" class="btn">取消</a>
												</div>
											</form>

										</div>


									</div>

								</div>
								<!-- 添加upwindows end -->
								<!-- <a data-toggle="modal"  id="updateWorks_a" href="#updateWorks" class="btn btn-warning">修改</a> -->
								<button id="updateWorks_a" class="btn btn-warning">修改</button>
								<div id="updateWorks_modal" class="modal hide"
									style="display: none;" aria-hidden="true">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">×</button>
										<h3>修改</h3>
									</div>
									<div class="modal-body">
										<div class="widget-content nopadding"
											style="border-bottom: 0px #cdcdcd;">
											<form class="form-horizontal" method="post"
												action="${basePath}portalUpdateWorks" name="works_update"
												id="works_update" novalidate="novalidate" enctype="multipart/form-data">
													<input name="id" type="hidden" id="update_works_id">
												 <div class="control-group">
													<label class="control-label">主题 :</label>
													<div class="controls">
														<!-- <input name="order" type="text" class="span11" /> -->
														<select id="update_theme_id" name="theme.id" class="form-control">
															<c:forEach items="${worksThemeList}" var="theme">
																<option value="${theme.id}">${theme.name}</option>														
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">文件上传 :</label>
													<div class="controls">
														<input name="uploadFile" id="update_uploadFile" placeholder="Choose File"
															readonly="readonly" />
														<div class="btn btn-primary">
															<span >Upload</span> 
															<input name="worksFile"  id="uploadBtn2" type="file"
																style="position: absolute; top: 78px; right: 52px; margin: 0; padding: 0; font-size: 20px; cursor: pointer; opacity: 0; width: 80px; filter: alpha(opacity = 0);" />
														</div>
													</div>
												</div>


												<div class="control-group">
													<label class="control-label">概要 :</label>
													<div class="controls">
														<textarea id="update_summary" name="summary" class="span11"></textarea>
													</div>
												</div>
												<!--提交表单的状态 start-->
												<div class="control-group">
													<!-- 													<label class="control-label">主题 :</label>
													<div class="controls">
														<input name="order" type="text" class="span11" />
													</div> -->

													<div class="alert alert-info alert-block">
														<a class="close" data-dismiss="alert" href="#">×</a>
														<h4 class="alert-heading">提示!</h4>
														<!--
														文件是必须选择的,且大小建议不要超过10M
														 
														 -->
														<div id="update_errorList">
														<span class="help-inline">
															文件是必须选择的,且大小建议不要超过10M
														</span>
														</div>
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
								<button id="deleteWorks" class="btn btn-danger">删除</button>
							</div>

						</div>




						<!-- table start  -->
						<div class="widget-content nopadding">
							<!-- dynamic table start -->
							<table id="themeTable" class="table table-bordered data-table">
								<thead>
									<tr>
										<th><input width="10" disabled="disabled" type="checkbox"></th>
										<th>编号</th>
										<th hidden="hidden">主题编号</th>
										<th>主题</th>
										<th>稿件名字</th>
										<th>作者</th>
										<th>稿件概要</th>
										<th>稿件大小</th>
										<th>状态</th>
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody id="theme_tbody">
									<%-- <c:forEach items="${worksList}" var="works"> --%>
									<%
									List<Works> worksList = (List<Works>)request.getAttribute("worksList");
									if(worksList!=null)
									{
									for(Works works :worksList)
									{
									%>
										<tr class="gradeA">
											<td style="text-align: center;"><input width="10px"
												type="checkbox" value="<%=works.getId()%>"></td>
											<td width="5%"><%=works.getId()%></td>
											<td hidden="hidden"><%=works.getTheme().getId() %></td>
											<td width="10%"><%=works.getTheme().getName() %></td>
											<td width="15%"><a href="${basePath}<%=HttpAssist.DOWNLOAD_URL %>?<%=HttpAssist.DOWNLOAD_ID %>=<%=works.getId()%>&id=<%=works.getTheme().getId()%>&<%=HttpAssist.BACK_URL %>=<%=request.getServletPath() %>" ><%=works.getName() %></a></td>
											<td width="10%"><%=works.getAuthor().getUsername() %></td>
											<td width="17%"><%=works.getSummary() %></td>
											 
											<%
											if(works.getFilesize()>1024)
											{
											%>
											<td width="8%"><fmt:formatNumber type="number"  maxFractionDigits="3" value="<%=works.getFilesize()/1024 %>" />mb</td>
											
											<%
											}else {
												%>
												<td width="8%"><fmt:formatNumber type="number"  maxFractionDigits="5" value="<%=works.getFilesize()%>" />kb</td>
												<%
											}%>
											
											
											<td width="10%"><%=WorksStatusConst.worksStatusMap.get(works.getStatus()) %></td>
											<td width="15%"><fmt:formatDate
													value="<%=works.getCreateDate() %>" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
										<%
										}
									}
									%>
								</tbody>
							</table>
							<!-- dynamic table end -->



						</div>
						<!--table end  -->

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
 <!-- 	<script src="js/matrix.tables.js"></script>   -->
	
    <script src="js/jquery.validate.js"></script> 
    <script src="js/messages_zh.js"></script>
   <!--  <script src="js/matrix.form_validation.js"></script> -->
    
    


	 

	<script src="js/worksApproval/portalUserCenter.js"></script>
	<script type="text/javascript">
		//用于添加
		document.getElementById("uploadBtn").onchange = function() {
			//console.log("1212");
			document.getElementById("uploadFile").value = this.value;
		};
		//用于更新
		document.getElementById("uploadBtn2").onchange = function() {
			//console.log("1212");
			document.getElementById("update_uploadFile").value = this.value;
		};
		
	</script>
</body>
</html>
