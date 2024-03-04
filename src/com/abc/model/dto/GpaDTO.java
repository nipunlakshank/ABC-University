/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class GpaDTO {
    private final int studentId;
    private final int degreeId;
    private final double marks;
    private final String grade;
    private final double gradeValue;
    private final double credits;

    public GpaDTO(int studentId, int degreeId, double marks, String grade, double gradeValue, double credits) {
        this.studentId = studentId;
        this.degreeId = degreeId;
        this.marks = marks;
        this.grade = grade;
        this.gradeValue = gradeValue;
        this.credits = credits;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    public double getCredits() {
        return credits;
    }
    
    
}
