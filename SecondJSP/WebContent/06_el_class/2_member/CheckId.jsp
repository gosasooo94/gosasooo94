<%@ page contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ page import="member.beans.*" %>

<%
	String id = request.getParameter("userId");
	MemberDao memDao = MemberDao.getInstance();
%>      
<!DOCTYPE html>
<html>
<head>
<title> 아이디 중복 체크 </title>
</head>
<body>
<%
	if( memDao.isDuplicatedId(id)) {
%>
		사용중인 아이디가 있습니다. 다시 입력하여 주십시오.
<%  }else { %>
		사용할 수 있는 아이디입니다.
<%  } %>
</body>
</html>