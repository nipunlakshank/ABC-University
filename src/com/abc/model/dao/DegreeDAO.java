/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.DegreeDTO;
import com.abc.service.MySQL;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nipun
 */
public class DegreeDAO {
    
    public Boolean isExist(int id) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `degree` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();
            
            return rs.next();
        }
    }
    
    public Boolean isExist(String title) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `degree` WHERE `title`=?")
                    .setString(1, title)
                    .executeQuery();
            
            return rs.next();
        }
    }
    
    public List<DegreeDTO> getAll() throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `degree`")
                    .executeQuery();
            List<DegreeDTO> degrees = new ArrayList<>();
            while(rs.next()){
                DegreeDTO degree = new DegreeDTO(rs.getInt("id"), rs.getString("title"), rs.getBoolean("is_active"));
                degrees.add(degree);
            }
            return degrees;
        }
    }
    
    public DegreeDTO get(int id) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `degree` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();
            
            if(rs.next()){
                return new DegreeDTO(rs.getInt("id"), rs.getString("title"), rs.getBoolean("is_active"));
            }
            return null;
        }
    }
    
    public int getId(String degree) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `degree` WHERE `title`=?")
                    .setString(1, degree)
                    .executeQuery();
            
            if(rs.next()){
                return rs.getInt("id");
            }
            return 0;
        }
    }
    
    public int save(String title) throws Exception{
        try(MySQL db = new MySQL()){
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `degree` (`title`) VALUES (?)")
                    .setString(1, title)
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
    
    public Boolean update(int id, String title, Boolean isActive) throws Exception{
        if(!isExist(id)){
            return false;
        }
        try(MySQL db = new MySQL()){
            db.createConnection()
                    .createIUDStatement("UPDATE `degree` SET `title`=?, `is_active`=? WHERE `id`=?")
                    .setString(1, title)
                    .setBoolean(2, isActive)
                    .setInt(3, id)
                    .executeUpdate()
                    .commit();
            return true;
        }
    }
    
}
