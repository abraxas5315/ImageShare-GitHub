<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>


<%
	// �T�[�u���b�g�p�X�̍쐬
	String path = request.getContextPath() + "/main";

	// ���O�C�����̎擾

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
		<title>�t�H���[�ꗗ</title>
	</head>

	<body onLoad="document.form1.id.focus();">
	<jsp:include page="header.jsp"/>

		<div align="center">

			<h3></h3>



			<p style="margin: 1em;">

			<form name="form1" method="post" action="<%= path %>">
			<h1>�t�H���[</h1>
				<table  cellspacing=1 cellpadding=20>
				<%for(Member member : member){%>
				<tr>	<td><%=member.getIcon %></td>
						<td><%=member.getName %><br><%=member.getProfile %></td>
						<td><%=member.getAccountId %></td>
						</tr><%} %>

				</table>

			</form>

		</div>

	</body>
</html>