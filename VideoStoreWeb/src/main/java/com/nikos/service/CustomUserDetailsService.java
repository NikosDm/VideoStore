package com.nikos.service;

import com.nikos.model.StoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by nikos on 8/16/2018.
 */
@Service("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StoreUserService storeUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        StoreUser storeUser = storeUserService.getStoreUserByUsername(username);

        if (storeUser !=null){

            Collection<GrantedAuthority> authorities = storeUser.getStoreUserRoleList()
                    .stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                    .collect(Collectors.toCollection(ArrayList::new));

            return new org.springframework.security.core.userdetails.
                    User(username,storeUser.getPassword(),true,true,true,true,authorities);
        }
        else {
            throw new UsernameNotFoundException("User Not found");
        }
    }
}
