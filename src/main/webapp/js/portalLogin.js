
$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

	$('#to-recover').click(function(){
		
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();
	});
	$('#to-login').click(function(){
		
		$("#recoverform").hide();
		$("#loginform").fadeIn();
	});
	
	
	$('#to-login').click(function(){
	
	});
    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);       
       
        $(input).val(input.attr('placeholder'));
               
        $(input).focus(function(){
             if (input.val() == input.attr('placeholder')) {
                 input.val('');
             }
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        });
    });

        
        
    }

    
});

//登录效验
function loginSubmit()
{
	//清空消息,避免二次第二次提交时上次的提示内容不清除
	$("#errormsg_div").html("");
	//errormsg_div
	var username = $("#username").val();
	var password = $("#password").val();
	var resultMsg = "";
	if(username==""||password==""){
		resultMsg = "请输入用户名和密码";
		console.log("has null ");
		$("#errormsg_div").html(errormsg(resultMsg));
	}else{
		var url = "/worksApproval/portalLogin";
		$.ajax({
			type:'POST',
			url:url,
			data:{
				username:username,
				password:password
				},
			success:function(data){
				if(data[0]=="true"){
					window.location.href="/worksApproval/"+data[1];//登录成功之后跳转到后台设定的页面
				}else if(data[0]=="false"){
					//window.location.href="";
					//alert("false");
					$("#errormsg_div").html(errormsg(data[1]));
				}
				
			},
			error:function(data){
				alert("error");
			}
		})
		
	}
	
	
	//必须是 return false;
	return false;
}

function errormsg(msg)
{
	return '<span class="bg_lo" style="padding: 4px 9px;color: #fff;display: inline-block;" id="errormsg">'+msg+'</span>';
}
