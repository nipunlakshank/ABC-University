/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class UserDTO {

    private final int id;
    private final int passId;
    private final int emailId;
    private final int userTypeId;

    public UserDTO(int id, int emailId, int passId, int userTypeId) {
        this.id = id;
        this.passId = passId;
        this.emailId = emailId;
        this.userTypeId = userTypeId;
    }

    public int getId() {
        return id;
    }

    public int getPassId() {
        return passId;
    }

    public int getEmailId() {
        return emailId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

}
