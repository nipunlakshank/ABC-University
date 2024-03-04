/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.service.MySQL;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nipun
 */
public class DegreeStudentDAO {

    public List<Integer> getStudentIds(int degId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `student`.`id` AS `id` FROM `degree_has_student` "
                            + "INNER JOIN `student` ON `degree_has_student`.`student_id`=`student`.`id` "
                            + "WHERE `degree_has_student`.`degree_id`=?")
                    .setInt(1, degId)
                    .executeQuery();

            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                ids.add(id);
            }
            return ids;
        }
    }

    public List<String> getDegrees(int studentId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `degree`.`title` AS `degree` "
                            + "FROM `degree` INNER JOIN `degree_has_student` ON `degree_has_student`.`degree_id`=`degree`.`id` "
                            + "INNER JOIN `student` ON `degree_has_student`.`student_id`=`student`.`id` "
                            + "WHERE `degree_has_student`.`student_id`=?")
                    .setInt(1, studentId)
                    .executeQuery();

            List<String> degrees = new ArrayList<>();
            while (rs.next()) {
                String degree = rs.getString("degree");
                degrees.add(degree);
            }
            return degrees;
        }
    }

    public Boolean isExist(int studentId, int degId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `degree_has_student` WHERE `student_id`=? AND `degree_id`=?")
                    .setInt(1, studentId)
                    .setInt(2, degId)
                    .executeQuery();

            return rs.next();
        }
    }
    
    public int save(int studentId, int degId) throws Exception{
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `degree_has_student` (`student_id`, `degree_id`) VALUES (?, ?)")
                    .setInt(1, studentId)
                    .setInt(2, degId)
                    .executeUpdate()
                    .getGeneratedKeys();

            if(rs.next()){
                db.commit();
                return rs.getInt(1);
            }
            db.rollback();
            return 0;
        }
    }
}
