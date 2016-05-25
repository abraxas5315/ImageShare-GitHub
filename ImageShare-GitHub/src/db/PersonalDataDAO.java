package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import data.Article;
import data.Member;
import data.PersonalData;

/**
 * 個人ページDAO
 *
 * @author Y.Shiomi
 *
 */
public class PersonalDataDAO {

	/**
	 * 個人ページで使用するフォロー数、フォロワー数、投稿数 を取得するメソッド
	 *
	 * @param member
	 *            // Member型のデータ
	 * @return 個人ページ情報（フォロー数、フォロワー数、投稿数）
	 * @throws SQLException
	 */
	public PersonalData selectPersonalData(Member member) throws SQLException {
		// 初期化
		PersonalData personalData = null;

		// アカウントIDに対するフォロー数、フォロワー数、投稿数を取得するクエリ

		String query = "SELECT COUNT(*) as personal FROM t_follow WHERE account_id=?"
				+ "UNION ALL "
				+ "SELECT COUNT(*) as personal FROM t_follow WHERE follow_id=?"
				+ "UNION ALL "
				+ "SELECT COUNT(*) as personal FROM t_article WHERE account_id=?";

		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try (Connection con = supplier.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);) {
			// コードを設定
			pstmt.setString(1, member.getAccountId());
			pstmt.setString(2, member.getAccountId());
			pstmt.setString(3, member.getAccountId());

			ResultSet res = pstmt.executeQuery();

			int follows = 0; // フォロー数
			int followers = 0; // フォロワー数
			int articles = 0; // 投稿数

			// SQL実行結果を格納する
			if (res.next()) {
				// フォロー数の格納
				follows = Integer.parseInt(res.getString("personal"));
				res.next();
				// フォロワー数の格納
				followers = Integer.parseInt(res.getString("personal"));
				res.next();
				// 投稿数の格納
				articles = Integer.parseInt(res.getString("personal"));

			}

			// PersonalDataクラスのコンストラクタに渡す
			personalData = new PersonalData(follows, followers, articles);

		}
		return personalData;
	}

	public List<Article> selectMyPosts(Member member) throws SQLException {
		// 初期化
		List<Article> la = new ArrayList<Article>();

		// アカウントIDに対するフォロー数、フォロワー数、投稿数を取得するクエリ

		String query = "SELECT * FROM t_article WHERE account_id=?";

		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try (Connection con = supplier.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);) {
			// コードを設定
			pstmt.setString(1, member.getAccountId());
			ResultSet res = pstmt.executeQuery();

			String text = "";
			String imageUrl = "";
			Timestamp date = null;
			// SQL実行結果を格納する
			if (res.next()) {
				// フォロー数の格納
				imageUrl = res.getString("image_url");
				text = res.getString("text");
				//date = res.getString("date");
				date = res.getTimestamp("date");

				// リストにArticleを追加する
				la.add(new Article(member, text, imageUrl, date));
			}


		}

		return la;
	}

}
