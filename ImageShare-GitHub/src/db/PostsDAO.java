package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import data.Article;
/**
 * 投稿する際に使われるDAO
 * 
 * @author s.funo
 *
 */
public class PostsDAO{
	public void post(Article article) throws SQLException{
		// クエリ文
		String query = "INSERT INTO teama.t_article(account_id,image_url,text,date) VALUES(?,?,?,?)";

		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try
		(
			Connection con = supplier.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
		)
		{
		Calendar cal = Calendar.getInstance();
		cal.setTime(article.getDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date d2 = new java.sql.Date(cal.getTimeInMillis());
		// コードを設定
		stmt.setString(1,article.getAccountId());
		stmt.setString(2, article.getImageUrl());
		stmt.setString(3, article.getText());
		stmt.setDate(4, d2);

		stmt.executeUpdate();
		}
	}
}