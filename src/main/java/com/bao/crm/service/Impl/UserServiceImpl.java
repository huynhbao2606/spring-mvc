package com.bao.crm.service.Impl;

import com.bao.crm.dao.RoleDAO;
import com.bao.crm.dao.UserDAO;
import com.bao.crm.dto.RegisteredUser;
import com.bao.crm.dto.UserDto;
import com.bao.crm.dto.UserParams;
import com.bao.crm.entity.Role;
import com.bao.crm.entity.User;
import com.bao.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    @Transactional
    public void saveUserDto(RegisteredUser userDto) {
        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassWord()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);

        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.findRoleByName("ROLE_USER"));
        user.setRoles(roles);

        userDAO.saveUserDto(user);
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setImage(userDto.getImageUrl());
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.findRoleByName("ROLE_USER"));
        user.setRoles(roles);

        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> getUsers(UserParams params) {
        return userDAO.getUsers(params);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public User getUserbyEmail(String email) {
        return userDAO.getUserbyEmail(email);
    }

    @Override
    @Transactional
    public int getUserCount(UserParams params) {
        return userDAO.getUserCount(params);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = userDAO.getUserById(id);

        if(user != null){
            user.getRoles().clear();
            userDAO.saveUser(user);
            userDAO.deleteUser(id);
        }
    }
}
