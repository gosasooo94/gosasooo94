<%@ page language="java" contentType="text/html; charset=utf-8"%>
 <%@ page import="mybatis.guest.model.Comment" %>   
 <%@ page import="mybatis.guest.service.CommentService" %>    
  
      
  <!-- 키에 해당하는 글번호를 넘겨받아 서비스의 메소드 호출  -->
  <% 
  long commentNo = Integer.parseInt(request.getParameter("commentNo"));
  Comment comment = CommentService.getInstance().selectCommentByPrimaryKey(commentNo);
  
  %>
     
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
<title> 메세지 보기 </title>
</head>
<body>
<form name="frm" action="updateCommentView.jsp" >

<table border="1">

		<tr><td>글번호</td><td><input type="text" name="commentNo"  value="<%=  comment.getCommentNo()%>" readonly></td></tr>
		<tr><td>작성자</td><td><input type="text" name="userId" value="<%=  comment.getUserId()%>"></td></tr>
		<tr><td>메세지</td><td><input type="text" name="commentContent" value="<%=  comment.getCommentContent()%>"></td></tr>
		<tr><td>등록일</td><td><%=  comment.getRegDate()%></td></tr>
		<tr><td colspan="2">
				<input type="submit" id="updateOkBtn" value="수정확인" />
				<input type="button" id="cancel" value="취소" />
		</td></tr>
</table>
</form>
<script>
var cancel = document.getElementById("cancel");
cancel.addEventListener("click", function(){
	location.href = "listComment.jsp";
});

</script>
</body>
</html> 