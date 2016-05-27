
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
 <%@ page import="data.*, java.util.ArrayList, java.util.List, java.text.SimpleDateFormat"%>

<%
PersonalData personal =  (PersonalData) request.getAttribute("personalData");
Member member = (Member) request.getAttribute("other");
List<Article> listArticle = (ArrayList<Article>) request.getAttribute("list.article");

SimpleDateFormat formatA =
new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
String formatDate = "";

%>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="personal.css" type="text/css">
<title>個人ページ画面</title>

</head>
<body>
<jsp:include page="header.jsp"/>

	<div id="wrapper">
		<div class="my-profile">
			<div class="user">
					<div class="my-icon">
						<img src="<%=member.getIcon()%>" width="150" alt="イラスト1">
					</div>
					<div class="my-name">
						<%=member.getName() %>
					</div>
					<div class="my-id">
						<%=member.getAccountId() %>
					</div>

			</div>

			<table class="follow">
				<tr>
					<th> フォロー数 <%=personal.getFollows() %> </th>
					<th>
						<form action="ShowFollowServlet" method="POST">
							<input type="submit" value="フォロー一覧">
							<input type="hidden" name="otherId" value="<%=member.getAccountId()%>">
						</form>
					</th>
					<th> フォロワー数 <%=personal.getFollowers() %> </th>
					<th> 投稿数 <%=personal.getArticles() %> </th>
				</tr>
			</table>

			<p> <%=member.getProfile() %></p>
		</div>


		<div class="new">
			<a href="javascript:location.reload();" id="new" class="button">最新情報を取得する</a>
		</div>

		<%for(int i=0; i<listArticle.size(); i++){
		 Article la = listArticle.get(i);
		%>

			<div class="content">

				<div class="message">
					<div class="icon">
						<img src="<%=la.getMember().getIcon() %>" width="80" height="80" alt="イラスト1">
					</div>

					<div class="account">

						<span class="name">
					 		 <%=la.getName() %>
						</span>

						<strong class="user-id">
							ID: <%=la.getAccountId() %>
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

					<img border="0" src="<%=la.getImageUrl()%>" width="500" alt="イラスト1">

				</div>
			</div>



		<%
		}
		%>


	</div>
</body>
</html>