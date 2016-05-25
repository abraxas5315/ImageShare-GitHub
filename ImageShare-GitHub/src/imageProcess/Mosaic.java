package imageProcess;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * 画像編集 - モザイク
 *
 * @author s.kawashima
 */
public class Mosaic implements ImageEditor
{
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.mosaic";
	/** モザイク大きさパラメーター名 */
	public static final String PARAM_NAME_MOSAIC_LENGTH = "mosaic.length";
	// モザイクサイズ
	private int _length;


	public Mosaic()
	{
		_length = 10;
	}

	/**
	 * モザイク１つの大きさ（ピクセル）を設定する。
	 *
	 * @param length
	 */
		public void setMosaicLength(String slength)
	{
			int length = 10;
		try
		{
			 length = Integer.parseInt(slength);
		}
		catch(NumberFormatException ex) { }

		if(length < 1 || length >= 100)
		{
			throw new IllegalArgumentException("Out of Range(1~100)");
		}


		_length = length;
	}
	@Override
	public BufferedImage edit(HttpServletRequest request , BufferedImage srcImage)
	{
		//モザイク１つの大きさ（ピクセル）を設定
		String sLength = request.getParameter(PARAM_NAME_MOSAIC_LENGTH);
		setMosaicLength(sLength);

		// 入力画像のピクセルの取得
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();
		int[] srcPixels = srcImage.getRGB(0, 0, w, h, null, 0, w);

		// 出力画像ピクセルの生成
		int[] dstPixels = new int[w * h];

		// ピクセルスキップ数の設定
		int skip = _length;

		for(int y=0; y<h; y+=skip)
		{
			for(int x=0; x<w; x+=skip)
			{
				// skip x skip の範囲内で色の平均色を求め、
				// その範囲内を求めた平均色で塗りつぶす
				int aveColor = average(y, x, skip, h, w, srcPixels);
				paintColor(aveColor, y, x, skip, h, w, dstPixels);
			}
		}

		// 出力画像の生成
		BufferedImage dstImage = new BufferedImage
				(
						w, h, BufferedImage.TYPE_INT_RGB
						);
		dstImage.setRGB(0, 0, w, h, dstPixels, 0, w);

		return dstImage;
	}

	/**
	 * skip x skip の範囲で色の平均を求める。
	 *
	 * @param y 開始Ｙ座標
	 * @param x 開始Ｘ座標
	 * @param skip スキップ数
	 * @param height 画像の高さ
	 * @param width 画像の幅
	 * @param pixels 対象画像のピクセル配列
	 * @return 平均色
	 */
	private int average
	(
			int y, int x, int skip, int height, int width, int[] pixels
			)
	{
		int rTotal = 0, gTotal = 0, bTotal = 0; // 各色の合計
		int pixTotal = 0; // ピクセル合計
		for(int sy=y; sy<y+skip && sy<height; sy++)
		{
			for(int sx=x; sx<x+skip && sx<width; sx++)
			{
				// ピクセル数を合計していく
				pixTotal++;

				int index = sx + (sy * width);

				// 入力ピクセルからＲＧＢ成分を取得
				int rgb = pixels[index];
				int r = (rgb>>16) & 0xff;
				int g = (rgb>>8) & 0xff;
				int b = (rgb) & 0xff;

				// 各色の値を合計していく
				rTotal += r;
				gTotal += g;
				bTotal += b;
			}
		}

		// 各色の平均を算出
		int rAve = rTotal / pixTotal;
		int gAve = gTotal / pixTotal;
		int bAve = bTotal / pixTotal;

		// 最終的に各色の平均をまとめ、それを平均値とする
		return (rAve<<16 | gAve<<8 | bAve);
	}

	/**
	 * skip x skip の範囲を指定された色で塗りつぶす。
	 *
	 * @param color 設定色
	 * @param y 開始Ｙ座標
	 * @param x 開始Ｘ座標
	 * @param skip スキップ数
	 * @param height 画像の高さ
	 * @param width 画像の幅
	 * @param pixels 対象画像のピクセル配列
	 */
	private void paintColor
	(
			int color, int y, int x, int skip,
			int height, int width, int[] pixels
			)
	{
		for(int sy=y; sy<y+skip && sy<height; sy++)
		{
			for(int sx=x; sx<x+skip && sx<width; sx++)
			{
				int index = sx + (sy * width);
				pixels[index] = color;
			}
		}
	}

	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}

	@Override
	public String toString()
	{
		return "モザイク";
	}
}
