

/**
 * 初始化返回的消息
 */
function responseMessage() {
	var resultMessage$ = $("#result_message").val();
	if (!!resultMessage$) {
		$.gritter.add({
			title : '提醒: ' + resultMessage$,
			text : '此消息由系统推送',
			time : 2000,
			sticky : false
		});
	}
}

/**
 * 初始化表格
 */
function initDataTable() {
	$('.data-table').dataTable({
		"bAutoWidth" : false,
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"sDom" : '<""l>t<"F"fp>',
		"oLanguage" : {
			"sUrl" : "js/cn.txt"
		},

		"sScrollX" : "100%",
		"sScrollXInner" : "110%",
		"bScrollCollapse" : true,
		"aoColumns": [
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [  "desc", "asc", "asc" ] },
		  			{ "asSorting": [ "desc", "asc", "asc"  ] },
		  			{ "asSorting": [ ] }
		  		] 
		 
	});
 
}

/**
 * 新增主题验证
 */
function newRoleValidate() {
	$("#role_add").validate({
		rules : {
			shortName : {
				required : true,
				minlength : 1,
				maxlength : 200,
				
			},
			comment : {
				required : true,
				minlength : 1,
				maxlength : 200,
			} 
		},
	/*pages:{
		required : true,
	},	*/
		 
		errorClass : "help-inline",
		errorElement : "span",
		highlight : function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');// 这一行之前是没有的,是个bug,现在添加就好了

			$(element).parents('.control-group').addClass('error');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}

	});

}

/**
 * 更新时数据效验
 * 
 */
function updateRoleValidate() {
	$("#role_update").validate({
		rules : {
			shortName : {
				required : true,
				minlength : 1,
				maxlength : 200,
				
			},
			comment : {
				required : true,
				minlength : 1,
				maxlength : 200,
			} 
		},
		 
		errorClass : "help-inline",
		errorElement : "span",
		highlight : function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');// 这一行之前是没有的,是个bug,现在添加就好了

			$(element).parents('.control-group').addClass('error');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}

	});

}



/**
 * 获取选中的数据并解析成对象
 */
function suredeleteRole()
{
	$("#sureDelete").attr("disabled","disabled");
	var ids = "";
	var tr_input$ = $("tr input:checked");
	$.each(tr_input$, function(index, element){
		  //alert( "Item #" + i + ": " + n );
		$(element).val();
		ids += $(element).val()+',';
		});
	$("#alertMessage").html("正在删除!");
	$.post("/worksApproval/deleteRole",{ ids:ids },
			function(data){
		$("#alertMessage").html(data[0]);
		setTimeout(function(){
			$('#alertModal').modal('toggle');
		}, 4600);
		if(!data[1])
			{
			window.location.href="/worksApproval/managementRole.html?t="+(new Date().getTime());	
			}
		
	});	
	
	
	}
function deleteRole() {
	$("#alertMessage").html("确认删除?");
	$("#sureDelete").removeAttr("disabled");
	var tr_input$ = $("tr input:checked");
	console.log(tr_input$);
	if (tr_input$.length == 0) {
		//提醒需要选中之后再更新
		$.gritter.add({
			title : '提醒: ' + "请选择要删除的数据",
			text : '此消息由系统推送',
			time : 2500,
			sticky : false
		});
		return false;
	}else if(tr_input$.length > 1)
	{
		//提醒需要选中之后再更新
		$.gritter.add({
			title : '提醒: ' + "删除角色时每次只能删除一条记录",
			text : '此消息由系统推送',
			time : 2500,
			sticky : false
		});
		return false;
	}else {
		$('#alertModal').modal('toggle');
	}
}
function updateRole()
{
	var tr_input$ = $("tr input:checked");
	if (tr_input$.length == 0) {
		//提醒需要选中之后再更新
		$.gritter.add({
			title : '提醒: ' + "请选择需要更新的数据",
			text : '此消息由系统推送',
			time : 2500,
			sticky : false
		});
		return false;
	}else if(tr_input$.length > 1){
		$.gritter.add({
			title : '提醒: ' + "更新数据时只能选择一条数据",
			text : '此消息由系统推送',
			time : 2500,
			sticky : false
		});
		return false;
		}
	else{
		//只选择了一个数据,启用弹窗
		var tr$ = tr_input$.parents("td").siblings();
		var role = {
				id : "",
				shortName : "",
				comment : ""

		};
		role.id=$(tr$[0]).html();
		role.shortName = $(tr$[1]).html();
		role.comment = $(tr$[2]).html();
		
		$("#update_role_id").val(role.id);
		$("#update_shortName").val(role.shortName);
		$("#update_comment").val(role.comment);

		var url = "/worksApproval/pageDetail";
		//Long roleId, int typeId
		//// 1,表示查询时使用,2表示在新增和更新时使用
		///pageDetail
		//$.post(url,[data],[fn],[type])
 $.post(url,{
			roleId :role.id,
			typeId :2
		},function(data){
			//showPageDetail_select
			var opts = createSelect(data);
			$("#updatePageDetail_select").attr("size",+data.length+"")
			$("#updatePageDetail_select").html(opts);
			//console.log(data);
			//画出select
		},"json")

		
		//弹出弹窗
		$('#updateRole_modal').modal('toggle');
		//加入表单效验
		updateRoleValidate();
		}


}
function createSelect(data)
{
	//showPageDetail_select
	var opts = "";
	$.each(data,function(index,element){
		/**
		 * 	private PageInfo page;
	private int status;
		 */
		if(element.status==1)//是这个角色的
			{
			opts += "<option value='"+element.page.id+"' selected='selected'>"+element.page.pageName+"</option>"	
			}
		else{
			opts += "<option value='"+element.page.id+"'>"+element.page.pageName+"</option>"
		}
		
	});
	console.log(opts);
	return opts;	
}

function showPageDetail()
{
	$(".pageDetail").click(function(){
		//获取id,然后通过ajax去后台查询这个id下面的角色,然后返回数据,后台方法可以可以传一个参数,
		//区分是查询还是新增还是更新,新增和更新可以使用同一个参数,查询使用一个参数
		var url = "/worksApproval/pageDetail";
		//Long roleId, int typeId
		//// 1,表示查询时使用,2表示在新增和更新时使用
		var t = $(this).parent().siblings()[1];
		var roleId = $(t).html().trim();
		///pageDetail
		//$.post(url,[data],[fn],[type])
 $.post(url,{
			roleId :roleId,
			typeId :1
		},function(data){
			//showPageDetail_select
			var opts = createSelect(data);
			$("#showPageDetail_select").attr("size",+data.length+"")
			$("#showPageDetail_select").html(opts);
			//console.log(data);
			//画出select
		},"json")
		
		$('#showPageDetail').modal('toggle');
		//showPageDetail_select 
	});
}

/**
 * 当页面加载完成之后执行
 */
$(function() {
	initDataTable();
	responseMessage();
	newRoleValidate();
	// 那些列需要排序,哪些列不需要排序,后期调整,还有就是不全选,
	// updatePage
	// 首先获取选中的数据,然后判断是否有选中的数据
	$("#updateRole_a").click(updateRole);
	$("#deleteRole").click(deleteRole);
	//sureDelete
	$("#sureDelete").click(suredeleteRole);

	showPageDetail();
});

/*
 * function testgetData() { alert(11111); //var tr_input$ = $("div#updatePage + form +
 * input:checked"); console.log(tr_input$.parent()); }
 */