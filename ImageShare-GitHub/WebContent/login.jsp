<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J"
name="author" content="kawashima">
<title>ログイン画面</title>
</head>
<body>
	<% String message = (String)request.getAttribute("login.error"); %>
	<%-- ログイン情報破棄のためセッション削除 --%>
	<% session.invalidate(); %>

	<div align="center">
		<font size="6" color="#000aff">ログイン画面</font>
	</div>
	<div align="center">
		<form action="authentication" method="POST">
		<%-- 初回アクセスとログアウト時はmessageがnull --%>
		<% if(message == null){ %>
			<h2>名前とパスワードを入力してください。</h2>
		<% } else { %>
			<h2><font color="#ff0000"><%=message %></font></h2>
		<% } %>
			<br>
			<table>
				<tr>
					<td align="left">&nbsp;&nbsp;ユーザID：</td>
					<td align="left"><input type="text" name="USERID"></td>
				</tr>
				<tr>
					<td align="left">パスワード：</td>
					<td align="left"><input type="password" name="PASSWORD"></td>
				</tr>
			</table>
			<br> <input type="submit" value="ログイン" name="ACTION">
		</form>
	</div>
</body>
</html>