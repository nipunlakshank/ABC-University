/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class ExamStudentViewDTO {
    private final int studentId;
    private final String sno;
    private final String name;
    private final String email;
    private final double marks;

    public ExamStudentViewDTO(int studentId, String sno, String name, String email, double marks) {
        this.studentId = studentId;
        this.sno = sno;
        this.name = name;
        this.email = email;
        this.marks = marks;
    }

    public int getId() {
        return studentId;
    }

    public String getSno() {
        return sno;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getMarks() {
        return marks;
    }
    
}
