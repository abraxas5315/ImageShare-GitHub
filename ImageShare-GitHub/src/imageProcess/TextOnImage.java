package imageProcess;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * 画像に文字列を描画するエディタ
 *
 * @author s.kawashima
 */
public class TextOnImage implements ImageEditor
{
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.overlay.text";

	/** テキストパラメーター名 */
	public static final String PARAM_NAME_TEXT = "filter.text";

	/** 垂直アライメントパラメーター名 */
	public static final String PARAM_NAME_VALIGN = "text.valign";

	/** 文字色パラメーター名 */
	public static final String PARAM_NAME_TEXT_COLOR = "text.color";


	// 中央揃え（垂直方向）
	public static final VerticalAlign CENTER_ALIGN = new CenterVerticalAlign();

	// 下揃え（垂直方向）
	public static final VerticalAlign BOTTOM_ALIGN = new BottomVerticalAlign();

	// 表示するテキスト
	private String _text;

	// 垂直方向アライメント
	private VerticalAlign _vAlign;

	// 文字色
	private Color _fontColor;


	/**
	 * 垂直方向のアライメント設定
	 */
	public interface VerticalAlign
	{
		float y(float centerY, int fontHeight);
	}

	/**
	 *
	 */
	public TextOnImage()
	{
		_vAlign = CENTER_ALIGN;
		_fontColor = Color.WHITE;
	}

	/**
	 * 描画文字列を設定する。
	 *
	 * @param text 描画文字列
	 */
	public void setText(String text)
	{
		_text = text;
	}

	/**
	 * 垂直方向のアライメントを設定する。
	 *
	 * @param vAlign 垂直方向アライメント
	 */
	public void setVerticalAlign(VerticalAlign vAlign)
	{
		_vAlign = vAlign;
	}

	/**
	 * 文字色を設定する。
	 *
	 * @param color 文字色
	 */
	public void setFontColor(Color color)
	{
		_fontColor = color;
	}

	@Override
	public BufferedImage edit(BufferedImage srcImage , HttpServletRequest request)
	{

		// 描画文字列設定
		String text = request.getParameter(PARAM_NAME_TEXT);
		setText(text);

		// 垂直アライメント設定
		String vAlign = request.getParameter(PARAM_NAME_VALIGN);
		if("center".equals(vAlign))
			setVerticalAlign(TextOnImage.CENTER_ALIGN);
		else if("bottom".equals(vAlign))
			setVerticalAlign(TextOnImage.BOTTOM_ALIGN);

		// 文字色設定
		String fgColor = request.getParameter(PARAM_NAME_TEXT_COLOR);
		if("white".equals(fgColor))
			setFontColor(Color.WHITE);
		else if("red".equals(fgColor))
			setFontColor(Color.RED);



        Graphics2D g2 = srcImage.createGraphics();
		g2.setRenderingHint
		(
			// アンチエイリアス
			RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON
		);

		// フォント設定
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		adjustFontSize(width, height, g2);

		// 文字色（影部分）設定
		//g2.setColor(new Color(255, 255, 255, 100));
		g2.setColor(new Color(0, 0, 0, 100));

		// 文字列の高さ、幅を計算
		// http://d.hatena.ne.jp/taka_2/20091112/p1
		FontMetrics fm = g2.getFontMetrics();
		int h = fm.getHeight();
		int w = 0;
		for(int j=0; j<_text.length(); j++)
		{
			w += fm.charWidth(_text.charAt(j));
		}

		// 文字列の描画位置を決定
		float cx = width / 2.0f;
		float cy = height / 2.0f;
		float x = (cx - (w / 2.0f));
		////float y = (cy + (h / 2.0f));
		//float y = (cy + (h / 4.0f));
		float y = _vAlign.y(cy, h);
		g2.drawString(_text, x, y);

		float gap = (h * 0.02f);
        g2.drawString(_text, x + gap, y + gap);
        g2.setColor(_fontColor);
        g2.drawString(_text, x, y);
        g2.dispose();

        return srcImage;
	}

	/**
	 * フォントのサイズを出来るだけ大きく調整する。
	 *
	 * @param width
	 * @param height
	 * @param g2
	 */
	private void adjustFontSize(int width, int height, Graphics2D g2)
	{
		// 最大サイズは画像サイズの小さい方の何割か
		int maxSize = (int)(Math.min(width, height) * 0.9f);

		int pt = 0; // フォントサイズ
		int increment = 10; // 増分
		for(pt=100; pt<800; pt+=increment)
		{
			g2.setFont(new Font("Monospaced", Font.BOLD, pt));

			// 文字列の高さ、幅を計算
			// http://d.hatena.ne.jp/taka_2/20091112/p1
			FontMetrics fm = g2.getFontMetrics();
			int h = fm.getHeight();
			int w = 0;
			for(int j=0; j<_text.length(); j++)
			{
				w += fm.charWidth(_text.charAt(j));
			}

			if(Math.max(w, h) > maxSize)
			{
				pt-=increment;
				g2.setFont(new Font("Monospaced", Font.BOLD, pt));
				break;
			}
		}
	}


	/* 中央揃え（垂直方向アライメント）*/
	private static class CenterVerticalAlign implements VerticalAlign
	{
		@Override
		public float y(float centerY, int fontHeight)
		{
			float cy = centerY;
			int h = fontHeight;
			//float y = (cy + (h / 2.0f));
			float y = (cy + (h / 4.0f));

			return y;
		}
	}

	/* 下揃え（垂直方向アライメント）*/
	private static class BottomVerticalAlign implements VerticalAlign
	{
		@Override
		public float y(float centerY, int fontHeight)
		{
			float cy = centerY;
			//int h = fontHeight;

			float height = cy * 2;
			float y = height - (height * 0.1f);

			return y;
		}
	}

	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}
}
