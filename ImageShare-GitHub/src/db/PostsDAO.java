package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import data.Article;

public class PostsDAO{
	public void post(Article article) throws SQLException{
		// クエリ文
		String query = "INSERT INTO teamA.article(account_id,image_url,text,date) VALUES(?,?,?,?)";

		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try
		(
			Connection con = supplier.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
		)
		{
		// コードを設定
		stmt.setString(1,article.getAccountId());
		stmt.setString(2, article.getImageUrl());
		stmt.setString(3, article.getText());
		stmt.setDate(4, (Date) article.getDate());

		stmt.executeUpdate();
		}
	}
}
