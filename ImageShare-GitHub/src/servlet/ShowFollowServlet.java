package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Member;
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
    	Member member = null;

    	// memberのセッションを取得
		member = (Member) session.getAttribute("member");

		// DAOをインスタンス化
		FollowDAO dao = new FollowDAO();

		// Member型のリストfollowを生成
		List<Member> follow = new ArrayList<Member>();

		// followにreturnを格納
		try {
			follow = dao.selectFollow(member);

			} catch (Exception e) {
				// 例外
				e.printStackTrace();
			}

		// followをrequestスコープに格納
		request.setAttribute("follow", follow);

	// 移譲先のjspを格納
	String url = "followList.jsp";

	RequestDispatcher rd = request.getRequestDispatcher(url);
	rd.forward(request, response);
	}
}
