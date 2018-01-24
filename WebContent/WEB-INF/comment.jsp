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
		CommentDAO dao = new CommentDAOImp();
	%>

	<%
		@SuppressWarnings("unchecked")
		List<Comment> comments = null;
		int idbook = Integer.parseInt(request.getParameter("idbook"));
		CommentDAO commentDAO = new CommentDAOImp();
		try {
			comments = commentDAO.getAllComment(idbook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	%>
	<form action="ControllerComment" method="GET">
		<div align="center">
			<h1>感想一覧</h1>
		</div>
		<table border="1" width="600px" align="center" cellpadding="1" cellspacing="0">
			<tr>
				<td colspan="4" align="right"><input type=submit
					value="追加" name="view"></td>
			</tr>
			<tr>
				<th>Id</th>
				<th>コメント</th>
				<th colspan="2"></th>
			</tr>
			<%
				for (Comment cmt : comments) {
			%>
			<tr>
				<td><%=cmt.getIdcomment()%></td>
				<td><%=cmt.getName()%></td>
				<td><a
					href="Controller?view=modify&idcomment=<%=cmt.getIdcomment()%>">修正</a></td>
				<td><a
					href="Controller?btnAction=delete&idcomment=<%=cmt.getIdcomment()%>">削除</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</body>
</html>