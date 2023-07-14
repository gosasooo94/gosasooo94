<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>
<html>
<head><title>자료실 목록</title></head>
<body>
<table border="1">
	<tr>
		<td>번호</td>
		<td>파일명</td>
		<td>파일크기</td>
		<td>다운로드회수</td>
		<td>다운로드</td>
	</tr>
	
<c:choose>
	<c:when test="${empty listModel }">
	<tr>
		<td colspan="5">
			게시글이 없습니다.
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<c:forEach var="item" items="${listModel}">
	<tr>
		<td>${item.id }</td>
		<td>${item.fileName }</td>
		<td>${item.fileSize }</td>
		<td>${item.downloadCount }</td>
		<td><a href="download.jsp?id=${item.id}">다운받기</a></td>
	</tr>
	</c:forEach>
	</c:otherwise>
</c:choose>
	
	<tr>
		<td colspan="5">
			<a href="uploadForm.jsp">파일 첨부</a>
		</td>
	</tr>	
</table>
</body>
</html>