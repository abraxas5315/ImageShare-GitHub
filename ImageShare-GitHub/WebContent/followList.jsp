<%@page import="org.apache.catalina.startup.SetAllPropertiesRule"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="data.Member,java.util.ArrayList, java.util.List"%>
<%
ArrayList<Member> follow = (ArrayList<Member>) request.getAttribute("follow");%>


<%
	// �T�[�u���b�g�p�X�̍쐬
	String path = request.getContextPath() + "/main";

	// ���O�C�����̎擾

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
	<meta http-equiv="Content-Type" name="author" content="N.Tsukazawa text/html; charset=Windows-31J">
	<link rel="stylesheet" href="personal.css" type="text/css">
		<title>�t�H���[�ꗗ</title>
	</head>

	<body onLoad="document.form1.id.focus();">
	<jsp:include page="header.jsp"/>

		<div align="center">

			<h3></h3>

			<form name="form1" method="post" action="<%= path %>">
			<h1>�t�H���[</h1>
				<table>
				<%// �t�H���[���Ă���l�̃��X�g��\������
				for(Member mem : follow){%>
				<tr>	<td><div class="icon"><img border="0" src="<%=mem.getIcon() %>" width="80" height="80" alt="�C���X�g1"></div></td>
						<td><a href="personal"><%=mem.getName() %> <%=mem.getAccountId() %></a><br><%=mem.getProfile() %></td>
						</tr><%} %>

				</table>
			</form>
		</div>
	</body>
</html>