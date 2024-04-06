package com.bao.crm.dao;

import com.bao.crm.dto.CustomerParams;
import com.bao.crm.entity.Customer;

import java.util.List;


public interface CustomerDAO{
    List<Customer> getCustomers(CustomerParams params);

    Customer getCustomerById(int id);

    Customer getCustomerbyEmail(String email);

    int getCustomerCount(CustomerParams customerParams);

    void saveCustomer(Customer customer);

    void deleteCustomer(int id);
}
