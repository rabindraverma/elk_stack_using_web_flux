package com.hvct.binding;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "customerindex")
public class Customer {

		@Id
	    private int id;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String gender;
	    private String contactNo;
	    private String country;
	    private String dob;
}
