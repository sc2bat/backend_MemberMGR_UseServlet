<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
<!-- 	안녕하세요 -->
<!-- http://localhost:8090/WEB08_MemberMGR_UseServlet/index.jsp -->
<!-- 웹앱에 최상단에 위치 -->

<!-- index 등의 페이지를 보여주지 않고 index 등에 접속시 sendRedirect 로 바로 서블렛으로 이동시켜줌 
 서블렛을 이용하여 index 등의 파일을 보여주지 않게끔 -->
	<% 
// 		response.sendRedirect("member/loginForm.jsp");			
		response.sendRedirect("login.do");
	%>
</body>
</html>