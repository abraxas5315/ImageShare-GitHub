<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ page import="servlet.ImageServlet" %>
    <%String size =(String)session.getAttribute("size"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
		<title>投稿</title>
	</head>
	<body>
		<div align="center">
			<%if(size != null){ %>
				 <font color="red">ファイルサイズを2MBまでにしてください。
				</font>
			<%} %>
			<form action="image" method="post">
				<input type="file" name="file" required >
					<span><br>ベースフィルター
					</span>
					<table>
						<tr><td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td>
						<td><img src = "sample/sample_none_filter.jpg" width = "100"></td></tr>
						<tr><th><input type = "radio" name="base" value="None"checked></th>
						<th><input type = "radio" name="base" value="Mosaic"></th>
						<th><input type = "radio" name="base" value="Scale">
						<select name="Mono">
								<option value="grayScale">グレー</option>
								<option value="redScale">レッド</option>
								<option value="blueScale">ブルー</option>
							</select></th></tr>
					</table>
					<span>詳細フィルター
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
								<option value="center">中央揃え</option>
								<option value="bottom">下揃え</option>
							</select>
							<select name="TextOnColor">
								<option value="white">ホワイト</option>
								<option value="red">レッド</option>
							</select>
						</th>
						<th><input type = "radio" name="filter" value="Gradation"></th>
						<th><input type = "radio" name="filter" value="ImageOverlay"></th></tr>
					</table>
					<input type = submit value ="画像をアップロード">
			</form>
		</div>
	</body>
</html>