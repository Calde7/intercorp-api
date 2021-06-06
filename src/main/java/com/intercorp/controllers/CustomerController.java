package com.intercorp.controllers;

import com.intercorp.dtos.CustomerKpi;
import com.intercorp.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.intercorp.services.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService service) {
        this.customerService = service;
    }

    @PostMapping(path = "/creacliente", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> save(@RequestBody Customer request) {
        final Customer customer = customerService.saveCustomer(request);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @GetMapping(path = "/listclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findAll() {
        final List<Customer> customers = customerService.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping(path = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerKpi> calculateKpi() {
        final CustomerKpi kpis = customerService.calculateKpi();
        return new ResponseEntity<CustomerKpi>(kpis, HttpStatus.OK);
    }
}
