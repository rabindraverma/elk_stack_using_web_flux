package com.hvct.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvct.service.ApiService;

@RestController
public class ElasticSearchController {

	@Value("${service.url}")
	private String restUrl;

	@Autowired
	private ApiService apiService;

	@PostMapping("/createOrUpdateDocument")
	public ResponseEntity<String> createOrUpdateDocument() throws IOException {
		String response = apiService.call3rdPartyApi();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@GetMapping("/getDocument/{id}")
//	public ResponseEntity<Object> getDocumentById(@PathVariable String id) throws IOException {
//		Customer response = elasticSearchQuery.getDocumentById(id);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	@DeleteMapping("/deleteDocument")
//	public ResponseEntity<Object> deleteDocumentById(@RequestParam String customerId) throws IOException {
//		String response = elasticSearchQuery.deleteDocumentById(customerId);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	@GetMapping("/searchDocument")
//	public ResponseEntity<Object> searchAllDocument() throws IOException {
//		List<Customer> response = elasticSearchQuery.searchAllDocuments();
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

}
