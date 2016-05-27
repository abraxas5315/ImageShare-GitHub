package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DataSourceSupplier;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
/**
 * 共通処理用
 * @author s.kawashima
 *
 */
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
		HttpSession session = request.getSession();
		if(session.getAttribute("member")==null)
		{
		RequestDispatcher login = request.getRequestDispatcher("login.jsp");
		login.forward(request, response);
		}
		else
		{
			doPost(request, response);
		}
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
