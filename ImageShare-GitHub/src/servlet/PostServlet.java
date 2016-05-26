package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
 * Servlet implementation class Post
 */
@WebServlet("/post")
/**
 * 投稿をするサーブレット
 *
 * @author s.funo
 *
 */
public class PostServlet extends MainServlet{
	private static final long serialVersionUID = 1L;
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコーディング指定
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		//セッションの取得
	    HttpSession session = request.getSession();

	    //リクエストの取得
	    String dstImage = (String)request.getParameter("dstImage");

		// リクエストテキストの値を取得
		String text = request.getParameter("text");

		//ログインしたアカウント情報
		Member member = (Member)session.getAttribute("member");

		//デバッグ用
		//Member member = new Member("aaaa", "aaaa", "aaaa", "aaaa");

		//DATE型でインスタンスを作成(時間を取得)
		Date date = new Date();
		Article article = new Article(member,text ,dstImage,date);
		//DAOのインスタンス生成
		PostsDAO dao = new PostsDAO();
		try {
			dao.post(article);
		}
		catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//TLサーブレットに委譲
	    RequestDispatcher rd = request.getRequestDispatcher("TL");
	    rd.forward(request, response);
	}


}
