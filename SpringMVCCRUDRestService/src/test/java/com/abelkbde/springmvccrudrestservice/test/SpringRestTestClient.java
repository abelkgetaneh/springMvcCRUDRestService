package com.abelkbde.springmvccrudrestservice.test;

import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.abelkbde.springmvccrudrestservice.model.Book;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8084/SpringMVCCRUDRestService";
	
	@SuppressWarnings("unchecked")
	    private static void listAllBooks(){
	        System.out.println("Testing listAllbooks API-----------");
	         
	        RestTemplate restTemplate = new RestTemplate();
	        List<LinkedHashMap<String, Object>> booksMap = restTemplate.getForObject(REST_SERVICE_URI+"/book/", List.class);
	         
	        if(booksMap!=null){
	            for(LinkedHashMap<String, Object> map : booksMap){
	                System.out.println("book : id="+map.get("id")+", Title="+map.get("title")+", Author="+map.get("author")+", Year="+map.get("year")+", "
	                		+ "Publisher="+map.get("publisher")+", Price= "+map.get("price"));
	            }
	        }else{
	            System.out.println("No book exists----------");
	        }
	    }
	
	/* GET */
    private static void getBook(){
        System.out.println("Testing getbook API----------");
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(REST_SERVICE_URI+"/book/1", Book.class);
        System.out.println(book);
    }
    
    /*POST*/
    private static void createBook() {
    	System.out.println("Testing create book API-------------");
    	RestTemplate restTemplate = new RestTemplate();
    	Book book = new Book(0, "Les Miserables", "Victor Hugo", new Date(1862, 11, 11), "Albert Lacroix", 12.99);
    	URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/book/", book, Book.class);
    	System.out.println("Location : " + uri.toASCIIString());    	
    }
    
    /*PUT*/
    private static void updateBook() {
    	System.out.println("Testing update book API----------------------");
    	RestTemplate restTemplate = new RestTemplate();
    	Book book = new Book(1,"Crime and Punishment", "Fyodor Dostoevsky", new Date(1866, 12, 10), "The Russian Messenger", 11.99);
    	restTemplate.put(REST_SERVICE_URI+"/book/1", book);
    	System.out.println(book);
    }
    
    /*DELETE*/
    private static void deletebook() {
    	System.out.println("Testing delete book API-----------------------");
    	RestTemplate restTemplate = new RestTemplate();
    	restTemplate.delete(REST_SERVICE_URI+"/book/3");
    }
    
    /*DELETE*/
    private static void deleteAllbooks() {
    	System.out.println("Testing delete book API-----------------------");
    	RestTemplate restTemplate = new RestTemplate();
    	restTemplate.delete(REST_SERVICE_URI+"/book/");
    }
    
    public static void main(String args[]) {
    	listAllBooks();
    	getBook();
    	createBook();
    	listAllBooks();
    	updateBook();
    	listAllBooks();
    	deletebook();
    	listAllBooks();
    	deleteAllbooks();
    	listAllBooks();
    }
    
    
}
