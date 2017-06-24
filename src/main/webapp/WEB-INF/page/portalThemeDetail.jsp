<%@page import="com.worksApproval.admin.constant.HttpAssist"%>
<%@page import="java.util.Date"%>
<%@page import="com.worksApproval.admin.entity.Works"%>
<%@page import="com.worksApproval.admin.entity.WorksTheme"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'> -->
</head>
<body>
<jsp:include page="common/portalNavbar.jsp"></jsp:include>
<div style="margin-left: 0px;" id="content" >
  <div id="content-header">
    <div id="breadcrumb"> <!-- <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> --></div>
  </div>
  <div  class="quick-actions_homepage" style="margin-left:20px">
    <ul class="quick-actions">
       <c:forEach items="${themes}" var="themeArr">
<li class="${themeArr[1] }"> <a href="portalThemeDetail.html?id=${(themeArr[0]).id }"><h4>${(themeArr[0]).name }</h4></a> </li>     
       </c:forEach>
       <li class="bg_dy"> <a href="portalUploadWork.html?t=<%=new Date()%>"><h4>我要投稿</h4></a> </li>
      <!-- class="btn tip-bottom" data-original-title="Tooltip in bottom">Bottom -->
    </ul>
  </div>
  <div class="container-fluid">   
        <div class="row-fluid">
		 		<div class="span12">
			        <div class="widget-box">
			          <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
			            <h5>${theme.name }</h5>
			          </div>
			          <div class="widget-content">${theme.comment }</div>
			        </div>
		       </div>
       </div>
     
       
      <%-- <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
            <h5>Full Width <code>class=Span12</code></h5>
          </div>
          <div class="widget-content"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </div>
        </div>
      </div> --%>
    
    	<div >
		<div class="">
			<hr>
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<!-- 上部的操作按钮 -->
						<div class="widget-title">
							<span class="icon"> <i class="icon-th"></i>
							</span>
							<h5>已录用稿件列表</h5>
							<c:if test="${not empty message}">
						           <input type="hidden" id="result_message" value="${message}"/>
							</c:if>
						</div>
						<!-- table start  -->
						<div class="widget-content nopadding">
							<!-- dynamic table start -->
							<table id="themeDetailTable" class="table table-bordered data-table">
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
								<tbody id="themeDetailtbody">
									<c:forEach items="${theme.works}" var="works">
										<tr class="gradeA">
											<td style="text-align: center;"><input width="10px"
												type="checkbox" value="${works.id }"></td>
											<td width="5%">${works.id }</td>
											<td hidden="hidden">${works.theme.id}</td>
											<td width="15%">${works.theme.name }</td>
											<td width="20%"><a href="${basePath}<%=HttpAssist.DOWNLOAD_URL %>?<%=HttpAssist.DOWNLOAD_ID %>=${works.id }&id=${works.theme.id}&<%=HttpAssist.BACK_URL %>=<%=request.getServletPath() %>" >${works.name }</a></td>
											<td width="10%">${works.author.username }</td>
											<td width="17%">${works.summary }</td>
											 <c:if test="${not empty works.filesize }">
											<c:if test="${works.filesize >= 1024 }">
											<td width="8%"><fmt:formatNumber type="number"  maxFractionDigits="3" value="${works.filesize/1024}" />mb</td>
											
											</c:if>
											
											<c:if test="${(works.filesize < 1024)}">
										<%-- 	<td width="8%">${works.filesize/(1024)}kb</td> --%>
											<td width="8%"><fmt:formatNumber type="number"  maxFractionDigits="3" value="${works.filesize}" />kb</td>
											</c:if>
											</c:if>
											 <c:if test="${empty works.filesize }">
											<td width="8%"></td>
											</c:if>
											<%----%>
											<c:if test="${works.status==0 }">
												<td width="5%">初审</td>
											</c:if>
											
											<c:if test="${works.status==1 }">
												<td width="5%">初审</td>
											</c:if>
											<c:if test="${works.status==2 }">
												<td width="5%">复审</td>
											</c:if>
											<c:if test="${works.status==3 }">
												<td width="5%">编辑审核</td>
											</c:if>
											<c:if test="${works.status==4 }">
												<td width="5%">录用</td>
											</c:if>
											<c:if test="${works.status==-1 }">
												<td width="5%">退回</td>
											</c:if>
											<c:if test="${works.status==-3 }">
												<td width="5%">废弃或者删除</td>
											</c:if>
											<c:if test="${works.status==-2 }">
												<td width="5%">投递失败</td>
											</c:if>
											
											<td width="15%"><fmt:formatDate
													value="${works.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!--table end  -->

					</div>


				</div>
			</div>
		</div>
	</div>
    
    <hr>
<!--     <div class="row-fluid">
      <div class="span12">
        <div class="widget-box widget-calendar">
          <div class="widget-title"> <span class="icon"><i class="icon-calendar"></i></span>
            <h5>Calendar</h5>
            <div class="buttons"> <a id="add-event" data-toggle="modal" href="#modal-add-event" class="btn btn-inverse btn-mini"><i class="icon-plus icon-white"></i> Add new event</a>
              <div class="modal hide" id="modal-add-event">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">×</button>
                  <h3>Add a new event</h3>
                </div>
                <div class="modal-body">
                  <p>Enter event name:</p>
                  <p>
                    <input id="event-name" type="text" />
                  </p>
                </div>
                <div class="modal-footer"> <a href="#" class="btn" data-dismiss="modal">Cancel</a> <a href="#" id="add-event-submit" class="btn btn-primary">Add event</a> </div>
              </div>
            </div>
          </div>
          <div class="widget-content">
            <div class="panel-left">
              <div id="fullcalendar"></div>
            </div>
            <div id="external-events" class="panel-right">
              <div class="panel-title">
                <h5>Drag Events to the calander</h5>
              </div>
              <div class="panel-content">
                <div class="external-event ui-draggable label label-inverse">My Event 1</div>
                <div class="external-event ui-draggable label label-inverse">My Event 2</div>
                <div class="external-event ui-draggable label label-inverse">My Event 3</div>
                <div class="external-event ui-draggable label label-inverse">My Event 4</div>
                <div class="external-event ui-draggable label label-inverse">My Event 5</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> -->
  </div>
</div>

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

<script src="js/worksApproval/portalThemeDetail.js"></script> 
<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  /* function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
} */
</script>


</body>
</html>
