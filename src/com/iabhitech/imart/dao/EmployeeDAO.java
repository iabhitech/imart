/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.dao;

import com.iabhitech.imart.dbutil.DBConnection;
import com.iabhitech.imart.pojo.EmployeePojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abhineet Verma
 */
public class EmployeeDAO {

    public static String getNextEmpID() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select max(empid) from employees");
        rs.next();
        int empid = Integer.parseInt(rs.getString(1).substring(1));
        empid += 1;
        return "E" + empid;
    }

    public static boolean addEmployee(EmployeePojo emp) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1, emp.getEmpid());
        ps.setString(2, emp.getEmpname());
        ps.setString(3, emp.getJob());
        ps.setDouble(4, emp.getSalary());

        return ps.executeUpdate() == 1;
    }

    public static List<EmployeePojo> getAllEmployees() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employees");
        ArrayList<EmployeePojo> empList = new ArrayList<>();
        while (rs.next()) {
            String eid = rs.getString(1);
            String ename = rs.getString(2);
            String ejob = rs.getString(3);
            Double esal = rs.getDouble(4);
            EmployeePojo e = new EmployeePojo(eid, ename, ejob, esal);
            empList.add(e);
        }
        return empList;
    }

    public static EmployeePojo getEmployee(String empId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from employees where empid=?");
        ps.setString(1, empId);
        ResultSet rs = ps.executeQuery();
        EmployeePojo emp = null;
        if (rs.next()) {
            emp = new EmployeePojo(rs.getString("empid"), rs.getString("empname"), rs.getString("job"), rs.getDouble("salary"));
        }
        return emp;
    }

    public static boolean updateEmployee(EmployeePojo emp) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn
                .prepareStatement("update employees set empname=?, salary=?, job=? where empid = ?");

        ps.setString(1, emp.getEmpname());
        ps.setDouble(2, emp.getSalary());
        ps.setString(3, emp.getJob());
        ps.setString(4, emp.getEmpid());

        int empRes = ps.executeUpdate();
        if (empRes == 0) {
            return false;
        }

        if (!UserDAO.isUserPresent(emp.getEmpid())) {
            return true;
        }
        ps = conn.prepareStatement("update users set username=?, usertype=? where empid=?");

        ps.setString(1, emp.getEmpname());
        ps.setString(2, emp.getJob());
        ps.setString(3, emp.getEmpid());

        return ps.executeUpdate() == 1;
    }

    public static List<String> getAllEmployeesIDs() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select empid from employees");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("empid"));
        }
        return ids;
    }

    public static boolean removeEmployee(String empId) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("delete employees where empid = ?");

        ps.setString(1, empId);

        return ps.executeUpdate() == 1;
    }

}