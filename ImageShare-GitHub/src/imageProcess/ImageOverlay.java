package imageProcess;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;


/**
 * 画像を重ねるエディタ
 *
 * @author s.kawashima
 */
public class ImageOverlay implements ImageEditor
{
	// オーバーレイ画像
	private BufferedImage _imgOverlay;

	// オーバーレイ画像を元画像いっぱいに重ねるか
	private boolean _isFullCover;


	public ImageOverlay
	(
		BufferedImage imgOverlay, boolean isFullCover
	)
	{
		_imgOverlay = imgOverlay;
		_isFullCover = isFullCover;
	}

	@Override
	public BufferedImage edit(BufferedImage srcImage , HttpServletRequest request)
	{
		BufferedImage imgBase = srcImage;
		Graphics2D gBase = imgBase.createGraphics();
		gBase.setComposite
		(
			AlphaComposite.getInstance
			(
				AlphaComposite.SRC_OVER, 0.6f
			)
		);

		// オーバーレイ画像の設定
		int w = imgBase.getWidth();
		int h = imgBase.getHeight();
		BufferedImage imgOverlay = _imgOverlay;
		if(!_isFullCover)
		{
			// オーバーレイ画像サイズをベース画像に応じて調整
			ImageScaling editor = new ImageScaling();
			editor.setScalingMaxLength(Math.min(w, h));
			imgOverlay = editor.edit(_imgOverlay , request);
		}

		/*
		 * 重ねる画像を中央に配置
		 */
		int cx = w / 2;
		int cy = h / 2;
		int x = cx - (imgOverlay.getWidth() / 2);
		int y = cy - (imgOverlay.getHeight() / 2);
		gBase.drawImage(imgOverlay, x, y, null);
		gBase.dispose();

		return imgBase;
	}

	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}
}
