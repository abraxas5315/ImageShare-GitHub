<%@page import="data.PersonalData"%>
<%@page import="org.apache.catalina.startup.SetAllPropertiesRule"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="data.Member,java.util.ArrayList, java.util.List"%>
<%
ArrayList<Member> follow = (ArrayList<Member>) request.getAttribute("follow");%>


<%
	// �T�[�u���b�g�p�X�̍쐬
	String path = request.getContextPath() + "/main";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
	<meta http-equiv="Content-Type" name="author" content="N.Tsukazawa text/html; charset=Windows-31J">
	<link rel="stylesheet" href="follow.css" type="text/css">
		<title>�t�H���[�ꗗ</title>
	</head>

	<body>
		<jsp:include page="header.jsp"/>

	<div align="center"><h1>�t�H���[</h1></div>

		<table class="follow">
			<%if(follow.size() == 0) {%>
				<div align ="center"><h2>�t�H���[���Ă���l�͂��܂���</h2></div>
			<%}else{ %>
				<%// �t�H���[���Ă���l�̃��X�g��\������
				for(Member mem : follow){%>
				<tr>	<td><div class="icon"><img border="0" src="<%=mem.getIcon() %>" width="80" height="80" alt="�C���X�g1"></div></td>
						<td><form action="personal" method="post">
							<input class="name" type="submit" name="���O" value="<%=mem.getName() %> : <%=mem.getAccountId() %>">
							<input type="hidden" name="otherId" value="<%=mem.getAccountId() %>"></form>
							<br><%=mem.getProfile() %></td>
						</tr><%}} %>
		</table>
	</body>
</html>