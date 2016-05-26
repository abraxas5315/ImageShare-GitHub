package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Article;
import data.Member;
import db.PostsDAO;

/**
 * Servlet implementation class TLServlet
 */
@WebServlet("/TL")
public class TLServlet extends MainServlet {
	private static final long serialVersionUID = 1L;

	void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションの取得
		HttpSession session = request.getSession();
		Member member = null;
		// ------------デバッグ用ログインセッション---------------
//		try {
//			String userID = "shiomi";
//			String password = "1234";
//			member = new AccountDAO().authentication(userID, password);
//			session.setAttribute("member", member);
//		} catch (SQLException e) {
//			System.out.println("データベース関連エラー");
//		} catch (Exception e) {
//			System.out.println("例外");
//			e.printStackTrace();
//		}

		// --------------デバッグ用ログインセッションここまで------------

		// member のセッションを取得
		member = (Member) session.getAttribute("member");

		// DAOをインスタンス化
		PostsDAO dao = new PostsDAO();
		// タイムラインに表示する情報を取得する
		List<Article> tl = new ArrayList<Article>();


		// DAOからのreturnをBeansに格納
		try {
			// member を元に個人ページで表示するPersonalDataをとる。
			tl = dao.selectTL(member);

			} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



		// 過去の投稿リストをrequestスコープに格納
		request.setAttribute("tl.article", tl);

		RequestDispatcher rd = request.getRequestDispatcher("timeLine.jsp");
		rd.forward(request, response);
	}
}
