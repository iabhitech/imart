/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.dao;

import com.iabhitech.imart.dbutil.DBConnection;
import com.iabhitech.imart.pojo.ReceptionistPojo;
import com.iabhitech.imart.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Abhineet Verma
 */
public class ReceptionistDAO {

    public static Map<String, String> getNonRegisteredReceptionists() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select empid,empname from employees where job='Receptionist' and empid not in (select empid from users where usertype='Receptionist')");
        HashMap<String, String> recepList = new HashMap<>();
        while (rs.next()) {
            recepList.put(rs.getString("empid"), rs.getString("empname"));
        }
        return recepList;
    }

    public static boolean addReceptionist(UserPojo user) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("insert into users values (?,?,?,?,?)");

        ps.setString(1, user.getUserId());
        ps.setString(2, user.getEmpid());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getUserType());
        ps.setString(5, user.getUsername());

        return ps.executeUpdate() == 1;
    }

    public static List<ReceptionistPojo> getAllReceptionistDetails() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();

        ResultSet rs = stmt.executeQuery("select users.empid, empname, userid, job, salary from employees, users where usertype='Receptionist' and users.empid = employees.empid");

        ArrayList<ReceptionistPojo> recepList = new ArrayList<>();

        while (rs.next()) {
            ReceptionistPojo r = new ReceptionistPojo();
            r.setUserid(rs.getString("userid"));
            r.setEmpid(rs.getString("empid"));
            r.setEmpname(rs.getString("empname"));
            r.setJob(rs.getString("job"));
            r.setSalary(rs.getDouble("salary"));
            recepList.add(r);
        }

        return recepList;
    }

    public static Map<String, String> getAllReceptionistIds() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select userid, username from users where usertype='Receptionist' order by userid");
        HashMap<String, String> recepList = new HashMap<>();
        while (rs.next()) {
            recepList.put(rs.getString("userid"), rs.getString("username"));
        }
        return recepList;
    }

    /**
     *
     * @param userid
     * @return Detail of Receptionist
     * @throws SQLException
     */
    public static UserPojo getReceptionist(String userid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from users where userid=?");
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        UserPojo user = null;
        if (rs.next()) {
            user = new UserPojo(rs.getString("empid"), rs.getString("userid"), rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
        }
        return user;
    }

    public static boolean updatePassword(String userid, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn
                .prepareStatement("update users set password = ? where userid = ?");

        ps.setString(1, password);
        ps.setString(2, userid);

        return ps.executeUpdate() == 1;
    }

    public static boolean removeReceptionist(String userid) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("delete users where userid = ?");

        ps.setString(1, userid);

        return ps.executeUpdate() == 1;
    }
}
