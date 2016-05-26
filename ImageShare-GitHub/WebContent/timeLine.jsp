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

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>


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

					<img border="0" src="<%=la.getImageUrl()%>" width="500" height="500" alt="イラスト1">

				</div>
			</div>

			<hr>

		<%
		}
		%>



</body>
</html>