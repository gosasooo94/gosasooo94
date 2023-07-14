<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Integer result = (Integer)request.getAttribute("result");
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 메세지 삭제 확인 </title>
</head>
<body>
	<% if( result == 0  ) { %>
		삭제할 메세지가 존재하지 않거나 비밀번호가 올바르지 않습니다.
	<% } else { %>
		메세지를 삭제하였습니다.
	<% } %>
	
	<br/><br/>
	<a href="GuestControl?cmd=list-page"> [ 목록보기 ] </a>
</body>
</html>