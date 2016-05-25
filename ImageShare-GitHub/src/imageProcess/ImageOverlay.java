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
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.overlay.sample";

	/** オーバーレイ画像パラメーター名 */
	public static final String PARAM_NAME_IMG_OVERLAY = "overlay.image";

	// オーバーレイ画像
	private BufferedImage _imgOverlay;

	// オーバーレイ画像配列
	private OverlayImage[] _overlays;

	private boolean _isFullCover;

	/**
	 * オーバーレイする画像用インターフェース
	 */
	public interface OverlayImage
	{
		/** OPTIONタグに設定する値の文字列を返す。*/
		String value();

		/** オーバーレイ画像を返す。*/
		BufferedImage getImage();
	}

	public void selectImageOverlay(HttpServletRequest request)
	{
		String value = request.getParameter(PARAM_NAME_IMG_OVERLAY);
		for(OverlayImage o : _overlays)
		{
			if(o.value().equals(value))
			{
				_imgOverlay = o.getImage();
				break;
			}
		}
	}


	public ImageOverlay(HttpServletRequest request , OverlayImage... overlays)
	{
		_overlays = overlays;
		selectImageOverlay(request);
		_isFullCover = false;
	}

	public ImageOverlay
	(
		BufferedImage imgOverlay, boolean isFullCover
	)
	{
		_imgOverlay = imgOverlay;
		_isFullCover = isFullCover;
	}

	@Override
	public BufferedImage edit(HttpServletRequest request , BufferedImage srcImage)
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
			imgOverlay = editor.edit(request , _imgOverlay);
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
