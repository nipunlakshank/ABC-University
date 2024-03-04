/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.DegreeDTO;
import com.abc.model.dto.DegreeViewDTO;
import com.abc.model.dto.SubjectDTO;
import com.abc.service.MySQL;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nipun
 */
public class DegreeSubjectDAO {

    public Map<Integer, Double> getTotalCredits() throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT SUM(credits) AS `total`, `degree_id` FROM `subject` GROUP BY `degree_id`")
                    .executeQuery();

            Map<Integer, Double> totalCredits = new HashMap<>();

            while (rs.next()) {
                totalCredits.put(rs.getInt("degree_id"), rs.getDouble("total"));
            }
            return totalCredits;
        }
    }
    
    public double getTotalCredits(int degId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT SUM(credits) AS `total`, `degree_id` FROM `subject` GROUP BY `degree_id`")
                    .executeQuery();

            Map<Integer, Double> totals = new HashMap<>();
            
            while(rs.next()){
                totals.put(rs.getInt("degree_id"), rs.getDouble("total"));
            }
            return totals.get(degId);
        }
    }

    public List<DegreeViewDTO> getDegreeViews() throws Exception {
        DegreeDAO degreeDAO = new DegreeDAO();
        List<DegreeDTO> degrees = degreeDAO.getAll();
        Map<Integer, Double> credits = getTotalCredits();
        List<DegreeViewDTO> degreeViews = new ArrayList<>();

        for (DegreeDTO degree : degrees) {
            DegreeViewDTO degreeView = new DegreeViewDTO(degree.getId(), degree.getTitle(), credits.get(degree.getId()), degree.getIsActive());
            degreeViews.add(degreeView);
        }

        return degreeViews;
    }

    public List<SubjectDTO> getSubjectViews(int degreeId) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `subject` WHERE `degree_id`=?")
                    .setInt(1, degreeId)
                    .executeQuery();

            List<SubjectDTO> subjects = new ArrayList<>();
            while (rs.next()) {
                SubjectDTO subject = new SubjectDTO(rs.getInt("id"), rs.getString("code"), rs.getString("title"), rs.getInt("credits"), rs.getInt("semester"), rs.getInt("degree_id"), rs.getString("state"));
                subjects.add(subject);
            }

            return subjects;
        }
    }

    public List<DegreeViewDTO> filterDegrees(String inTitle) throws Exception {

        if (inTitle.isEmpty()) {
            return getDegreeViews();
        }

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `degree` WHERE `title` LIKE ?")
                    .setString(1, '%' + inTitle + '%')
                    .executeQuery();

            Map<Integer, Double> credits = getTotalCredits();
            List<DegreeViewDTO> degViews = new ArrayList<>();
            while (rs.next()) {
                DegreeViewDTO degView = new DegreeViewDTO(rs.getInt("id"), rs.getString("title"), credits.get(rs.getInt("id")), rs.getBoolean("is_active"));
                degViews.add(degView);
            }
            return degViews;
        }
    }

    public List<SubjectDTO> filterSubjects(int degId, String inCode, String inTitle) throws Exception {
        if (inTitle.isEmpty() && inCode.isEmpty()) {
            return getSubjectViews(degId);
        }

        List<SubjectDTO> subViews = new ArrayList<>();

        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createSelectStatement("SELECT * FROM `subject` WHERE `code` LIKE ? AND `title` LIKE ? AND `degree_id`=?")
                    .setString(1, inCode.isEmpty() ? "%" : "%" + inCode + "%")
                    .setString(2, inTitle.isEmpty() ? "%" : "%" + inTitle + "%")
                    .setInt(3, degId)
                    .executeQuery();
            while (rs.next()) {
                SubjectDTO subject = new SubjectDTO(rs.getInt("id"), rs.getString("code"), rs.getString("title"), rs.getInt("credits"), rs.getInt("semester"), degId, rs.getString("state"));
                subViews.add(subject);
            }
            return subViews;
        }

    }

}
