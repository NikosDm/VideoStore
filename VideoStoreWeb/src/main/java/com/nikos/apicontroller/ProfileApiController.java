package com.nikos.apicontroller;

import com.nikos.model.StoreUser;
import com.nikos.model.StoreUserRole;
import com.nikos.service.LoginRegisterService;
import com.nikos.service.StoreUserRoleService;
import com.nikos.service.StoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileApiController {

    @Autowired
    private LoginRegisterService loginRegisterService;

    @Autowired
    private StoreUserService storeUserService;

    @Autowired
    private StoreUserRoleService storeUserRoleService;

    @RequestMapping(value = "/api/ProfileApi/registerUser", method = RequestMethod.POST)
    public ResponseEntity<Boolean> registerUser (@RequestBody String registerUserJson) {

        try {
            StoreUser registerUser = loginRegisterService.SetRegisteredUser(registerUserJson);

            if (registerUser != null){
                Authentication auth = new UsernamePasswordAuthenticationToken(registerUser.getUsername(), registerUser.getPassword());
                SecurityContext ctx = SecurityContextHolder.createEmptyContext();
                SecurityContextHolder.setContext(ctx);
                ctx.setAuthentication(auth);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/ProfileApi/failLogin", method = RequestMethod.GET)
    public ResponseEntity failedLogin() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/ProfileApi/GetUserDetails", method = RequestMethod.GET)
    public ResponseEntity<StoreUser> getLoggedInUserDetails(){

        try {
            String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            StoreUser loggedInUser = storeUserService.getStoreUserByUsername(loggedInUsername);
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new StoreUser(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/ProfileApi/UpdateUserDetails", method = RequestMethod.POST)
    public ResponseEntity<StoreUser> updateLoggedInUserDetails(@RequestBody String userDetailsJSON){
        try {
            StoreUser storeUser = storeUserService.updateStoreUserDetails(userDetailsJSON);
            return new ResponseEntity<>(storeUser, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new StoreUser(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/ProfileApi/updateStoreUserRole", method = RequestMethod.POST)
    public ResponseEntity<StoreUserRole> updateStoreUserRole (String storeUserRoleJSON) {
        try {
            StoreUserRole storeUserRole = storeUserRoleService.updateStoreUserRole(storeUserRoleJSON);
            return new ResponseEntity<>(storeUserRole, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new StoreUserRole(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/ProfileApi/updateStoreUserRoleList", method = RequestMethod.PUT)
    public ResponseEntity<List<StoreUserRole>> updateStoreUserRoleList (String storeUserRoleListJSON) {
        try {
            List<StoreUserRole> storeUserRoles = storeUserRoleService.updateStoreUserRoleList(storeUserRoleListJSON);
            return new ResponseEntity<>(storeUserRoles, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/ProfileApi/deleteStoreUserRole", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteStoreUserRole (int SettingID) {
        try {
            int deleteStoreUserRoleID = storeUserRoleService.deleteSelectedStoreUserRole(SettingID);
            return new ResponseEntity<>(deleteStoreUserRoleID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
