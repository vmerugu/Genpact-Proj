package com.library.project.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.data.Books;
import com.library.project.data.Library;
import com.library.project.repository.BooksRepository;
import com.library.project.repository.LibraryRepository;
import com.library.project.request.BooksRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	public Books addBookDetails(BooksRequest booksRequest) throws Exception {
		try{
			Library library = libraryRepository.findById(booksRequest.getLibraryId()).orElse(null);
			if (null == library) {
				throw new Exception("Invalid Library ID :"+booksRequest.getLibraryId());
			}
			Books books = new Books();
			BeanUtils.copyProperties(booksRequest,books);
			books.setLibrary(library);
			Books savedProduct = booksRepository.save(books);		
			return savedProduct;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public List<Books> getAllBooksDetailsOfLibrary(Long libraryId) throws Exception {
		try{
			Library library = libraryRepository.findById(libraryId).orElse(null);
			if (null == library) {
				throw new Exception("Invalid Library ID :"+libraryId);
			}
			List<Books> booksList =booksRepository.findByLibrary(library);
			return booksList;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public Books getBookDetails(Long id) throws Exception {
		try{
			Books books=booksRepository.findById(id).orElse(null);
			if(books==null) {
				throw new Exception("Invalid Book Id: "+id);
			}
			return books;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public Books updateBookDetails(Long id,String title,String author,String subject) throws Exception {
		try{
			Books books=booksRepository.findById(id).orElse(null);
			if(books==null) {
				throw new Exception("Invalid Book Id: "+id);
			}
			if(title!=null && !"".equalsIgnoreCase(title)) {
				books.setTitle(title);
			}
			if(author!=null && !"".equalsIgnoreCase(author)) {
				books.setAuthor(author);
			}
			if(subject!=null && !"".equalsIgnoreCase(subject)) {
				books.setSubject(subject);
			}
			Books updatedProduct = booksRepository.save(books);		
			return updatedProduct;
		}catch (Exception e) {
			throw e;
		}
	}
}
