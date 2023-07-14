<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>파일 등록</title></head>
<body>

<!-- 파일명을 얻어오려면  FileUploadServlet에서 forward 전에 지정한 Attribute를 확인 -->
[${uploadedItem.fileName}] 파일을 업로드 했습니다.
<br/>
<a href="/SecondJSP/09_upload_class/2_pds/list.jsp">[목록보기]</a>
</body>
</html>