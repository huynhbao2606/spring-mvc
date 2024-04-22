package com.bao.crm.dao;

import com.bao.crm.dto.UserParams;
import com.bao.crm.entity.User;

import java.util.List;

public interface UserDAO  {

     List<User> getUsers(UserParams params);

     User findByUserName(String username);

     User getUserById(int id);

     User getUserbyEmail(String email);

     int getUserCount(UserParams params);

     void saveUser(User user);

     void saveUserDto(User user);

     void deleteUser(int id);
}
