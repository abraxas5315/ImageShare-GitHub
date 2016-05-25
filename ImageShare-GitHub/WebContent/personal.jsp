
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
 <%@ page import="data.*, java.util.ArrayList, java.util.List"%>

<%
PersonalData personal =  (PersonalData) request.getAttribute("personalData");
Member member = (Member) session.getAttribute("member");
List<Article> la = (ArrayList<Article>) request.getAttribute("list.article");

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

		<table class="time-line">
			<tr>
				<td> ニックネーム <%=la.get(0).getName() %></td>
			</tr>
			<tr>
				<td> ユーザID <%=la.get(0).getAccountId() %></td>
				<td> タイムスタンプ<%=la.get(0).getDate() %></td>
			</tr>
			<tr>
				<td class="tl-image">
					<img border="0" src="images/Desert.jpg" width="500" height="500" alt="イラスト1">
				</td>
			</tr>
			<tr>
				<td class="coment">
					<%=la.get(0).getText() %>
				</td>
			</tr>

		</table>


	</div>
</body>
</html>