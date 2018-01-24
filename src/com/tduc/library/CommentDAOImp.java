package com.tduc.library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImp implements CommentDAO{
	public CommentDAOImp() {
		// TODO Auto-generated constructor stub
	}

	List<Comment> comments = new ArrayList<Comment>();
	@Override
	public List<Comment> getAllComment(int idbook) throws SQLException {
		// TODO Auto-generated method stub
		String queryClub = "SELECT * FROM library_db.comment WHERE book_id=" + idbook;
		ResultSet resultSet = DataHelper.query(queryClub);
		while (resultSet.next()) {
			comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("name"), 
					resultSet.getInt("book_id")));
		}
		return comments;
	}

	@Override
	public Comment getComment(int idcomment) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM library_db.comment WHERE id=" + idcomment;
		ResultSet resultSet = DataHelper.query(query);
		if (resultSet.next()) {
			Comment Comment = new Comment(resultSet.getInt("id"), resultSet.getString("name"), 
					resultSet.getInt("book_id"));
			return Comment;
		}
		return null;
	}

	@Override
	public void addComment(String name, int idbook) {
		// TODO Auto-generated method stub
		String createComment = "INSERT INTO library_db.comment (name,book_id) VALUES (\"" + name + "\",\"" + idbook + "\")";
		DataHelper.update(createComment);
	}

	@Override
	public void deleteComment(int idcomment) {
		// TODO Auto-generated method stub
		String deleteComment = "DElETE FROM library_db.comment WHERE id=" + idcomment;
		DataHelper.update(deleteComment);
	}

	@Override
	public void updateComment(int idcomment, String name) {
		// TODO Auto-generated method stub
		String updateMatch = "UPDATE library_db.comment SET name = \"" + name + "\" WHERE id = " + idcomment;
		DataHelper.update(updateMatch);
	}

}
