package com.tduc.library;

public class Comment {
	private int idcomment;
	private String name;
	private int idbook;

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int idcomment, String name, int idbook) {
		// TODO Auto-generated constructor stub
		this.setIdcomment(idcomment);
		this.setName(name);
		this.setIdbook(idbook);
	}

	public int getIdcomment() {
		return idcomment;
	}

	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdbook() {
		return idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}
}
