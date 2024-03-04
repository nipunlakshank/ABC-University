/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class DegreeViewDTO {
    
    private final int id;
    private final String title;
    private final double credits;
    private final Boolean isActive;

    public DegreeViewDTO(int id, String title, double credits, Boolean isActive) {
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCredits() {
        return credits;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    
}
