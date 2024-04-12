package com.bao.crm.service.Impl;

import com.bao.crm.dao.UserDAO;
import com.bao.crm.entity.User;
import com.bao.crm.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }
}
