/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class StudentViewDTO {
    private final int id;
    private final String sno;
    private final String name;
    private final String mobile;
    private final String email;

    public StudentViewDTO(int id, String sno, String name, String mobile, String email) {
        this.id = id;
        this.sno = sno;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    
    public String getSno(){
        return sno;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}
