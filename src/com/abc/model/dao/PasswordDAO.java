/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.exceptions.DataInsertException;
import com.abc.exceptions.NoSuchEntityFoundException;
import com.abc.service.MySQL;
import com.abc.service.PassEncrypt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author nipun
 */
public class PasswordDAO {

    public Boolean isExist(int id) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `id` FROM `password` WHERE `id`=?")
                    .setInt(1, id)
                    .executeQuery();
            
            return rs.next();
        }
    }

    public Boolean match(int passId, String password) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `password` WHERE `id`=?")
                    .setInt(1, passId)
                    .executeQuery();

            if (!rs.next()) {
                throw new NoSuchEntityFoundException();
            }

            return PassEncrypt.isMatched(password, rs.getString("hash"), rs.getString("salt"));
        }
    }

    public Map<String, String> encrypt(String password) {
        return PassEncrypt.getEncryptedPass(password);
    }

    public int save(String password) throws DataInsertException, Exception {

        Map<String, String> pass = PassEncrypt.getEncryptedPass(password);

        try (MySQL db = new MySQL()) {
            try {
                ResultSet rs = db.createConnection()
                        .createIUDStatement("INSERT INTO `password` (`hash`, `salt`) VALUES (?, ?)")
                        .setString(1, pass.get("hash"))
                        .setString(2, pass.get("salt"))
                        .executeUpdate()
                        .getGeneratedKeys();

                if (rs.next()) {
                    db.commit();
                    return rs.getInt(1);
                }
                return 0;
            } catch (SQLException e) {
                db.rollback();
                db.commit();
                throw new DataInsertException();
            }
        }
    }

    public void update(int id, String newPassword) throws Exception {
        Map<String, String> pass = PassEncrypt.getEncryptedPass(newPassword);

        try (MySQL db = new MySQL()) {
            db.createConnection()
                    .createIUDStatement("UPDATE `password` SET `hash`=?, `salt`=? WHERE `id`=?")
                    .setString(1, pass.get("hash"))
                    .setString(2, pass.get("salt"))
                    .setInt(3, id)
                    .executeUpdate()
                    .commit();
        }
    }
}
