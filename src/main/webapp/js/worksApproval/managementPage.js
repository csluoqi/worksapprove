

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
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ "desc", "asc", "asc" ] }
		  		] 
		 
	});
 
}

/**
 * 新增主题验证
 */
function newPageValidate() {
	$("#page_add").validate({
		rules : {
			url : {
				required : true,
				minlength : 1,
				maxlength : 200,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "/worksApproval/isRepeatPage", // servlet
					data : {
						// 这个地方和input中的name一样,然后值只能是false或者true
						url : function() {
							return $("#new_url").val();
						}
					}
				}
			},
			pageName : {
				required : true,
				minlength : 1,
				maxlength : 200,
			} ,
			order : {
				required : true,
				min : 1,
				max : 50
			} 
		},
		messages : {
			url : {
				remote : '主题已经存在,请重新输入'
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
 * 更新时数据效验
 * 
 */
function updatePageValidate() {
	$("#page_update").validate({
		rules : {
			url : {
				required : true,
				minlength : 1,
				maxlength : 200,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "/worksApproval/isRepeatPage", // servlet
					data : {
						// 这个地方和input中的name一样,然后值只能是false或者true
						url : function() {
							return $("#update_url").val();
							
						},
						id: function() {
							return $("#update_page_id").val();
						}
					}
				}
			},
			pageName : {
				required : true,
				minlength : 1,
				maxlength : 200
			},
			order : {
				required : true,
				min : 1,
				max : 50
			} 
		},
		messages : {
			url : {
				remote : '主题已经存在,请重新输入'
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
function sureDeletePage()
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
	$.post("/worksApproval/deletePage",{ ids:ids },
			function(data){
		$("#alertMessage").html(data[0]);
		setTimeout(function(){
			$('#alertModal').modal('toggle');
		}, 4600);
		window.location.href="/worksApproval/managementPage.html?t="+(new Date().getTime());
	});	
	
	
	}
function deletePage() {
	$("#alertMessage").html("确认删除?");
	$("#sureDelete").removeAttr("disabled");
	var tr_input$ = $("tr input:checked");
	if (tr_input$.length == 0) {
		//提醒需要选中之后再更新
		$.gritter.add({
			title : '提醒: ' + "请选择要删除的数据",
			text : '此消息由系统推送',
			time : 2500,
			sticky : false
		});
		return false;
	}
	else if(tr_input$.length > 1) {
		//提醒需要选中之后再更新
		$.gritter.add({
			title : '提醒: ' + "只能选择一条记录!",
			text : '此消息由系统推送',
			time : 2500,
			sticky : false
		});
		return false;
	}else {
		$('#alertModal').modal('toggle');
	}
}
function updatePage()
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
		var page = {
				id : "",
				url : "",
				pageName : "",
				notes : "",
				useState : "",
				order:""
		};
		page.id=$(tr$[0]).html();
		page.url = $(tr$[1]).html();
		page.pageName = $(tr$[2]).html();
		page.notes =  $(tr$[3]).html();
		page.useState =  $(tr$[4]).html();//id
		page.order =  $(tr$[6]).html();//id
		//pageUseState =  $(tr$[5]).html();//id
		//update_order
	 //console.log(page.order);
		//console.log(pageUseState);
		
		
		$("#update_page_id").val(page.id);
		$("#update_url").val(page.url);
		$("#update_pageName").val(page.pageName);
		$("#update_notes").val(page.notes);
		$("#update_useState").val(page.useState);
		$("#update_order").val(page.order);
		//弹出弹窗
		$('#updatePage_modal').modal('toggle');
		//加入表单效验
		updatePageValidate();
		}


}
/**
 * 当页面加载完成之后执行
 */
$(function() {
	initDataTable();
	responseMessage();
	newPageValidate();
	// 那些列需要排序,哪些列不需要排序,后期调整,还有就是不全选,
	// updatePage
	// 首先获取选中的数据,然后判断是否有选中的数据
	$("#updatePage_a").click(updatePage);
	$("#deletePage").click(deletePage);
	//sureDelete
	$("#sureDelete").click(sureDeletePage);

});

/*
 * function testgetData() { alert(11111); //var tr_input$ = $("div#updatePage + form +
 * input:checked"); console.log(tr_input$.parent()); }
 */