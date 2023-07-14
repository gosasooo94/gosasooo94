<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mvc.guest.model.*" %>    
<%@ page import="java.util.List" %>
 
<%
	// Control에서 param을 넘겨받아 mList 변수에 지정
	List <Message> mList = (List <Message>)request.getAttribute("param");  
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 목록 2 </title>
</head>
<body>
	
	<a href="GuestControl?cmd=input-page">방명록 남기기 </a><br/><br/>
	
	<% if( mList == null ) { %>
		남겨진 메세지가 하나도~~없습니다. <br>
	<% } else { %>
	<table border="1">
	
		<% for( Message msg : mList ) { %>
		<tr>	
			<td> <%= msg.getId() %> </td> 
			<td> <%= msg.getGuestName() %></td> 
			<td> <a href="GuestControl?cmd=delete-page&messageId=<%= msg.getId()%>"> [ 삭제하기 ]</a> </td>			
		</tr>
		<tr>
			<td colspan='3'> 
			<textarea cols=35 rows=3 style="font-family: '돋움', '돋움체'; font-size: 10pt; font-style: normal; line-height: normal; color: #003399;background-color:#D4EBFF;border:1 solid #00009C;"><%= msg.getMessage() %>
			</textarea>
			</td>
		</tr>
		<% } %>
	</table>
	

	<% } // end if-else %>
</body>
</html>