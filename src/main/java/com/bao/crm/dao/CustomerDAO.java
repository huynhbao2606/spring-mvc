package com.bao.crm.dao;

import com.bao.crm.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers(String search);

    Customer getCustomerById(int id);

    Customer getCustomerbyEmail(String email);

    void saveCustomer(Customer customer);

    void deleteCustomer(int id);
}