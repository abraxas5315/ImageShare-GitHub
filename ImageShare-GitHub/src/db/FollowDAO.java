package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		try (
			// データベースへの接続の取得、ステートメント取得、SQLステートメントの実行
			Connection con = supplier.getConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(
					"SELECT t1.account_id,t1.name,t1.my_image,t1.profile "
					+ "FROM teama.m_account t1,teama.t_follow t2 "
					+ "WHERE t1.account_id=t2.account_id");
		) {
			// 結果の取得
			while (res.next()) {
				member = new Member(
						res.getString("account_id"),
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
