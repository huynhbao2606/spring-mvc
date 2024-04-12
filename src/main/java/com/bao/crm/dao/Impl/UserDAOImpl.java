package com.bao.crm.dao.Impl;

import com.bao.crm.dao.UserDAO;
import com.bao.crm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User findByUserName(String username) {
        try (Session session = sessionFactory.openSession()) {
            User user = (User) session.createQuery("from User where userName = :userName")
                    .setParameter("userName", username)
                    .uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
