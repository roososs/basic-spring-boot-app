package com.sarah.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id){
        return customerService.getCustomer(id);

    }

    @PostMapping("")
    public void addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id,customerDTO);
    }
}
