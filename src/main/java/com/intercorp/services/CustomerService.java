package com.intercorp.services;

import com.intercorp.dtos.CustomerKpi;
import com.intercorp.models.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(final Customer customer);

    List<Customer> findAll();

    CustomerKpi calculateKpi();
}
