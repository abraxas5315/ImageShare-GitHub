<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="servlet.ImageServlet" %>
<%
	// パスの作成
	String dstImg =(String)request.getAttribute("dstImage");
	String path =(String)request.getAttribute("path");
	path += dstImg;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
		<title>投稿</title>
		<link rel="stylesheet" href="personal.css" type="text/css">
	</head>
	<body>
	<jsp:include page="header.jsp"/>
		<div align="center" id="wrapper">
			<form action="post" method="post">
				<input type="hidden" name="dstImage" value="<%=path %>">
				<table>
					<tr><td><img src = "<%= path %>" width = "100"></td>
					<td><textarea rows ="3"cols ="40" maxlength = "280"required placeholder="コメントを入力" name="text"></textarea>
				</table>
				<input type = submit value ="投稿">
			</form>
		</div>
	</body>
</html>