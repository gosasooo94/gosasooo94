<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL의 URL 태그</title>
</head>
<body>
<%-- 	  1. import는 URL 결과를 삽입  --%>
	<%-- <c:import url="https://unsplash.com/ko/s/%EC%82%AC%EC%A7%84/%EA%B3%A0%EC%96%91%EC%9D%B4"></c:import>  --%>

<%-- 	 2. 사진 사이트에서 고양이를 검색  --%>
	<%-- <c:import url="http://www.flickr.com/search/"> --%>
<%-- <c:param name="text" value="고양이"></c:param> --%>
 <%-- 	</c:import>  --%>
	
<%-- 	 3.유튜브에서 고양이 검색 --%>
<%-- <c:import url="https://www.youtube.com/results?search_query=%EA%B3%A0%EC%96%91%EC%9D%B4"></c:import> --%>
<%-- <c:import url="https://www.youtube.com/results">--%>
    <%--  <c:param name="search_query" value="고양이"></c:param> --%>
 <%--  </c:import> --%>
   
   <!-- 2. c:url은 URL을 변수에  -->
<%--  <c:url value="https://www.flickr.com/search/?text=%EA%B3%A0%EC%96%91%EC%9D%B4" var="flickrBtn"/> 
 <c:url value="http://search.daum.net/search?w=img&nil_search=btn&DA=NTB&enc=utf8&q=%EA%B3%A0%EC%96%91%EC%9D%B4" var="daumBtn"/>
 <c:url value="https://www.youtube.com/results?search_query=%EA%B3%A0%EC%96%91%EC%9D%B4" var="youtubeBtn"/> 
	<c:url value="1_basic.jsp" var="doc"/> 
 <!-- <ul> --> 
 <li><a href="${flickrBtn }">사진</a></li> 
 <li><a href="${daumBtn }">다음</a></li>
 <li><a href="${youtubeBtn }">유투브</a></li> 
<li><a href=${doc }>내부문서</a> --%>
<!-- </ul> -->

 <%-- <c:import url="http://search.daum.net/search"> --%> 
 <%-- <c:param name="nil_suggest" value="btn"></c:param> --%> 
 <%-- <c:param name="w" value="img"></c:param> --%> 
 <%-- <c:param name="q" value="고양이"></c:param> --%> 
 <%-- </c:import> --%> 
	
<%-- <c:url value="1_basic.jsp"/> --%>

<!-- 6. c:redirect는 페이지 리다이렉팅 -->

 <c:redirect url="http://search.daum.net/search"> 
 <c:param name="nil_suggest" value="btn"></c:param> 
 <c:param name="w" value="img"></c:param> 
 <c:param name="q" value="고양이"></c:param> 
 </c:redirect>  
 
 	<%-- 
 	-c:import & c:redirect 차이점-
 	import는 지정된 url을 태그가 사용된 jsp페이지에 출력시키는 기능/ redirect는 지정된 url로 페이지를 이동
 	import url은 기본 url / context는 다음에 오는 로컬 어플리케이션의 이름
 	redirect url은 사용자 브라우저에서 리다이렉트하는 url / context	는 다음에 오는 로컬 웹 응용 프로그램의 이름	
 	import 페이지 첨부,import 태그 내에 param 태그도 사용할 수 있다. 
 	redirect 페이지 이동
 	--%>
	
</body>
</html>

