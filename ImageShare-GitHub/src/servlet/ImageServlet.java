package servlet;

import imageProcess.CircleClipper;
import imageProcess.GradationEffect;
import imageProcess.GrayScale;
import imageProcess.ImageEditor;
import imageProcess.ImageOverlay;
import imageProcess.ImageScaling;
import imageProcess.MonochromeEffect;
import imageProcess.Mosaic;
import imageProcess.NoneFilter;
import imageProcess.TextOnImage;
import io.ImageFileStorage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
@MultipartConfig()
public class ImageServlet extends MainServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ImageFileStorage storage = new ImageFileStorage();
//		try
//		{
			File imgFile = null;
			Part p = request.getPart("filename");
			String cType = p.getContentType();

			// ファイルサイズの取得
			long size = p.getSize();
			System.out.println("size: " + size);

			// 画像ファイルの取得
			BufferedImage image = null;
			try{
				image = ImageIO.read(p.getInputStream());
			}catch(IOException ex){
				// 仮のイメージ
				image = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);
			}

			/*
			 * フィルタ処理＆画像保存
			*/
			ImageEditor[] editors1 = new ImageEditor[]
				{
					new GrayScale(),
					new Mosaic(),
					new MonochromeEffect(),
					new NoneFilter()
				};
			// image = editor(request, "base", editors1, image);
			ImageEditor[] editors2 = new ImageEditor[]
				{
					new CircleClipper(),
					//new ImageOverlay(request, createOverlays()),
					new TextOnImage(),
					new GradationEffect(),
					new ImageScaling(),
					new NoneFilter(),
				};
			image = editor(request, "filter", editors2, image);
			imgFile = storage.store(getServletContext(), cType, image);
			request.setAttribute("dstImage", imgFile);
			RequestDispatcher rd = request.getRequestDispatcher("Post.jsp");
			rd.forward(request, response);
			// HTML書き出し
//		}
//		catch(IllegalStateException ex)
//		//catch(FileUploadBase.FileSizeLimitExceededException ex)
//		{
//			ex.printStackTrace();
//		}
	}
	private BufferedImage editor
	(
		HttpServletRequest request, String paramName,
		ImageEditor[] editor, BufferedImage image
	)
		throws IOException
	{
		/*
		 * 適切なリゾールバーを特定し、フィルタ処理を実行
		 */
		BufferedImage filtered = null;
		for(ImageEditor e : editor)
		{
			if(e.isAcceptable(request.getParameter(paramName)))
			{
				filtered = e.edit(request,image);
				break;
			}
		}

		return filtered;
	}
	/* オーバーレイ画像の読み込み＆設定 */
	private ImageOverlay.OverlayImage[] createOverlays() throws IOException
	{
		List<ImageOverlay.OverlayImage> list = new ArrayList<>();

		File fileSample = new File(getServletContext().getRealPath("res/img/") + "/sample.png");
		final BufferedImage imgSample = ImageIO.read(fileSample);
		list.add
		(
			new ImageOverlay.OverlayImage()
			{
				@Override
				public String value() { return "sample"; }
				@Override
				public BufferedImage getImage() { return imgSample; }
			}
		);

		File fileCorrect = new File(getServletContext().getRealPath("res/img/") + "/correct.png");
		final BufferedImage imgCorrect = ImageIO.read(fileCorrect);
		list.add
		(
			new ImageOverlay.OverlayImage()
			{
				@Override
				public String value() { return "correct"; }
				@Override
				public BufferedImage getImage() { return imgCorrect; }
			}
		);

		File fileWrong = new File(getServletContext().getRealPath("res/img/") + "/wrong.png");
		final BufferedImage imgWrong = ImageIO.read(fileWrong);
		list.add
		(
			new ImageOverlay.OverlayImage()
			{
				@Override
				public String value() { return "wrong"; }
				@Override
				public BufferedImage getImage() { return imgWrong; }
			}
		);

		return (list.toArray(new ImageOverlay.OverlayImage[list.size()]));
	}

	private String clipUploadFileName(Part part)
	{
		if(part == null || part.getContentType().indexOf("image") == -1)
		{
			return "";
		}

		// HTTPヘッダの値を取得
		String contentDisposition = part.getHeader("content-disposition");

		/* アップロードしたファイル名の取得 */
		// 変数contentDispositionから"filename="以降を抜き出す
		int filenamePosition = contentDisposition.indexOf("filename=");
		String filename = contentDisposition.substring(filenamePosition);
		// "filename="と"を除く
		filename = filename.replace("filename=", "");
		filename = filename.replace("\"", "");

		return filename;
	}
}
