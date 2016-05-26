<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="imageProcess.*" %>
<%@ page import="servlet.ImageServlet" %>
<%String size =(String)session.getAttribute("size"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="Windows-31J">
		<title>投稿</title>
	</head>
	<body>
		<div align="center">
			<%if(size != null){ %>
				 <font color="red">ファイルサイズを2MBまでにしてください。
				</font>
			<%} %>
			<form action="image" method="post" enctype="multipart/form-data">
				<input type="file" name="filename" required >
					<span><br>ベースフィルター
					</span>
					<table>
						<tr><td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="base" value="None"checked></th>
						<th><input type = "radio" name="base" value="<%= Mosaic.FILTER_VALUE %>">
							<select name="<%= Mosaic.PARAM_NAME_MOSAIC_LENGTH %>">
								<option value="10">小さめ</option>
								<option value="20" selected>普通</option>
								<option value="30">大きめ</option>
							</select></th>
						<th><input type = "radio" name="base" value="<%=MonochromeEffect.FILTER_VALUE%>">
						<select name="<%= MonochromeEffect.PARAM_NAME_COLOR_CONCOLOR  %>">
								<option value="<%= MonochromeEffect.GRAY_SCALE%>">グレー</option>
								<option value="<%=MonochromeEffect.REDDING%>">レッド</option>
								<option value="<%=MonochromeEffect.BLUING%>">ブルー</option>
							</select></th></tr>
					</table>
					<span>詳細フィルター
					</span>
					<table>
						<tr><td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<th><img src = "sample/sample_none_filter.jpg" width = "100"></th>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="filter" value="<%= NoneFilter.FILTER_VALUE  %>"checked></th>
						<th><input type = "radio" name="filter" value="<%= CircleClipper.FILTER_VALUE %>"></th>
						<th><input type = "radio" name="filter" value="<%= TextOnImage.FILTER_VALUE %>"><br>
						<input class="filter" type="text" name="<%= TextOnImage.PARAM_NAME_TEXT %>" size="10" maxlength="10">
						<select name="<%= TextOnImage.PARAM_NAME_VALIGN %>">
								<option value="center">中央揃え</option>
								<option value="bottom">下揃え</option>
							</select>
							<select name="<%= TextOnImage.PARAM_NAME_TEXT_COLOR %>">
								<option value="white">ホワイト</option>
								<option value="red">レッド</option>
							</select>
						</th>
						<th><input type = "radio" name="filter" value="<%= GradationEffect.FILTER_VALUE %>"></th>
						<th><input type = "radio" name="filter" value="<%= ImageOverlay.FILTER_VALUE %>"></th></tr>
					</table>
					<br>
					<input type="submit" value ="画像をアップロード">
			</form>
		</div>
	</body>
</html>