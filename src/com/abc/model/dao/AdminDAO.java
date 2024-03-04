/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.exceptions.DataInsertException;
import com.abc.exceptions.EmailAlreadyExistsException;
import com.abc.exceptions.IlleagalUpdateOnEntityException;
import com.abc.exceptions.NoSuchEntityFoundException;
import com.abc.model.dto.AdminDTO;
import com.abc.service.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nipun
 */
public class AdminDAO {

    public Boolean isExist(String email) throws Exception {
        EmailDAO emailDAO = new EmailDAO();
        int emailId = emailDAO.getId(email);
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `admin` WHERE `email_id`=?")
                    .setInt(1, emailId)
                    .executeQuery();

            return rs.next();
        }
    }

    public List<AdminDTO> getAll() throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `admin`")
                    .executeQuery();

            List<AdminDTO> admins = new ArrayList<>();
            while (rs.next()) {
                AdminDTO admin = new AdminDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                admins.add(admin);
            }
            return admins;
        }
    }

    public AdminDTO get(String email) throws Exception {
        try (MySQL db = new MySQL()) {
            return null;
        }
    }

    public int save(AdminDTO admin) throws EmailAlreadyExistsException, DataInsertException, Exception {

        EmailDAO emailDAO = new EmailDAO();
        if (!emailDAO.isExist(admin.getEmailId())) {
            return -1;
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `admin` (`fname`, `lname`, `mobile`, `email_id`) VALUES (?, ?, ?, ?)")
                    .setString(1, admin.getFname())
                    .setString(2, admin.getLname())
                    .setString(3, admin.getMobile())
                    .setInt(4, admin.getEmailId())
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

    public Boolean update(int adminId, AdminDTO newAdmin) throws NoSuchEntityFoundException, IlleagalUpdateOnEntityException, Exception {
        return false;
    }

}
