package com.bao.crm.dao;

import com.bao.crm.entity.User;

public interface UserDAO {
     User findByUserName(String username);
}
