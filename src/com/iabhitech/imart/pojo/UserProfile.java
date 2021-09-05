/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.pojo;

/**
 *
 * @author Abhineet Verma
 */
public class UserProfile {

    private static String userName;
    private static String userType;
    private static String userId;
    
    public static String getUsername() {
        return userName;
    }

    public static void setUsername(String username) {
        UserProfile.userName = username;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        UserProfile.userType = userType;
    }
    
    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserProfile.userId = userId;
    }

}
