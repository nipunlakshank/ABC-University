/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.service.Grades;
import com.abc.service.MySQL;
import java.sql.ResultSet;

/**
 *
 * @author nipun
 */
public class GpaDAO {

    private final Grades grades;
    private final DegreeSubjectDAO degSubDAO;

    public GpaDAO() {
        grades = new Grades();
        degSubDAO = new DegreeSubjectDAO();
    }

    public double getGpa(int studentId, int degId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `student_has_exam`.`marks` AS `marks`, `subject`.`credits` AS `credits` FROM "
                            + "`subject` INNER JOIN `exam` ON `subject`.`id`=`exam`.`subject_id` "
                            + "INNER JOIN `student_has_exam` ON `student_has_exam`.`exam_id`=`exam`.`id` "
                            + "WHERE `student_has_exam`.`student_id`=? AND `subject`.`degree_id`=?")
                    .setInt(1, studentId)
                    .setInt(2, degId)
                    .executeQuery();

            double totalGradePoints = 0;
            double totalCredits = degSubDAO.getTotalCredits(degId);
            while (rs.next()) {
                totalGradePoints += grades.getPoints(grades.getGrade(rs.getDouble("marks"))) * rs.getDouble("credits");
            }
            double totalGpa = totalGradePoints / totalCredits;
            return totalGpa;
        }
    }
}
