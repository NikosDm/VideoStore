package com.nikos.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.StoreUser;
import com.nikos.model.StoreUserRole;
import com.nikos.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nikos on 8/4/2018.
 */
@Service
public class LoginRegisterService {

    @Autowired
    private StoreUserService storeUserService;

    @Autowired
    private StoreUserRoleService storeUserRoleService;

    public StoreUser SetRegisteredUser(String registerUserJSON){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            StoreUserRole storeUserRole = storeUserRoleService.getStoreUserRole();
            List<StoreUserRole> storeUserRoles = new ArrayList<StoreUserRole>();
            storeUserRoles.add(storeUserRole);
            StoreUser registerUser = objectMapper.readValue(registerUserJSON, StoreUser.class);
            registerUser.setCreateDate(new Date());
            registerUser.setPassword(Utilities.encodePassword(registerUser.getPassword()));
            registerUser.setStoreUserRoleList(storeUserRoles);
            registerUser = storeUserService.registerUser(registerUser);
            return registerUser;
        }
        catch (Exception ex){
            return null;
        }
    }
}
