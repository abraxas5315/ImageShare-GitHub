package imageProcess;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * 画像編集用インターフェース
 *
 * @author s.kawashima
 */
public interface ImageEditor
{
	/**
	 * 指定された画像を編集して返す。
	 *
	 * @param srcImage 編集対象画像
	 * @return 編集後画像
	 */
	BufferedImage edit(HttpServletRequest request , BufferedImage srcImage);

	/**
	 * 指定されたフィルタ値の処理を受け持つか否かを返す。
	 *
	 * @param value フィルタ値
	 * @return {@code true}:受け持つ
	 */
	boolean isAcceptable(String value);


}
