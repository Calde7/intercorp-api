package com.intercorp.services;

import com.intercorp.daos.CustomerDao;
import com.intercorp.dtos.CustomerKpi;
import com.intercorp.models.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class CustomerServicesTest {

    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    @Mock
    private CustomerDao customerDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenSaveCustomerThenReturnCustomer() {
        Customer customer = getCustomer();
        when(customerDao.save(customer)).thenReturn(customer);
        customer = customerService.saveCustomer(customer);
        assertNotNull(customer);
        assertEquals("Leandro", customer.getName());
        assertEquals("Franco", customer.getLastname());
        assertEquals(29, (int) customer.getAge());
        assertEquals("1991-06-08", customer.getBirthday().toString());
    }

    @Test
    public void whenFindAllCustomersThenReturnListOfCustomers() {
        Customer customer = getCustomer();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerDao.findAll()).thenReturn(customers);
        List<Customer> response = customerService.findAll();
        assertNotNull(response);
        assertEquals("Leandro", response.get(0).getName());
        assertEquals("Franco", response.get(0).getLastname());
        assertEquals(29, (int) response.get(0).getAge());
        assertEquals("1991-06-08", response.get(0).getBirthday().toString());
    }

    @Test
    public void whenCalculateKpiThenReturnResult() {
        Customer customer = getCustomer();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerDao.findAll()).thenReturn(customers);
        CustomerKpi response = customerService.calculateKpi();
        assertNotNull(response);
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
