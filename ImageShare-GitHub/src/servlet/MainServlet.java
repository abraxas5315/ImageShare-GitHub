package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DataSourceSupplier;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public abstract class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException
	{
		super.init();
		// データソースの作成
		DataSourceSupplier.getInstance();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字コード設定
		request.setCharacterEncoding("Windows-31J");
		//response.setContentType("text/html; charset=Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		doMain(request,response);
	}

	abstract void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
