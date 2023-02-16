package com.hvct.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hvct.binding.Customer;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "customer_data";


    public String createOrUpdateDocument(Customer customer) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(customer.getId())
                .document(customer)
        );
        if (response.result().name().equals("Created")) {
            return new StringBuilder("Document has been successfully created.").toString();
        } else if (response.result().name().equals("Updated")) {
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public Customer getDocumentById(String productId) throws IOException {
        Customer product = null;
        GetResponse<Customer> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(productId),
                Customer.class
        );

        if (response.found()) {
            product = response.source();
            System.out.println("Customer name " + product.getFirstName()+"  "+product.getLastName());
        } else {
            System.out.println("Customer not found");
        }

        return product;
    }

    public String deleteDocumentById(String customerId) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(customerId));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("Product not found");
        return new StringBuilder("Product with id " + deleteResponse.id() + " does not exist.").toString();

    }

    public List<Customer> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Customer.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<Customer> products = new ArrayList<>();
        for (Hit object : hits) {

            System.out.print(((Customer) object.source()));
            products.add((Customer) object.source());

        }
        return products;
    }
}
