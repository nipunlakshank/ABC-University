/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.SubjectDTO;
import com.abc.service.MySQL;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nipun
 */
public class SubjectDAO {
    
    public List<SubjectDTO> getAll() throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `subject`")
                    .executeQuery();
            
            List<SubjectDTO> subjects = new ArrayList<>();
            while(rs.next()){
                SubjectDTO subject = new SubjectDTO(rs.getInt("id"), rs.getString("code"), rs.getString("title"), rs.getInt("credits"),rs.getInt("semester"), rs.getInt("degree_id"),rs.getString("state"));
                subjects.add(subject);
            }
            return subjects;
        }
    }
    
    public List<SubjectDTO> filter(String inCode, String inTitle) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `subject` WHERE `code` LIKE ? AND `title` LIKE ?")
                    .setString(1, inCode.isEmpty() ? "%" : "%" + inCode + "%")
                    .setString(2, inTitle.isEmpty() ? "%" : "%" + inTitle + "%")
                    .executeQuery();
            
            List<SubjectDTO> subjects = new ArrayList<>();
            while(rs.next()){
                SubjectDTO subject = new SubjectDTO(rs.getInt("id"), rs.getString("code"), rs.getString("title"), rs.getInt("credits"),rs.getInt("semester"), rs.getInt("degree_id"),rs.getString("state"));
                subjects.add(subject);
            }
            return subjects;
        }
    }
    
    public SubjectDTO get(int id) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `subject` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();
            
            if(rs.next()){
                return new SubjectDTO(rs.getInt("id"), rs.getString("code"), rs.getString("title"), rs.getInt("credits"), rs.getInt("semester"), rs.getInt("degree_id"),rs.getString("state"));
            }
            return null;
        }
    }
    
    public int save(SubjectDTO subject) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `subject` (`code`, `title`, `credits`, `semester`, `degree_id`, `state`) VALUES (?, ?, ?, ?, ?, ?)")
                    .setString(1, subject.getCode())
                    .setString(2, subject.getTitle())
                    .setInt(3, subject.getCredits())
                    .setInt(4, subject.getSemester())
                    .setInt(5, subject.getDegreeId())
                    .setString(6, subject.getState())
                    .executeUpdate()
                    .getGeneratedKeys();
            
            if(!rs.next()){
                db.rollback();
                return 0;
            }
            db.commit();
            return rs.getInt(1);
        }
    }
    
}
