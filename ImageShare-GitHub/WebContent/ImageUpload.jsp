<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ page import="servlet.ImageServlet" %>
    <%String size =(String)session.getAttribute("size"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
		<title>���e</title>
	</head>
	<body>
		<div align="center">
			<%if(size != null){ %>
				 <font color="red">�t�@�C���T�C�Y��2MB�܂łɂ��Ă��������B
				</font>
			<%} %>
			<form action="image" method="post">
				<input type="file" name="file" required >
					<span><br>�x�[�X�t�B���^�[
					</span>
					<table>
						<tr><td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="base" value="None"checked></th>
						<th><input type = "radio" name="base" value="Mosaic"></th>
						<th><input type = "radio" name="base" value="Scale">
						<select name="Mono">
								<option value="grayScale">�O���[</option>
								<option value="redScale">���b�h</option>
								<option value="blueScale">�u���[</option>
							</select></th></tr>
					</table>
					<span>�ڍ׃t�B���^�[
					</span>
					<table>
						<tr><td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="filter" value="filter.none"checked></th>
						<th><input type = "radio" name="filter" value="Circl"></th>
						<th><input type = "radio" name="filter" value="Text">
						<select name="TextOn">
								<option value="center">��������</option>
								<option value="bottom">������</option>
							</select>
							<select name="TextOnColor">
								<option value="white">�z���C�g</option>
								<option value="red">���b�h</option>
							</select>
						</th>
						<th><input type = "radio" name="filter" value="Gradation"></th>
						<th><input type = "radio" name="filter" value="ImageOverlay"></th></tr>
					</table>
					<input type = submit value ="�摜���A�b�v���[�h">
			</form>
		</div>
	</body>
</html>