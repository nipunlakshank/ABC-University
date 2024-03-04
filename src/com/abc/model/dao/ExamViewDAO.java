/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.ExamViewDTO;
import com.abc.service.MySQL;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nipun
 */
public class ExamViewDAO {
    public List<ExamViewDTO> getViews() throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT exam.id AS exam_id, subject_id, exam.code AS code, title, semester, status "
                            + "FROM `exam` INNER JOIN `subject` ON `exam`.`subject_id`=`subject`.`id`")
                    .executeQuery();
            
            List<ExamViewDTO> views = new ArrayList<>();
            while(rs.next()){
                ExamViewDTO view = new ExamViewDTO(rs.getInt("exam_id"), rs.getInt("subject_id"), rs.getString("code"), rs.getString("title"), rs.getInt("semester"), rs.getBoolean("status") ? "Active" : "Closed");
                views.add(view);
            }
            return views;
        }
    }
    
    public List<ExamViewDTO> filter(String inCode, String inSubjectId, String inTitle, String status) throws Exception{
        
        String query;
        switch(status){
            case "Active" -> {
                query = "SELECT exam.id AS exam_id, subject_id, exam.code AS code, title, semester, status "
                            + "FROM `exam` INNER JOIN `subject` ON `exam`.`subject_id`=`subject`.`id` "
                            + "WHERE `exam`.`code` LIKE ? AND `subject_id` LIKE ? AND `title` LIKE ? AND `status`=1";
            }
            case "Closed" -> {
                query = "SELECT exam.id AS exam_id, subject_id, exam.code AS code, title, semester, status "
                            + "FROM `exam` INNER JOIN `subject` ON `exam`.`subject_id`=`subject`.`id` "
                            + "WHERE `exam`.`code` LIKE ? AND `subject_id` LIKE ? AND `title` LIKE ? AND `status`=0";
            }
            default -> {
                query = "SELECT exam.id AS exam_id, subject_id, exam.code AS code, title, semester, status "
                            + "FROM `exam` INNER JOIN `subject` ON `exam`.`subject_id`=`subject`.`id` "
                            + "WHERE `exam`.`code` LIKE ? AND `subject_id` LIKE ? AND `title` LIKE ?";
            }
        }
        
        try(MySQL db = new MySQL()){
            
            ResultSet rs = db.createConnection()
                    .createSelectStatement(query)
                    .setString(1, inCode.isEmpty() ? "%" : "%" + inCode + "%")
                    .setString(2, inSubjectId.isEmpty() ? "%" : "%" + inSubjectId + "%")
                    .setString(3, inTitle.isEmpty() ? "%" : "%" + inTitle + "%")
                    .executeQuery();
            
            
            List<ExamViewDTO> views = new ArrayList<>();
            while(rs.next()){
                ExamViewDTO view = new ExamViewDTO(rs.getInt("exam_id"), rs.getInt("subject_id"), rs.getString("code"), rs.getString("title"), rs.getInt("semester"), rs.getBoolean("status") ? "Active" : "Closed");
                views.add(view);
            }
            return views;
        }
    }
}
