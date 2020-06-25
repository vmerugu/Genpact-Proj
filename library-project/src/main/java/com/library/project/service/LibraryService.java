package com.library.project.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.project.data.Library;
import com.library.project.repository.LibraryRepository;
import com.library.project.request.LibraryRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LibraryService {
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	public Library addLibraryDetails(LibraryRequest libraryRequest) throws Exception {
		try{
			Library library = new Library();
			library.setName(libraryRequest.getName());
			library.setLocation(libraryRequest.getLocation());
			Library savedProduct = libraryRepository.save(library);	
			return savedProduct;
		}catch (Exception e) {
			throw e;
		}
	}
	
	
	public List<Library> getLibraryDetails() throws Exception {
		try{
			List<Library> libraryDetails=libraryRepository.findAll();
			return libraryDetails;
		}catch (Exception e) {
			throw e;
		}
	}
}
