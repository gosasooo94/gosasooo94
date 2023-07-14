<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- JSTL을 사용하여 프로그램을 작성하시오.
	<%
	int ranNum = Math.abs(new Random().nextInt()%30)+1;
	session.setAttribute("answer", ranNum);
	%>
	
1. 임의의 수를 발생하여 그 수만큼 다음의 형태로 '*'을 출력하는 프로그램을 작성하시오.
******   (6인 발생된 경우)
*****
****
***
**
* -->

<c:forEach var="k" begin="1" end="${answer}" step="1" ><br/>
	<c:forEach var="j" begin="1" end="${answer-k}" step="1">
	*
	</c:forEach>
</c:forEach>
<!-- 
2. 1~30 사이의 숫자를 출력하되 3의 배수는 제외하고 출력하는 프로그램을 작성하시오.
1 2 4 5 7 8 10 .... 29 -->
<c:forEach var="i" begin="1" end="30" step="1">
	<c:if test="${i%3 ne 0}">${i}</c:if>
</c:forEach>

</body>
</html>