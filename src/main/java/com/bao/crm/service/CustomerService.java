package com.bao.crm.service;

import com.bao.crm.dto.CustomerParams;
import com.bao.crm.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<Customer> getCustomers(CustomerParams params);
    Customer getCustomerById(int id);
    Customer getCustomerByEmail(String email);
    void saveCustomer(Customer customer);
    void deleteCustomer(int id);
    int getCustomerCount(CustomerParams params);
}
