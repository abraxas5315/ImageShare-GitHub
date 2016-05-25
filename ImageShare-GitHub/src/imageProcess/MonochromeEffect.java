package imageProcess;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * 画像編集 - 単色化効果
 *
 * @author t.yoshida
 */
public class MonochromeEffect implements ImageEditor
{
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.concolor";

	/** 色指定パラメーター名 */
	public static final String PARAM_NAME_COLOR_CONCOLOR = "color.concolor";


	// 白黒化
	public static final Monochromizer GRAY_SCALE;

	// 赤色化
	public static final Monochromizer REDDING;

	// 青色化
	public static final Monochromizer BLUING;

	// 色
	private Monochromizer _monochromizer;


	/*
	 * 単色化処理用インターフェース
	 */
	public interface Monochromizer
	{
		/* 担当色の文字列を返す。*/
		String color();

		/* 指定されたピクセル平均色を単色化して返す。*/
		int monochromize(int rgbAverage);
	}

	static
	{
		GRAY_SCALE = new GrayScale();
		REDDING = new Redding();
		BLUING = new Bluing();
	}

	public MonochromeEffect()
	{
		// デフォルトは白黒
		_monochromizer = GRAY_SCALE;
	}

	/**
	 * 単色化の色指定をする。
	 *
	 * @param monochrome モノクローム
	 */
	public void setMonochrome(Monochromizer monochrome)
	{
		_monochromizer = monochrome;
	}

	@Override
	public BufferedImage edit(BufferedImage srcImage , HttpServletRequest request)
	{
		//モノクロ指定
		String sColor = request.getParameter(PARAM_NAME_COLOR_CONCOLOR);
		Monochromizer[] mono = new Monochromizer[]
				{
				GRAY_SCALE, REDDING, BLUING,
				};
		for(Monochromizer m : mono)
		{
			if(m.color().equals(sColor))
			{
				setMonochrome(m);
				break;
			}
		}


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

				// 入力ピクセルからＲＧＢ成分を取得
				int rgb = srcPixels[index];
				int r = (rgb>>16) & 0xff;
				int g = (rgb>>8) & 0xff;
				int b = (rgb) & 0xff;

				// 平均を計算し、その値を単色化処理へ
				int ave = (r + g + b) / 3;
				int color = _monochromizer.monochromize(ave);
				dstPixels[index] = color;
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

	@Override
	public String toString()
	{
		return "単色化";
	}


	/* 白黒化処理 */
	static class GrayScale implements Monochromizer
	{
		private GrayScale() { }

		@Override
		public String color()
		{
			return "gray";
		}

		@Override
		public int monochromize(int rgbAverage)
		{
			int r = rgbAverage;
			int g = rgbAverage;
			int b = rgbAverage;

			return (r<<16 | g<<8 | b);
		}
	}

	/* 赤色化処理 */
	static class Redding implements Monochromizer
	{
		private Redding() { }

		@Override
		public String color()
		{
			return "red";
		}

		@Override
		public int monochromize(int rgbAverage)
		{
			int r = (int)(rgbAverage * 1.3f);
			if(r >= 0xff) r = 0xff;

			return (r<<16);
		}
	}

	/* 青色化処理 */
	static class Bluing implements Monochromizer
	{
		private Bluing() { }

		@Override
		public String color()
		{
			return "blue";
		}

		@Override
		public int monochromize(int rgbAverage)
		{
			int b = (int)(rgbAverage * 1.3f);
			if(b >= 0xff) b = 0xff;

			return b;
		}
	}
	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}
}
