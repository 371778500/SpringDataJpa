<%@ page language="java" contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <title>Insert title here</title>
 <script src="js/jquery-1.11.2.js"></script>
 </head>
 <body>
    ${user.username },你好！ 
    <button id="test">访问数据</button>
 </body>
 <script type="text/javascript">
 $(function(){
	 $("#test").click(function(){
		 $.ajax({
				type: 'GET',
				url: "/TestSpringJpa/user/welcome",
				data: '',
				dataType: 'text',
				success: function(data) {
					console.log(data)
				},
				error:function(e){
					console.log(e)
				}
		 });
	 })
 })
 </script>
 </html>