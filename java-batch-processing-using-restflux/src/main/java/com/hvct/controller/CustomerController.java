package com.hvct.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.hvct.binding.Customer;
import com.hvct.repository.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Value("${service.url}")
	private  String restUrl;
	
	@GetMapping("/get")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		WebClient webClient = WebClient.create();
		
		
		Customer[] block = webClient.get()
									.uri(restUrl)
									.retrieve()
									.bodyToMono(Customer[].class)
									.log()
									.block();
		List<Customer> asList = Arrays.asList(block);
		customerRepository.saveAll(asList);
		//asList.forEach(cus -> System.out.println(cus));
		return new ResponseEntity<>(asList, HttpStatus.OK);

	}

}
