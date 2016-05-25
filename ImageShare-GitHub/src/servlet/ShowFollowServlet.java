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
 * Servlet implementation class ShowFollowServlet
 * @author N.Tsukazawa
 *
 */
@WebServlet("/ShowFollowServlet")
public class ShowFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFollowServlet() {
        super();
        // データソースの生成
        DataSourceSupplier.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションの取得
    	HttpSession session = request.getSession();

    	// 値を取得
    	String action = request.getParameter("timeline");

    	// 移譲先のjspを格納する変数
    	String url = "followList.jsp";

    	RequestDispatcher rd = request.getRequestDispatcher(url);
    	rd.forward(request, response);
	}
}
