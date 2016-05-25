package data;

/**
 * 個人ページヘッダに表示する情報を保持するクラス
 * @author s.kawashima
 *
 */
public class PersonalData {

	//フォロー数
	private int follows;
	//フォロワー数
	private int followers;
	//投稿数
	private int articles;

	/**
	 * コンストラクタ
	 * @param follows フォロー数
	 * @param followers フォロワー数
	 * @param articles 投稿数
	 */
	public PersonalData(int follows , int followers , int articles)
	{
		this.follows = follows;
		this.followers = followers;
		this.articles = articles;
	}

	/**
	 * フォロー数を返す
	 * @return フォロー数
	 */
	public int getFollows() {
		return follows;
	}

	/**
	 * フォロワー数を返す
	 * @return フォロワー数
	 */
	public int getFollowers() {
		return followers;
	}

	/**
	 * 投稿数を返す
	 * @return 投稿数
	 */
	public int getArticles() {
		return articles;
	}

}
