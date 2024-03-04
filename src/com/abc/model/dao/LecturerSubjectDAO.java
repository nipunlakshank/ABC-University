/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.LecturerSubjectViewDTO;
import com.abc.service.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nipun
 */
public class LecturerSubjectDAO {

    public Boolean isExist(int lecId, int subId) throws Exception {
        try (MySQL db = new MySQL()) {
            return db.createConnection()
                    .createSelectStatement("SELECT * FROM `lecturer_has_subject` WHERE `lecturer_id`=? AND `subject_id`=?")
                    .setInt(1, lecId)
                    .setInt(2, subId)
                    .executeQuery()
                    .next();
        }
    }

    public int save(int lecId, int subId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `lecturer_has_subject` (`lecturer_id`, `subject_id`) VALUES (?, ?)")
                    .setInt(1, lecId)
                    .setInt(2, subId)
                    .executeUpdate()
                    .getGeneratedKeys();

            if (rs.next()) {
                db.commit();
                return rs.getInt(1);
            }
            db.rollback();
            return -1;
        }
    }

    public List<LecturerSubjectViewDTO> getSubjects(int emailId) throws Exception {
        LecturerDAO lecDAO = new LecturerDAO();
        int lecId = lecDAO.getId(emailId);
        if (lecId < 1) {
            return null;
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `subject`.`id` AS `sub_id`, `code`, `title`, `semester` FROM `subject` INNER JOIN `lecturer_has_subject` ON `subject`.`id`=`lecturer_has_subject`.`id` WHERE `lecturer_id`=?")
                    .setInt(1, lecId)
                    .executeQuery();

            List<LecturerSubjectViewDTO> views = new ArrayList<>();
            while (rs.next()) {
                LecturerSubjectViewDTO view = new LecturerSubjectViewDTO(lecId, rs.getInt("sub_id"), rs.getString("code"), rs.getString("title"), rs.getInt("semester"));
                views.add(view);
            }
            return views;
        }
    }
    
    public List<LecturerSubjectViewDTO> filterViews(int emailId, String inCode, String inTitle) throws Exception {

        if (inCode.isEmpty() && inTitle.isEmpty()) {
            return getSubjects(emailId);
        }

        LecturerDAO lecDAO = new LecturerDAO();
        
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `student`.`id` AS `id`, CONCAT(`fname`, ' ', `lname`) AS `name`, "
                            + "`sno`, `mobile`, `email`.`address` AS `email` FROM `student` INNER JOIN `email` ON `student`.`email_id`=`email`.`id` "
                            + "WHERE (`student`.`fname` LIKE ? OR `student`.`lname` LIKE ?) "
                            + "AND `student`.`sno` LIKE ? AND `student`.`mobile` LIKE ? AND `email`.`address` LIKE ?")
                    .executeQuery();

            List<LecturerSubjectViewDTO> views = new ArrayList<>();
            while (rs.next()) {
                LecturerSubjectViewDTO view = new LecturerSubjectViewDTO(lecDAO.getId(emailId), rs.getInt("sub_id"), rs.getString("code"), rs.getString("title"), rs.getInt("semester"));
                views.add(view);
            }
            return views;
        }
    }
}
