package com.bao.crm.dao.Impl;

import com.bao.crm.dao.CustomerDAO;
import com.bao.crm.dto.CustomerParams;
import com.bao.crm.entity.Customer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    public final SessionFactory  sessionFactory;

    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers(CustomerParams params) {
        String search = params.getSearch();
        String sort = params.getSort();

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();

        if(search != null && !search.trim().isEmpty()){
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get("firstName")), "%" + search.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("lastName")), "%" + search.toLowerCase() + "%")
            ));
        }

        query.where(predicates.toArray(new Predicate[0]));

        if (sort != null && !sort.isEmpty()) {
            switch (sort) {
                case "firstNameAsc":
                    query.orderBy(builder.asc(root.get("firstName")));
                    break;
                case "firstNameDesc":
                    query.orderBy(builder.desc(root.get("firstName")));
                    break;
                case "lastNameAsc":
                    query.orderBy(builder.asc(root.get("lastName")));
                    break;
                case "lastNameDesc":
                    query.orderBy(builder.desc(root.get("lastName")));
                    break;
                case "emailAsc":
                    query.orderBy(builder.asc(root.get("email")));
                    break;
                case "emailDesc":
                    query.orderBy(builder.desc(root.get("email")));
                    break;
                default:
                    query.orderBy(builder.asc(root.get("id")));
                    break;
            }
        }

        int page = params.getPageNumber();
        int pageSize = params.getPageSize();
        int firstResult = (page - 1) * pageSize;

        return sessionFactory.getCurrentSession().
                createQuery(query).
                setFirstResult(firstResult)
                .setMaxResults(pageSize)
                .getResultList();
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
    public int getCustomerCount(CustomerParams params) {
        String search = params.getSearch();

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Customer> root = query.from(Customer.class);

        query.select(builder.count(root));

        List<Predicate> predicates = new ArrayList<>();

        if(search != null && !search.trim().isEmpty()){
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get("firstName")), "%" + search.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("lastName")), "%" + search.toLowerCase() + "%")
            ));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return Math.toIntExact(sessionFactory.getCurrentSession()
                .createQuery(query)
                .getSingleResult());
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
