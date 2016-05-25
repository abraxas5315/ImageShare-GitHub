package imageProcess;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * 画像編集 - グレースケール化
 *
 * @author s.kawashima
 */
public class GrayScale implements ImageEditor
{
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.grayscale";


	@Override
	public BufferedImage edit(BufferedImage srcImage , HttpServletRequest request)
	{
		// 入力画像のピクセルの取得
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();
		int[] srcPixels = srcImage.getRGB(0, 0, w, h, null, 0, w);

		// 出力画像ピクセルの生成
		int[] dstPixels = new int[w * h];

		for(int y=0; y<h; y++)
		{
			for(int x=0; x<w; x++)
			{
				// ピクセル配列のインデックス
				int index = x + (y * w);

				// グレースケール化
				dstPixels[index] = toGray(srcPixels[index]);
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
	 * 指定画素をグレーに変換する。
	 *
	 * @param rgb RGBピクセル値
	 * @return グレー変換後ピクセル値
	 */
	private int toGray(int rgb)
	{
		 int r = (rgb>>16) & 0xff;
		 int g = (rgb>>8) & 0xff;
		 int b = (rgb) & 0xff;

		 int ave = (r + g + b) / 3;

		 return ((ave<<16) | (ave<<8) | ave);
	}

	@Override
	public String toString()
	{
		return "グレースケール";
	}

	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}
}
