package com.bao.crm.service.Impl;


import com.bao.crm.dto.CustomUserDetails;
import com.bao.crm.entity.User;
import com.bao.crm.entity.UserRole;
import com.bao.crm.service.UserDetailService;
import com.bao.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserService userService;

    @Autowired
    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        System.out.println(user);
        if(user == null){
            throw new UsernameNotFoundException("Not Found User");
        }
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Set<UserRole> roles =  user.getUserRoles();
        for(UserRole userRole : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }

        return new CustomUserDetails(grantedAuthorities, user);
    }

}
