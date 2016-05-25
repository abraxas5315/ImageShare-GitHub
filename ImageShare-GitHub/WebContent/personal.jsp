
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
 <%@ page import="data.*, java.util.ArrayList, java.util.List"%>

<%
PersonalData personal =  (PersonalData) request.getAttribute("personalData");
Member member = (Member) session.getAttribute("member");
List<Article> listArticle = (ArrayList<Article>) request.getAttribute("list.article");

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

		<%for(int i=0; i<listArticle.size(); i++){
		Article la = listArticle.get(i);
		%>

			<div class="content">

				<div class="icon">
					<img border="0" src="images/Desert.jpg" width="80" height="80" alt="�C���X�g1">
				</div>

				<a class="link" href="./userid">
					<span class="name">
				 		�j�b�N�l�[�� <%=la.getName() %>
					</span>

					<strong class="user-id">
						���[�UID <%=la.getAccountId() %>
					</strong>

				</a>

				<div>

					<%=la.getText() %>

				</div>

				<div>

					�^�C���X�^���v<%=la.getDate() %>
				</div>
				<div>

					<img border="0" src="images/Desert.jpg" width="500" height="500" alt="�C���X�g1">

				</div>
			</div>

			<hr>

		<%
		}
		%>


	</div>
</body>
</html>