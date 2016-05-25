package imageProcess;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * 画像のスケーリングエディタ
 *
 * @author t.yoshida
 */
public class ImageScaling implements ImageEditor
{

	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.thumbnail";

	/** 最大長パラメーター名 */
	public static final String PARAM_NAME_MAX_LENGTH = "length.max";

	// スケーリング最大長
	private int _scalingMaxLength;


	/**
	 * スケーリングエディタを生成する。
	 */
	public ImageScaling()
	{

	}

	/**
	 * スケーリング最大長を設定する。
	 *
	 * @param scalingMaxLength スケーリング最大長（最大幅or高さ）
	 */
	public void setScalingMaxLength(int scalingMaxLength)
	{
		_scalingMaxLength = scalingMaxLength;
	}

	@Override
	public BufferedImage edit(BufferedImage srcImage , HttpServletRequest request)
	{
		/*
		 * 縦横サイズの決定
		 */
		int width = 0;
		int height = 0;
		int orgWidth = srcImage.getWidth();
		int orgHeight = srcImage.getHeight();
		if(orgWidth > orgHeight)
		{
			width = _scalingMaxLength;
			height = (int)(_scalingMaxLength * (orgHeight / (float)orgWidth));
		}
		else
		{
			height = _scalingMaxLength;
			width = (int)(_scalingMaxLength * (orgWidth / (float)orgHeight));
		}

		/*
		 * スケーリング画像の作成
		 */
		BufferedImage scaling = new BufferedImage
		(
			width, height, srcImage.getType()
		);
		Graphics2D g2 = scaling.createGraphics();

		// 描画アルゴリズムの設定(品質優先、アンチエイリアスON)
		g2.setRenderingHint
		(
			RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT
		);
		g2.setRenderingHint
		(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);

		// 元画像のスケーリング
		g2.drawImage(srcImage, 0, 0, width, height, null);
		g2.dispose();

		return scaling;
	}
	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}
}
