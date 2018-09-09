package com.nikos.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;

@Entity
@Table(name = "StoreUserRole")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StoreUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RoleID")
    private int RoleID = 0;

    @Column(name="RoleName", nullable = false)
    private String RoleName = "";

    public StoreUserRole(){}

    public StoreUserRole(String roleName) {
        RoleName = roleName;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}
