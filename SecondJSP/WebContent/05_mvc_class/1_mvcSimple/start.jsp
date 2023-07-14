<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 기초 이해하기</title>
</head>
<body>
   <%   //프로젝트명을 변수에 담아놓기
   String projectName = "/SecondJSP";
   %>
   <a href="<%= projectName %>/05_mvc_class/1_mvcSimple/simpleView.jsp"> 기존방식 </a><br/>
   
   컨트롤 파일 호출 형태<br/>
   
   <a href="<%=projectName %>/SimpleControl">여기부터</a><br/>
   ||
   <a href="<%=projectName %>/xxxx.sc">여기부터</a><br/>
   ||
   <a href="<%=projectName %>/xxxx.sc?type=first">여기부터</a>
   
</body>
</html>