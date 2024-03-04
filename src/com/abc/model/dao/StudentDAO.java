/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.exceptions.DataInsertException;
import com.abc.exceptions.EmailAlreadyExistsException;
import com.abc.model.dto.StudentDTO;
import com.abc.service.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nipun
 */
public class StudentDAO {

    public Boolean isExist(String email) throws Exception {
        EmailDAO emailDAO = new EmailDAO();
        int emailId = emailDAO.getId(email);
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `student` WHERE `email_id`=?")
                    .setInt(1, emailId)
                    .executeQuery();

            return rs.next();
        }
    }

    public List<StudentDTO> getAll() {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `student`")
                    .executeQuery();

            List<StudentDTO> students = new ArrayList<>();
            while (rs.next()) {
                StudentDTO student = new StudentDTO(rs.getInt("id"), rs.getString("sno"), rs.getString("fname"), rs.getString("lname"), rs.getString("mobile"), rs.getInt("email_id"));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            return null;
        }
    }

    public StudentDTO get(String email) throws Exception {

        EmailDAO emailDAO = new EmailDAO();
        int emailId = emailDAO.getId(email);

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `student` WHERE `email_id`=?")
                    .setInt(1, emailId)
                    .executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new StudentDTO(rs.getInt("id"), rs.getString("sno"), rs.getString("fname"), rs.getString("lname"), rs.getString("mobile"), rs.getInt("email_id"));
        }
    }

    public StudentDTO get(int id) throws Exception {

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `user` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new StudentDTO(rs.getInt("id"), rs.getString("sno"), rs.getString("fname"), rs.getString("lname"), rs.getString("mobile"), rs.getInt("email_id"));
        }
    }

    public int save(StudentDTO student) throws EmailAlreadyExistsException, DataInsertException, Exception {
        EmailDAO emailDAO = new EmailDAO();

        if (!emailDAO.isExist(student.getEmailId())) {
            return -1;
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `student` (`fname`, `lname`, `sno`, `mobile`, `email_id`) VALUES (?, ?, ?, ?, ?)")
                    .setString(1, student.getFname())
                    .setString(2, student.getLname())
                    .setString(3, student.getSno())
                    .setString(4, student.getMobile())
                    .setInt(5, student.getEmailId())
                    .executeUpdate()
                    .getGeneratedKeys();

            if (!rs.next()) {
                db.rollback();
                return -1;
            }

            int id = rs.getInt(1);

            String sno = id + "";
            while (sno.length() < 4) {
                sno = "0" + sno;
            }

            sno = "ST " + sno;

            db.createIUDStatement("UPDATE `student` SET `sno`=? WHERE `id`=?")
                    .setString(1, sno)
                    .setInt(2, id)
                    .executeUpdate()
                    .commit();

            return id;
        }
    }

    public int save(String fname, String lname, String mobile, String email) throws Exception {
        EmailDAO emailDAO = new EmailDAO();

        if (emailDAO.isExist(email)) {
            return 0;
        }
        int emailId = emailDAO.save(email);

        StudentDTO student = new StudentDTO(0, "", fname, lname, mobile, emailId);
        return save(student);

    }

    public Boolean update(int studentId, String newFname, String newLname, String newEmail, String newMobile, java.sql.Date newDob) throws Exception {
        StudentDTO student = get(studentId);

        EmailDAO emailDAO = new EmailDAO();
        int emailId = student.getEmailId();
        if (!emailDAO.update(emailId, newEmail)) {
            return false;
        }

        try (MySQL db = new MySQL()) {
            db.createConnection()
                    .createIUDStatement("UPDATE `student` SET `fname`=?, `lname`=?, `mobile`=?, `dob`=? WHERE `id`=?")
                    .setString(1, newFname)
                    .setString(2, newLname)
                    .setString(3, newMobile)
                    .setDate(4, newDob)
                    .setInt(5, studentId)
                    .executeUpdate()
                    .commit();
        }
        return true;
    }

    public Boolean update(int studentId, String newFname, String newLname, String newEmail, String newMobile, Date newDob) throws Exception {
        return update(studentId, newFname, newLname, newEmail, newMobile, new java.sql.Date(newDob.getTime()));
    }

    public Boolean delete(String email) throws Exception {
        return false;
    }

}
