<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.beans.Member" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
</head>
<body>
	1.자바소스<br/>
	<%
	Member m = new Member();
	m.setName("홍동우");
	m.setAddr("대한민국");
	%>
	이름 : <%= m.getName() %> / 주소 : <%= m.getAddr() %>	<br/><br/>
	
	2.JSTL 변수를 사용선언하여 사용<br/>
	<c:set var="name"><%=m.getName()%></c:set>
	<c:set var="addr"><%=m.getAddr()%></c:set>
	
	이름 : ${name} <br/>
	주소 : ${addr }<br/><br/>
	
	<c:set var="m2" value="<%=m %>"></c:set>
	
	<c:set var="name2">${m2.name }</c:set>
	<c:set var="addr2">${m2.addr }</c:set>
	
	이름 : ${name2 } <br/>
	주소 : ${addr2 }<br/><br/>
	
	3.JSTL에서 setter를 사용하려면 <br/>
	<c:set var="m3" value="<%=m %>"></c:set>
	<c:set target="<%=m%>" property="name" value="홍길동"/>
	<c:set target="${m3 }" property="addr" value="구로"/>
	
	이름 : ${m3.name } <br/>
	주소 : ${m3.addr }<br/><br/>
	
</body>
</html>