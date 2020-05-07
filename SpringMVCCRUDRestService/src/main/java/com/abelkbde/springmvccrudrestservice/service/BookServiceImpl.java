package com.abelkbde.springmvccrudrestservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abelkbde.springmvccrudrestservice.model.Book;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	private static final AtomicInteger counter = new AtomicInteger();

	private static List<Book> books = populateDummyBooks();

	static {
		books = populateDummyBooks();
	}

	@Override
	public List<Book> findAllBooks() {
		return books;
	}

	@Override
	public Book findBookByTitle(String title) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				return book;
			}
		}
		return null;
	}

	@Override
	public void saveBook(Book book) {
		book.setId(counter.incrementAndGet());
		books.add(book);

	}

	@Override
	public void updateBookByTitle(Book book, String title) {
		int index = books.indexOf(book);
		books.set(index, book);

	}

	@Override
	public void deleteBookByTitle(String title) {
		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
			Book book = iterator.next();
			if (book.getTitle().equalsIgnoreCase(title)) {
				iterator.remove();
			}
		}

	}
	
	@Override
	public Boolean isBookExist(Book book) {
		return (findBookByTitle(book.getTitle()) !=null);
	}
	
	@Override
	public void deleteAllBooks() {
		books.clear();

	}
	
	

	private static List<Book> populateDummyBooks() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(counter.incrementAndGet(), "Les Miserables", "Victor Hugo", new Date(1862, 11, 11), "Albert Lacroix", 12.99));
		books.add(new Book(counter.incrementAndGet(), "Crime and Punishment", "Fyodor Dostoevsky", new Date(1866, 12, 10), "The Russian Messenger", 11.99));
		books.add(new Book(counter.incrementAndGet(), "War and Peace", "Leo Tolstoy", new Date(1865, 10, 18), "The Russian Messenger", 16.99));
		books.add(new Book(counter.incrementAndGet(), "Atlas Shrugged", "Ayn Rand", new Date(1957, 10, 10), "Random House", 15.99));
		
		
		return books;
	}

	

}
