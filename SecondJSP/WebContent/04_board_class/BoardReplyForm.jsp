<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 답변글의 부모 게시글의 번호를 넘겨받기
	String parentId = request.getParameter("parentId");
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 답변 글쓰기 </title>
</head>
 <body>
	<h4> 답변 글 쓰기 </h4><br/>
	
	<form name='frm' method='post' action="BoardReply.jsp">
	<input type="hidden" name="parentId" value=<%=parentId%>> 
	작성자 : <input type='text' name="writerName"><br/><br/>
	제  목 : <input type='text' name="title"><br/><br/>
	내  용 : <textarea rows='10' cols='40' name="content"></textarea><br/><br/>
	패스워드(수정/삭제시 필요) :
			 <input type='password' name="password"><br/><br/>
	<input type='submit' value='작성'>
	<input type='reset' value='취소'>
	</form>

</body>
</html>