package com.nikos.model.dto;

public class UserRoleStatistics {

    private String roleName = "";

    private int totalUsers = 0;

    public UserRoleStatistics(String roleName, int totalUsers) {
        this.roleName = roleName;
        this.totalUsers = totalUsers;
    }

    public UserRoleStatistics() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }
}
