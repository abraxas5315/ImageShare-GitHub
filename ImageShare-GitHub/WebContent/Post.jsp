<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ page import="servlet.ImageServlet" %>
    <%//String dstImg =(String)request.getAttribute("datImage"); %>
    <%String dstImg ="sample/sample_none_filter.jpg"; %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
		<title>���e</title>
	</head>
	<body>
		<div align="center">
			<form action="post" method="post">
				<table>
					<tr><td><img src = "<%= dstImg %>" width = "100"></td>
					<td><textarea rows ="3"cols ="40" maxlength = "280"required placeholder="�R�����g�����"></textarea>
				</table>
				<input type = submit value ="���e">
			</form>
		</div>
	</body>
</html>