<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="imageProcess.*" %>
<%@ page import="servlet.ImageServlet" %>
<%String size =(String)session.getAttribute("size"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="Windows-31J">
		<title>���e</title>
	</head>
	<body>
		<div align="center">
			<%if(size != null){ %>
				 <font color="red">�t�@�C���T�C�Y��2MB�܂łɂ��Ă��������B
				</font>
			<%} %>
			<form action="image" method="post" enctype="multipart/form-data">
				<input type="file" name="filename" required >
					<span><br>�x�[�X�t�B���^�[
					</span>
					<table>
						<tr><td><img src = "sample/a.jpg" width = "100"></td>
						<td><img src = "sample/b.jpg" width = "100"></td>
						<td><img src = "sample/c.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="base" value="None"checked></th>
						<th><input type = "radio" name="base" value="<%= Mosaic.FILTER_VALUE %>">
							<select name="<%= Mosaic.PARAM_NAME_MOSAIC_LENGTH %>">
								<option value="10">������</option>
								<option value="20" selected>����</option>
								<option value="30">�傫��</option>
							</select></th>
						<th><input type = "radio" name="base" value="<%=MonochromeEffect.FILTER_VALUE%>">
						<select name="<%= MonochromeEffect.PARAM_NAME_COLOR_CONCOLOR  %>">
								<option value="<%= MonochromeEffect.GRAY_SCALE%>">�O���[</option>
								<option value="<%=MonochromeEffect.REDDING%>">���b�h</option>
								<option value="<%=MonochromeEffect.BLUING%>">�u���[</option>
							</select></th></tr>
					</table>
					<span>�ڍ׃t�B���^�[
					</span>
					<table>
						<tr><td><img src = "sample/a.jpg" width = "100"></td>
						<td><img src = "sample/d.jpg" width = "100"></td>
						<th><img src = "sample/e.jpg" width = "100"></th>
						<td><img src = "sample/f.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="filter" value="<%= NoneFilter.FILTER_VALUE  %>"checked></th>
						<th><input type = "radio" name="filter" value="<%= CircleClipper.FILTER_VALUE %>"></th>
						<th><input type = "radio" name="filter" value="<%= TextOnImage.FILTER_VALUE %>"><br>
						<input class="filter" type="text" name="<%= TextOnImage.PARAM_NAME_TEXT %>" size="10" maxlength="10">
						<select name="<%= TextOnImage.PARAM_NAME_VALIGN %>">
								<option value="center">��������</option>
								<option value="bottom">������</option>
							</select>
							<select name="<%= TextOnImage.PARAM_NAME_TEXT_COLOR %>">
								<option value="white">�z���C�g</option>
								<option value="red">���b�h</option>
							</select>
						</th>
						<th><input type = "radio" name="filter" value="<%= GradationEffect.FILTER_VALUE %>"></th>
						<th><input type = "radio" name="filter" value="<%= ImageOverlay.FILTER_VALUE %>"></th></tr>
					</table>
					<br>
					<input type="submit" value ="�摜���A�b�v���[�h">
			</form>
		</div>
	</body>
</html>