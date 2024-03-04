/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.ExamStudentViewDTO;
import com.abc.service.MySQL;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nipun
 */
public class ExamStudentViewDAO {

    public List<ExamStudentViewDTO> getViews(int examId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `student`.`id` AS `id`, `sno`, CONCAT(`fname`, ' ', `lname`) AS `name`, `email`.`address` AS `email`, `marks` FROM `student` "
                            + "INNER JOIN `student_has_exam` ON `student`.`id`=`student_has_exam`.`student_id` "
                            + "INNER JOIN `email` ON `student`.`email_id`=`email`.`id` "
                            + "WHERE `student_has_exam`.`exam_id`=?")
                    .setInt(1, examId)
                    .executeQuery();

            List<ExamStudentViewDTO> views = new ArrayList<>();
            while (rs.next()) {
                ExamStudentViewDTO view = new ExamStudentViewDTO(rs.getInt("id"), rs.getString("sno"), rs.getString("name"), rs.getString("email"), rs.getDouble("marks"));
                views.add(view);
            }
            return views;
        }
    }

    public List<ExamStudentViewDTO> filter(int examId, String inSno, String inName, String inEmail) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `student`.`id` AS `id`, `sno`, CONCAT(`fname`, ' ', `lname`) AS `name`, `email`.`address` AS `email`, `marks` FROM `student` "
                            + "INNER JOIN `student_has_exam` ON `student`.`id`=`student_has_exam`.`student_id` "
                            + "INNER JOIN `email` ON `student`.`email_id`=`email`.`id` "
                            + "WHERE (`fname` LIKE ? OR `lname` LIKE ?) AND `sno` LIKE ? AND `email`.`address` LIKE ?"
                            + "AND `student_has_exam`.`exam_id`=?")
                    .setString(1, inName.isEmpty() ? "%" : "%" + inName + "%")
                    .setString(2, inName.isEmpty() ? "%" : "%" + inName + "%")
                    .setString(3, inSno.isEmpty() ? "%" : "%" + inSno + "%")
                    .setString(4, inEmail.isEmpty() ? "%" : "%" + inEmail + "%")
                    .setInt(5, examId)
                    .executeQuery();

            List<ExamStudentViewDTO> views = new ArrayList<>();
            while (rs.next()) {
                ExamStudentViewDTO view = new ExamStudentViewDTO(rs.getInt("id"), rs.getString("sno"), rs.getString("name"), rs.getString("email"), rs.getDouble("marks"));
                views.add(view);
            }
            return views;
        }
    }
}
