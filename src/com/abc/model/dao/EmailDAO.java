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
public class EmailDAO {
    
    public Boolean isExist(int id) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `id` FROM `email` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();
            
            return rs.next();
        }
    }
    
    public Boolean isExist(String email) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `id` FROM `email` WHERE `address`=?")
                    .setString(1, email)
                    .executeQuery();
            
            return rs.next();
        }
    }

    public String getEmail(int id) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `address` FROM `email` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        }
    }

    public int getId(String email) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `id` FROM `email` WHERE `address`=?")
                    .setString(1, email)
                    .executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
            return 0;
        }

    }
    
    public Boolean update(int id, String newEmail) throws Exception{
        if(!isExist(id)){
            return false;
        }
        
        try(MySQL db = new MySQL()){
            db.createConnection()
                    .createIUDStatement("UPDATE `email` SET `address`=? WHERE `id`=?")
                    .setString(1, newEmail)
                    .setInt(2, id)
                    .executeUpdate()
                    .commit();
        }
        return true;
    }

    public int save(String email) throws Exception {
        int id = getId(email);
        if (id > 0) {
            return -1;
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `email` (`address`) VALUES (?)")
                    .setString(1, email)
                    .executeUpdate()
                    .getGeneratedKeys();

            if (rs.next()) {
                db.commit();
                return rs.getInt(1);
            }
            return 0;
        }
    }

}
