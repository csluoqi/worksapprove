

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
{ "asSorting": [ "desc", "asc", "asc" ] },
{ "asSorting": [ "desc", "asc", "asc" ] },
{ "asSorting": [ ""] },
{ "asSorting": [ ] },
{ "asSorting": [ "desc", "asc", "asc" ] },
{ "asSorting": [ ] },
{ "asSorting": [ "desc", "asc", "asc" ] },
{ "asSorting": [ ] },
{ "asSorting": [ "desc", "asc", "asc" ] }
			  		] 
	});
 
}

/**
 * 新增主题验证
 */
function newWorksValidate() {
	$("#works_add").validate({
		rules : {
			uploadFile :{
				required : true,
				minlength : 2,
				maxlength : 512,
				remote : { // 
					type : "POST",
					url : "/worksApproval/isRepeatWorks", // servlet
					data : {
						name : function() {
							
							return $("#uploadFile").val();//这个地方在更新和新增时验证的元素ID是不一样的
						}
					}
				 
				}
			},
			summary : {
				required : true,
				minlength : 1,
				maxlength : 1024
			} 
		},
		messages : {
			uploadFile : {
				required : '文件必须选择',
				remote : '文件已经存在服务器,请修改文件名,刷新页面之后再上传'
			},
			summary : {
				required : '概要必须填写'
			}
		},
		 
		errorClass : "help-inline",
		errorElement : "span",
		
		
        
        errorElement:'label',
        errorPlacement:function($error,element)
        {
        $error.insertBefore(element);    
        },
        //errorContainer:'#info',
        errorLabelContainer:'#errorList',
        wrapper:'span',
		
		
		
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
function updateWorksValidate() {
	$("#works_update").validate({

		rules : {
			uploadFile :{
				required : true,
				minlength : 2,
				maxlength : 512,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "/worksApproval/isRepeatWorks", // servlet
					data : {
						name : function() {
							
							return $("#update_uploadFile").val();//这个地方在更新和新增时验证的元素ID是不一样的
						}
					}
				 
				}
			},
			summary : {
				required : true,
				minlength : 1,
				maxlength : 1024
			} 
		},
		messages : {
			uploadFile : {
				required : '文件必须选择',
				remote : '文件已经存在服务器,请修改文件名,刷新页面之后再上传'
			},
			summary : {
				required : '概要必须填写'
			}
		},
		 
		errorClass : "help-inline",
		errorElement : "span",
		
		
        
        errorElement:'label',
        errorPlacement:function($error,element)
        {
        $error.insertBefore(element);    
        },
        //errorContainer:'#info',
        errorLabelContainer:'#update_errorList',
        wrapper:'span',
		
		
		
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
function suredeleteWorks()
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
	$.post("/worksApproval/deleteWorks",{ ids:ids },
			function(data){
		$("#alertMessage").html(data[0]);
		setTimeout(function(){
			$('#alertModal').modal('toggle');
		}, 4600);
		window.location.href="/worksApproval/managementWorks.html?t="+(new Date().getTime());
	});	
	
	
	}
function deleteWorks() {
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
function updateWorks()
{
	console.log(121212);
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
		var works = {
				id : "",
				theme_id :"",
				uploadFile:"",
				summary : ""

			};

		works.id=$(tr$[0]).html();
		works.theme_id = $(tr$[1]).html();
		works.summary = $(tr$[5]).html();
		works.uploadFile = $(tr$[3]).html();
				
		$("#update_works_id").val(works.id);
		$("#update_theme_id").val(works.theme_id);
		$("#update_uploadFile").val(works.uploadFile);
		$("#update_summary").val(works.summary);
		
		
		$('#updateWorks_modal').modal('toggle');
		//加入表单效验
		updateWorksValidate();
		}


}
/**
 * 当页面加载完成之后执行
 */
$(function() {
	//initDataTable();
	responseMessage();
	newWorksValidate();
	// 那些列需要排序,哪些列不需要排序,后期调整,还有就是不全选,
	// 首先获取选中的数据,然后判断是否有选中的数据
	//$("#updateWorks_a").click(updateWorks);
	//$("#deleteWorks").click(deleteWorks);
	//sureDelete
	//$("#sureDelete").click(suredeleteWorks);

});
