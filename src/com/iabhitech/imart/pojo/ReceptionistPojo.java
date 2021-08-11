/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */

package com.iabhitech.imart.pojo;

/**
 *
 * @author Abhineet Verma
 */
public class ReceptionistPojo {
    private String empid;
    private String empname;
    private String userid;
    private String job;
    private double salary;

    public ReceptionistPojo() {
    }

    public ReceptionistPojo(String empid, String empname, String userid, String job, double salary) {
        this.empid = empid;
        this.empname = empname;
        this.userid = userid;
        this.job = job;
        this.salary = salary;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
}
