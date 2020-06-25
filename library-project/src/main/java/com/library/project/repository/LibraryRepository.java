package com.library.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.project.data.Library;

public interface LibraryRepository  extends JpaRepository<Library, Long>{
	
}
