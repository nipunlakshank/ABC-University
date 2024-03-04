/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nipun
 */
public class Grades {

    Map<String, Double> gradePoints;

    public Grades() {
        gradePoints = new HashMap<>();
        gradePoints.put("A", 4.00);
        gradePoints.put("A-", 3.70);
        gradePoints.put("B+", 3.30);
        gradePoints.put("B", 3.00);
        gradePoints.put("B-", 2.70);
        gradePoints.put("C+", 2.30);
        gradePoints.put("C", 2.00);
        gradePoints.put("C-", 1.70);
        gradePoints.put("D+", 1.30);
        gradePoints.put("D", 1.00);
        gradePoints.put("F", 0.0);
    }

    public String getGrade(Double marks) {

        String grade;

        if (marks <= 100 && marks >= 80) {
            grade = "A";
        } else if (marks >= 70) {
            grade = "A-";
        } else if (marks >= 65) {
            grade = "B+";
        } else if (marks >= 60) {
            grade = "B";
        } else if (marks >= 55) {
            grade = "B-";
        } else if (marks >= 50) {
            grade = "C+";
        } else if (marks >= 45) {
            grade = "C";
        } else if (marks >= 40) {
            grade = "C-";
        } else if (marks >= 35) {
            grade = "D+";
        } else if (marks >= 30) {
            grade = "D";
        } else if (marks >= 0) {
            grade = "F";
        } else {
            grade = "null";
        }
        return grade;
    }

    public double getPoints(String grade) {
        return gradePoints.get(grade);
    }
}
