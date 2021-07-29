/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */

package com.iabhitech.imart.pojo;

/**
 *
 * @author Abhineet Verma
 */
public class EmployeePojo {
    private String empid;
    private String empname;
    private String job;
    private Double salary;
    
    public EmployeePojo(){}

    public EmployeePojo(String empid, String empname, String job, Double salary) {
        this.empid = empid;
        this.empname = empname;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
}
