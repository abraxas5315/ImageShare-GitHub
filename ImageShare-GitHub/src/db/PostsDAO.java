package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.Article;
/**
 * 投稿する際に使われるDAO
 *
 * @author s.funo
 *
 */
import data.Member;

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

	/**
	 * データベースからＴＬを取得する
	 * @param member ログインしている会員情報
	 * @return 取得したＴＬ
	 * @throws SQLException
	 * @author s.kawashima
	 */
	public List<Article> selectTL(Member member) throws SQLException
	{
		//フォローリスト
		List<Member> follows = new ArrayList<Member>();
		//TL格納用のリスト
		List<Article> articles = new ArrayList<Article>();

		//フォローリスト取得用のSQL文
		StringBuilder str = new StringBuilder();
		str.append("SELECT t2.account_id , name , my_image , profile ");
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

			while (res.next()) {
				follows.add(new Member(
						(res.getString("account_id")),
						(res.getString("name")),
						(res.getString("my_image")),
						(res.getString("profile"))
						));
			}

			//TL取得用のSQL文
			query = "SELECT account_id , image_url , text , date FROM t_article WHERE account_id IN(?)";

			try (
					PreparedStatement pstmt2 = con.prepareStatement(query);)
			{
				StringBuilder follow = new StringBuilder();
				//IN句に追加する条件の作成（フォローとログインしているユーザのaccountId）
				follow.append(member.getAccountId());
				for(Member f : follows)
				{
					follow.append(",").append(f.getAccountId());
				}
				// コードを設定
				pstmt2.setString(1, follow.toString());

				ResultSet res2 = pstmt2.executeQuery();
				Member fMember = null;

				//取得したＴＬの格納
				while (res2.next()) {
					for(Member f : follows){
						//記事が誰のものと一致しているかをチェック
						if(res2.getString("") != f.getAccountId())
						{
							fMember = f;
							break;
						}
					}
					//記事の格納
					articles.add(new Article(
							fMember,
							(res2.getString("image_url")),
							(res2.getString("text")),
							(res2.getDate("date"))
							));
				}
			}
		}
		return articles;
	}


	/**
	 * 個人の投稿を取得する
	 * @param member 表示する個人ページの会員情報
	 * @return 取得した個人の投稿
	 * @throws SQLException
	 * @author s.kawashima
	 */
	public List<Article> selectMyPosts(Member member) throws SQLException
	{
		//投稿格納用のリスト
		List<Article> articles = new ArrayList<Article>();

		//個人投稿取得
		String query = "SELECT image_url , text , date FROM t_article WHERE account_id = ?";

		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try (Connection con = supplier.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);)
		{
			//コード設定
			pstmt.setString(1, member.getAccountId());

			ResultSet res2 = pstmt.executeQuery();

			//取得した1個人投稿の格納
			while (res2.next()) {
				//記事の格納
				articles.add(new Article(
						member,
						(res2.getString("image_url")),
						(res2.getString("text")),
						(res2.getDate("date"))
						));
			}
		}
		return articles;

	}
}
