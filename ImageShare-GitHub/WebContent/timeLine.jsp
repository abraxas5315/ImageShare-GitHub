<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>

    <%@ page import="data.*, java.util.ArrayList, java.util.List, java.text.SimpleDateFormat"%>

<%
Member member = (Member) session.getAttribute("member");
List<Article> timeLine = (ArrayList<Article>) request.getAttribute("tl.article");

SimpleDateFormat formatA =
new SimpleDateFormat("yyyy年MM月dd HH時mm分ss秒");
String formatDate = "";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="personal.css" type="text/css">
<title>タイムライン</title>
</head>
<body>
<jsp:include page="header.jsp"/>

	<div id="wrapper">
		<div class="my-profile">
			<table class="user">
				<tr>
					<!-- <th> <img border="0" src="<%=member.getIcon()%>" width="128" height="128" alt="イラスト1"> </th>   -->
					<th> <img border="0" src="images/Desert.jpg" width="128" height="128" alt="イラスト1"> </th>
					<th> <%=member.getName() %> </th>
					<th> <%=member.getAccountId() %> </th>
				</tr>
			</table>
			<p> <%=member.getProfile() %></p>
		</div>





		<%for(int i=0; i<timeLine.size(); i++){
		Article la = timeLine.get(i);
		%>

			<div class="content">

				<div class="message">
					<div class="icon">
						<img border="0" src="<%=la.getMember().getIcon() %>" width="80" height="80" alt="イラスト1">
					</div>

					<div class="account">
							<span class="name">
						 		 <%=la.getName() %>
							</span>

							<strong class="user-id">
								ID <%=la.getAccountId() %>
							</strong>

<form action="personal" method="post">
<p>○○<input type="submit" name="名前" value="個人ページへ"></p>
<p>○○<input type=”hidden” name="otherId" value="<%=la.getAccountId()%>"></p>
</form>


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

			<hr>

		<%
		}
		%>
	</div>



</body>
</html>