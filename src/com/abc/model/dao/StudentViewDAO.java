/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.StudentDTO;
import com.abc.model.dto.StudentViewDTO;
import com.abc.service.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nipun
 */
public class StudentViewDAO {

    public List<StudentViewDTO> getViews() throws Exception {
        StudentDAO stDAO = new StudentDAO();
        EmailDAO emailDAO = new EmailDAO();
        List<StudentDTO> students = stDAO.getAll();
        List<StudentViewDTO> views = new ArrayList<>();

        for (StudentDTO student : students) {
            StudentViewDTO view = new StudentViewDTO(student.getId(), student.getSno(), student.getFname() + " " + student.getLname(), student.getMobile(), emailDAO.getEmail(student.getEmailId()));
            views.add(view);
        }
        return views;
    }

    public List<StudentViewDTO> filter(String inName, String inSno, String inMobile, String inEmail) throws Exception {

        if (inName.isEmpty() && inMobile.isEmpty() && inEmail.isEmpty() && inSno.isEmpty()) {
            return getViews();
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT `student`.`id` AS `id`, CONCAT(`fname`, ' ', `lname`) AS `name`, "
                            + "`sno`, `mobile`, `email`.`address` AS `email` FROM `student` INNER JOIN `email` ON `student`.`email_id`=`email`.`id` "
                            + "WHERE (`student`.`fname` LIKE ? OR `student`.`lname` LIKE ?) "
                            + "AND `student`.`sno` LIKE ? AND `student`.`mobile` LIKE ? AND `email`.`address` LIKE ?")
                    .setString(1, inName.isEmpty() ? "%" : "%" + inName + "%")
                    .setString(2, inName.isEmpty() ? "%" : "%" + inName + "%")
                    .setString(3, inSno.isEmpty() ? "%" : "%" + inSno + "%")
                    .setString(4, inMobile.isEmpty() ? "%" : "%" + inMobile + "%")
                    .setString(5, inEmail.isEmpty() ? "%" : "%" + inEmail + "%")
                    .executeQuery();

            List<StudentViewDTO> views = new ArrayList<>();
            while (rs.next()) {
                StudentViewDTO view = new StudentViewDTO(rs.getInt("id"), rs.getString("sno"), rs.getString("name"), rs.getString("mobile"), rs.getString("email"));
                views.add(view);
            }
            return views;
        }
    }
}
