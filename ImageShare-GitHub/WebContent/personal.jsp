
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
 <%@ page import="data.*"%>

<%
PersonalData personal =  (PersonalData) request.getAttribute("personalData");
Member member = (Member) session.getAttribute("member");

%>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="personal.css" type="text/css">
<title>�l�y�[�W���</title>
</head>
<body>
	<div align="center">
		�w�b�_�[����
	</div>
	<div id="wrapper">
		<table class="user">
			<tr>
				<!-- <th> <img border="0" src="<%=member.getIcon()%>" width="128" height="128" alt="�C���X�g1"> </th>   -->
				<th> <img border="0" src="images/Desert.jpg" width="128" height="128" alt="�C���X�g1"> </th>
				<th> <%=member.getName() %> </th>
				<th> <%=member.getAccountId() %> </th>
			</tr>
		</table>

		<table class="follow">
			<tr>
				<th> �t�H���[�� <%=personal.getFollows() %> </th>
				<th>  <input type="submit" value="�t�H���[�ꗗ"> </th>
				<th> �t�H�����[�� <%=personal.getFollowers() %> </th>
				<th> ���e�� <%=personal.getArticles() %> </th>
			</tr>
		</table>

		<p> <%=member.getProfile() %></p>

		<table class="time-line">
			<tr>
				<td> �j�b�N�l�[��</td>
			</tr>
			<tr>
				<td> ���[�UID</td>
				<td> �^�C���X�^���v</td>
			</tr>
			<tr>
				<td class="tl-image">
					<img border="0" src="images/Desert.jpg" width="500" height="500" alt="�C���X�g1">
				</td>
			</tr>
			<tr>
				<td class="coment">
					���e�R�����g����
				</td>
			</tr>

		</table>


	</div>
</body>
</html>