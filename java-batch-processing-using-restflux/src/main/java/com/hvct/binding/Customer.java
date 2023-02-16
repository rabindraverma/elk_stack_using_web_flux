package com.hvct.binding;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(indexName = "customer_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    private String Id;

    @Field(type = FieldType.Text, name = "firstName")
    private String firstName;
    
    @Field(type = FieldType.Text, name = "lastName")
    private String lastName;

    @Field(type = FieldType.Text, name = "email")
    private String email;

    @Field(type = FieldType.Double, name = "contactNo")
    private String contactNo;
    
    @Field(type = FieldType.Double, name = "country")
    private String country;
    
    @Field(type = FieldType.Double, name = "dob")
    private String dob;

    
}
