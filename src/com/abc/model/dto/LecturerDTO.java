/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class LecturerDTO {

    private final int id;
    private final String fname;
    private final String lname;
    private final int emailId;
    private final String mobile;

    public LecturerDTO(int id, String fname, String lname, String mobile, int emailId) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.emailId = emailId;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getEmailId() {
        return emailId;
    }

    public String getMobile() {
        return mobile;
    }

}
