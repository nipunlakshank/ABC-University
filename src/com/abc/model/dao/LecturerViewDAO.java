/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.LecturerDTO;
import com.abc.model.dto.LecturerViewDTO;
import com.abc.service.MySQL;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author nipun
 */
public class LecturerViewDAO {

    public List<LecturerViewDTO> getViews() throws Exception {
        EmailDAO emailDAO = new EmailDAO();
        LecturerDAO lecDAO = new LecturerDAO();

        List<LecturerDTO> lecturers = lecDAO.getAll();
        List<LecturerViewDTO> views = new ArrayList<>();

        for (LecturerDTO lecturer : lecturers) {
            LecturerViewDTO view = new LecturerViewDTO(lecturer.getId(), lecturer.getFname() + " " + lecturer.getLname(), lecturer.getMobile(), emailDAO.getEmail(lecturer.getEmailId()));
            views.add(view);
        }

        return views;
    }

    public List<LecturerViewDTO> filter(String inName, String inMobile, String inEmail) throws Exception {

        if (inName.isEmpty() && inMobile.isEmpty() && inEmail.isEmpty()) {
            return getViews();
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `lecturer`.`id` AS `id`, CONCAT(`fname`, ' ', `lname`) AS `name`, `mobile`, `email`.`address` AS `email` "
                            + "FROM `lecturer` INNER JOIN `email` ON `lecturer`.`email_id`=`email`.`id` WHERE (`fname` LIKE ? OR `lname` LIKE ?) AND `mobile` LIKE ? AND `email`.`address` LIKE ?")
                    .setString(1, inName.isEmpty() ? "%" : "%" + inName + "%")
                    .setString(2, inName.isEmpty() ? "%" : "%" + inName + "%")
                    .setString(3, inMobile.isEmpty() ? "%" : "%" + inMobile + "%")
                    .setString(4, inEmail.isEmpty() ? "%" : "%" + inEmail + "%")
                    .executeQuery();

            List<LecturerViewDTO> views = new ArrayList<>();
            while (rs.next()) {
                LecturerViewDTO view = new LecturerViewDTO(rs.getInt("id"), rs.getString("name"), rs.getString("mobile"), rs.getString("email"));
                views.add(view);
            }
            return views;
        }
    }

}
