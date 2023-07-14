<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 </title>
<script type="text/javascript">
	function checkFields()
	{
		var frmObj = document.frm;
		
		frmObj.action = "GuestControl?cmd=save-page";
		frmObj.submit();
	}
</script>
</head>

<body>

	<form name="frm" method="post">
		이름 : <input type="text" name="guestName"/><br/><br/>
		암호 : <input type="password" name="password"/><br/><br/>
		메세지 : <textarea name="message" rows="3" cols="30"></textarea><br/><br/>
		<input type="button" value="메세지 남기기" onclick="Javascript:checkFields()">
	</form>
</body>
</html>