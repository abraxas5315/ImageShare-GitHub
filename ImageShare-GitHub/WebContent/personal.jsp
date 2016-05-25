
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
<title>個人ページ画面</title>
</head>
<body>
	<div align="center">
		ヘッダーだよ
	</div>
	<div id="wrapper">
		<table class="user">
			<tr>
				<!-- <th> <img border="0" src="<%=member.getIcon()%>" width="128" height="128" alt="イラスト1"> </th>   -->
				<th> <img border="0" src="images/Desert.jpg" width="128" height="128" alt="イラスト1"> </th>
				<th> <%=member.getName() %> </th>
				<th> <%=member.getAccountId() %> </th>
			</tr>
		</table>

		<table class="follow">
			<tr>
				<th> フォロー数 <%=personal.getFollows() %> </th>
				<th>  <input type="submit" value="フォロー一覧"> </th>
				<th> フォロワー数 <%=personal.getFollowers() %> </th>
				<th> 投稿数 <%=personal.getArticles() %> </th>
			</tr>
		</table>

		<p> <%=member.getProfile() %></p>

		<%for(int i=0; i<listArticle.size(); i++){
		Article la = listArticle.get(i);
		%>

			<div class="content">

				<div class="icon">
					<img border="0" src="images/Desert.jpg" width="80" height="80" alt="イラスト1">
				</div>

				<a class="link" href="./userid">
					<span class="name">
				 		ニックネーム <%=la.getName() %>
					</span>

					<strong class="user-id">
						ユーザID <%=la.getAccountId() %>
					</strong>

				</a>

				<div>

					<%=la.getText() %>

				</div>

				<div>

					タイムスタンプ<%=la.getDate() %>
				</div>
				<div>

					<img border="0" src="images/Desert.jpg" width="500" height="500" alt="イラスト1">

				</div>
			</div>

			<hr>

		<%
		}
		%>


	</div>
</body>
</html>