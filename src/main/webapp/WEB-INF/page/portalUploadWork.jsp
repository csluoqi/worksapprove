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
		<h1>稿件上传</h1>
	</div>
<div class="container-fluid">
  <hr>
  <div class="row-fluid">
    <div class="span12">
 
      <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5>编辑作品信息</h5>
        </div>
        <div class="widget-content nopadding">
		<form class="form-horizontal" method="post"
		action="${basePath}uploadWork" name="works_add" id="works_add"
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
							style="position: absolute; top: 100px; left: 415px; margin: 0; padding: 0; font-size: 20px; cursor: pointer; opacity: 0; width: 70px; filter: alpha(opacity = 0);" />
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

            <div class="form-actions">
              <button type="submit" class="btn btn-success">提交</button>
            </div>
          </form>
        </div>
      </div>
       
    </div>
    
  </div>
  
</div></div>

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
    
    


	 

	<script src="js/worksApproval/managementWorks.js"></script>
	<script type="text/javascript">
		//用于添加
		document.getElementById("uploadBtn").onchange = function() {
			//console.log("1212");
			document.getElementById("uploadFile").value = this.value;
		};
		//用于更新
		/* document.getElementById("uploadBtn2").onchange = function() {
			//console.log("1212");
			document.getElementById("update_uploadFile").value = this.value;
		}; */
		
	</script>
</body>
</html>
