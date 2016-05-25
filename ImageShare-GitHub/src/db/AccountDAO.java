package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.Member;

/**
 * 会員情報テーブル参照DAO
 * @author s.kawashima
 *
 */
public class AccountDAO {

	public Member authentication(String userID, String password) throws SQLException
	{
		Member member = null;
		// クエリ文
		String query = "SELECT * FROM m_account where account_id = ? and pass = ?";
		// データベースへの接続の取得
		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try
		(
			Connection con = supplier.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
		)
		{
			// SQLステートメントの実行（参照系）
			pstmt.setString(1, userID);
			pstmt.setString(2, password);
			ResultSet res = pstmt.executeQuery();

			// 結果を取得
			if(res.next())
			{
				member = new Member(
						res.getString("account_id"),
						res.getString("name"),
						res.getString("my_image"),
						res.getString("profile")
						);
			}
		}

		// Member型の値をreturn
		return member;
	}

	public Member selectByAccount(String accountId) throws SQLException
	{
		Member member = null;
		// クエリ文
		String query = "SELECT * FROM m_account WHERE account_id=?";
		DataSourceSupplier supplier = DataSourceSupplier.getInstance();
		try
		(
		Connection con = supplier.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		)
		{
			// コードを設定
			pstmt.setString(1, accountId);

			ResultSet res = pstmt.executeQuery();
			if(res.next())
			{
				member = new Member(
						res.getString("account_id"),
						res.getString("name"),
						res.getString("my_image"),
						res.getString("profile")
						);
			}
		}
		return member;
	}

}
