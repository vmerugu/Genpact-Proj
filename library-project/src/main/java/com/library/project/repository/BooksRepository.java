package com.library.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.project.data.Books;
import com.library.project.data.Library;

public interface BooksRepository extends JpaRepository<Books, Long>{
	List<Books> findByLibrary(Library library);
}
