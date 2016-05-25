/*
 * 総合１ 雛形	LoginManagementServlet.java
 *
 */
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
import db.AccountDAO;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/authentication")
public class AuthenticationServlet extends MainServlet {
	private static final long serialVersionUID = 1L;

	public AuthenticationServlet()
	{
	super();
	}

	void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 移譲先を格納する変数
		String url = null;

		//セッションの取得
		HttpSession session = request.getSession();

		// ※リクエストACTIONの値を判定し、移譲先をurlに格納

		Member member = null;
		try
		{
			String userID = request.getParameter("USERID");
			String password = request.getParameter("PASSWORD");
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

		if(member != null)
		{
			url = "personal.jsp";
		}
		else
		{
			url = "login.jsp";
			request.setAttribute("login.error", "ユーザIDかパスワードが間違っています");
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
