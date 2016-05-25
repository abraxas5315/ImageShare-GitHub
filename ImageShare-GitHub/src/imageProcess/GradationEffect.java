package imageProcess;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * グラデーション効果を付けるエディタ
 *
 * @author t.yoshida
 */
public class GradationEffect implements ImageEditor
{
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.overlay.gradation";

	/** グラデーション色パラメーター名 */
	public static final String PARAM_NAME_GRADATION_COLOR = "color.gradation";

	// グラデーション色（終点）
	private Color _color;


	public GradationEffect()
	{
		_color = Color.BLACK;
	}

	/**
	 * グラデーション色を設定する。
	 *
	 * @param color グラデーション色
	 */
	public void setColor(Color color)
	{
		_color = color;
	}

	@Override
	public BufferedImage edit(BufferedImage srcImage , HttpServletRequest request)
	{

		String color = request.getParameter(PARAM_NAME_GRADATION_COLOR);
		if("black".equals(color))
			setColor(Color.BLACK);
		else if("red".equals(color))
			setColor(Color.RED);

		/*
		 * グラデーション画像の生成
		 */
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		float cx = width / 2.0f;
		BufferedImage imgOverlay = new BufferedImage
		(
			width, height, BufferedImage.TYPE_INT_ARGB
		);
		float[] dist = {0.0f, 0.5f, 1.0f};
		Color[] colors = { _color, new Color(0, 0, 0, 0), _color };
		LinearGradientPaint gradient = new LinearGradientPaint
		(
			cx, 0.0f, cx, height, dist, colors,
			MultipleGradientPaint.CycleMethod.NO_CYCLE
		);
		Graphics2D g2 = imgOverlay.createGraphics();
		g2.setPaint(gradient);
//		//g2.fill(new Rectangle2D.Double(0, 0, width, height));
		g2.fill(new Rectangle(width, height));
		g2.dispose();

		// 元画像にグラデーション画像を重ねる
		ImageOverlay overlay = new ImageOverlay(imgOverlay, true);
		BufferedImage dstImage = overlay.edit(srcImage , request);

		return dstImage;
	}

	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}
}
