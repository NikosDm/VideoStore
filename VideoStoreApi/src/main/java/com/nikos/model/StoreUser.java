package com.nikos.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "StoreUser")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StoreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserID")
    private int UserID = 0;

    @Column(name="Username", nullable = false, unique = true)
    @NotEmpty(message = "Username is Required.")
    private String Username = "";

    @Column(name="Password", nullable = false)
    @NotEmpty(message = "Password is Required.")
    private String Password = "";

    @Column(name="FirstName", nullable = false)
    private String FirstName = "";

    @Column(name="LastName", nullable = false)
    private String LastName = "";

    @Column(name="CreateDate", nullable = true)
    private Date CreateDate = new Date();

    @Column(name="Email", nullable = false, unique = true)
    private String Email = "";

    @Column(name="UserImage")
    private byte[] UserImage = new byte[0];

    @Transient
    private String UserImageBase64 = "";

    @ManyToMany(fetch = FetchType.EAGER)
    private List<StoreUserRole> storeUserRoleList = new ArrayList<StoreUserRole>();

    public StoreUser(){}

    public StoreUser(String username, String password, String firstName, String lastName, Date createDate, String email, byte[] userImage, List<StoreUserRole> storeUserRoleList) {
        Username = username;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        CreateDate = createDate;
        Email = email;
        UserImage = userImage;
        this.storeUserRoleList = storeUserRoleList;
    }

    public void setStoreUserRoleList(List<StoreUserRole> storeUserRoleList) {
        this.storeUserRoleList = storeUserRoleList;
    }

    public List<StoreUserRole> getStoreUserRoleList() {
        return storeUserRoleList;
    }

    public int getUserID() {
        return UserID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public byte[] getUserImage() {
        return UserImage;
    }

    public void setUserImage(byte[] userImage) {
        UserImage = userImage;
    }

    public String getUserImageBase64() {
        return UserImageBase64;
    }

    public void setUserImageBase64(String userImageBase64) {
        UserImageBase64 = userImageBase64;
    }
}
