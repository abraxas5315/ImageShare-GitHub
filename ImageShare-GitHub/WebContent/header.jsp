<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ page import="data.Member"%>
<!DOCTYPE html">
<%
	Member member = (Member) session.getAttribute("member");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J"
	name="author" content="kawashima">
<title>Title</title>
</head>
<body>
<% if(member == null) {
request.getRequestDispatcher("login.jsp").forward(request,response);
}%>
	<div style="background: darkseagreen">
		<table>
			<tr>
				<td><a href="personal"><%=member.getName()%>&nbsp;ID:&nbsp;<%=member.getAccountId()%></a></td>
				<td><a href="TL"><input type=button name=timeline
						value="タイムライン"></a></td>
				<td><a href="image_upload.jsp"><input type=button name=post
						value="投稿"></a></td>
				<td><a href="login.jsp"><input type=button name=logout
						value="ログアウト"></a></td>
			</tr>
		</table>
	</div>
</body>
</html>