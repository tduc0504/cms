package com.tduc.library;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class ControllerComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String view = request.getParameter("view");
		String action = request.getParameter("btnAction");
		if (action != null) {
			int idcomment = Integer.parseInt(request.getParameter("idcomment"));
			if (action.equals("送信") && idcomment==0 ) {
				String name = request.getParameter("name");
				int idbook = Integer.parseInt(request.getParameter("idbook"));
				
				CommentDAO commentDAO = new CommentDAOImp();
				commentDAO.addComment(name,idbook);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("送信") && idcomment!=0) // processing Modify Song
			{
				idcomment = Integer.parseInt(request.getParameter("idbook"));
				String name = request.getParameter("name");

				CommentDAO commentDAO = new CommentDAOImp();
				commentDAO.updateComment(idcomment,name);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("delete")) {
				idcomment = Integer.parseInt(request.getParameter("idcomment"));
				CommentDAO commentDAO = new CommentDAOImp();
				commentDAO.deleteComment(idcomment);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/comment.jsp");
				dispatcher.forward(request, response);
			}
		}
		if (view != null) {
			if (view.equals("戻る")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/comment.jsp");
				dispatcher.forward(request, response);
			} else if (view.equals("追加")) {
				request.setAttribute("idcomment", 0);
				request.setAttribute("comments", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifyComment.jsp");
				dispatcher.forward(request, response);
			} else if (view.equals("modify")) {
				String idcomment = request.getParameter("idcomment");
				request.setAttribute("idcomment", idcomment);

				List<Comment> comments = null;
				int idbook = Integer.parseInt(request.getParameter("idbook"));
				CommentDAO commentDAO = new CommentDAOImp();
				try {
					comments = commentDAO.getAllComment(idbook);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				
				request.setAttribute("comments", comments);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifyComment.jsp");
				dispatcher.forward(request, response);
			} else if (view.equals("comment")) {
				String idbook = request.getParameter("idbook");
				request.setAttribute("idbook", idbook );
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/comment.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
