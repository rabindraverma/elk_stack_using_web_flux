package com.hvct.repository;

import java.io.Serializable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.hvct.binding.Customer;

@EnableElasticsearchRepositories
public interface CustomerRepository extends ElasticsearchRepository<Customer, Serializable>{

}
