package data;

/**
 * 会員情報を保持するクラス
 * @author s.kawashima
 *
 */
public class Member {

	//ユーザID
	private String accountId;
	//ニックネーム
	private String name;
	//アイコン保存場所のパス
	private String icon;
	//プロフィール
	private String profile;

	/**
	 * コンストラクタ
	 * @param accountId ユーザID
	 * @param name ニックネーム
	 * @param icon アイコン
	 * @param profile プロフィール
	 */
	public Member(String accountId , String name , String icon , String profile)
	{
		this.accountId = accountId;
		this.name = name;
		this.icon = icon;
		this.profile = profile;
	}

	/**
	 * ユーザIDを返す
	 * @return ユーザID
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * ニックネームを返す
	 * @return ニックネーム
	 */
	public String getName() {
		return name;
	}

	/**
	 * アイコン画像のパスを返す
	 * @return アイコン画像のパス
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * プロフィールを返す
	 * @return プロフィール
	 */
	public String getProfile() {
		return profile;
	}

}
