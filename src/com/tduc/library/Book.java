package com.tduc.library;

public class Book {
	private int idbook;
	private String name;
	private String publisher;
	private int page;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(int idbook, String name, String publisher, int page) {
		// TODO Auto-generated constructor stub
		this.setIdbook(idbook);
		this.setName(name);
		this.setPublisher(publisher);
		this.setPage(page);
	}

	public int getIdbook() {
		return idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
