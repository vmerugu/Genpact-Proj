package com.library.project.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibraryRequest {
	@NotEmpty
	@NotBlank
	private String name;
	@NotEmpty
	@NotBlank
	private String location;
}
