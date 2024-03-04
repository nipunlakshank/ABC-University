/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.exceptions.DataInsertException;
import com.abc.exceptions.EmailAlreadyExistsException;
import com.abc.model.dto.LecturerDTO;
import com.abc.service.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nipun
 */
public class LecturerDAO {

    public Boolean isExist(String email) throws Exception {
        EmailDAO emailDAO = new EmailDAO();
        int emailId = emailDAO.getId(email);
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `lecturer` WHERE `email_id`=?")
                    .setInt(1, emailId)
                    .executeQuery();

            return rs.next();
        }
    }

    public List<LecturerDTO> getAll() throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `lecturer`")
                    .executeQuery();

            List<LecturerDTO> lecturers = new ArrayList<>();
            while (rs.next()) {
                LecturerDTO lecturer = new LecturerDTO(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("mobile"), rs.getInt("email_id"));
                lecturers.add(lecturer);
            }
            return lecturers;
        }
    }

    public LecturerDTO get(int id) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `lecturer` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new LecturerDTO(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("mobile"), rs.getInt("email_id"));
        }
    }
    
    public int getId(int emailId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `id` FROM `lecturer` WHERE `email_id`=?")
                    .setInt(1, emailId)
                    .executeQuery();

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("id");
        }
    }

    public int save(LecturerDTO lecturer) throws EmailAlreadyExistsException, DataInsertException, Exception {
        EmailDAO emailDAO = new EmailDAO();

        if (!emailDAO.isExist(lecturer.getEmailId())) {
            return -1;
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `lecturer` (`fname`, `lname`, `mobile`, `email_id`) VALUES (?, ?, ?, ?)")
                    .setString(1, lecturer.getFname())
                    .setString(2, lecturer.getLname())
                    .setString(3, lecturer.getMobile())
                    .setInt(4, lecturer.getEmailId())
                    .executeUpdate()
                    .getGeneratedKeys();

            if (!rs.next()) {
                db.rollback();
                return -1;
            }

            db.commit();
            return rs.getInt(1);

        }
    }

    public Boolean update(int lecturerId, String newFname, String newLname, String newMobile, String newEmail) throws Exception {
        LecturerDTO lecturer = get(lecturerId);

        EmailDAO emailDAO = new EmailDAO();
        int emailId = lecturer.getEmailId();
        if (!emailDAO.update(emailId, newEmail)) {
            return false;
        }

        try (MySQL db = new MySQL()) {
            db.createConnection()
                    .createIUDStatement("UPDATE `lecturer` SET `fname`=?, `lname`=?, `mobile`=? WHERE `id`=?")
                    .setString(1, newFname)
                    .setString(2, newLname)
                    .setString(3, newMobile)
                    .setInt(5, lecturerId)
                    .executeUpdate()
                    .commit();
        }
        return true;
    }

    public Boolean delete(String email) throws Exception {
        return false;
    }

}
