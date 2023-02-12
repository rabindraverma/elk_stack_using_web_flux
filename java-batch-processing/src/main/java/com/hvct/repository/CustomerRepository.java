package com.hvct.repository;


import com.hvct.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CustomerRepository  extends JpaRepository<Customer, Serializable> {
}
