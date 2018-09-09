package com.nikos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.StoreUserRole;
import com.nikos.repository.StoreUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreUserRoleService {

    @Autowired
    private StoreUserRoleRepository storeUserRoleRepository;

    public StoreUserRole getStoreUserRole() {
        try{
            return storeUserRoleRepository.getStoreUserAdminRole();
        }
        catch(Exception e){
            return null;
        }
    }

    public StoreUserRole updateStoreUserRole(String storeUserRoleJSON){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            StoreUserRole storeUserRole = objectMapper.readValue(storeUserRoleJSON, StoreUserRole.class);

            if (storeUserRole.getRoleID() == 0) {
                storeUserRole = storeUserRoleRepository.insertNewStoreUserRole(storeUserRole);
            }
            else {
                storeUserRole = storeUserRoleRepository.updateSelectedStoreUserRole(storeUserRole);
            }

            return storeUserRole;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<StoreUserRole> updateStoreUserRoleList(String storeUserRoleListJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<StoreUserRole> storeUserRoleList = objectMapper.readValue(storeUserRoleListJSON, new TypeReference<List<StoreUserRole>>(){});
            StoreUserRole updatedStoreUserRole;
            List<StoreUserRole> newPlatformList = new ArrayList<>();

            for (StoreUserRole storeUserRole: storeUserRoleList){
                if (storeUserRole.getRoleID() == 0) {
                    updatedStoreUserRole = storeUserRoleRepository.insertNewStoreUserRole(storeUserRole);
                }
                else {
                    updatedStoreUserRole = storeUserRoleRepository.updateSelectedStoreUserRole(storeUserRole);
                }
                newPlatformList.add(updatedStoreUserRole);
            }

            return newPlatformList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedStoreUserRole(int StoreUserRoleID) {
        try {
            return storeUserRoleRepository.deleteSelectedStoreUserRole(StoreUserRoleID);
        }
        catch (Exception ex){
            return 0;
        }
    }
}
