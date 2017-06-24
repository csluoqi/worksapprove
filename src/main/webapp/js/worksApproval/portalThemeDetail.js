/**
 * 初始化表格
 */
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
 * 初始化返回的消息
 */
function responseMessage() {
	var resultMessage$ = $("#result_message").val();
	if (!!resultMessage$) {
		$.gritter.add({
			title : '提醒: ' + resultMessage$,
			text : '此消息由系统推送',
			time : 2000,
			sticky : true
		});
	}
}

$(function(){
	initDataTable();
	responseMessage();
});


//themeDetailTable

//function 