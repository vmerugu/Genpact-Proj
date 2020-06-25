package com.library.project.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksRequest {
	@NotEmpty
	@NotBlank
	private String title;
	@NotEmpty
	@NotBlank
	private String author;
	@NotEmpty
	@NotBlank
	private String subject;
	private Boolean isIssued;
	private Long libraryId;
}
