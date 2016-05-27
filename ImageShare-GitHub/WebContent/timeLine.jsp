<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>

    <%@ page import="data.*, java.util.ArrayList, java.util.List, java.text.SimpleDateFormat"%>

<%
Member member = (Member) session.getAttribute("member");
List<Article> timeLine = (ArrayList<Article>) request.getAttribute("tl.article");

SimpleDateFormat formatA =
new SimpleDateFormat("yyyy�NMM��dd HH��mm��ss�b");
String formatDate = "";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">

<link rel="stylesheet" href="timeline.css" type="text/css">
<title>�^�C�����C��</title>
</head>
<body>

<jsp:include page="header.jsp"/>


		<div class="my-info">
			<div class="user">
				<div class="icon">
				<img src="<%=member.getIcon()%>" width="100" alt="�C���X�g1">
				</div>
				<div class="my-name">
					<p class="name-style"> �j�b�N�l�[�� </p>
					<p> <%=member.getName() %> </p>
					<p class="name-style"> ���O </p>
					<p> <%=member.getAccountId() %> </p>
				</div>
			</div>

			<div class="my-profile">
				<%=member.getProfile() %>
			</div>
		</div>



<div id="wrapper">

		<div class="new">
			<a href="TL" id="new" class="button">�ŐV�����擾����</a>
		</div>

		<%for(int i=0; i<timeLine.size(); i++){
		Article la = timeLine.get(i);
		%>
		<hr>

			<div class="content">

				<div class="message">
					<div class="icon">
						<img border="0" src="<%=la.getMember().getIcon() %>" width="80" height="80" alt="�C���X�g1">
					</div>

					<div class="account">
							<span class="name">
						 		 <%=la.getName() %>
							</span>

							<strong class="user-id">
								ID <%=la.getAccountId() %>
							</strong>

							<form action="personal" method="post">
								<input type="submit" name="���O" value="�l�y�[�W��">
								<input class="hidden" type=�hhidden�h name="otherId" value="<%=la.getAccountId()%>">
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

					<img border="0" src="<%=la.getImageUrl()%>" width="500" alt="�C���X�g1">

				</div>
			</div>



		<%
		}
		%>
	</div>



</body>
</html>