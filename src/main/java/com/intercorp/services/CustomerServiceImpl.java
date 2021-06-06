package com.intercorp.services;

import com.intercorp.daos.CustomerDao;
import com.intercorp.dtos.CustomerKpi;
import com.intercorp.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public CustomerKpi calculateKpi() {
        List<Customer> customers = customerDao.findAll();
        CustomerKpi kpi = new CustomerKpi();
        double customersAverage = getCustomersAverage(customers);
        kpi.setAverage((int) customersAverage);
        double customersDesviation = getCustomersDesviation(customers, customersAverage);
        kpi.setStandardDeviation(customersDesviation);
        return kpi;
    }

    private double getCustomersAverage(final List<Customer> customers) {
        return customers.stream()
                    .mapToInt(Customer::getAge)
                    .average().orElse(Double.NaN);
    }

    private double getCustomersDesviation(List<Customer> customers, double customersAverage) {
        double variance = 0;
        for(Customer customer: customers){
            double range;
            range = Math.pow(customer.getAge() - customersAverage, 2f);
            variance = variance + range;
        }
        variance = variance / customers.size();
        return Math.sqrt(variance);
    }
}
