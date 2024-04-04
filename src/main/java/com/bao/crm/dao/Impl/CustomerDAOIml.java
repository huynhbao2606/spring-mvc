package com.bao.crm.dao.Impl;

import com.bao.crm.dao.CustomerDAO;
import com.bao.crm.entity.Customer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOIml implements CustomerDAO {

    public final SessionFactory  sessionFactory;

    public CustomerDAOIml(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers(String search) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = null;

        if (search != null && !search.trim().isEmpty()) {
            query = session.createQuery("from Customer where lower(firstName) like :theName "
                    + "or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + search.toLowerCase() + "%");
        } else {
            query = session.createQuery("from Customer", Customer.class);
        }

        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public Customer getCustomerbyEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer where email = :email", Customer.class);
        query.setParameter("email", email);
        List<Customer> customers = query.getResultList();
        if (!customers.isEmpty()) {
            return customers.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
    }
}
