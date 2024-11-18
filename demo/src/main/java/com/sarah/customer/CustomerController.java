package com.sarah.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id){
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return new ResponseEntity<>("Customer created Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id,customerDTO);
        return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
    }
}
