package com.tduc.library;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
	public List<Comment> getAllComment(int idbook) throws SQLException;

	public Comment getComment(int idcomment) throws SQLException;

	public void addComment(String name, int idbook);

	public void deleteComment(int idcomment);

	void updateComment(int idcomment, String name);
}
