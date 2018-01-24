package com.tduc.library;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
	public List<Book> getAllBook() throws SQLException;

	public Book getBook(int idbook) throws SQLException;

	public void addBook(String name, String publisher, int page);

	public void deleteBook(int idbook);

	void updateBook(int idbook, String name, String publisher, int page);
}
