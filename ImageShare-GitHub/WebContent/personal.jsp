
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
 <%@ page import="data.*, java.util.ArrayList, java.util.List, java.text.SimpleDateFormat"%>

<%
PersonalData personal =  (PersonalData) request.getAttribute("personalData");
Member member = (Member) request.getAttribute("other");
List<Article> listArticle = (ArrayList<Article>) request.getAttribute("list.article");

SimpleDateFormat formatA =
new SimpleDateFormat("yyyy�NMM��dd�� HH��mm��ss�b");
String formatDate = "";

%>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="personal.css" type="text/css">
<title>�l�y�[�W���</title>

</head>
<body>
<jsp:include page="header.jsp"/>

	<div id="wrapper">
		<div class="my-profile">
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
					<th> <form action="ShowFollowServlet" method="POST">
					<input type="submit" value="�t�H���[�ꗗ"> </form></th>
					<th> �t�H�����[�� <%=personal.getFollowers() %> </th>
					<th> ���e�� <%=personal.getArticles() %> </th>
				</tr>
			</table>

			<p> <%=member.getProfile() %></p>
		</div>


		<div class="new">
			<a href="javascript:location.reload();" id="new" class="button">�ŐV�����擾����</a>
		</div>

		<%for(int i=0; i<listArticle.size(); i++){
		 Article la = listArticle.get(i);
		%>

			<div class="content">

				<div class="message">
					<div class="icon">
						<img border="0" src="images/Desert.jpg" width="80" height="80" alt="�C���X�g1">
					</div>

					<div class="account">

						<span class="name">
					 		 <%=la.getName() %>
						</span>

						<strong class="user-id">
							ID <%=la.getAccountId() %>
						</strong>

					</div>

					<div class="comment">

						<%=la.getText() %>
						<div class="date">
						<% formatDate = formatA.format(la.getDate()); %>
						<%=formatDate %>
						</div>

					</div>


				</div>

				<div class="my-image">

					<img border="0" src="images/Desert.jpg" width="500" height="500" alt="�C���X�g1">

				</div>
			</div>



		<%
		}
		%>


	</div>
</body>
</html>