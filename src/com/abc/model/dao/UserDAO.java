/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.exceptions.DataInsertException;
import com.abc.exceptions.EmailAlreadyExistsException;
import com.abc.gui.MainPanel;
import com.abc.gui.Welcome;
import com.abc.gui.user.admin.AdminHome;
import com.abc.gui.user.lecturer.LecturerHome;
import com.abc.gui.user.student.StudentHome;
import com.abc.model.dto.UserDTO;
import com.abc.service.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nipun
 */
public class UserDAO {

    public List<UserDTO> getAll() throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `user`")
                    .executeQuery();

            List<UserDTO> users = new ArrayList<>();
            while (rs.next()) {
                UserDTO user = new UserDTO(rs.getInt("id"), rs.getInt("password_id"), rs.getInt("email_id"), rs.getInt("user_type_id"));
                users.add(user);
            }
            return users;
        }
    }

    public Boolean isExist(int id) throws Exception {
        return get(id) != null;
    }

    public Boolean isExist(String email) throws Exception {
        EmailDAO emailDAO = new EmailDAO();
        int emailId = emailDAO.getId(email);

        if (emailId < 1) {
            return false;
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `user` WHERE `email_id`=?")
                    .setInt(1, emailId)
                    .executeQuery();

            return rs.next();
        }
    }

    public UserDTO get(String email) throws Exception {
        EmailDAO emailDAO = new EmailDAO();
        int emailId = emailDAO.getId(email);

        return get(emailId);
    }

    public UserDTO get(int emailId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `user` WHERE `email_id`=?")
                    .setInt(1, emailId)
                    .executeQuery();

            if (rs.next()) {
                return new UserDTO(rs.getInt("id"), rs.getInt("email_id"), rs.getInt("password_id"), rs.getInt("user_type_id"));
            }
            return null;
        }
    }

    public UserDTO getIfUserElseNull(String email, String password) throws Exception {

        UserDTO user = get(email);

        if (user != null) {
            PasswordDAO passDAO = new PasswordDAO();

            if (passDAO.match(user.getPassId(), password)) {
                return user;
            }
        }

        return null;
    }

    public int save(UserDTO user) throws EmailAlreadyExistsException, DataInsertException, Exception {

        EmailDAO emailDAO = new EmailDAO();
        PasswordDAO passDAO = new PasswordDAO();

        if (!emailDAO.isExist(user.getEmailId())) {
            return -1;
        }

        if (!passDAO.isExist(user.getPassId())) {
            return -1;
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO `user` (`email_id`, `password_id`, `user_type_id`) VALUES (?, ?, ?)")
                    .setInt(1, user.getEmailId())
                    .setInt(2, user.getPassId())
                    .setInt(3, user.getUserTypeId())
                    .executeUpdate()
                    .getGeneratedKeys();

            if (!rs.next()) {
                db.rollback();
                return 0;
            }

            db.commit();
            return rs.getInt(1);

        }

    }

    public int save(String email, String password) throws Exception {

        EmailDAO emailDAO = new EmailDAO();
        PasswordDAO passDAO = new PasswordDAO();
        UserTypeDAO userTypeDAO = new UserTypeDAO();

        if (!emailDAO.isExist(email)) {
            return -1;
        }

        if (isExist(email)) {
            return 0;
        }

        int emailId = emailDAO.getId(email);
        int passId = passDAO.save(password);
        String userType = userTypeDAO.getType(email);
        int userTypeId = userTypeDAO.getId(userType);
        
        UserDTO user = new UserDTO(0, emailId, passId, userTypeId);

        return save(user);

    }

    public Boolean update(int id, Boolean isActive) throws Exception {

        try (MySQL db = new MySQL()) {

            db.createConnection()
                    .createIUDStatement("UPDATE `user` SET `is_active`=?")
                    .setBoolean(1, isActive)
                    .executeUpdate()
                    .commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean delete(int id) {

        try (MySQL db = new MySQL()) {
            db.createConnection()
                    .createIUDStatement("DELETE FROM `user` WHERE `id`=?")
                    .setInt(1, id)
                    .executeUpdate()
                    .commit();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public MainPanel getHome(UserDTO user) throws Exception {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        String userType = userTypeDAO.getType(user.getUserTypeId());

        return switch (userType) {
            case "admin" ->
                new AdminHome();
            case "lecturer" ->
                new LecturerHome();
            case "student" ->
                new StudentHome();
            default ->
                new Welcome();
        };
    }
}
