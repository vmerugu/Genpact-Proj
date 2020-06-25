package com.library.project.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.data.Books;
import com.library.project.data.Library;
import com.library.project.repository.BooksRepository;
import com.library.project.repository.LibraryRepository;
import com.library.project.request.BooksRequest;
import com.library.project.service.BooksService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/book")
public class BooksController {
	
	@Autowired
	private BooksService booksService;
		
	@PostMapping(value="/add")
	public ResponseEntity<Books> addBookDetails(
			@Valid @RequestBody BooksRequest booksRequest) throws Exception {
			long startTimeMills = System.currentTimeMillis();
			String requestId=UUID.randomUUID().toString();
			log.info("REQUEST ID: {} | REQUEST RECIEVED | START TIME: {}",requestId,startTimeMills);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("requestId", requestId);
			Books savedProduct = booksService.addBookDetails(booksRequest);
			long endTimeMills = System.currentTimeMillis();
			log.info("REQUEST ID: {} | RETURNED RESPONSE | END TIME: {} | RESPONSE TIME: {}",requestId,endTimeMills,endTimeMills-startTimeMills);
			return ResponseEntity.ok().headers(responseHeaders).body(savedProduct);
	}
	
	@GetMapping(value="/ofLibrary")
	public ResponseEntity<List<Books>> getAllBooksDetailsOfLibrary(@NotEmpty @RequestParam("libraryId") Long libraryId) throws Exception {
			long startTimeMills = System.currentTimeMillis();
			String requestId=UUID.randomUUID().toString();
			log.info("REQUEST ID: {} | REQUEST RECIEVED | ID: {} | START TIME: {}",requestId, libraryId,startTimeMills);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("requestId", requestId);
			List<Books> booksList =booksService.getAllBooksDetailsOfLibrary(libraryId);
			long endTimeMills = System.currentTimeMillis();
			log.info("REQUEST ID: {} | RETURNED RESPONSE | END TIME: {} | RESPONSE TIME: {}",requestId,endTimeMills,endTimeMills-startTimeMills);
			return ResponseEntity.ok().headers(responseHeaders).body(booksList);
	}
	
	@GetMapping(value="/details")
	public ResponseEntity<Books> getBookDetails(@NotEmpty @RequestParam("id") Long id) throws Exception {
			long startTimeMills = System.currentTimeMillis();
			String requestId=UUID.randomUUID().toString();
			log.info("REQUEST ID: {} | REQUEST RECIEVED | ID: {} | START TIME: {}",requestId, id,startTimeMills);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("requestId", requestId);
			Books books=booksService.getBookDetails(id);
			long endTimeMills = System.currentTimeMillis();
			log.info("REQUEST ID: {} | RETURNED RESPONSE | END TIME: {} | RESPONSE TIME: {}",requestId,endTimeMills,endTimeMills-startTimeMills);
			return ResponseEntity.ok().headers(responseHeaders).body(books);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<Books> updateBookDetails(@NotEmpty @RequestParam("id") Long id,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String subject) throws Exception {
			long startTimeMills = System.currentTimeMillis();
			String requestId=UUID.randomUUID().toString();
			log.info("REQUEST ID: {} | REQUEST RECIEVED | ID: {} | START TIME: {}",requestId, id,startTimeMills);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("requestId", requestId);
			Books updatedProduct = booksService.updateBookDetails(id,title,author,subject) ;		
			long endTimeMills = System.currentTimeMillis();
			log.info("REQUEST ID: {} | RETURNED RESPONSE | END TIME: {} | RESPONSE TIME: {}",requestId,endTimeMills,endTimeMills-startTimeMills);
			return ResponseEntity.ok().headers(responseHeaders).body(updatedProduct);
	}

}
