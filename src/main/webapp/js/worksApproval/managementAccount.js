

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
 *//*
function initDataTable() {
	alert(121212);
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
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] },
		  			{ "asSorting": [ ] }
		  		] 
	});
	alert(99900);
}
*/
/**
 * 初始化表格
 */
function initDataTable() {
	$('#accountTable').dataTable({
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
			  			{ "asSorting": [ ] },
			  			{ "asSorting": [ ] },
			  			{ "asSorting": [ ] },
			  			{ "asSorting": [ ] },
			  			{ "asSorting": [ ] },
			  			{ "asSorting": [ ] },
			  			{ "asSorting": [ ] },
			  			{ "asSorting": [ ] }
			  		]
		 
	});
}






/**
 * 新增主题验证
 */
function newAccountValidate( ) {
	$("#account_add").validate({
		rules : {
			username : {
				required : true,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "/worksApproval/isRepeatAccount", // servlet
					data : {
						// 这个地方和input中的name一样,然后值只能是false或者true
						username : function() {
							return $("#new_username").val();
						}
					}
				}
			},
			realName : {
				required : true,
				maxlength:20,
				minlength:0
			},
			phoneNumber : {
				required : true,
				maxlength:11,
				minlength:11
			}
		},
		messages : {
			username : {
				remote : '昵称已经存在,请重新输入'
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
function updateAccountValidate() {
	$("#account_update").validate({
		rules : {
			username : {
				required : true,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "/worksApproval/isRepeatAccount", // servlet
					data : {
						// 这个地方和input中的name一样,然后值只能是false或者true
						id : function() {
							return $("#update_id").val();
						},
						username : function() {
							return $("#update_username").val();
						}
					}
				}
			},
			realName : {
				required : true,
				maxlength:20,
				minlength:0
			},
			phoneNumber : {
				required : true,
				maxlength:11,
				minlength:11
			}
		},
		messages : {
			username : {
				remote : '昵称已经存在,请重新输入'
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
function sureDeleteAccount()
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
	$.post("/worksApproval/deleteAccount",{ ids:ids },
			function(data){
		$("#alertMessage").html(data[0]);
		setTimeout(function(){
			$('#alertModal').modal('toggle');
		}, 4600);
		if(!data[1])
			{
			window.location.href="/worksApproval/managementAccount.html?t="+(new Date().getTime());	
			}
		
	});	
	
	
	}
function deleteAccount() {
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
function updateAccount()
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
		var userInfo = {
				id : "",
				username : "",//disabled
				realName : "",//disabled
				birthday : "",
				gender : "",
				phoneNumber : "",
				email : "",
				address : "",
				profile : "",
				userRole : "",

			};
		
		
		userInfo.id=$(tr$[0]).html();
		//通过ajax找到
		var url = "/worksApproval/findAccountById";
		$.post(url,{
			id :userInfo.id,
		},function(data){
			console.log(data);
			createUpdateAccount(data);
		
		},"json")


		
	//
		/*
		theme.name = $(tr$[1]).html();
		theme.comment = $(tr$[2]).html();
		theme.order =  $(tr$[3]).html();
		
		$("#update_id").val(theme.id);
		$("#update_name").val(theme.name);
		$("#update_comment").val(theme.comment);
		$("#update_order").val(theme.order);
		*/
		$('#updateAccount_modal').modal('toggle');
		//加入表单效验
		updateAccountValidate();
		}


}

function createUpdateAccount(userInfo)
{
	$("#update_id").val(userInfo.id);
	$("#update_username").val(userInfo.username);
	$("#update_realName").val(userInfo.realName);
	var d = parseDate(userInfo.birthday);
	console.log(userInfo.gender);
	$("#update_birthday").val(d);
	//gender
	
	if(userInfo.gender==1){
		//男选中,女没有选中checked="checked"
		$("#gender_f").removeProp("checked");
		$("#gender_m").prop("checked","checked");
	}
	if(userInfo.gender == 0){
		//男选中,女没有选中checked="checked"
		$("#gender_m").removeProp("checked");
		$("#gender_f").prop("checked","checked");
	}
	$("#update_phoneNumber").val(userInfo.phoneNumber);
	$("#update_email").val(userInfo.email);
	$("#update_address").val(userInfo.address);
	$("#update_profile").val(userInfo.profile);
	$("#update_userRoleId").val(userInfo.userRole.id);
	
}
/**
 * 当页面加载完成之后执行
 */
$(function() {
	initDataTable();
	responseMessage();
	newAccountValidate();
	// 那些列需要排序,哪些列不需要排序,后期调整,还有就是不全选,
	// updateAccount
	// 首先获取选中的数据,然后判断是否有选中的数据
	$("#updateAccount_a").click(updateAccount);
	$("#deleteAccount").click(deleteAccount);
	//sureDelete
	$("#sureDelete").click(sureDeleteAccount);

});

function parseDate(dateTime)
{
	var d = new Date(dateTime);
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	
	}