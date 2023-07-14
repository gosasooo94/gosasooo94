<%@ page contentType="text/html; charset=UTF-8" %>

<% String projectName = "/SecondJSP"; %>
<html>
<head><title>파일 등록</title></head>
<body>
<form action="<%=projectName %>/pds" method="post" enctype="multipart/form-data">
파일: <input type="file" name="file" /> <br/>
설명: <input type="text" name="description" /> <br/>
<input type="submit" value="업로드" />
</form>
</body>
</html>

<!-- 1. web.xml에 FileUploadServlet 클래스를 pds 경로로 서블릿 설정해야 한다. -->
<!-- 2. FileUploadServlet 에서 실제 업로드 경로를 변경  -->