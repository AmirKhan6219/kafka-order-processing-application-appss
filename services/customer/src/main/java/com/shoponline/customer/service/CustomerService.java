package com.shoponline.customer.service;

import com.shoponline.customer.dto.CustomerDto;
import com.shoponline.customer.entity.Customer;
import com.shoponline.customer.exception.EmailAlreadyExistsException;
import com.shoponline.customer.exception.ResourceNotFoundException;
import com.shoponline.customer.mapper.CustomerMapper;
import com.shoponline.customer.repository.CustomerRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {

        repository.findByEmail(customerDto.getEmail())
                .ifPresent(customer-> {
                    throw new EmailAlreadyExistsException("Email already exist");
                });

        var customer = repository.save(mapper.toCustomer(customerDto));
        return mapper.toCustomerDto(customer);
    }

    public CustomerDto updateCustomer(CustomerDto customerDto, long id) {
        var customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)
                );
        updatedCustomer(customer, customerDto);
        return mapper.toCustomerDto(repository.save(customer));
    }

    private void updatedCustomer(Customer customer, CustomerDto customerDto) {
        if(StringUtils.isNotBlank(customerDto.getFirstname()))
            customer.setFirstname(customerDto.getFirstname());
        if(StringUtils.isNotBlank(customerDto.getLastname()))
            customer.setLastname(customerDto.getLastname());
        if(StringUtils.isNotBlank(customerDto.getEmail()))
            customer.setEmail(customerDto.getEmail());
        if(customerDto.getAddress() != null)
            customer.setAddress(customerDto.getAddress());
    }

    public List<CustomerDto> findAllCustomer() {
        return repository.findAll()
                .stream()
                .map(mapper::toCustomerDto)
                .toList();
    }

    public CustomerDto findCustomerById(long id) {
        var customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)
                );
        return mapper.toCustomerDto(customer);
    }

    public void deleteCustomerById(long id) {
        var customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)
                );
        repository.delete(customer);
    }
}
