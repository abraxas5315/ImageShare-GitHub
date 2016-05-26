package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Member;


/**
 *フォロー一覧DAO
 * @author N.Tsukazawa
 *
 */
public class FollowDAO {

	/**
	 *
	 * @param 会員情報
	 * @throws Exception
	 */
	public ArrayList<Member> selectFollow(Member member) throws Exception{

		ArrayList<Member> follow = new ArrayList<Member>();

		// データソースの取得
		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		String query = "SELECT t2.follow_id,name,my_image,profile "
				+ "FROM m_account t1,t_follow t2 "
				+ "WHERE t1.account_id=t2.follow_id "
				+ "AND t2.account_id=?";

		try (
			// データベースへの接続の取得、ステートメント取得、SQLステートメントの実行
			Connection con = supplier.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);)
			{
			// 値の設定
			stmt.setString(1, member.getAccountId());

			ResultSet res = stmt.executeQuery();
			// 結果の取得
			while (res.next()) {
				member = new Member(
						res.getString("follow_id"),
						res.getString("name"),
						res.getString("my_image"),
						res.getString("profile"));

				follow.add(member);
			}

			// SQLに関する例外処理
			} catch (SQLException e) {
				System.out.println("フォローしている人はいません。");
				e.printStackTrace();
			}
		return follow;
	}

}
