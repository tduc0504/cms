
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>

<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.*"%>
<%@page import="com.tduc.library.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編集画面</title>
</head>
<body>
	<%
		CommentDAO comments = (CommentDAO) request.getAttribute("comments");

		int idcomment = Integer.parseInt((String) request.getAttribute("idcomment"));
		Comment comment = comments.getComment(idcomment);

	%>

	<form action="ControllerComment" method="POST">
		<div align="center">
			<h1>
				編集画面
			</h1>
		</div>
		<input type="hidden" name="sid" value="<%=idcomment%>" />
		<table width="500" align="center">
			<tr>
				<td align="right">コメント:</td>
				<td><input type="text" name="name"
					value="<%=comment.getName()%>" /></td>
			</tr>
			
			<tr>
				<td></td>
				<td align="left"><input type="submit" value="送信"
					name="btnAction" /><input type="submit" value="戻る"
					name="view" /></td>
			</tr>

		</table>
	</form>
</body>
</html>