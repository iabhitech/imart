/*
 * SUPER MARKET MANAGEMENT SYSTEM
 * Designed By Abhineet Verma  * 
 */

package com.iabhitech.imart.pojo;

/**
 *
 * @author Abhineet Verma
 */
public class UserPojo {
    private String username;
    private String userID;
    private String password;
    private String userType;

    public UserPojo(){}
    
    public UserPojo(String userID, String password, String userType) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserId() {
        return this.userID;
    }
    
     public void setUserId(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserPojo{" + "username=" + username + ", userID=" + userID + ", password=" + password + ", userType=" + userType + '}';
    }
    
}
