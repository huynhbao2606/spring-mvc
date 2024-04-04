package com.bao.crm.service;

import com.bao.crm.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers(String search);
    Customer getCustomerById(int id);
    Customer getCustomerByEmail(String email);
    void saveCustomer(Customer customer);
    void deleteCustomer(int id);
}
