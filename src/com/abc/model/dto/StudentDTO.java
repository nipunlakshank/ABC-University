/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

import java.sql.Date;

/**
 *
 * @author nipun
 */
public class StudentDTO {

    private final int id;
    private final String fname;
    private final String lname;
    private final String sno;
    private final int emailId;
    private final String mobile;

    public StudentDTO(int id, String sno, String fname, String lname, String mobile, int emailId) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.sno = sno;
        this.emailId = emailId;
        this.mobile = mobile;
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

    public String getSno() {
        return sno;
    }

    public int getEmailId() {
        return emailId;
    }

    public String getMobile() {
        return mobile;
    }

}
