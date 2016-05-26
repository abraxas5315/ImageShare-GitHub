package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Article;
import data.Member;

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
	public List<Article> selectTL(Member member) throws SQLException
	{
		List<Member> follows = null;
		List<Article> articles = new ArrayList<Article>();
		StringBuilder str = new StringBuilder();
		str.append("SELECT t2.account_id,t1.name,t1.my_image,t1.profile ");
		str.append("FROM m_account t1,t_follow t2 ");
		str.append("WHERE t1.account_id = t2.account_id  ");
		str.append("AND t2.follow_id= ? ");
		String query = str.toString();

		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try (Connection con = supplier.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);) {
			// コードを設定
			pstmt.setString(1, member.getAccountId());
			ResultSet res = pstmt.executeQuery();

			follows = new ArrayList<Member>();
			while (res.next()) {
				follows.add(new Member(
						(res.getString("account_id")),
						(res.getString("name")),
						(res.getString("my_image")),
						(res.getString("profile"))
						));
			}

			for(int i=0 ; i<follows.size() ; i++)
			{
				System.out.println(follows.get(i).getAccountId());
			}

			query = "SELECT * FROM t_article WHERE account_id=IN(?)";
			StringBuilder str2 = new StringBuilder();
			str2.append(member.getAccountId());
			for(Member f : follows)
			{
				str.append(",").append(f.getAccountId());
			}
			pstmt.setString(1, str.toString());
			try(
				PreparedStatement pstmt2 = con.prepareStatement(query);)
				{

				ResultSet res2 = pstmt.executeQuery();
				Member fMember = null;

				while (res2.next()) {
					for(Member f : follows){
						if(res2.getString("") != f.getAccountId())
						{
							fMember = f;
							break;
						}
					}
					articles.add(new Article(
							fMember,
							(res2.getString("image_url")),
							(res2.getString("text")),
							(res2.getDate("date"))
							));
				}
			}
		}
		for(int i=0 ; i<articles.size() ; i++)
		{
			System.out.println(articles.get(i).getAccountId());
		}

		return articles;

	}
}
