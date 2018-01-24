<%@ page language="java" contentType="text/html"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.tduc.library.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.SQLException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>Insert title here</title>
</head>
<body>
<%
		BookDAO dao = new BookDAOImp();
	%>

	<%
		@SuppressWarnings("unchecked")
		List<Book> books = null;
		BookDAO bookDAO = new BookDAOImp();
		try {
			books = bookDAO.getAllBook();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	%>
	<form action="Controller" method="GET">
		<div align="center">
			<h1><font color="#663E30">List Book</font></h1>
		</div>
		<table border="1" width="600px" align="center" cellpadding="1" cellspacing="0">
			<tr>
				<td colspan="6" align="right"><input type=submit
					value="追加" name="view"></td>
			</tr>
			<tr>
				<th>Id</th>
				<th>書籍名</th>
				<th>出版社名</th>
				<th>ページ数</th>
				<th colspan="3"></th>
			</tr>
			<%
				for (Book book : books) {
			%>
			<tr>
				<td><%=book.getIdbook()%></td>
				<td><%=book.getName()%></td>
				<td><%=book.getPublisher()%></td>
				<td><a
					href="Controller?view=modify&idbook=<%=book.getIdbook()%>">修正</a></td>
				<td><a
					href="Controller?btnAction=delete&idbook=<%=book.getIdbook()%>">削除</a></td>
				<td><a
					href="Controller?view=comment&idbook=<%=book.getIdbook()%>">感想の一覧</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</body>
</html>