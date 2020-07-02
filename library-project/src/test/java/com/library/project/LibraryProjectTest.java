package com.library.project;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.project.controller.BooksController;
import com.library.project.controller.LibraryController;
import com.library.project.data.Books;
import com.library.project.data.Library;
import com.library.project.repository.BooksRepository;
import com.library.project.repository.LibraryRepository;
import com.library.project.request.BooksRequest;
import com.library.project.request.LibraryRequest;

import junit.framework.Assert;
@SpringBootTest
@RunWith(SpringRunner.class)
public class LibraryProjectTest 
{
	@MockBean
	private BooksRepository booksRepository;

	@MockBean
	private LibraryRepository libraryRepository;
	
	@Autowired
	private BooksController booksController;
	
	@Autowired
	private LibraryController libraryController;
	

	@Test
	public void getBookDetails() throws Exception {
		Long id=new Long(1);
		when(booksRepository.findById(id)).thenReturn(Optional.of(getBooks()));
		ResponseEntity<Books> responseString=booksController.getBookDetails(id);
		Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
	}

	@Test
	public void addBookDetails() throws Exception {
		 BooksRequest booksRequest=new BooksRequest();
		 booksRequest.setTitle("title");
		 booksRequest.setAuthor("testAuthor");
		 booksRequest.setSubject("test");
		 booksRequest.setIsIssued(false);
		 booksRequest.setLibraryId(new Long(1));
		when(libraryRepository.findById(new Long(1))).thenReturn(Optional.of(getLibrary()));
		when(booksRepository.save(getBooks())).thenReturn(getBooks());
		ResponseEntity<Books> responseString=booksController.addBookDetails(booksRequest);
		Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
	}
	
	
	@Test
	public void getAllBooksDetailsOfLibrary() throws Exception {
		Long libraryId=new Long(1);
		List<Books> list=new ArrayList<>();
		list.add(getBooks());
		when(libraryRepository.findById(libraryId)).thenReturn(Optional.of(getLibrary()));
		when(booksRepository.findByLibrary(getLibrary())).thenReturn(list);
		ResponseEntity<List<Books>> responseString=booksController.getAllBooksDetailsOfLibrary(libraryId);
		Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
	}
	
	@Test
	public void updateBookDetails() throws Exception {
		Long id=new Long(1);
		when(booksRepository.findById(id)).thenReturn(Optional.of(getBooks()));
		when(booksRepository.save(getBooks())).thenReturn(getBooks());	
		String title="title";
		String author="testAuthor";
		String subject="test";
		ResponseEntity<Books> responseString=booksController.updateBookDetails(id,title,author,subject);
		Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
	}

	@Test
	public void getLibraryDetails() throws Exception {
		List<Library> allLibrary = new ArrayList<>();
		allLibrary.add(getLibrary());
		when(libraryRepository.findAll()).thenReturn(allLibrary);
		ResponseEntity<List<Library>> responseString=libraryController.getLibraryDetails();
		Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
	}

	@Test
	public void addLibraryDetails() throws Exception {
		LibraryRequest libraryRequest=new LibraryRequest();
		libraryRequest.setName("requestObj");
		libraryRequest.setLocation("testLocation");
		when(libraryRepository.save(getLibrary())).thenReturn(getLibrary());
		ResponseEntity<Library> responseString=libraryController.addLibraryDetails(libraryRequest);
		Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
	}
	
	private Library getLibrary() {
		Library savedProduct = new Library();
		savedProduct.setName("testLibrary");
		savedProduct.setId(new Long(1));
		savedProduct.setCreatedAt(new Date());
		savedProduct.setLocation("testLocation");
		savedProduct.setUpdatedAt(new Date());
		return savedProduct;
	}
	
	private Books getBooks() {
		Books savedProduct = new Books();
		savedProduct.setTitle("title");
		savedProduct.setAuthor("author");
		savedProduct.setLibrary(getLibrary());
		savedProduct.setId(new Long(1));
		savedProduct.setCreatedAt(new Date());
		savedProduct.setSubject("subject");
		savedProduct.setUpdatedAt(new Date());
		return savedProduct;
	}


}
