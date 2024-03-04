/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class ResultsViewDTO {
    private final int examId;
    private final String examCode;
    private final String subject;
    private final double credits;
    private final double marks;
    private final String grade;

    public ResultsViewDTO(int examId, String examCode, String subject, double credits, double marks, String grade) {
        this.examId = examId;
        this.examCode = examCode;
        this.subject = subject;
        this.credits = credits;
        this.marks = marks;
        this.grade = grade;
    }

    public int getExamId() {
        return examId;
    }

    public String getExamCode() {
        return examCode;
    }

    public String getSubject() {
        return subject;
    }

    public double getCredits() {
        return credits;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }
}
