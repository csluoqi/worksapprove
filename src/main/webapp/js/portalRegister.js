
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
function registerSubmit()
{
	//清空消息,避免二次第二次提交时上次的提示内容不清除
	$("#errormsg_div").html("");
	//errormsg_div
	var username = $("#username").val();
	var password = $("#password").val();
	var surePassword = $("#surePassword").val();
	var resultMsg = "";
	if(username==""||password=="" || surePassword==""){
		resultMsg = "用户名、密码和确认密码不能为空，请重新输入";
		$("#errormsg_div").html(errormsg(resultMsg));
	}else if(password != surePassword){
		resultMsg = "密码和确认密码不一致，请重新输入";
		$("#errormsg_div").html(errormsg(resultMsg));
	}else{
		var url = "/worksApproval/portalRegister";
		$.ajax({
			type:'POST',
			url:url,
			data:{
				username:username,
				password:password
				},
			success:function(data){
				if(data[0]=="true"){
					
					$("#errormsg_div").html(errormsg("注册成功,前往登录页面"));
					//$("#loginbtn").show();
					//window.location.href="/worksApproval/"+data[1];//登录成功之后跳转到后台设定的页面
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
