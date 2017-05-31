<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <script src="js/jquery-1.11.2.js"></script>
 <title>Insert title here</title>
 </head>
 <body>
     <div>
         <form action="/TestSpringJpa/user/welcome" method="post">
             <input type="text" name="username">
             <input type="text" name="password">
             <input type="submit" value="SUBMIT">
         </form>
     </div>
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