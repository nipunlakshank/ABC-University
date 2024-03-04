/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.service.MySQL;
import java.sql.ResultSet;

/**
 *
 * @author nipun
 */
public class UserTypeDAO {
    
    public String getType(int id) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `user_type` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();
            
            if(rs.next()){
                return rs.getString("type");
            }
            return null;
        }
    }
    
    public String getType(String email) throws Exception{
        
        StudentDAO studentDAO = new StudentDAO();
        if(studentDAO.isExist(email)){
            return "student";
        }
        
        LecturerDAO lecturerDAO = new LecturerDAO();
        if(lecturerDAO.isExist(email)){
            return "lecturer";
        }
        
        AdminDAO adminDAO = new AdminDAO();
        if(adminDAO.isExist(email)){
            return "admin";
        }
        return null;
    }
    
    public int getId(String type) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `user_type` WHERE `type`=?")
                    .setString(1, type)
                    .executeQuery();
            
            if(rs.next()){
                return rs.getInt("id");
            }
            return 0;
        }
    }
    
}
