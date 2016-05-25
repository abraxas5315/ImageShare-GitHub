package imageProcess;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;

/**
 * 加工なしエディタ
 * @author s.kawashima
 *
 */
public class NoneFilter implements ImageEditor
{
	/** フィルタ値 */
	public static final String FILTER_VALUE = "filter.none";

	@Override
	public BufferedImage edit(BufferedImage srcImage, HttpServletRequest request)
	{
		//何もせずそのまま返す
		return srcImage;
	}

	@Override
	public boolean isAcceptable(String value)
	{
		return (FILTER_VALUE.equals(value));
	}


}
