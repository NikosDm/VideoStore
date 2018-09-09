package com.nikos.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.StoreUser;
import com.nikos.model.StoreUserRole;
import com.nikos.repository.StoreUserRepository;
import com.nikos.repository.StoreUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class StoreUserService {

    @Autowired
    private StoreUserRepository storeUserRepository;

    @Autowired
    private StoreUserRoleRepository storeUserRoleRepository;

    public StoreUser registerUser(StoreUser newStoreUser) {
        try{
            return storeUserRepository.insertNewData(newStoreUser);
        }
        catch(Exception e){
            return null;
        }
    }

    public StoreUser getStoreUserByUsername(String Username) {
        try{
            StoreUser storeUser = storeUserRepository.getData("WHERE Username = '" + Username + "'").get(0);
            StoreUserRole adminRole = storeUserRoleRepository.getStoreUserRoleByUserID(storeUser.getUserID());
            List<StoreUserRole> storeUserRoles = new ArrayList<StoreUserRole>();
            storeUserRoles.add(adminRole);
            storeUser.setStoreUserRoleList(storeUserRoles);
            storeUser.setUserImageBase64(storeUser.getUserImage().length != 0 ? "data:image/*;base64," + Base64.getEncoder().encodeToString(storeUser.getUserImage()) : storeUser.getUserImageBase64());
            return storeUser;
        }
        catch(Exception e){
            return null;
        }
    }

    public StoreUser updateStoreUserDetails(String userDetailsJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            StoreUser userDetails = objectMapper.readValue(userDetailsJSON, StoreUser.class);

            if (!userDetails.getUserImageBase64().isEmpty()){
                String encodedMediaImg = userDetails.getUserImageBase64().split(",")[1];
                userDetails.setUserImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
            }

            storeUserRepository.updateStoreUserDetails(userDetails);
            return userDetails;
        }
        catch (Exception ex){
            return null;
        }
    }
}
