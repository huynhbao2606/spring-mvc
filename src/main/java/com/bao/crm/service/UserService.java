package com.bao.crm.service;

import com.bao.crm.entity.User;

public interface UserService {
    User findByUserName(String username);
}
