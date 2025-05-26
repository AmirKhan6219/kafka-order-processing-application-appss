package com.shoponline.customer.mapper;

import com.shoponline.customer.dto.CustomerDto;
import com.shoponline.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());

        return customer;
    }

    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setEmail(customer.getEmail());
        customerDto.setAddress(customer.getAddress());

        return customerDto;
    }

}
