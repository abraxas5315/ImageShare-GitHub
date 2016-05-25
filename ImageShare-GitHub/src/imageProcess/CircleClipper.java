package imageProcess;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


public class CircleClipper implements ImageEditor
{
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.circle";


	/**
	 * サークルクリッパーを生成する。
	 */
	public CircleClipper()
	{

	}

	@Override
	public BufferedImage edit(BufferedImage srcImage , HttpServletRequest request)
	{
		// 入力画像のピクセルの取得
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();
		int[] srcPixels = srcImage.getRGB(0, 0, w, h, null, 0, w);

		// 出力画像ピクセルの生成
		int[] dstPixels = new int[w * h];

		// 画像中心座標
		int centerX = w / 2;
		int centerY = h / 2;

		// 円の半径の計算
		int radius = Math.min(w, h) / 2;
		int radius2 = radius * radius;

		for(int y=0; y<h; y++)
		{
			for(int x=0; x<w; x++)
			{
				// ピクセル配列のインデックス
				int index = x + (y * w);

				if(isInCircle(radius2, centerX, centerY, x, y))
				{
					// 円形内では入力ピクセルそのまま
					dstPixels[index] = srcPixels[index];
				}
				else
				{
					// 円形外では透明ピクセル（元画像がアルファ値有効な場合）
					dstPixels[index] = 0x00ffffff;
				}
			}
		}

		// 出力画像の生成
		BufferedImage dstImage = new BufferedImage
		(
			w, h, srcImage.getType()
		);
		dstImage.setRGB(0, 0, w, h, dstPixels, 0, w);

		return dstImage;
	}

	/**
	 * 指定座標が円形内か否かを判定する。
	 *
	 * @param radius2 半径の二乗
	 * @param cx 画像中心座標（Ｘ座標）
	 * @param cy 画像中心座標（Ｙ座標）
	 * @param x Ｘ座標
	 * @param y Ｙ座標
	 * @return {@code true}:円形内の座標
	 */
	private boolean isInCircle(int radius2, int cx, int cy, int x, int y)
	{
		int R2 = (x - cx) * (x - cx) + (y - cy) * (y - cy);
		return (R2 <= radius2);
	}

	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}
}
