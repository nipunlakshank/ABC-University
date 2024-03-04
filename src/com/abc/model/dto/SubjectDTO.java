/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class SubjectDTO {
    
    private final int id;
    private final String code;
    private final String title;
    private final int credits;
    private final int semester;
    private final int degreeId;
    private final String state;

    public SubjectDTO(int id, String code, String title, int credits, int semester, int degreeId, String state) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.semester = semester;
        this.degreeId = degreeId;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public int getSemester() {
        return semester;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public String getState() {
        return state;
    }
    
}
