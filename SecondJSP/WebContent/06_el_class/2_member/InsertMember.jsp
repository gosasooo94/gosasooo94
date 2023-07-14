<%@ page contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ page import="member.beans.*" %>

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="mem" class="member.beans.Member">
	<jsp:setProperty name="mem" property="*"/>
</jsp:useBean> 
 
<%
	MemberDao memDao = MemberDao.getInstance();
	memDao.insertMember(mem);
%>


<!DOCTYPE html>
<html>
<head>
<title> 회원가입 </title>
</head>
<body>
	아이디 : ${mem.id}<br/>
	패스워드 : ${mem.password}<br/>
	이름 : ${mem.name}<br/>
	전화 : ${mem.tel}<br/>
	주소 : ${mem.addr }<br/>
</body>
</html>