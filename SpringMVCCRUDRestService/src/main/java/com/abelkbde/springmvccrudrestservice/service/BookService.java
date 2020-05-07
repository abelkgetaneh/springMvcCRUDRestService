package com.abelkbde.springmvccrudrestservice.service;

import java.util.List;

import com.abelkbde.springmvccrudrestservice.model.Book;

public interface BookService {

	void saveBook(Book book);

	List<Book> findAllBooks();

	void deleteBookByTitle(String title);

	Book findBookByTitle(String title);

	void updateBookByTitle(Book book, String title);
	
	void deleteAllBooks();
	
	Boolean isBookExist(Book book);
	
}
