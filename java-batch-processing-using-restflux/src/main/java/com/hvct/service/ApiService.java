package com.hvct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hvct.binding.Customer;
import com.hvct.repository.ElasticSearchQuery;

@Service
public class ApiService {

	@Autowired
	private ElasticSearchQuery elasticSearchQuery;

	@Value("${service.url}")
	private String restUrl;

	public String call3rdPartyApi() {
		
		WebClient webClient = WebClient.create();

		webClient.get()
				 .uri(restUrl)
				 .retrieve()
				 .bodyToMono(new ParameterizedTypeReference<List<Customer>>() {})		
				 .subscribe(response -> {
					 try {
						 response.forEach(customer -> {
							 try {
								 System.out.println(customer);
								 elasticSearchQuery.createOrUpdateDocument(customer);
							 } catch (Exception e) {
								 e.printStackTrace();
							 }
						 });
					 } catch (Exception e) {
						 e.printStackTrace();
					 }		
				 });
		return "saved to elasticsearch sucessfully....!";
	}
}
