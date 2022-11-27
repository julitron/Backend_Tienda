package com.proyecto.ciclo4G17.mitiendavirtual.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.MainUser;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userService.getByUserName(userName).get();
        return MainUser.build(user);
    }
    
}