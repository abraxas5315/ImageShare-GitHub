package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Member;
import db.AccountDAO;
import db.DataSourceSupplier;
import db.FollowDAO;

/**
 * フォロー一覧サーブレット
 * @author N.Tsukazawa
 *
 */
@WebServlet("/ShowFollowServlet")
public class ShowFollowServlet extends MainServlet {
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
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションの取得
    	HttpSession session = request.getSession();

		// ------------デバッグ用ログインセッション---------------
		Member member = null;
		try {
			String userID = "aaaa";
			String password = "aaaaaaaa";
			member = new AccountDAO().authentication(userID, password);
		session.setAttribute("member", member);
		} catch (SQLException e) {
			System.out.println("データベース関連エラー");

		}

		// --------------デバッグ用ログインセッションここまで------------

    	// DAOをインスタンス化
    	FollowDAO dao = new FollowDAO();


    	ArrayList<Member> follow = new ArrayList<Member>();


    	try {
    		 follow = dao.selectFollow(member);

    			} catch (Exception e) {
    				// TODO 自動生成された catch ブロック
    				e.printStackTrace();
    			}

    	request.setAttribute("follow", follow);

    	//Member member = (Member) session.getAttribute("member");


    	// 移譲先のjspを格納する変数
    	String url = "followList.jsp";

    	RequestDispatcher rd = request.getRequestDispatcher(url);
    	rd.forward(request, response);
	}
}
