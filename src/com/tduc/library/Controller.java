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
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
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
			int idbook = Integer.parseInt(request.getParameter("idbook"));
			if (action.equals("送信") && idbook==0 ) {
				String name = request.getParameter("name");
				String publisher = request.getParameter("publisher");
				int page = Integer.parseInt(request.getParameter("page"));
				
				BookDAO bookDAO = new BookDAOImp();
				bookDAO.addBook(name,publisher,page);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("送信") && idbook!=0) // processing Modify Song
			{
				idbook = Integer.parseInt(request.getParameter("idbook"));
				String name = request.getParameter("name");
				String publisher = request.getParameter("publisher");
				int page = Integer.parseInt(request.getParameter("page"));
				BookDAO bookDAO = new BookDAOImp();
				bookDAO.updateBook(idbook,name,publisher,page);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("delete")) {
				idbook = Integer.parseInt(request.getParameter("idbook"));
				BookDAO bookDAO = new BookDAOImp();
				bookDAO.deleteBook(idbook);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
				dispatcher.forward(request, response);
			}
		}
		if (view != null) {
			if (view.equals("戻る")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
				dispatcher.forward(request, response);
			} else if (view.equals("追加")) {
				request.setAttribute("idbook", 0);
				request.setAttribute("books", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifyBook.jsp");
				dispatcher.forward(request, response);
			} else if (view.equals("modify")) {
				String idbook = request.getParameter("idbook");
				request.setAttribute("idbook", idbook);

				List<Book> books = null;
				BookDAO bookDAO = new BookDAOImp();
				try {
					books = bookDAO.getAllBook();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				request.setAttribute("books", books);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifyBook.jsp");
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
