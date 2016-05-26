package data;

import java.util.Date;

/**
 * 投稿した記事の内容を保持するクラス
 * @author s.kawashima
 *
 */
public class Article {

	//記事を投稿した会員
	private Member member;
	//投稿ＩＤ
	private int articleId;
	//投稿内容
	private String text;
	//投稿画像のURL
	private String imageUrl;
	//投稿日時
	private Date date;

	/**
	 * コンストラクタ 投稿記事生成用
	 * @param member 投稿した会員の情報
	 * @param text 投稿の内容
	 * @param imageUrl 投稿画像のパス
	 * @param date 投稿日時
	 */
	public Article(Member member, String text , String imageUrl,Date date)
	{
		this.member = member;
		this.text = text;
		this.imageUrl = imageUrl;
		this.date = date;
	}

	/**
	 * コンストラクタ 記事取得時用
	 * @param member 投稿した会員の情報
	 * @param text 投稿の内容
	 * @param imageUrl 投稿画像のパス
	 * @param date 投稿日時
	 */
	public Article(Member member, int articleId , String text , String imageUrl,Date date)
	{
		this.member = member;
		this.articleId = articleId;
		this.text = text;
		this.imageUrl = imageUrl;
		this.date = date;
	}

	/**
	 * 記事の投稿ＩＤを返す
	 * @return 記事の投稿ＩＤ
	 */
	public int getArticleId() {
		return articleId;
	}

	/**
	 * 投稿者のユーザIDを返す
	 * @return ユーザID
	 */
	public String getAccountId() {
		return member.getAccountId();
	}

	/**
	 * 投稿者のニックネームを返す
	 * @return ニックネーム
	 */
	public String getName() {
		return member.getName();
	}

	/**
	 * 投稿内容を返す
	 * @return 投稿内容
	 */
	public String getText() {
		return text;
	}

	/**
	 * 投稿画像のパス
	 * @return 投稿画像のパス
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 投稿日時を返す
	 * @return 投稿日時
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 記事の投稿者を返す
	 * @return 投稿者
	 */
	public Member getMember()
	{
		return member;
	}
}
