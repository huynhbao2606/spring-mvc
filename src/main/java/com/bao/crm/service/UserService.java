package com.bao.crm.service;

import com.bao.crm.dto.RegisteredUser;
import com.bao.crm.dto.UserParams;
import com.bao.crm.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByUserName(String username);
    User getUserById(int id);
    User getUserbyEmail(String email);
    List<User> getUsers(UserParams params);
    void saveUserDto(RegisteredUser userDto);
    void saveUser(User user);
    void deleteUser(int id);
    int getUserCount(UserParams params);

}
