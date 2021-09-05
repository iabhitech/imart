/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.dao;

import com.iabhitech.imart.dbutil.DBConnection;
import com.iabhitech.imart.pojo.UserPojo;
import com.iabhitech.imart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Abhineet Verma
 */
public class UserDAO {

    public static final String MANAGER = "Manager";
    public static final String RECEPTIONIST = "Receptionist";

    public static boolean validateUser(UserPojo user) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement("select * from users where userid = ? and password = ? and usertype = ?");
        stmt.setString(1, user.getUserId());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getUserType());

        ResultSet userData = stmt.executeQuery();

        if (userData.next()) {
            UserProfile.setUsername(userData.getString("username"));
            UserProfile.setUserType(userData.getString("usertype"));
            UserProfile.setUserId(userData.getString("userid"));
            return true;
        }
        return false;
    }

    static boolean isUserPresent(String empid) throws SQLException {
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement("select 1 from users where empid = ?");
        stmt.setString(1, empid);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean isValidUserId(String userid) throws SQLException {
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement("select 1 from users where userid = ?");
        stmt.setString(1, userid);
        ResultSet rs = stmt.executeQuery();
        return !rs.next();
    }

}
