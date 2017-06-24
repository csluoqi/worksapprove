

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
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ "desc", "asc", "asc" ] },
		  			{ "asSorting": [ "desc", "asc", "asc" ] }
		  		] 
		 
	});
 
}

/**
 * 新增主题验证
 */
function newThemeValidate() {
	$("#theme_add").validate({
		rules : {
			name : {
				required : true,
				minlength : 1,
				maxlength : 6,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "/worksApproval/isRepeat", // servlet
					data : {
						// 这个地方和input中的name一样,然后值只能是false或者true
						name : function() {
							return $("#name").val();
						}
					}
				}
			},
			comment : {
				required : false
			},
			order : {
				required : true,
				max : 10000,
				min : 0

			}
		},
		messages : {
			name : {
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
function updateThemeValidate() {
	$("#theme_update").validate({
		rules : {
			name : {
				required : true,
				minlength : 1,
				maxlength : 6,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "/worksApproval/isRepeat", // servlet
					data : {
						name : function() {
							return $("#update_name").val();//这个地方在更新和新增时验证的元素ID是不一样的
						},
						id:function() {
							return $("#update_id").val();//这个地方在更新和新增时验证的元素ID是不一样的
						}
					}
				 
				}
			},
			comment : {
				required : false
			},
			order : {
				required : true,
				max : 10000,
				min : 0

			}
		},
		messages : {
			name : {
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
function sureDeleteTheme()
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
	$.post("/worksApproval/deleteTheme",{ ids:ids },
			function(data){
		$("#alertMessage").html(data[0]);
		setTimeout(function(){
			$('#alertModal').modal('toggle');
		}, 4600);
		window.location.href="/worksApproval/managementTheme.html?t="+(new Date().getTime());
	});	
	
	
	}
function deleteTheme() {
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
	}else {
		$('#alertModal').modal('toggle');
	}
}
function updateTheme()
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
		var theme = {
				id : "",
				name : "",
				comment : "",
				order : "",
				createDate : ""

			};
		theme.id=$(tr$[0]).html();
		theme.name = $(tr$[1]).html();
		theme.comment = $(tr$[2]).html();
		theme.order =  $(tr$[3]).html();
		
		$("#update_id").val(theme.id);
		$("#update_name").val(theme.name);
		$("#update_comment").val(theme.comment);
		$("#update_order").val(theme.order);
		
		$('#updateTheme_modal').modal('toggle');
		//加入表单效验
		updateThemeValidate();
		}


}
/**
 * 当页面加载完成之后执行
 */
$(function() {
	initDataTable();
	responseMessage();
	newThemeValidate();
	// 那些列需要排序,哪些列不需要排序,后期调整,还有就是不全选,
	// updateTheme
	// 首先获取选中的数据,然后判断是否有选中的数据
	$("#updateTheme_a").click(updateTheme);
	$("#deleteTheme").click(deleteTheme);
	//sureDelete
	$("#sureDelete").click(sureDeleteTheme);

});

/*
 * function testgetData() { alert(11111); //var tr_input$ = $("div#updateTheme + form +
 * input:checked"); console.log(tr_input$.parent()); }
 */