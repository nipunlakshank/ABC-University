/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.ResultsViewDTO;
import com.abc.service.Grades;
import com.abc.service.MySQL;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nipun
 */
public class ResultsViewDAO {

    public List<ResultsViewDTO> getViews(int studentId, int degId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `exam`.`id` AS `exam_id`, `exam`.`code` AS `exam_code`, `degree`.`title` AS `degree`, `subject`.`title` AS `subject`, `subject`.`credits` AS `credits`, `student_has_exam`.`marks` AS `marks` FROM "
                            + "`subject` INNER JOIN `exam` ON `subject`.`id`=`exam`.`subject_id` "
                            + "INNER JOIN `degree` ON `degree`.`id`=`subject`.`degree_id` "
                            + "INNER JOIN `student_has_exam` ON `student_has_exam`.`exam_id`=`exam`.`id` "
                            + "WHERE `student_has_exam`.`student_id`=? AND `degree`.`id`=?")
                    .setInt(1, studentId)
                    .setInt(2, degId)
                    .executeQuery();

            List<ResultsViewDTO> views = new ArrayList<>();

            while (rs.next()) {
                Grades grades = new Grades();
                String grade = grades.getGrade(rs.getDouble("marks"));
                ResultsViewDTO view = new ResultsViewDTO(rs.getInt("exam_id"), rs.getString("exam_code"), rs.getString("subject"), rs.getDouble("credits"), rs.getDouble("marks"), grade);
                views.add(view);
            }
            return views;
        }
    }

    public List<ResultsViewDTO> filter(int studentId, int degId, String inExamCode, String inSubject) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `exam`.`id` AS `exam_id`, `exam`.`code` AS `exam_code`, `degree`.`title` AS `degree`, `subject`.`title` AS `subject`, `subject`.`credits` AS `credits`, `student_has_exam`.`marks` AS `marks` FROM "
                            + "`subject` INNER JOIN `exam` ON `subject`.`id`=`exam`.`subject_id` "
                            + "INNER JOIN `degree` ON `degree`.`id`=`subject`.`degree_id` "
                            + "INNER JOIN `student_has_exam` ON `student_has_exam`.`exam_id`=`exam`.`id` "
                            + "WHERE `exam`.`code` LIKE ? AND `subject`.`title` LIKE ? AND `student_has_exam`.`student_id`=? AND `degree`.`id`=?")
                    .setString(1, inExamCode.isEmpty() ? "%" : "%" + inExamCode + "%")
                    .setString(2, inSubject.isEmpty() ? "%" : "%" + inSubject + "%")
                    .setInt(3, studentId)
                    .setInt(4, degId)
                    .executeQuery();

            List<ResultsViewDTO> views = new ArrayList<>();

            while (rs.next()) {
                Grades grades = new Grades();
                String grade = grades.getGrade(rs.getDouble("marks"));
                ResultsViewDTO view = new ResultsViewDTO(rs.getInt("exam_id"), rs.getString("exam_code"), rs.getString("subject"), rs.getInt("credits"), rs.getDouble("marks"), grade);
                views.add(view);
            }
            return views;
        }
    }
}
