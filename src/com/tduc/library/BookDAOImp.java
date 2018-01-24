package com.tduc.library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImp implements BookDAO {
	public BookDAOImp() {
		// TODO Auto-generated constructor stub
	}

	List<Book> books = new ArrayList<Book>();
	
	@Override
	public List<Book> getAllBook() throws SQLException {
		// TODO Auto-generated method stub
		String queryClub = "SELECT * FROM library_db.book";
		ResultSet resultSet = DataHelper.query(queryClub);
		while (resultSet.next()) {
			books.add(new Book(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("publisher"),
					resultSet.getInt("page")));
		}
		return books;
	}

	@Override
	public Book getBook(int idbook) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM library_db.book WHERE id=" + idbook;
		ResultSet resultSet = DataHelper.query(query);
		if (resultSet.next()) {
			Book Book = new Book(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("publisher"),
					resultSet.getInt("page"));
			return Book;
		}
		return null;
	}

	@Override
	public void addBook(String name, String publisher, int page) {
		// TODO Auto-generated method stub
		String createBook = "INSERT INTO library_db.book (name,publisher,page) VALUES (\"" + name + "\",\"" + publisher
				+ "\",\"" + page + "\")";
		DataHelper.update(createBook);
	}

	@Override
	public void deleteBook(int idbook) {
		// TODO Auto-generated method stub
		String deleteBook = "DElETE FROM library_db.book WHERE id=" + idbook;
		DataHelper.update(deleteBook);
	}

	@Override
	public void updateBook(int idbook, String name, String publisher, int page) {
		// TODO Auto-generated method stub
		String updateMatch = "UPDATE library_db.book SET (name,publisher,page) VALUES (\"" + name + "\",\"" + publisher
				+ "\",\""+ page + "\") WHERE id = " + idbook;
		DataHelper.update(updateMatch);
	}

}
