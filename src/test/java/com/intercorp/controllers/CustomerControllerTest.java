package com.intercorp.controllers;

import com.intercorp.dtos.CustomerKpi;
import com.intercorp.models.Customer;
import com.intercorp.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @Mock
    private CustomerService mockService;

    private CustomerController mockController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockController = new CustomerController(mockService);
    }

    @Test
    public void whenSaveCustomerReturnCreatedResponse() {
        Customer customer = new Customer();
        customer = getCustomer();
        when(mockService.saveCustomer(customer)).thenReturn(customer);
        ResponseEntity<Customer> responseEntity = mockController.save(customer);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Leandro", responseEntity.getBody().getName());
        assertEquals("Franco", responseEntity.getBody().getLastname());
        assertEquals(29, (int) responseEntity.getBody().getAge());
        assertEquals("1991-06-08", responseEntity.getBody().getBirthday().toString());
    }

    @Test
    public void whenListCustomersReturnOkResponse() {
        Customer customer = new Customer();
        customer = getCustomer();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(mockService.findAll()).thenReturn(customers);
        ResponseEntity<List<Customer>> responseEntity = mockController.findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Leandro", responseEntity.getBody().get(0).getName());
        assertEquals("Franco", responseEntity.getBody().get(0).getLastname());
        assertEquals(29, (int) responseEntity.getBody().get(0).getAge());
        assertEquals("1991-06-08", responseEntity.getBody().get(0).getBirthday().toString());
    }

    @Test
    public void whenCalculateKpiReturnOkResponse() {
        CustomerKpi kpi = new CustomerKpi();
        when(mockService.calculateKpi()).thenReturn(kpi);
        ResponseEntity<CustomerKpi> responseEntity = mockController.calculateKpi();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("Leandro");
        customer.setLastname("Franco");
        customer.setBirthday(LocalDate.of(1991, 6, 8));
        customer.setAge(29);
        return customer;
    }
}
