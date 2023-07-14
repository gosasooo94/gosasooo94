<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 연습</title>
</head>
<body>
	<%-- 변수 선언 --%>
	<c:set var="gender" value="man"/>
	
	<%-- 흐름제어문 --%>
	<c:if test="${gender eq 'man' }">당신은 남자입니다.</c:if><br/>
	<c:if test="${gender == 'women' }">당신은 여자입니다.</c:if><br/>
	
	<c:set var="age">20</c:set>
	<c:if test="${age >= 20}">당신은 성인입니다.</c:if> <br/>
	<c:if test="${age ge 20}">당신은 성인입니다.</c:if> <br/>
	
	<c:choose>
		<c:when test="${age lt 10}">당신은 어린이입니다.</c:when> 
		<c:when test="${age ge 10 and age lt 20 }">당신은 청소년입니다.</c:when>
		<c:otherwise>당신은 성인 입니다.</c:otherwise> 
	</c:choose> <br/>
	
	<!-- 반복문 -->
	<c:forEach var="i" begin="0" end="100" step="2">
		<c:set var="sum" value="${sum+i}"/>
	</c:forEach>
	1~100까지의 짝수 합 = ${sum} <br/>
	
	<!-- 짝수의 합과 홀수의 합을 구하기 -->
	<c:forEach var="j" begin="1" end="100" step="2">
		<c:set var="sum2" value="${sum2+j}"/>
	</c:forEach>
		1~100까지의 홀수 합 = ${sum2} <br/><br/>
		
	<!--  변수 하나를 선언하여 그 변수값에 따른 구구단 출력 -->	
	<c:set var="gogo" value="1"/>
		<c:forEach var="gogogo" begin="1" end="9">
			${gogo}*${gogogo }=${gogo*gogogo }<br/>
		</c:forEach><br/><br/>
	
	<c:forEach var="i" begin="2" end="9">
		${i }단<br/>
		<c:forEach var="j" begin="1" end="9">
		${i }*${j}=${i*j }<br/>
		</c:forEach><br/>
	</c:forEach>
	
</body>
</html>