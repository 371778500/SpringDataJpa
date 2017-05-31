$(function(){
	
	$("#regist").click(function(){
		var username=$("#contact-name").val();
		var pw=$("#password").val();
		
		if(!username){
			alert("用户名不能为空！")
			return;
		}
		
		if ($.trim(pw) == '') {
			alert("密码不能为空！");
			return false;
		}
		var patrn = /^[0-9A-Za-z_+-/*%]{6,15}$/;
		if (!patrn.exec(pw)) {
			alert("密码格式错误！")
			return false;
		}
		$.ajax({
			type:"post",
			data:{"username":username,"password":pw},
			url:"http://localhost:8082/TestSpringJpa/user/regist",
			async:false,
			dataType:'json',
			success:function(data){
				window.localStorage.setItem("user",JSON.stringify(data));
				var url=location.href.substring(0,location.href.lastIndexOf('/'))+'/index';
				window.location.href=url;
			},
			error:function(e){
				console.log(e);
			}
		});
		
	})
	
	$("#login").click(function(){
		var username=$("#contact-name").val();
		var pw=$("#password").val();
		
		if(!username){
			alert("用户名不能为空！")
			return;
		}
		
		if ($.trim(pw) == '') {
			alert("密码不能为空！");
			return false;
		}
		var patrn = /^[0-9A-Za-z_+-/*%]{6,25}$/;
		if (!patrn.exec(pw)) {
			alert("密码格式错误！")
			return false;
		}
		$.ajax({
			type:"post",
			data:{"username":username,"password":pw},
			url:"http://localhost:8082/TestSpringJpa/user/login",
			async:false,
			dataType:'json',
			success:function(data){
				if(data.username.endsWith("null")){
					alert("用户名或密码错误！");
					return;
				}
				window.localStorage.setItem("user",JSON.stringify(data));
				var url=location.href.substring(0,location.href.lastIndexOf('/'))+'/index';
				window.location.href=url;
			},
			error:function(e){
				console.log(e);
			}
		});
	})
})
