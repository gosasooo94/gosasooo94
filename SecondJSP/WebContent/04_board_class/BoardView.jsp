<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.service.*, board.model.*" %>
<%
	// 1. 해당 게시물의 게시글번호값을 얻어온다
	// 2. Service에 getArticleById() 호출하여 그 게시글번호를 갖는 레코드를 검색한다.
	request.setCharacterEncoding("UTF-8");
	
	 String id = request.getParameter("id");
	 BoardRec rec = ViewArticleService.getInstance().getArticleById(id);
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 게시글 보기 </title>
</head>
<body>

	<h4> 게시판 글 보기 </h4><br/>
	<table border="1" bordercolor="red">
	<tr>
		<td> 제  목 : <%=rec.getTitle() %></td>
		<td></td>
	</tr>
	<tr>
		<td> 작성자 : <%= rec.getWriterName() %> </td>
		<td></td>
	</tr>
	<tr>
		<td> 작성일자 : <%= rec.getPostingDate() %> </td>
		<td></td>
	</tr>
	<tr>
		<td> 내  용 : <%= rec.getContent() %> </td>
		<td></td>
	</tr>
	<tr>
		<td colspan="2">
		<a href="BoardList.jsp">[목록보기]</a>	
		<a href="BoardReplyForm.jsp?parentId=<%=rec.getArticleId()%>">[답변하기]</a>	
		<a href="BoardModifyForm.jsp?id=<%=rec.getArticleId()%>">[수정하기]</a>	
		<a href="BoardDeleteForm.jsp?id=<%=rec.getArticleId()%>">[삭제하기]</a>	
		</td>
	</tr>
	</table>


</body>
</html>