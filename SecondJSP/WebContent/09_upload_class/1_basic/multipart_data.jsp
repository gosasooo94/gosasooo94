<%@ page contentType="text/plain; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.io.InputStream" %>
<%
	//request 객체에는 입력스트림이 response에는 출력 스트림이 있음. 얻어오기
	InputStream is = request.getInputStream();
	ServletOutputStream os = response.getOutputStream();
	
	os.write('[');
	os.write(request.getContentType().getBytes());
	os.write(']');
	os.write('\n');
	
	int data = -1;
	while ( (data = is.read()) != -1) {
		os.write(data);
	}
%>