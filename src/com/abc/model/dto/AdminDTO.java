/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class AdminDTO {

    private final int id;
    private final String fname;
    private final String lname;
    private final String mobile;
    private final int emailId;

    public AdminDTO(int id, String fname, String lname, String mobile, int emailId) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.emailId = emailId;
    }

    public int getId(){
        return id;
    }
    
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMobile() {
        return mobile;
    }

    public int getEmailId() {
        return emailId;
    }

}
