<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="imageProcess.*" %>
<%@ page import="servlet.ImageServlet" %>
<%String sizeString =(String)request.getAttribute("size");
String back = request.getParameter("back");
//�摜�̓�d�����h�~�p
session.setAttribute("key", "imageUpload");
	//int size = Integer.parseInt(sizeString);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="Windows-31J">
		<link rel="stylesheet" href="personal.css" type="text/css">
		<title>���e</title>

	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div align="center" id="wrapper">
			<%if(sizeString != null){ %>
				 <font color="red">�t�@�C���T�C�Y��2MB�܂łɂ��Ă��������B
				</font>
			<%} %>
			<form action="image" method="post" enctype="multipart/form-data">
					<span><br>�x�[�X�t�B���^�[
					</span>
					<table>
						<tr><td><img src = "sample/a.jpg" width = "100"></td>
						<td><img src = "sample/b.jpg" width = "100"></td>
						<td><img src = "sample/c.jpg" width = "100"></td></tr>
						<tr><th><br><input type = "radio" name="base" value="None"checked></th>
						<th>
							<select name="<%= Mosaic.PARAM_NAME_MOSAIC_LENGTH %>">
								<option value="10">������</option>
								<option value="20" selected>����</option>
								<option value="30">�傫��</option>
							</select><br><input type = "radio" name="base" value="<%= Mosaic.FILTER_VALUE %>">
						</th>
						<th>
							<select name="<%= MonochromeEffect.PARAM_NAME_COLOR_CONCOLOR  %>">
								<option value="<%= MonochromeEffect.GRAY_SCALE.color()%>">�O���[</option>
								<option value="<%=MonochromeEffect.REDDING.color()%>">���b�h</option>
								<option value="<%=MonochromeEffect.BLUING.color()%>">�u���[</option>
							</select><br><input type = "radio" name="base" value="<%=MonochromeEffect.FILTER_VALUE%>">
						</th></tr>
					</table>
					<span>�ڍ׃t�B���^�[
					</span>
					<table>
						<tr><td><img src = "sample/a.jpg" width = "100"></td>
						<td><img src = "sample/d.jpg" width = "100"></td>
						<th><img src = "sample/e.jpg" width = "100"></th>
						<td><img src = "sample/f.jpg" width = "100"></td>
						<td><img src = "sample/g.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="filter" value="<%= NoneFilter.FILTER_VALUE  %>"checked></th>
						<th><input type = "radio" name="filter" value="<%= CircleClipper.FILTER_VALUE %>"></th>
						<th>
						<input class="filter" type="text" name="<%= TextOnImage.PARAM_NAME_TEXT %>" size="10" maxlength="10">
						<br><select name="<%= TextOnImage.PARAM_NAME_VALIGN %>">
								<option value="center">��������</option>
								<option value="bottom">������</option>
							</select>
							<select name="<%= TextOnImage.PARAM_NAME_TEXT_COLOR %>">
								<option value="white">�z���C�g</option>
								<option value="red">���b�h</option>
							</select>
							<br><input type = "radio" name="filter" value="<%= TextOnImage.FILTER_VALUE %>">
						</th>
						<th><select name="<%= GradationEffect.PARAM_NAME_GRADATION_COLOR %>">
								  <option value="black">�u���b�N</option>
								<option value="red">���b�h</option>
							</select><br>
							<input type = "radio" name="filter" value="<%= GradationEffect.FILTER_VALUE %>"></th>
						<th>
								<select name="<%= ImageOverlay.PARAM_NAME_IMG_OVERLAY %>">
								<option value="sample">�T���v��</option>
								<option value="correct">����</option>
								<option value="wrong">�s����</option></select><br>
								<input type = "radio" name="filter" value="<%= ImageOverlay.FILTER_VALUE %>">
						</th></tr>
					</table>
					<br><br>
					<%if(back != null){ %>
					<input type="file" name="filename" required value = "<%=back%>">
					<%} else{ %>
					<input type="file" name="filename" required>
					<%} %>
					<input type="submit" value ="�摜���A�b�v���[�h">
					<br><br><br>
			</form>
		</div>
	</body>
</html>