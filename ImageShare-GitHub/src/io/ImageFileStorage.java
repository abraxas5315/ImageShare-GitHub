package io;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;


/**
 * 画像ファイルの保存を行うクラス
 *
 * @author t.yoshida
 */
public class ImageFileStorage
{
	public ImageFileStorage()
	{

	}

	/**
	 * 画像を保存するディレクトリ（絶対パス）を返す。
	 *
	 * @return
	 */
	public static String getAbsoluteImageDir(ServletContext context)
	{
		return (context.getRealPath(getRelativeImageDir()));
	}

	/**
	 * 画像を保存するディレクトリ（相対パス）を返す。
	 *
	 * @return
	 */
	public static String getRelativeImageDir()
	{
		//return "res/img/";
		return "upload/img/";
	}

	/**
	 * 画像ファイルのパスを生成する。
	 *
	 * @param context
	 * @param contentType
	 * @return 画像ファイルパス
	 */
	private File createImageFilePath
	(
		ServletContext context, String contentType
	)
	{
		// フォルダの作成
		File dir = new File(getAbsoluteImageDir(context));
		if(!dir.exists())
		{
			// 指定フォルダが存在しない場合作成する
			dir.mkdirs();
		}

		// ファイルの作成
		return (new File(dir, new Date().getTime() + extension(contentType)));
	}

	/**
	 * 指定されたストリームをファイルとして保存する。
	 *
	 * @param context コンテキスト
	 * @param contentType コンテントタイプ
	 * @param in 入力ストリーム
	 * @return 保存ファイルパス
	 * @throws IOException
	 */
	public File store
	(
		ServletContext context, String contentType, InputStream in
	)
		throws IOException
	{
		File file = createImageFilePath(context, contentType);
		try
		(
			BufferedOutputStream out = new BufferedOutputStream
			(
				new FileOutputStream(file)
			);
		)
		{
			byte[] b = new byte[1024];
			while(in.read(b) != -1)
			{
				out.write(b);
			}
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(in != null) { in.close(); }
		}

		return file;
	}

	/**
	 * 指定された画像をファイルとして保存する。
	 *
	 * @param context コンテキスト
	 * @param contentType コンテントタイプ
	 * @param image 入力画像
	 * @return 保存ファイルパス
	 * @throws IOException
	 */
	public File store
	(
		ServletContext context,
		String contentType, BufferedImage image
	)
		throws IOException
	{
		File file = createImageFilePath(context, contentType);
		ImageIO.write(image, format(contentType), file);

		return file;
	}

	/*
	 * コンテントタイプから画像フォーマットを返す。
	 */
	private String format(String contentType)
	{
		String format = "jpeg";
		if(contentType.indexOf("png") != -1)
		{
			format = "png";
		}
		else if(contentType.indexOf("gif") != -1)
		{
			format = "gif";
		}

		return format;
	}

	/*
	 * コンテントタイプからファイルの拡張子を返す。
	 */
	private String extension(String contentType)
	{
		String extension = ".jpg";
		if(contentType.indexOf("png") != -1)
		{
			extension = ".png";
		}
		else if(contentType.indexOf("gif") != -1)
		{
			extension = ".gif";
		}

		return extension;
	}
}
