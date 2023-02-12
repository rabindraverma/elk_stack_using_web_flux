package com.hvct.controller;

import com.hvct.entity.Customer;
import com.hvct.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {

    private CustomerRepository customerRepository;

    private Job job;
    private JobLauncher jobLauncher;

    @PostMapping("/importCustomers")
    public void importCsvToDb(){
        JobParameters jobParameters=new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> all = customerRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerRepository.save(customer),HttpStatus.OK);
    }
}
