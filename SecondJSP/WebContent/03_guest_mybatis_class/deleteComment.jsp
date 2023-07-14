<%@ page language="java" contentType="text/html; charset=utf-8"%>
 <%@ page import="mybatis.guest.model.Comment" %>   
 <%@ page import="mybatis.guest.service.CommentService" %>    
  
      
  <!-- 키에 해당하는 글번호를 넘겨받아 서비스의 메소드 호출  -->
  <% 
  long commentNo = Integer.parseInt(request.getParameter("commentNo"));
  CommentService.getInstance().deleteComment(commentNo);
  
  %>
     
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
<title> 메세지 보기 </title>
</head>
<body>
삭제되었는지 확인해보세요. <br/>
<a href="listComment.jsp">목록보기</a>
<script>


</script>
</body>
</html> 