package com.sarah.customer;

import com.sarah.exception.ResourceAlreadyExistsException;
import com.sarah.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Integer id){
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));
    }

    public void addCustomer(CustomerDTO customerDTO){
        customerRepository.findByEmail(customerDTO.email()).ifPresent(customer -> {
                    throw new ResourceAlreadyExistsException("Customer with email " + customerDTO.email() + " already exists");
                });
        Customer customer = new Customer();
        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());
        customer.setAge(customerDTO.age());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer with id " + id + " not found"));
        customerRepository.delete(customer);
    }

    public void updateCustomer(Integer id, CustomerDTO customerDTO){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer with id " + id + " not found"));
        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());
        customer.setAge(customerDTO.age());
        customerRepository.save(customer);

    }

}
