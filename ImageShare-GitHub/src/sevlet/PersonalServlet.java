package sevlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Member;
import data.PersonalData;
import db.AccountDAO;
import db.PersonalDataDAO;

/**
 * Servlet implementation class PerSonalServlet
 */


/**
 * 個人ページ用サーブレット
 * @author Y.Shiomi
 *
 */
@WebServlet("/personal")
public class PersonalServlet extends MainServlet {
	private static final long serialVersionUID = 1L;

	void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションの取得
		HttpSession session = request.getSession();

		//------------デバッグ用ログインセッション---------------
		Member member = null;
		try
		{
			String userID = "shiomi";
			String password = "1234";
			member = new AccountDAO().authentication(userID, password);
			session.setAttribute("member", member);
		}
		catch (SQLException e)
		{
			System.out.println("データベース関連エラー");
		}
		catch (Exception e)
		{
			System.out.println("例外");
			e.printStackTrace();
		}

		//--------------デバッグ用ログインセッションここまで------------






		// member のセッションを取得
		member = (Member) session.getAttribute("member");

		// DAOをインスタンス化
		PersonalDataDAO dao = new PersonalDataDAO();
		PersonalData personalData = null;

		// DAOからのreturnをBeansに格納
		try {
			// member を元に個人ページで表示するPersonalDataをとる。
			personalData = dao.selectPersonalData(member);
			//if(personalData.getA)

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// requestスコープに格納
		request.setAttribute("personalData", personalData);


		RequestDispatcher rd = request.getRequestDispatcher("personal.jsp");
		rd.forward(request, response);

	}

}
