<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="servlet.ImageServlet" %>
<%
	// �p�X�̍쐬
	String dstImg =(String)request.getAttribute("dstImage");
	String path =(String)request.getAttribute("path");
	path += dstImg;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
		<title>���e</title>
		<link rel="stylesheet" href="personal.css" type="text/css">
	</head>
	<body>
	<jsp:include page="header.jsp"/>
		<div align="center" id="wrapper">
			<form action="post" method="post">
				<input type="hidden" name="dstImage" value="<%=path %>">
				<table>
					<tr><td><img src = "<%= path %>" width = "300"></td>
					<td><textarea rows ="15"cols ="40" maxlength = "280"required placeholder="�R�����g�����" name="text"></textarea>
				</table>
				<br>
				<br>
				<input type="button" onClick="document.formB.submit();" value="�߂�">
				<input type = submit value ="���e">
				<br><br><br>
			</form>
			<form action ="image_upload.jsp" name ="formB">
					<input type="hidden" name="back" value="path">
			</form>
		</div>
	</body>
</html>