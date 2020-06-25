package com.library.project.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.data.Library;
import com.library.project.repository.LibraryRepository;
import com.library.project.request.LibraryRequest;
import com.library.project.service.LibraryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/library")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@PostMapping
	public ResponseEntity<Library> addLibraryDetails(
			@Valid @RequestBody LibraryRequest libraryRequest) throws Exception {
			long startTimeMills = System.currentTimeMillis();
			String requestId=UUID.randomUUID().toString();
			log.info("REQUEST ID: {} | REQUEST RECIEVED | START TIME: {}",requestId, startTimeMills);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("requestId", requestId);
			Library savedProduct = libraryService.addLibraryDetails(libraryRequest);	
			long endTimeMills = System.currentTimeMillis();
			log.info("REQUEST ID: {} | RETURNED RESPONSE | END TIME: {} | RESPONSE TIME: {}",requestId,endTimeMills,endTimeMills-startTimeMills);
			return ResponseEntity.ok().headers(responseHeaders).body(savedProduct);
	}
		
		@GetMapping
		public ResponseEntity<List<Library>> getLibraryDetails() throws Exception {
				long startTimeMills = System.currentTimeMillis();
				String requestId=UUID.randomUUID().toString();
				log.info("REQUEST ID: {} | REQUEST RECIEVED | START TIME: {}",requestId, startTimeMills);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("requestId", requestId);
				List<Library> libraryDetails=libraryService.getLibraryDetails();
				long endTimeMills = System.currentTimeMillis();
				log.info("REQUEST ID: {} | RETURNED RESPONSE | END TIME: {} | RESPONSE TIME: {}",requestId,endTimeMills,endTimeMills-startTimeMills);
				return ResponseEntity.ok().headers(responseHeaders).body(libraryDetails);
		}
}
