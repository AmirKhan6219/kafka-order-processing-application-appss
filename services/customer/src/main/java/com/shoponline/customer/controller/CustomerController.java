package com.shoponline.customer.controller;

import com.shoponline.customer.dto.CustomerDto;
import com.shoponline.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(
            @RequestBody @Valid CustomerDto customerDto
    ){
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @RequestBody @Valid CustomerDto customerDto,
            @PathVariable(name = "id") long id
    ){

        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomer(){
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(
            @PathVariable("id") long id
    ){
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(
            @PathVariable("id") long id
    ){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(format("Customer with id %s is deleted successfully", id), HttpStatus.OK);
    }
}
