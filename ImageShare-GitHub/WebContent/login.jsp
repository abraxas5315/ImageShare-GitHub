<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J"
name="author" content="kawashima">
<title>���O�C�����</title>
</head>
<body>
	<% String message = (String)request.getAttribute("login.error"); %>
	<%-- ���O�C�����j���̂��߃Z�b�V�����폜 --%>
	<% session.invalidate(); %>

	<div align="center">
		<font size="6" color="#000aff">���O�C�����</font>
	</div>
	<div align="center">
		<form action="authentication" method="POST">
		<%-- ����A�N�Z�X�ƃ��O�A�E�g����message��null --%>
		<% if(message == null){ %>
			<h2>���O�ƃp�X���[�h����͂��Ă��������B</h2>
		<% } else { %>
			<h2><font color="#ff0000"><%=message %></font></h2>
		<% } %>
			<br>
			<table>
				<tr>
					<td align="left">&nbsp;&nbsp;���[�UID�F</td>
					<td align="left"><input type="text" name="USERID"></td>
				</tr>
				<tr>
					<td align="left">�p�X���[�h�F</td>
					<td align="left"><input type="password" name="PASSWORD"></td>
				</tr>
			</table>
			<br> <input type="submit" value="���O�C��" name="ACTION">
		</form>
	</div>
</body>
</html>