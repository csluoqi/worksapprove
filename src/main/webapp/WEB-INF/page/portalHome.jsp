<%@page import="java.util.Iterator"%>
<%@page import="com.worksApproval.admin.entity.Works"%>
<%@page import="java.util.Date"%>
<%@page import="com.worksApproval.admin.entity.WorksTheme"%>
<%@page import="java.util.List"%>
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
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />

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
       <li class="bg_dy"> <a href="portalUploadWork.html?t=<%=new Date().getTime()%>"><h4>我要投稿</h4></a> </li>
      <!-- class="btn tip-bottom" data-original-title="Tooltip in bottom">Bottom -->
    </ul>
  </div>
  <div class="container-fluid">
    
     
       
       <c:forEach items="${themes}" var="themeArr">
        <div class="row-fluid">
		 		<div class="span12">
			        <div class="widget-box">
			          <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
			            <h5>${(themeArr[0]).name }</h5>
			          </div>
			          <div class="widget-content">${(themeArr[0]).comment }</div>
			        </div>
		       </div>
       </div>
       </c:forEach>
       
      <%-- <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
            <h5>Full Width <code>class=Span12</code></h5>
          </div>
          <div class="widget-content"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </div>
        </div>
      </div> --%>
    <%
    List<Object[]> themes = (List<Object[]>)request.getAttribute("themes");
    
    %>
    
    <div class="row-fluid">
    <div class="span6">
    <%
    for(int i = 0;i<themes.size();i++)
    {
    %>
      <!-- 单元 -->
      <%  if(i%2==0) 
    {
    %>
        <div class="widget-box">
          <div class="widget-title bg_lo"  data-toggle="collapse" href="#collapseG<%=i %>" > <span class="icon"> <i class="icon-chevron-down"></i> </span>
            <h5><%=((WorksTheme)themes.get(i)[0]).getName() %></h5>
          </div>
          <div class="widget-content nopadding updates collapse in" id="collapseG<%=i %>">
             <ul class="recent-posts">
             <%
             /* for(Works w :((WorksTheme)themes.get(i)[0]).getWorks() ) */
             if(((WorksTheme)themes.get(i)[0])!=null&&((WorksTheme)themes.get(i)[0]).getWorks()!=null)
             {
           	 Iterator<Works> iterators =  ((WorksTheme)themes.get(i)[0]).getWorks().iterator();
             Works w = null;
             while(iterators.hasNext())
             {
            	 w = iterators.next();
             %>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="img/demo/av1.jpg"> </div>
                <div class="article-post"> <span class="user-info">作者: <%=w.getAuthor().getUsername() %></span>
                  <p>作品: <a href="javascript: approvalCommentDetail(<%=w.getId() %>);"><%=w.getName() %></a> 通过审核 </p>
                </div>
              </li>
              <%}} %>
             
              <li>
                <a href="portalThemeDetail.html?id=<%=((WorksTheme)themes.get(i)[0]).getId() %>" class="btn btn-warning btn-mini">查看全部</a>
              </li>
            </ul>
          </div>
        </div> 
        <%
        }
     }
     %>
      </div>
      
    
      <div class="span6">
      <!-- 单元 -->
      <%
    for(int i = 0;i<themes.size();i++)
    {
    %>
      <%if(i%2!=0) 
      {
      %>
        <div class="widget-box">
          <div class="widget-title bg_lo"  data-toggle="collapse" href="#collapseG<%=i %>" > <span class="icon"> <i class="icon-chevron-down"></i> </span>
        <h5><%=((WorksTheme)themes.get(i)[0]).getName() %></h5>
          </div>
          <div class="widget-content nopadding updates collapse in" id="collapseG<%=i %>">
             <ul class="recent-posts">
             <%
             if(((WorksTheme)themes.get(i)[0])!=null&&((WorksTheme)themes.get(i)[0]).getWorks()!=null)
             {
           	 Iterator<Works> iterators =  ((WorksTheme)themes.get(i)[0]).getWorks().iterator();
             Works w = null;
             while(iterators.hasNext())
             {
            	 w = iterators.next();
             %>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="img/demo/av1.jpg"> </div>
                <div class="article-post"> <span class="user-info">作者: <%=w.getAuthor().getUsername() %></span>
                  <p>作品: <a href="javascript: approvalCommentDetail(<%=w.getId() %>);"><%=w.getName() %></a> 通过审核 </p>
                </div>
              </li>
              <%} 
              }%>
             
              <li>
                <a href="portalThemeDetail.html?id=<%=((WorksTheme)themes.get(i)[0]).getId() %>" class="btn btn-warning btn-mini">查看全部</a>
              </li>
            </ul>
          </div>
        </div>
        <%}
      }%>
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

		<!-- alert start -->
	<div id="alertModal" class="modal hide">
        <div class="modal-header">
          <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>审批详情</h3>
        </div>
	<div class="modal-body">
        
		<div class="widget-content nopadding updates">
			<%--稿件信息 --%>
			<div class="new-update clearfix">
				<i class="icon-ok-sign"></i>
				<div class="update-done">
					<span ><strong id="a_works"></strong></span> <span id="a_author"></span>
				</div>
			</div>
			<%--初审 --%>
			<div class="new-update clearfix">
				<i class="icon-gift"></i> <span class="update-notice"> 
				<span><strong id="b1_approvalor"></strong></span> <span id="b1_comment"></span>
				</span>
			</div>
			<%--复审 --%>
			<div class="new-update clearfix">
				<i class="icon-move"></i> <span class="update-alert"> 
				<span><strong id="b2_approvalor"></strong></span> <span id="b2_comment"></span>
				</span>
			</div>
			<%--编辑审核 --%>
			<div class="new-update clearfix">
				<i class="icon-leaf"></i> <span class="update-done"> 
				<span><strong id="b3_approvalor"></strong></span> <span id="b3_comment"></span>
				</span>
			</div>
		</div>
       

                
        </div>
         <!-- <div class="modal-footer"> 
         	<button id="sureDelete" class="btn btn-primary">确定</button>
         	<a data-dismiss="modal" class="btn" href="#">取消</a> </div> -->
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

<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

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
}
  
function approvalCommentDetail(id)
{
	var url = "/worksApproval/approvalCommentDetail";
	$.ajax({
		type:'POST',
		url:url,
		data:{
			id:id
			},
		success:function(data){
			console.log(data);
			
			//a_works
			//a_author
			//b1_approvalor
			//b1_comment
			$("#a_works").html("作品: "+data[0]);
			$("#a_author").html("作者: "+data[1]);
			$("#b1_approvalor").html("初级审批: "+data[2]);
			$("#b1_comment").html("<b>评语:</b> "+data[3]);
			
			$("#b2_approvalor").html("初级审批: "+data[4]);
			$("#b2_comment").html("<b>评语:</b> "+data[5]);
			$("#b3_approvalor").html("初级审批: "+data[6]);
			$("#b3_comment").html("<b>评语:</b> "+data[7]);
			$('#alertModal').modal('toggle');		
			
		},
		error:function(data){
			alert("error");
		}
	});
	
	
	
}
$(function(){
	
});  
  
  
</script>
</body>
</html>
