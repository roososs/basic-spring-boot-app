package com.sarah.customer;

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
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) return customer.get();
        return null;
    }

    public void addCustomer(CustomerDTO customerDTO){
        Optional<Customer> customerOptional = customerRepository.findByEmail(customerDTO.email());
        if(!customerOptional.isPresent())
        {   Customer customer = new Customer();
            customer.setName(customerDTO.name());
            customer.setEmail(customerDTO.email());
            customer.setAge(customerDTO.age());
            customerRepository.save(customer);}
    }

    public void deleteCustomer(Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent())
        {customerRepository.delete(customer.get());}
    }

    public void updateCustomer(Integer id, CustomerDTO customerDTO){
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.setName(customerDTO.name());
            customer.setEmail(customerDTO.email());
            customer.setAge(customerDTO.age());
            customerRepository.save(customer);
        }
    }

}
